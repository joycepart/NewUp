package com.qluxstory.qingshe.me.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.PhotoSystemUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.dto.UserPicDTO;
import com.qluxstory.qingshe.me.entity.UserPicResult;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 用户信息主页面
 */
public class UserInformationActivity extends BaseTitleActivity {

    @Bind(R.id.user_name)
    TextView mUserName;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.user_img)
    ImageView mUserImg;
    @Bind(R.id.tv_num)
    TextView mTvNum;
    @Bind(R.id.user_information)
    LinearLayout mUserInformation;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    private String mMemberheadimg;
    Bitmap image;
    private String mImg;
    TextView mCamera,mPhoto,mExit;
    private Intent intent;
    Bitmap myBitmap;
    Dialog dialog;
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_PHOTO_REQUEST = 0xa2;
    PopupWindow popWindow;
    private static final String FILE_PATH = Environment.getExternalStorageDirectory()+ "/123.jpg";

    @Override
    protected int getContentResId() {
        return R.layout.activity_me_userinformation;
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= 23) {
            int readSDPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (readSDPermission != PackageManager.PERMISSION_GRANTED) {
                LogUtils.e("readSDPermission",""+readSDPermission);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        123);
            }
        }
        mUserName.setText(AppContext.get("mUserName",""));
        mTvName.setText(AppContext.get("mUserName",""));
        mTvNum.setText(AppContext.get("mobileNum",""));
        ImageLoaderUtils.displayAvatarImage(AppContext.get("mPictruePath",""),
                mUserImg);
        mUserInformation.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }



    @OnClick({R.id.user_img, R.id.user_information})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_img:
                showPicPop();//修改用户图像
                break;
            case R.id.user_information:
                MeUiGoto.modifyUser(this);//修改用户名
                break;
            case R.id.btn_alter_pic_camera://拍照

                Intent intentFromCapture = null;
                intentFromCapture = new Intent();
                // 指定开启系统相机的Action
                intentFromCapture.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intentFromCapture.addCategory(Intent.CATEGORY_DEFAULT);
                // 根据文件地址创建文件
                File file = new File(FILE_PATH);
                if (file.exists())
                {
                    file.delete();
                }
                // 把文件地址转换成Uri格式
                Uri uri = Uri.fromFile(file);
                // 设置系统相机拍摄照片完成后图片文件的存放地址
                intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
                break;
            case R.id.btn_alter_pic_photo://选择照片
                try {
                    //选择照片的时候也一样，我们用Action为Intent.ACTION_GET_CONTENT，
                    //有些人使用其他的Action但我发现在有些机子中会出问题，所以优先选择这个
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, CODE_PHOTO_REQUEST);
                } catch (ActivityNotFoundException e) {

                }
                break;
            case R.id.btn_alter_exit:
                backgroundAlpha(1f);
                popWindow.dismiss();
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            default:
                break;
        }
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


    private void reqUserPic() {
        UserPicDTO bdto=new UserPicDTO();
        bdto.setMemberheadimg(mMemberheadimg);
        bdto.setMembermob(AppContext.get("mobileNum",""));
        bdto.setSign(AppConfig.SIGN_1);
        bdto.setTimestamp(TimeUtils.getSignTime());
        CommonApiClient.userPic(this, bdto, new CallBack<UserPicResult>() {
            @Override
            public void onSuccess(UserPicResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("修改用户图像成功");
                    AppContext.set("mPictruePath",result.getData().get(0).getMemberHeadimg());
                    ImageLoaderUtils.displayAvatarImage(result.getData().get(0).getMemberHeadimg(),mUserImg);
                    popWindow.dismiss();
                    backgroundAlpha(1f);
                }
            }
        });

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ContentResolver resolver = getContentResolver();
        LogUtils.e("data--------",""+data);
        switch (requestCode) {
            case CODE_CAMERA_REQUEST:
                File file = new File(FILE_PATH);
                Uri uri = Uri.fromFile(file);
                LogUtils.e("uri------------if", "" + uri);
                String mImg = PhotoSystemUtils.getRealFilePath(this, uri);
                LogUtils.e("mImg------------if", "" + mImg);
                try {
                    myBitmap = MediaStore.Images.Media.getBitmap(resolver, uri);
                    LogUtils.e("bitmap-----MediaStore",""+myBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap bit = PhotoSystemUtils.comp(myBitmap);
                mMemberheadimg = ImageLoaderUtils.bitmaptoString(bit);
                LogUtils.e("mMemberheadimg------------",""+mMemberheadimg);
                reqUserPic();//修改用户图像

//                if (mImg != null) {
//                    ImageLoader.getInstance().displayImage("file:///" + mImg,
//                            mUserImg, ImageLoaderUtils.getDefaultOptions());
//                    AppContext.set("mPictruePath","file:///" + mImg);
//                    LogUtils.e("ImageLoader------------if", "file:///" + mImg);
//                } else {
//                }
                break;
            case CODE_PHOTO_REQUEST:
                if (data != null) {
                    //取得返回的Uri,基本上选择照片的时候返回的是以Uri形式，但是在拍照中有得机子呢Uri是空的，所以要特别注意
                    Uri mImageCaptureUri = data.getData();
                    LogUtils.e("mImageCaptureUri--------------", mImageCaptureUri + "");
                    mImg = PhotoSystemUtils.getRealFilePath(this, mImageCaptureUri);
                    LogUtils.e("mImg-------mImageCaptureUri", mImg + "");
                    //返回的Uri不为空时，那么图片信息数据都会在Uri中获得。如果为空，那么我们就进行下面的方式获取
                    if (mImageCaptureUri != null) {
                        try {
                            image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageCaptureUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        ImageLoader.getInstance().displayImage("file:///" + mImg,
//                                mUserImg, ImageLoaderUtils.getAvatarOptions());
//                        AppContext.set("mPictruePath","file:///" + mImg);
                    }

                } else {
                        if (PhotoSystemUtils.hasSdcard()) {
                            File tempFile = new File(
                                    Environment.getExternalStorageDirectory(),
                                    IMAGE_FILE_NAME);
                            Uri uriPhptp = Uri.fromFile(tempFile);
                            LogUtils.e("uriPhptp--------------", uriPhptp + "");
//                            mImg = PhotoSystemUtils.getRealFilePath(this,uriPhptp);
//                            LogUtils.e("mImg--------------", mImg + "");
//                            ImageLoader.getInstance().displayImage("file:///" + mImg,
//                                    mUserImg, ImageLoaderUtils.getAvatarOptions());
//                            AppContext.set("mPictruePath","file:///" + mImg);
                            try {
                                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriPhptp);
                                LogUtils.e("image-----uriPhptp", image + "");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                                    .show();
                        }

                        }

                    Bitmap bitmap = PhotoSystemUtils.comp(image);
                    mMemberheadimg = ImageLoaderUtils.bitmaptoString(bitmap);
                    reqUserPic();//修改用户图像

                break;
            case MeUiGoto.MODIFYUSER_REQUEST:
                String mName = AppContext.get("mUserName","");
                mUserName.setText(mName);
                mTvName.setText(mName);
                break;
            default:
                break;

        }
    }


}
