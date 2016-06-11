package com.qluxstory.qingshe.home.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPopupWindow;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.PhotoSystemUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.home.dto.PayDTO;
import com.qluxstory.qingshe.home.dto.TakeDTO;
import com.qluxstory.qingshe.home.entity.Address;
import com.qluxstory.qingshe.home.entity.BalanceResult;
import com.qluxstory.qingshe.home.entity.Consignee;
import com.qluxstory.qingshe.home.entity.PayResult;
import com.qluxstory.qingshe.home.entity.ProductDetails;
import com.qluxstory.qingshe.home.entity.TakeEntity;
import com.qluxstory.qingshe.home.entity.TakeResult;
import com.qluxstory.qingshe.home.entity.TimeEntity;
import com.qluxstory.qingshe.home.entity.TimeResult;
import com.qluxstory.qingshe.issue.IssueUiGoto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 提交订单主页面
 */
public class PlaceOrderActivity extends BaseTitleActivity {
    @Bind(R.id.place_address_time)
    RelativeLayout mTime;
    @Bind(R.id.place_take_delivery)
    RelativeLayout mPlaceTake;
    @Bind(R.id.place_address)
    LinearLayout mPlaceAddress;
    @Bind(R.id.rel_coupon)
    RelativeLayout mRelCoupon;
    @Bind(R.id.place_send)
    LinearLayout mPlaceSend;
    @Bind(R.id.rel_total)
    RelativeLayout mRelTotal;
    @Bind(R.id.pla_tv)
    TextView mPlaTv;
    @Bind(R.id.place_send_tv)
    TextView mSendTv;
    @Bind(R.id.place_send_tv_name)
    TextView mSendAddress;
    @Bind(R.id.place_titlt)
    TextView mPlaceTitlt;
    @Bind(R.id.placord_dan)
    TextView mPlacordDan;
    @Bind(R.id.placord_dan_num)
    TextView mDanNum;
    @Bind(R.id.place_coupon)
    TextView mPlaceCoupon;
    @Bind(R.id.place_total)
    TextView mPlaceTotal;
    @Bind(R.id.place_img)
    ImageView mPlaceImg;
    @Bind(R.id.place_order_img_pon)
    ImageView mImgPon;
    @Bind(R.id.place_order_img)
    ImageView mPlaceOrderImg;
    @Bind(R.id.placeorder_tv)
    TextView mTv;
    @Bind(R.id.tv_balance)
    TextView mTvBalance;
    @Bind(R.id.place_tv_nm)
    TextView mTvNm;
    @Bind(R.id.set_pay_Btn)
    Button mSetPayBtn;
    @Bind(R.id.placeorder_lin)
    LinearLayout mLrderLin;
    @Bind(R.id.palce_pay_wx)
    LinearLayout mPayWx;
    @Bind(R.id.palce_pay_alipay)
    LinearLayout mPayAlipay;
    @Bind(R.id.palce_pay_balance)
    LinearLayout mPayBalance;
    @Bind(R.id.place_cb_wx)
    CheckBox mCbWx;
    @Bind(R.id.place_cb_zhi)
    CheckBox mCbZhi;
    @Bind(R.id.place_cb_hui)
    CheckBox mVbHui;
    @Bind(R.id.place_tv_address_time)
    TextView mTvTime;
    @Bind(R.id.place_tv_address)
    TextView mTvAddress;
    @Bind(R.id.pla_tv_add_time)
    TextView mAddTime;
    @Bind(R.id.place_send_tv_city)
    TextView mSendVity;
    @Bind(R.id.place_send_tv_add)
    TextView mSendAdd;
    @Bind(R.id.place_send_tv_ipone)
    TextView mSendIpone;
    @Bind(R.id.place_tv_address_name)
    TextView mAddressName;
    @Bind(R.id.place_tv_address_city)
    TextView mAddressCity;
    @Bind(R.id.place_tv_address_add)
    TextView mAddressAdd;
    private String mPrice;
    private String mName;
    private String mPic;
    private String mCode;
    Address address;
    private String rturn;
    Consignee consignee;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;
    List<TakeEntity> takeEntity ;
    List<TimeEntity> timeEntity ;
    ProductDetails mProductDetails;
    Bitmap bitmap;
    private String mMemberheadimg,mConName,mConMobile,mConCity,mConAddress;


