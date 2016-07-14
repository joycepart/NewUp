package com.qluxstory.qingshe.me.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.MainActivity;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.PhotoSystemUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.home.entity.BalanceResult;
import com.qluxstory.qingshe.me.dto.CommentDTO;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发表评论的主页面
 */
public class CommentActivity extends BaseTitleActivity {


    @Bind(R.id.ps_btn_one)
    Button mPsBtnOne;
    @Bind(R.id.ps_btn_two)
    Button mPsBtnTwo;
    @Bind(R.id.ps_btn_three)
    Button mPsBtnThree;
    @Bind(R.id.ps_btn_four)
    Button mPsBtnFour;
    @Bind(R.id.ps_btn_five)
    Button mPsBtnFive;
    @Bind(R.id.cs_btn_one)
    Button mCsBtnOne;
    @Bind(R.id.cs_btn_two)
    Button mCsBtnTwo;
    @Bind(R.id.cs_btn_three)
    Button mCsBtnThree;
    @Bind(R.id.cs_btn_four)
    Button mCsBtnFour;
    @Bind(R.id.cs_btn_five)
    Button mCsBtnFive;
    @Bind(R.id.ds_btn_one)
    Button mDsBtnOne;
    @Bind(R.id.ds_btn_two)
    Button mDsBtnTwo;
    @Bind(R.id.ds_btn_three)
    Button mDsBtnThree;
    @Bind(R.id.ds_btn_four)
    Button mDsBtnFour;
    @Bind(R.id.ds_btn_five)
    Button mDsBtnFive;
    @Bind(R.id.comment_et)
    EditText mCommentEt;
    @Bind(R.id.comment_tv)
    TextView mCommentTv;
    @Bind(R.id.gridView)
    GridView mGridView;

    @Bind(R.id.cmt_img)
    ImageView mComImg;
    private TextView mBaseEnsure;
    PopupWindow popWindow;
    TextView mCamera,mPhoto,mExit;
    /* 请求识别码 */
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_PHOTO_REQUEST = 0xa2;
    Bitmap image;
    private String mPic,mOrdNum,mCode;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private GridAdapter gridAdapter;
    private String mPImg1,mPImg2,mPImg3,mDisLev,mKfLev,mLve,mPic1,mPic2,mPic3;
    private int mDLev,mKlev,mLLve;


    @Override
    protected int getContentResId() {
        return R.layout.activity_comment;
    }

