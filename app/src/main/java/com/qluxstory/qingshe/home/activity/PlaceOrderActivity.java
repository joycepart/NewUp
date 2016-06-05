package com.qluxstory.qingshe.home.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.qluxstory.qingshe.common.utils.SecurityUtils;
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

import java.io.File;
import java.io.IOException;

import butterknife.Bind;

/**
 * 提交订单主页面
 */
public class PlaceOrderActivity extends BaseTitleActivity {
    @Bind(R.id.place_take_delivery)
    RelativeLayout mPlaceTake;
    @Bind(R.id.place_address)
    RelativeLayout mPlaceAddress;
    @Bind(R.id.rel_coupon)
    RelativeLayout mRelCoupon;
    @Bind(R.id.place_send)
    RelativeLayout mPlaceSend;
    @Bind(R.id.rel_total)
    RelativeLayout mRelTotal;
    @Bind(R.id.pla_tv)
    TextView mPlaTv;
    @Bind(R.id.pla_tv_add)
    TextView mPlaTvAdd;
    @Bind(R.id.send_address)
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
    private  String mPrice;
    private  String mName;
    private  String mPic;
    private  String mCode;
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
    TakeEntity  takeEntity;
    ProductDetails mProductDetails;
    Bitmap bitmap;
    private String mMemberheadimg;


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
        mPlacordDan.setText("¥ "+mPrice+"*1="+mPrice);
        mDanNum.setText("¥ "+mPrice);
        mPlaceTotal.setText(mPrice);
        mTv.setText("¥ ");
        mTvNm.setText(mPrice);
        ImageLoaderUtils.displayImage(mPic,mPlaceImg);
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
            case R.id.place_take_delivery:
                reqTake();//取送方式
                break;
            case R.id.place_address:
                UIHelper.showRorSelectFragment(this, SimplePage.SELECT_ADDRESS);//收货地址
                break;
            case R.id.place_send:
                Bundle bundle = new Bundle();
                bundle.putString("rturn",rturn);
                UIHelper.showRorSendFragment(this, SimplePage.SEND_ADDRESS,bundle);//寄送地址
                break;
            case R.id.rel_coupon:
                Bundle b = new Bundle();
                b.putString("mCode",mCode);
                b.putString("mPrice",mPrice);
                UIHelper.showRorCouponFragment(this, SimplePage.VOUCHERS,b);//优惠劵
                break;
            case R.id.set_pay_Btn:
                if(mPlaTvAdd.getText().toString()==null){
                    DialogUtils.showPrompt(this,"暂无可提现余额","确定");
                }
                if(mSendAddress.getText().toString()==null){
                    DialogUtils.showPrompt(this,"暂无可提现余额","确定");
                }
                if(!mCbWx.isChecked()&&!mCbZhi.isChecked()&&!mVbHui.isChecked()){
                    DialogUtils.showPrompt(this,"暂无可提现余额","确定");
                }else {
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

    private void reqBalance() {
        BaseDTO dto=new BaseDTO();
        dto.setSign(AppConfig.SIGN_1);
        LogUtils.e("未加密前的----", TimeUtils.getSignTime()+AppConfig.SIGN_1);
        LogUtils.e("加密后的---",SecurityUtils.MD5(TimeUtils.getSignTime() + AppConfig.SIGN_1));
        dto.setTimestamp(TimeUtils.getSignTime());
        dto.setMembermob(AppContext.get("mobileNum",""));
        CommonApiClient.balance(this, dto, new CallBack<BalanceResult>() {
            @Override
            public void onSuccess(BalanceResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("会员余额成功");
                    mTvBalance.setText(result.getData().get(0).getCashAmountMoney());

                }

            }
        });
    }


    private void reqTake() {
        TakeDTO dto=new TakeDTO();
        dto.setCity("北京市");
        CommonApiClient.take(this, dto, new CallBack<TakeResult>() {
            @Override
            public void onSuccess(TakeResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("取送方式成功");
                    takeEntity = result.getData().get(0);
                    rturn = takeEntity.getDis_type_code();
                    mPlaTv.setText(takeEntity.getDis_type_name());

                }

            }
        });
    }


    private void reqPay() {
        PayDTO dto=new PayDTO();
        dto.setConsigneeType(takeEntity.getDis_type_name());
        if(takeEntity.getDis_type_name().equals("上门取送")){
            dto.setTimeToAppointmen("10");
        }else {
            dto.setTimeToAppointmen("10");
        }
        if(takeEntity.getDis_type_name().equals("全国包回邮")){
            dto.setServerYJCode(mSendAddress.getText().toString());
        }else {
            dto.setServerYJCode("");
        }
        dto.setConsigneeCode(consignee.getConsigneeCode());
        dto.setConsigneeName(consignee.getConsigneeName());
        dto.setDeliveredMobile(consignee.getDeliveredMobile());
        dto.setProvincialCity(consignee.getProvincialCity());
        dto.setAddressInDetail(consignee.getAddressInDetail());
        dto.setComOnlyCode(mCode);
        dto.setOrderMoney(mPlaceTotal.getText().toString());
        dto.setComCount("1");
        dto.setCouponPrice("1");
        dto.setMemberIDCoupon("1");
        dto.setCouponCode("1");
        dto.setMemMobile(AppContext.get("mobileNum",""));
        if(mCbWx.isChecked()){
            dto.setApplyType("微信");
        }else  if(mCbZhi.isChecked()){
            dto.setApplyType("支付宝");
        }else {
            dto.setApplyType("会员");
        }
        dto.setServiceMoney("1");
        dto.setReqType("服务");
        dto.setOldOrderNum("1");
        dto.setShoudamoney(mPrice);
        dto.setBase64string(mMemberheadimg);
        dto.setServerName(mProductDetails.getSellSort());
        dto.setSign(AppConfig.SIGN_1);
        dto.setTimestamp(TimeUtils.getSignTime());
        CommonApiClient.pay(this, dto, new CallBack<PayResult>() {
            @Override
            public void onSuccess(PayResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("去支付成功");

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
                mSendAddress.setText( AppContext.get("Dis_province_send",""));//寄送地址
                AppContext.set("Dis_province_send","");
                break;

            case CODE_CAMERA_REQUEST:
                if (PhotoSystemUtils.hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    Uri uri = Uri.fromFile(tempFile);
                    String mImg = PhotoSystemUtils.getRealFilePath(this,uri);
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mMemberheadimg = ImageLoaderUtils.imgToBase64(mImg,bitmap,null);
                    cropRawPhoto(uri);
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }
            case UIHelper.SELECT_REQUEST:
                mPlaTvAdd.setText(consignee.getConsigneeName()+consignee.getDeliveredMobile()
                        +consignee.getProvincialCity()+consignee.getAddressInDetail());

//                mPlaTvAdd.setText( AppContext.get("Dis_province_select",""));//收货地址
//                AppContext.set("Dis_province_select","");
                break;
            case UIHelper.COUPON_REQUEST:
                mPlaceCoupon.setText( AppContext.get("vouchers",""));//代金劵
                AppContext.set("vouchers","");
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
            mPlaceOrderImg.setImageBitmap(photo);
        }
    }
}