    @Override
    protected int getContentResId() {
        return R.layout.activity_placeorder;

    }

    @Override
    public void initView() {
        mProductDetails = AppContext.getInstance().getProductDetails();
        consignee = AppContext.getInstance().getConsignee();
        mPrice = mProductDetails.getSellPrice();
        mName = mProductDetails.getSellName();
        mPic = mProductDetails.getSellPic();
        mCode = mProductDetails.getSellOnlyCode();
        mPlaceTitlt.setText(mName);
        mPlacordDan.setText("¥ " + mPrice + "*1=" + mPrice);
        mDanNum.setText("¥ " + mPrice);
        mPlaceTotal.setText(mPrice);
        mTv.setText("¥ ");
        mTvNm.setText(mPrice);
        ImageLoaderUtils.displayImage(mPic, mPlaceImg);
        setTitleText("提交订单");

        mSetPayBtn.setOnClickListener(this);
        mPlaceOrderImg.setOnClickListener(this);
        mPlaceTake.setOnClickListener(this);
        mPlaceAddress.setOnClickListener(this);
        mPlaceSend.setOnClickListener(this);
        mRelCoupon.setOnClickListener(this);
        mPayWx.setOnClickListener(this);
        mPayAlipay.setOnClickListener(this);
        mPayBalance.setOnClickListener(this);
        mTime.setOnClickListener(this);
    }