    @Override
    public void initView() {
        mDLev = 5;
        mKlev = 5;
        mLLve = 5;
        Intent intent = getIntent();
        if(intent!=null){
            mPic = intent.getBundleExtra("bundle").getString("pic");
            mOrdNum = intent.getBundleExtra("bundle").getString("mOrdNum");
            mCode = intent.getBundleExtra("bundle").getString("mCode");
        }
        setTitleText("发表评论");
        setEnsureText("提交");
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        mBaseEnsure.setOnClickListener(this);
        mCommentEt.addTextChangedListener(textWatcher);
        ImageLoaderUtils.displayImage(mPic, mComImg);


        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        mGridView.setNumColumns(cols);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imgs = (String) parent.getItemAtPosition(position);
                LogUtils.e("imgs----", "" + imgs);
                if ("000000".equals(imgs)) {
                    showPicPop();
                } else {
                    return;
                }


            }
        });


    }

    private void initPic() {
        mPImg1 = AppContext.get("mPImg1","");
        mPImg2 = AppContext.get("mPImg2", "");
        mPImg3 = AppContext.get("mPImg3", "");
        if(TextUtils.isEmpty(mPImg1)){
            mPImg1 ="";
        }else {

            mPic1 = ImageLoaderUtils.imgToBase64(mPImg1,null,null);

        }
        if(TextUtils.isEmpty(mPImg2)){
            mPImg2 ="";
        }else {

            mPic2 = ImageLoaderUtils.imgToBase64(mPImg2, null, null);
        }
        if(TextUtils.isEmpty(mPImg3)){
            mPImg3 ="";
        }else {
            mPic3 = ImageLoaderUtils.imgToBase64(mPImg3, null, null);
        }
        LogUtils.e("initPic---mPImg1---", "" + mPic1);
        LogUtils.e("initPic---mPImg2---",""+mPic2);
        LogUtils.e("initPic---mPImg3---", "" + mPic3);
    }


    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            String str = mCommentEt.getText().toString();
            int num = str.length();
            LogUtils.e("num---",""+num);
            mCommentTv.setText(num+"/200");

        }
    };


    @Override
    public void initData() {

        imagePaths.add("000000");
        gridAdapter = new GridAdapter(imagePaths);
        mGridView.setAdapter(gridAdapter);

    }



    @OnClick({R.id.ps_btn_one, R.id.ps_btn_two, R.id.ps_btn_three, R.id.ps_btn_four, R.id.ps_btn_five, R.id.cs_btn_one, R.id.cs_btn_two, R.id.cs_btn_three, R.id.cs_btn_four, R.id.cs_btn_five, R.id.ds_btn_one, R.id.ds_btn_two, R.id.ds_btn_three, R.id.ds_btn_four, R.id.ds_btn_five})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ps_btn_one:
                mDLev = 1;
                mPsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnTwo.setBackgroundResource(R.drawable.xingxing_hu);
                mPsBtnThree.setBackgroundResource(R.drawable.xingxing_hu);
                mPsBtnFour.setBackgroundResource(R.drawable.xingxing_hu);
                mPsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.ps_btn_two:
                mDLev = 2;
                mPsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnThree.setBackgroundResource(R.drawable.xingxing_hu);
                mPsBtnFour.setBackgroundResource(R.drawable.xingxing_hu);
                mPsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.ps_btn_three:
                mDLev =3;
                mPsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnThree.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnFour.setBackgroundResource(R.drawable.xingxing_hu);
                mPsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.ps_btn_four:
                mDLev =4;
                mPsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnThree.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnFour.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.ps_btn_five:
                mDLev =5;
                mPsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnThree.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnFour.setBackgroundResource(R.drawable.xingxing_huang);
                mPsBtnFive.setBackgroundResource(R.drawable.xingxing_huang);
                break;

            case R.id.cs_btn_one:
                mKlev =1;
                mCsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnTwo.setBackgroundResource(R.drawable.xingxing_hu);
                mCsBtnThree.setBackgroundResource(R.drawable.xingxing_hu);
                mCsBtnFour.setBackgroundResource(R.drawable.xingxing_hu);
                mCsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.cs_btn_two:
                mKlev =2;
                mCsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnThree.setBackgroundResource(R.drawable.xingxing_hu);
                mCsBtnFour.setBackgroundResource(R.drawable.xingxing_hu);
                mCsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.cs_btn_three:
                mKlev =3;
                mCsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnThree.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnFour.setBackgroundResource(R.drawable.xingxing_hu);
                mCsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.cs_btn_four:
                mKlev =4;
                mCsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnThree.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnFour.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.cs_btn_five:
                mKlev =5;
                mCsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnThree.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnFour.setBackgroundResource(R.drawable.xingxing_huang);
                mCsBtnFive.setBackgroundResource(R.drawable.xingxing_huang);
                break;

            case R.id.ds_btn_one:
                mLLve =1;
                mDsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnTwo.setBackgroundResource(R.drawable.xingxing_hu);
                mDsBtnThree.setBackgroundResource(R.drawable.xingxing_hu);
                mDsBtnFour.setBackgroundResource(R.drawable.xingxing_hu);
                mDsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.ds_btn_two:
                mLLve =2;
                mDsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnThree.setBackgroundResource(R.drawable.xingxing_hu);
                mDsBtnFour.setBackgroundResource(R.drawable.xingxing_hu);
                mDsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.ds_btn_three:
                mLLve =3;
                mDsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnThree.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnFour.setBackgroundResource(R.drawable.xingxing_hu);
                mDsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.ds_btn_four:
                mLLve =4;
                mDsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnThree.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnFour.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnFive.setBackgroundResource(R.drawable.xingxing_hu);
                break;
            case R.id.ds_btn_five:
                mLLve =5;
                mDsBtnOne.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnTwo.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnThree.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnFour.setBackgroundResource(R.drawable.xingxing_huang);
                mDsBtnFive.setBackgroundResource(R.drawable.xingxing_huang);
                break;

            case R.id.btn_alter_pic_camera://拍照
                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);

                break;

            case R.id.btn_alter_pic_photo://选择照片

                PhotoPickerIntent intent = new PhotoPickerIntent(CommentActivity.this);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true); // 是否显示拍照
                intent.setMaxTotal(3); // 最多选择照片数量，默认为6
                intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                LogUtils.e("imagePaths---", "" + imagePaths);
                startActivityForResult(intent, REQUEST_CAMERA_CODE);

                break;
            case R.id.btn_alter_exit:
                backgroundAlpha(1f);
                popWindow.dismiss();
                break;
            case R.id.base_titlebar_ensure:
                String str = mCommentEt.getText().toString();
                int nm = str.length();
                if(nm<2){
                    DialogUtils.showPrompt(CommentActivity.this, "提示", "评论不得少于两个字", "知道了");
                }else {
                    reqComment();//发表评论
                }
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }

    private void reqComment() {
        showDialogMit();
        initPic();
        CommentDTO dto = new CommentDTO();
        String time = TimeUtils.getSignTime();
        dto.setSign(time + AppConfig.SIGN_1);
        dto.setTime(time);
        dto.setCode(mCode);
        dto.setRec_code(mOrdNum);
        dto.setContent(mCommentEt.getText().toString());

        if(mDLev ==1){
            mDisLev = "1";
        }
        else if(mDLev ==2){
            mDisLev = "2";
        }
        else if(mDLev ==3){
            mDisLev = "3";
        }
        else if(mDLev ==4){
            mDisLev = "4";
        }
        else if(mDLev ==5){
            mDisLev = "5";
        }

        if(mKlev==1){
            mKfLev = "1";
        }
        else if(mKlev==2){
            mKfLev = "2";
        }
        else if(mKlev==3){
            mKfLev = "3";
        }
        else if(mKlev==4){
            mKfLev = "4";
        }
        else if(mKlev==5){
            mKfLev = "5";
        }

        if(mLLve ==1){
            mLve = "1";
        }
        else if(mLLve ==2){
            mLve = "2";
        }
        else if(mLLve ==3){
            mLve = "3";
        }
        else if(mLLve ==4){
            mLve = "4";
        }
        else if(mLLve ==5){
            mLve = "5";
        }

        dto.setDis_level(mDisLev);
        dto.setKf_level(mKfLev);
        dto.setLevel(mLve);
        dto.setImage1(mPic1);
        dto.setiImage2(mPic2);
        dto.setImage3(mPic3);

        dto.setRev_phone(AppContext.get("mobileNum", ""));
        dto.setCom_sell_type("0");
        dto.setRev_type("0");
        CommonApiClient.comment(this, dto, new CallBack<BalanceResult>() {
            @Override
            public void onSuccess(BalanceResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("发表评论成功");
                    hideDialogLoading();
                    Intent intent = new Intent(CommentActivity.this, MainActivity.class);
                    intent.putExtra("tag",4);
                    startActivity(intent);

                }

            }
        });

    }

    private void showPicPop() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.from(this).inflate(R.layout.popup_pic, null);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);

        // 需要设置一下此参数，点击外边可消失
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popWindow.setFocusable(true);
        //防止虚拟软键盘被弹出菜单遮住
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        backgroundAlpha(0.7f);

        mCamera = (TextView) view.findViewById(R.id.btn_alter_pic_camera);
        mPhoto = (TextView) view.findViewById(R.id.btn_alter_pic_photo);
        mExit = (TextView) view.findViewById(R.id.btn_alter_exit);
        mCamera.setOnClickListener(this);
        mPhoto.setOnClickListener(this);
        mExit.setOnClickListener(this);

        View parent = getWindow().getDecorView();//高度为手机实际的像素高度
        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //添加pop窗口关闭事件
        popWindow.setOnDismissListener(new poponDismissListener());
    }
    public class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
            popWindow.dismiss();
        }

    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("data--------", "" + data);
        switch (requestCode) {

            //拍照
            case CODE_CAMERA_REQUEST:
                LogUtils.e("CODE_CAMERA_REQUEST----", "CODE_CAMERA_REQUEST");
                popWindow.dismiss();
                backgroundAlpha(1f);
                if (data == null) {
                    LogUtils.e("data----CODE_CAMERA_REQUEST", "" + data);
                    return;
                } else {
                    LogUtils.e("data----else", "else");
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                        LogUtils.e("TestFile", "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
                    LogUtils.e("name", "" + name);
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
                    Bitmap bm = PhotoSystemUtils.comp(bitmap);
                    LogUtils.e("bitmap", "" + bitmap);

                    FileOutputStream b = null;
                    File file = new File("/sdcard/myImage/");
                    file.mkdirs();// 创建文件夹
                    String fileName = "/sdcard/myImage/" + name;
                    LogUtils.e("fileName", "" + fileName);
                    try {
                        b = new FileOutputStream(fileName);
                        bm.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            b.flush();
                            b.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    LogUtils.e("imagePaths----if---", "" + imagePaths);


                    if (imagePaths.size()==1){
                        imagePaths.clear();
                        imagePaths.add(fileName);
                        imagePaths.add("000000");
                        LogUtils.e("imagePaths----1", "" + imagePaths);
                    }
                    else if (imagePaths.size()==2){
                        imagePaths.add("000000");
                        imagePaths.set(1, fileName);
                        LogUtils.e("imagePaths----2", "" + imagePaths);
                    }
                    else if (imagePaths.size()==3){
                        imagePaths.add("000000");
                        imagePaths.set(2, fileName);
                        LogUtils.e("imagePaths----3", "" + imagePaths);
                    }


//                    if(TextUtils.isEmpty(imagePaths.get(1))){
//                        imagePaths.add(1,fileName);
//                        imagePaths.add(2,"000000");
//                    }
//                    if(imagePaths.get(2).equals("000000")){
//                        imagePaths.add(2,fileName);
//                        imagePaths.add(2,"000000");
//                    }
//                    if(imagePaths.get(3).equals("000000")){
//                        imagePaths.add(3,fileName);
//                    }
//
//                    LogUtils.e("imagePaths----", "" + imagePaths);

                    gridAdapter  = new GridAdapter(imagePaths);
                    mGridView.setAdapter(gridAdapter);
                }
                backgroundAlpha(1f);
                popWindow.dismiss();
                break;

            // 选择照片
            case REQUEST_CAMERA_CODE:
                backgroundAlpha(1f);
                popWindow.dismiss();
                if(data == null){
                    return;
                }else
                {
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    LogUtils.e("list: ", "list = " + list + "size" + list.size());
                    if(null==list){
                        return;
                    }else {
                        loadAdpater(list);
                    }
                }

                break;

            default:
                break;

        }

    }

    private void loadAdpater(ArrayList<String> paths){
        if (imagePaths!=null&& imagePaths.size()>0){
            imagePaths.clear();
        }
        if (paths.contains("000000")){
            paths.remove("000000");
        }
        paths.add("000000");
        LogUtils.e("paths----", "" + paths);
        imagePaths.addAll(paths);
        gridAdapter  = new GridAdapter(imagePaths);
        mGridView.setAdapter(gridAdapter);

        try{
            JSONArray obj = new JSONArray(imagePaths);
            LogUtils.e("obj--", obj.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;
        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
            if(listUrls.size() == 4){
                listUrls.remove(listUrls.size()-1);
            }
            inflater = LayoutInflater.from(CommentActivity.this);
        }

        public int getCount(){
            return  listUrls.size();
        }
        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_comment_img, parent,false);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            LogUtils.e("listUrls---",""+listUrls);
            final String path=listUrls.get(position);
            LogUtils.e("path",""+path);
            LogUtils.e("listUrls.size()---",""+listUrls.size());

            if(listUrls.size()==1){
                mPImg1 = "";
                mPImg2 = "";
                mPImg3 = "";
                LogUtils.e("listUrls.get(0)----", "" + listUrls.get(0));
            }
            if(listUrls.size()==2){
                mPImg1 =listUrls.get(0);
                mPImg2 = "";
                mPImg3 = "";
                LogUtils.e("listUrls.get(0)----", "" + listUrls.get(0));
                LogUtils.e("listUrls.get(1)----", "" + listUrls.get(1));
            }
            if(listUrls.size()==3){
                mPImg1 =listUrls.get(0);
                mPImg2 =listUrls.get(1);
                if(listUrls.get(2).equals("000000")){
                    mPImg3 = "";
                }else {
                    mPImg3 = listUrls.get(2);
                }
                LogUtils.e("listUrls.get(0)----", "" + listUrls.get(0));
                LogUtils.e("listUrls.get(1)----", "" + listUrls.get(1));
                LogUtils.e("listUrls.get(2)----", "" + listUrls.get(2));
            }

            AppContext.set("mPImg1",mPImg1);
            AppContext.set("mPImg2",mPImg2);
            AppContext.set("mPImg3",mPImg3);




            if (path.equals("000000")){
                holder.image.setImageResource(R.drawable.tianjia);
            }else {
                Glide.with(CommentActivity.this)
                        .load(path)
                        .placeholder(R.mipmap.default_error)
                        .error(R.mipmap.default_error)
                        .centerCrop()
                        .crossFade()
                        .into(holder.image);
            }
            return convertView;
        }
        class ViewHolder {
            ImageView image;
        }
    }

}