    @Override
    public void initData() {
        reqTake();//取送方式
        reqBalance();//会员余额

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.place_address_time:
                if(mTvTime.getText().toString().equals("预约上门时间：")){
                    reqTime();//获取后十五天时间
                    LogUtils.e("reqTime---","先");

                }
                break;

            case R.id.place_take_delivery:
                reqTake();//取送方式
                showTakePop();
                break;
            case R.id.place_address:
                Bundle b = new Bundle();
                b.putString("rturn", rturn);
                UIHelper.showRorSelectFragment(this, SimplePage.SELECT_ADDRESS);//收货地址或上门地址
//                if(mTvAddress.getText().toString().equals("选择门店：")){
//                    Bundle bundle = new Bundle();
//                    bundle.putString("rturn", rturn);
//                    UIHelper.showRorStoreFragment(this, SimplePage.STORE,bundle);//选择门店
//                }else {
//                    UIHelper.showRorSelectFragment(this, SimplePage.SELECT_ADDRESS);//收货地址或上门地址
//                }


                break;
            case R.id.place_send:
                Bundle bundle = new Bundle();
                bundle.putString("rturn", rturn);
                UIHelper.showRorSendFragment(this, SimplePage.SEND_ADDRESS, bundle);//寄送地址
                break;
            case R.id.rel_coupon:
                Bundle bun = new Bundle();
                bun.putString("mCode", mCode);
                bun.putString("mPrice", mPrice);
                UIHelper.showRorCouponFragment(this, SimplePage.VOUCHERS, bun);//优惠劵
                break;
            case R.id.set_pay_Btn:
                if(TextUtils.isEmpty(mMemberheadimg)){
                    DialogUtils.showPrompt(this, "请上传商品照片", "知道了");
                }
                else if (mPlaceAddress.getVisibility()== View.VISIBLE && mPlaTv.getText().toString().equals("全国包回邮")&&TextUtils.isEmpty(mAddressName.getText().toString())) {
                    DialogUtils.showPrompt(this, "请选择收货地址", "知道了");
                }
                else if(mPlaceAddress.getVisibility()== View.VISIBLE &&mPlaTv.getText().toString().equals("上门取送")&&TextUtils.isEmpty(mAddressName.getText().toString())){
                    DialogUtils.showPrompt(this, "请选择上门地址", "知道了");
                }
                else if(mPlaceAddress.getVisibility()== View.VISIBLE &&mPlaTv.getText().toString().equals("自送门店")&&TextUtils.isEmpty(mSendAddress.getText().toString())){
                    DialogUtils.showPrompt(this, "请选择门店地址", "知道了");
                }
                else if (mPlaceSend.getVisibility()== View.VISIBLE&&TextUtils.isEmpty(mSendAddress.getText().toString())) {
                    DialogUtils.showPrompt(this, "请选择寄送地址", "知道了");
                }
                else if (mTime.getVisibility()== View.VISIBLE&& mPlaTv.getText().toString().equals("自送门店")&&TextUtils.isEmpty(mAddTime.getText().toString())) {
                    DialogUtils.showPrompt(this, "请选择上门时间", "知道了");
                }
                else if (!mCbWx.isChecked() && !mCbZhi.isChecked() && !mVbHui.isChecked()) {
                    DialogUtils.showPrompt(this, "请选择支付方式", "知道了");
                } else {
                    reqPay();//去支付
                }


                break;
            case R.id.palce_pay_wx:
                mCbWx.setChecked(true);
                mCbZhi.setChecked(false);
                mVbHui.setChecked(false);
                break;
            case R.id.palce_pay_alipay:
                mCbWx.setChecked(false);
                mCbZhi.setChecked(true);
                mVbHui.setChecked(false);
                break;
            case R.id.palce_pay_balance:
                mCbWx.setChecked(false);
                mCbZhi.setChecked(false);
                mVbHui.setChecked(true);
                break;
            case R.id.place_order_img:
                choseHeadImageFromCameraCapture();
                break;
            default:
                break;
        }
        super.onClick(v);
    }

    ArrayList<String>  tList ;
    private void showTakePop() {
        tList = new ArrayList<>();
        tList.add(takeEntity.get(0).getDis_type_name());
        tList.add(takeEntity.get(1).getDis_type_name());
        tList.add(takeEntity.get(2).getDis_type_name());
//        for(int i = 0;i<takeEntity.size();i++){
//            LogUtils.e("takeEntity----"+takeEntity.size());
//            LogUtils.e("takeEntity----tttt"+takeEntity.get(i).getDis_type_name());
//            tiList.add(takeEntity.get(i).getDis_type_name());
//            LogUtils.e("tiList----tttt"+tiList);
//        }
        OptionsPopupWindow tipPopup = new OptionsPopupWindow(this);
        tipPopup.setPicker(tList);//设置里面list
        tipPopup.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {//确定的点击监听
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                if ("全国包回邮".equals(tList.get(options1))) {
                    mPlaTv.setText("全国包回邮");
                    mTime.setVisibility(View.GONE);
                    mPlaceAddress.setVisibility(View.VISIBLE);
                    mPlaceSend.setVisibility(View.VISIBLE);
                    mTvAddress.setText("收货地址:");
                    mAddressName.setText("");
                    mAddressCity.setText("");
                    mAddressAdd.setText("");
                    rturn = takeEntity.get(0).getDis_type_code();

                } else if("上门取送".equals(tList.get(options1))){
                    mPlaTv.setText("上门取送");
                    mTime.setVisibility(View.VISIBLE);
                    mPlaceAddress.setVisibility(View.VISIBLE);
                    mPlaceSend.setVisibility(View.GONE);
                    mTvTime.setText("预约上门时间：");
                    mTvAddress.setText("上门地址:");
                    mAddressName.setText("");
                    mAddressCity.setText("");
                    mAddressAdd.setText("");
                    rturn = takeEntity.get(1).getDis_type_code();

                }
                else if("自送门店".equals(tList.get(options1))){
                    mPlaTv.setText("自送门店");
                    mTime.setVisibility(View.VISIBLE);
                    mPlaceAddress.setVisibility(View.GONE);
                    mPlaceSend.setVisibility(View.VISIBLE);
                    mSendTv.setText("选择门店：");
                    mTvTime.setText("门店工作时间：");
                    mAddTime.setText("10:00 - 18:00");
                    mSendAddress.setText("");
                    mSendVity.setText("");
                    mSendAdd.setText("");
                    mSendIpone.setText("");
                    rturn = takeEntity.get(2).getDis_type_code();

                }

            }
        });
        tipPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {//设置窗体消失后，屏幕恢复亮度
            @Override
            public void onDismiss() {
                closePopupWindow();
            }
        });
        tipPopup.showAtLocation(mPlaceCoupon, Gravity.BOTTOM, 0, 0);//显示的位置
        //弹窗后背景变暗
        openPopupWindow();
    }

    /**
     *  打开窗口 
     */
    private void openPopupWindow() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.7f;
        getWindow().setAttributes(params);
    }

    /**
     *  关闭窗口 
     */
    private void closePopupWindow() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 1f;
        getWindow().setAttributes(params);
    }

    ArrayList<String>  tiList ;

    private void showTimePop() {
        tList = new ArrayList<>();
        tiList.add(timeEntity.get(0).getTime());
        tiList.add(timeEntity.get(1).getTime());
        tiList.add(timeEntity.get(2).getTime());
        tiList.add(timeEntity.get(3).getTime());
        tiList.add(timeEntity.get(4).getTime());
        tiList.add(timeEntity.get(5).getTime());
        tiList.add(timeEntity.get(6).getTime());
        tiList.add(timeEntity.get(7).getTime());
        tiList.add(timeEntity.get(8).getTime());
        tiList.add(timeEntity.get(9).getTime());
        tiList.add(timeEntity.get(10).getTime());
        tiList.add(timeEntity.get(11).getTime());
        tiList.add(timeEntity.get(12).getTime());
        tiList.add(timeEntity.get(13).getTime());
        tiList.add(timeEntity.get(14).getTime());

//        for(int i = 0;i<timeEntity.size();i++){
//            tiList.add(timeEntity.get(i).getTime());
//        }
        OptionsPopupWindow tipPopup = new OptionsPopupWindow(this);
        tipPopup.setPicker(tiList);//设置里面list
        tipPopup.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {//确定的点击监听
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                mAddTime.setText(tiList.get(options1)+"10:00 - 18:00");

            }
        });
        tipPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {//设置窗体消失后，屏幕恢复亮度
            @Override
            public void onDismiss() {
                closePopupWindow();
            }
        });
        tipPopup.showAtLocation(mPlaceCoupon, Gravity.BOTTOM, 0, 0);//显示的位置
        //弹窗后背景变暗
        openPopupWindow();

    }

    private void reqTime() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.time(this, dto, new CallBack<TimeResult>() {
            @Override
            public void onSuccess(TimeResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("获取后十五天成功");
                    timeEntity = result.getData();
                    LogUtils.e("timeEntity----",""+timeEntity);
                    LogUtils.e("timeEntity--；；；；；；",""+timeEntity.get(0).getTime());
                    showTimePop();
                    LogUtils.e("showTimePop---","hou");

                }

            }
        });
    }

    private void reqBalance() {
        BaseDTO dto = new BaseDTO();
        dto.setSign(AppConfig.SIGN_1);
        dto.setTimestamp(TimeUtils.getSignTime());
        dto.setMembermob(AppContext.get("mobileNum", ""));
        CommonApiClient.balance(this, dto, new CallBack<BalanceResult>() {
            @Override
            public void onSuccess(BalanceResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("会员余额成功");
                    mTvBalance.setText(result.getData().get(0).getCashAmountMoney());

                }

            }
        });
    }


    private void reqTake() {
        TakeDTO dto = new TakeDTO();
        dto.setCity("北京市");
        CommonApiClient.take(this, dto, new CallBack<TakeResult>() {
            @Override
            public void onSuccess(TakeResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("取送方式成功");
                    takeEntity = result.getData();
                    if(!TextUtils.isEmpty(takeEntity.get(0).getDis_type_name())){
                        mPlaTv.setText(takeEntity.get(0).getDis_type_name());
                        rturn = takeEntity.get(0).getDis_type_code();
                    }



                }
            }
        });
    }


    private void reqPay() {
        PayDTO dto = new PayDTO();
        dto.setConsigneeType(mPlaTv.getText().toString());
        dto.setConsigneeCode(consignee.getConsigneeCode());
        dto.setConsigneeName(mConName);
        dto.setDeliveredMobile(mConMobile);
        dto.setProvincialCity(mConCity);
        dto.setAddressInDetail(mConAddress);
        dto.setComOnlyCode(mCode);
        dto.setOrderMoney(mPlaceTotal.getText().toString());
        dto.setComCount("1");
        dto.setCouponPrice(mPlaceCoupon.getText().toString());
        dto.setMemberIDCoupon("");
        dto.setCouponcode("");
        dto.setMemMobile(AppContext.get("mobileNum", ""));
        dto.setOrderType("养护");


        if (mPlaTv.getText().toString().equals("上门取送")) {
            dto.setTimeToAppointmen("2016-06-07 10:00 - 18:00");
        } else {
            dto.setTimeToAppointmen("");
        }
        if (mPlaTv.getText().toString().equals("全国包回邮")) {
            dto.setServerYJCode(mSendAddress.getText().toString());
        } else {
            dto.setServerYJCode("");
        }


        if (mCbWx.isChecked()) {
            dto.setApplyType("微信");
        } else if (mCbZhi.isChecked()) {
            dto.setApplyType("支付宝");
        } else {
            dto.setApplyType("会员");
        }
        dto.setServiceMoney("");
        dto.setReqType("service");
        dto.setOldOrderNum("");
        dto.setShoudamoney(mPrice);
//        dto.setBase64string(mMemberheadimg);
        dto.setBase64string("");
        dto.setServerName(mProductDetails.getSellSort());
        dto.setSign(AppConfig.SIGN_1);
//        dto.setSign("");
        dto.setTimestamp(TimeUtils.getSignTime());
        CommonApiClient.pay(this, dto, new CallBack<PayResult>() {
            @Override
            public void onSuccess(PayResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("去支付成功");
                    IssueUiGoto.payment(PlaceOrderActivity.this);//支付结果页

                }

            }
        });
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (PhotoSystemUtils.hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        // 用户没有进行有效的设置操作，返回
//        if (resultCode == RESULT_CANCELED) {
//            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
//            return;
//        }

        switch (requestCode) {
            case UIHelper.SEND_REQUEST:
                mSendAddress.setText(AppContext.get("Dis_province_name",""));
                mSendIpone.setText(AppContext.get("Dis_province_phone",""));
                mSendVity.setText(AppContext.get("Dis_province_city",""));
                mSendAdd.setText(AppContext.get("Dis_province_area",""));
                AppContext.set("Dis_province_name", "");
                AppContext.set("Dis_province_city", "");
                AppContext.set("Dis_province_area", "");
                AppContext.set("Dis_province_phone", "");
                break;

            case CODE_CAMERA_REQUEST:
                if (PhotoSystemUtils.hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    Uri uri = Uri.fromFile(tempFile);
                    LogUtils.e("uri------------",""+uri);
                    String mImg = PhotoSystemUtils.getRealFilePath(this, uri);
                    LogUtils.e("mImg------------",""+mImg);
                    if(mImg!=null){
                        mLrderLin.setVisibility(View.GONE);
                        mImgPon.setVisibility(View.VISIBLE);
                        ImageLoader.getInstance().displayImage("file:///" + mImg,
                                mImgPon, ImageLoaderUtils.getDefaultOptions());
                    }else {
                        mLrderLin.setVisibility(View.VISIBLE);
                        mImgPon.setVisibility(View.GONE);
                    }
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        LogUtils.e("bitmap",""+bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bitmap bit = PhotoSystemUtils.comp(bitmap);
                    mMemberheadimg = ImageLoaderUtils.bitmaptoString(PhotoSystemUtils.comp(bit));
                    LogUtils.e("mMemberheadimg------------",""+mMemberheadimg);
//                    cropRawPhoto(uri);
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }
                break;
            case UIHelper.SELECT_REQUEST:
                if(consignee!=null){
                    LogUtils.e("consignee---if",consignee+"");
                    mConName = consignee.getConsigneeName();
                    mConMobile = consignee.getDeliveredMobile().trim();
                    mConCity = consignee.getProvincialCity();
                    mConAddress = consignee.getAddressInDetail();
                    mAddressName.setText(mConName + mConMobile);
                    mAddressCity.setText(mConCity);
                    mAddressAdd.setText(mConAddress);
                    consignee.setConsigneeName("");
                    consignee.setDeliveredMobile("");
                    consignee.setProvincialCity("");
                    consignee.setAddressInDetail("");

                }else {
                    LogUtils.e("consignee---else",consignee+"");
                    mAddressName.setText("");
                    mAddressCity.setText("");
                    mAddressAdd.setText("");
                }

                break;
            case UIHelper.COUPON_REQUEST:
                mPlaceCoupon.setText(AppContext.get("vouchers", ""));//代金劵
                AppContext.set("vouchers", "");
                break;
        }

    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            if(photo!=null){
                mLrderLin.setVisibility(View.GONE);
                mImgPon.setVisibility(View.VISIBLE);
                mImgPon.setImageBitmap(photo);
            }else {
                mLrderLin.setVisibility(View.VISIBLE);
                mImgPon.setVisibility(View.GONE);
            }

        }
    }
}
