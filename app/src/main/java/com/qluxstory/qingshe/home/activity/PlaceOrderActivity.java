package com.qluxstory.qingshe.home.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.alipay.sdk.app.PayTask;
import com.bigkoo.pickerview.OptionsPopupWindow;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.alipay.Keys;
import com.qluxstory.qingshe.alipay.PayResult;
import com.qluxstory.qingshe.alipay.Rsa;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.PhotoSystemUtils;
import com.qluxstory.qingshe.common.utils.RandomUtils;
import com.qluxstory.qingshe.common.utils.SecurityUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.home.dto.PayDTO;
import com.qluxstory.qingshe.home.dto.TakeDTO;
import com.qluxstory.qingshe.home.entity.Address;
import com.qluxstory.qingshe.home.entity.BalanceResult;
import com.qluxstory.qingshe.home.entity.Consignee;
import com.qluxstory.qingshe.home.entity.PaypayEntity;
import com.qluxstory.qingshe.home.entity.PaypayResult;
import com.qluxstory.qingshe.home.entity.ProductDetails;
import com.qluxstory.qingshe.home.entity.TakeEntity;
import com.qluxstory.qingshe.home.entity.TakeResult;
import com.qluxstory.qingshe.home.entity.TimeEntity;
import com.qluxstory.qingshe.home.entity.TimeResult;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
    @Bind(R.id.place_img_sc)
    ImageView mImgSc;
    @Bind(R.id.place_order_img_pon)
    ImageView mImgPon;
    @Bind(R.id.place_order_img)
    ImageView mPlaceOrderImg;
    @Bind(R.id.place_frame)
    FrameLayout mFrame;
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
    @Bind(R.id.place_send_num)
    TextView mSendAddress;
    @Bind(R.id.place_send_tv_city)
    TextView mSendVity;
    @Bind(R.id.place_send_tv_add)
    TextView mSendAdd;
    @Bind(R.id.place_tv_address_name)
    TextView mAddressName;
    @Bind(R.id.place_tv_address_city)
    TextView mAddressCity;
    @Bind(R.id.place_tv_address_add)
    TextView mAddressAdd;
    @Bind(R.id.tv_tog)
    TextView mTvTog;
    @Bind(R.id.edittext)
    EditText mEditText;
    @Bind(R.id.togglebutton)
    ToggleButton mToggle;
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
    Bitmap myBitmap;
    Uri uri;
    private byte[] mContent;
    private String mMemberheadimg;
    private String mConName;
    private String mConMobile;
    private String mConCity;
    private String mConAddress;
    private static String mWxKey;
    TextView mBaseEnsure;
    private static final String FILE_PATH = Environment.getExternalStorageDirectory()+ "/po.jpg";
    private String mCity;
    private String mCouponType,mDiscountNumber,mCouponMoneyEqual,mCouponmoney,mCoupon;
    private  int t;
    private String mIntegralNum;

    @Override
    protected int getContentResId() {
        return R.layout.activity_placeorder;

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
        mEditText.clearFocus();
        mProductDetails = AppContext.getInstance().getProductDetails();
        consignee = AppContext.getInstance().getConsignee();
        mPrice = mProductDetails.getSellPrice();
        mName = mProductDetails.getSellName();
        mPic = mProductDetails.getSellPic();
        mCode = mProductDetails.getSellOnlyCode();
        mPlaceTitlt.setText(mName);
        mPlacordDan.setText("¥ " + mPrice.replace(".00","") + "*1=" + mPrice.replace(".00",""));
        mDanNum.setText("¥ " + mPrice.replace(".00",""));
        mPlaceTotal.setText(mPrice);
        mTv.setText("¥ ");
        mTvNm.setText(mPrice);
        ImageLoaderUtils.displayImage(mPic, mPlaceImg);
        setTitleText("提交订单");
        mIntegralNum = AppContext.get("mIntegral","");
        if(TextUtils.isEmpty(mIntegralNum)){
            mIntegralNum = "0";
            t=0;
        }else {
            int i = Integer.parseInt(mIntegralNum);
            t = i/10;
        }

        mTvTog.setText("可用"+mIntegralNum+"积分抵扣"+t+"元");

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
        mImgSc.setOnClickListener(this);
        mToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    //选中
                    String str = mPlaceTotal.getText().toString().replace(".00","");
                    int in = Integer.parseInt(str);
                    mTvNm.setText(String.valueOf(in-t));

                }else{
                    //未选中
                    String str = mPlaceTotal.getText().toString().replace(".00","");
                    mTvNm.setText(str);
                }
            }
        });// 添加监听事件
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
            case R.id.place_img_sc:
                mLrderLin.setVisibility(View.VISIBLE);
                mFrame.setVisibility(View.GONE);
                break;
            case R.id.place_address:
                Bundle b = new Bundle();
                b.putString("rturn", rturn);
                UIHelper.showRorSelectFragment(this, SimplePage.SELECT_ADDRESS);//收货地址或上门地址

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
                    DialogUtils.showPrompt(this, "提示","请上传商品照片", "知道了");
                }
                else if (mPlaceAddress.getVisibility()== View.VISIBLE && mPlaTv.getText().toString().equals("全国包回邮")&&TextUtils.isEmpty(mAddressName.getText().toString())) {
                    DialogUtils.showPrompt(this, "提示","请选择收货地址", "知道了");
                }
                else if(mPlaceAddress.getVisibility()== View.VISIBLE &&mPlaTv.getText().toString().equals("上门取送")&&TextUtils.isEmpty(mAddressName.getText().toString())){
                    DialogUtils.showPrompt(this, "提示","请选择上门地址", "知道了");
                }
                else if(mPlaceAddress.getVisibility()== View.VISIBLE &&mPlaTv.getText().toString().equals("自送门店")&&TextUtils.isEmpty(mSendAddress.getText().toString())){
                    DialogUtils.showPrompt(this, "提示","请选择门店地址", "知道了");
                }
                else if (mPlaceSend.getVisibility()== View.VISIBLE&&TextUtils.isEmpty(mSendAddress.getText().toString())) {
                    DialogUtils.showPrompt(this, "提示","请选择寄送地址", "知道了");
                }
                else if (mTime.getVisibility()== View.VISIBLE&& mPlaTv.getText().toString().equals("上门取送")&&TextUtils.isEmpty(mAddTime.getText().toString())) {
                    DialogUtils.showPrompt(this, "提示","请选择上门时间", "知道了");
                }
                else if (!mCbWx.isChecked() && !mCbZhi.isChecked() && !mVbHui.isChecked()) {
                    DialogUtils.showPrompt(this, "提示","请选择支付方式", "知道了");
                }
                else if (mVbHui.isChecked() && mTvBalance.getText().toString().equals("0.00")) {
                    DialogUtils.showPrompt(PlaceOrderActivity.this,"提示","账户余额不足","知道了");
                }
                else {
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
                LogUtils.e("choseH---","choseH");
                choseHeadImageFromCameraCapture();
                break;
            default:
                break;
        }
        super.onClick(v);
    }

    private static final int RQF_PAY = 1;

    private static final int RQF_LOGIN = 2;




    ArrayList<String>  tList ;
    private void showTakePop() {
        tList = new ArrayList<>();
        for(int i = 0;i<takeEntity.size();i++){
            LogUtils.e("tList---",""+takeEntity.get(i).getDis_type_name());
            tList.add(takeEntity.get(i).getDis_type_name().toString());
        }
        OptionsPopupWindow tipPopup = new OptionsPopupWindow(this);
        tipPopup.setPicker(tList);//设置里面list
        tipPopup.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {//确定的点击监听
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String str = tList.get(options1);
                if ("全国包回邮".equals(str)) {
                    mPlaTv.setText("全国包回邮");
                    mTime.setVisibility(View.GONE);
                    mPlaceAddress.setVisibility(View.VISIBLE);
                    mPlaceSend.setVisibility(View.VISIBLE);
                    mTvAddress.setText("收货地址:");
                    mAddressName.setText("");
                    mAddressCity.setText("");
                    mAddressAdd.setText("");
                    mSendTv.setText("寄送地址:");
                    mSendAddress.setText("");
                    mSendVity.setText("");
                    mSendAdd.setText("");
                    rturn = takeEntity.get(0).getDis_type_code();

                } else if(str.contains("上门取送")){
                    mPlaTv.setText(str);
                    mPlaceAddress.setVisibility(View.VISIBLE);
                    mPlaceSend.setVisibility(View.GONE);
                    mTime.setVisibility(View.VISIBLE);
                    mTvAddress.setText("上门地址:");
                    mAddressName.setText("");
                    mAddressCity.setText("");
                    mAddressAdd.setText("");
                    mTvTime.setText("预约上门时间：");
                    mAddTime.setText("");
                    rturn = takeEntity.get(1).getDis_type_code();

                }
//                else if("自送门店".equals(tList.get(options1))){
//                    mPlaTv.setText("自送门店");
//                    mPlaceAddress.setVisibility(View.GONE);
//                    mPlaceSend.setVisibility(View.VISIBLE);
//                    mTime.setVisibility(View.VISIBLE);
//                    mSendTv.setText("选择门店：");
//                    mTvTime.setText("门店工作时间：");
//                    mAddTime.setText("10:00 - 18:00");
//                    mSendAddress.setText("");
//                    mSendVity.setText("");
//                    mSendAdd.setText("");
//                    rturn = takeEntity.get(2).getDis_type_code();
//
//                }

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
        tiList = new ArrayList<>();
        for(int i = 0;i<timeEntity.size();i++){
            tiList.add(timeEntity.get(i).getTime());
        }

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
                    showTimePop();

                }

            }
        });
    }

    private void reqBalance() {
        BaseDTO dto = new BaseDTO();
        String time = TimeUtils.getSignTime();
        dto.setSign(time+AppConfig.SIGN_1);
        dto.setTimestamp(time);
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
        String mCity = AppContext.get("mCity","");
        if(TextUtils.isEmpty(mCity)){
            dto.setCity("北京市");
            LogUtils.e("dto.setCity---","定位失败");
        }else {
            dto.setCity(mCity);
            LogUtils.e("dto.setCity---","定位成功"+mCity);
        }

        CommonApiClient.take(this, dto, new CallBack<TakeResult>() {
            @Override
            public void onSuccess(TakeResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("取送方式成功");
                    if(null==result.getData()){
                        LogUtils.e("null------","null");
                        return;
                    }else {
                        takeEntity = result.getData();
                        LogUtils.e("getDis_type_name------",""+takeEntity.get(0).getDis_type_name());
                        if (!TextUtils.isEmpty(takeEntity.get(0).getDis_type_name())) {
                            mPlaTv.setText(takeEntity.get(0).getDis_type_name());
                            rturn = takeEntity.get(0).getDis_type_code();
                        }
                    }
                }
            }
        });
    }


    private void reqPay() {
        mSetPayBtn.setEnabled(false);
        PayDTO dto = new PayDTO();
        if(mToggle.isChecked()){
            dto.setIntegralMoney(String.valueOf(t));
            dto.setIntegralNum(mIntegralNum);
        }else {
            dto.setIntegralMoney("0");
            dto.setIntegralNum("0");
        }

        String time = TimeUtils.getSignTime();
        dto.setConsigneeType(mPlaTv.getText().toString());
        dto.setConsigneeCode(consignee.getConsigneeCode());
        dto.setConsigneeName(mConName);
        dto.setDeliveredMobile(mConMobile);
        dto.setProvincialCity(mConCity);
        dto.setAddressInDetail(mConAddress);
        dto.setComOnlyCode(mCode);
        dto.setOrderMoney(mPlaceTotal.getText().toString());
        dto.setComCount("1");
        if(TextUtils.isEmpty(mPlaceCoupon.getText().toString())){
            dto.setCouponPrice("0");
        }else {
            dto.setCouponPrice(mCoupon);
        }

        dto.setMemberIDCoupon("");
        dto.setCouponcode("");
        dto.setMemMobile(AppContext.get("mobileNum", ""));
        dto.setOrderType("养护");
        if (mPlaTv.getText().toString().equals("上门取送")) {
            dto.setTimeToAppointmen(mAddTime.getText().toString());
        } else {
            dto.setTimeToAppointmen("");
        }
        if (mPlaTv.getText().toString().equals("全国包回邮")) {
            dto.setServerYJCode(AppContext.get("Dis_only_code",""));
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
        LogUtils.e("setCustomernote---",mEditText.getText().toString());
        dto.setCustomernote(mEditText.getText().toString());
        dto.setServiceMoney("0");
        dto.setReqType("service");
        dto.setOldOrderNum("0");
        dto.setShoudamoney(mPrice);
        dto.setBase64string(mMemberheadimg);
        dto.setServerName(mProductDetails.getSellSort());
        dto.setSign(time+AppConfig.SIGN_1);
        dto.setTimestamp(time);
        CommonApiClient.pay(this, dto, new CallBack<PaypayResult>() {
            @Override
            public void onSuccess(PaypayResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("去支付成功");
                    if(result.getData().get(0).getApplyType().equals("支付宝")){
                        reqAlipayPay(result.getData());
                        mSetPayBtn.setEnabled(true);
                        LogUtils.e("getApplyType---1","支付宝支付成功");

                    }else if(result.getData().get(0).getApplyType().equals("微信")){
                        reqWx(result.getData());
                        mSetPayBtn.setEnabled(true);
                        LogUtils.e("getApplyType---2","微信支付成功");

                    }else {
                        IssueUiGoto.payment(PlaceOrderActivity.this);//支付结果页
                        mSetPayBtn.setEnabled(true);

                        LogUtils.e("getApplyType---3",result.getData().get(0).getApplyType());


                    }





                }

            }
        });
    }
    IWXAPI msgApi;
    private void reqWx(List<PaypayEntity> data) {
        msgApi = WXAPIFactory.createWXAPI(this, AppConfig.Wx_App_Id);
        msgApi.registerApp(AppConfig.Wx_App_Id);
        if (msgApi != null) {
            if (msgApi.isWXAppInstalled()) {
                String characterEncoding = "UTF-8";
                mWxKey = data.get(0).getPrivateKey();
                PayReq req = new PayReq();
                req.appId = AppConfig.Wx_App_Id;
                req.partnerId = data.get(0).getPartnerID();
                req.prepayId = data.get(0).getPrepayid();
                req.packageValue = "Sign=WXPay";
                String time =  TimeUtils.genTimeStamp();
                String nonceStr = RandomUtils.generateString(10);
                req.nonceStr = nonceStr;;
                req.timeStamp = time;
                String str = "appid="+AppConfig.Wx_App_Id
                        +"&noncestr="+nonceStr
                        +"&package="+"Sign=WXPay"
                        +"&partnerid="+data.get(0).getPartnerID()
                        +"&prepayid="+data.get(0).getPrepayid()
                        +"&timestamp="+time;
                String sing = str.trim().toString()+"&key="+mWxKey;
                LogUtils.e("sing---------",sing);
                req.sign = SecurityUtils.MD5(sing.trim().toString());
                LogUtils.e("sign------------MD5",SecurityUtils.MD5(sing.trim().toString()));
                msgApi.sendReq(req);
            }
        }

    }


    private void reqAlipayPay(List<PaypayEntity> data) {
        String info = getNewOrderInfo(data);//这个是订单信息
        // 这里根据签名方式对订单信息进行签名
        String strsign = Rsa.sign(info, Keys.PRIVATE);
        LogUtils.e("strsign----",""+strsign);
        try {
            // 仅需对sign 做URL编码
            strsign = URLEncoder.encode(strsign, "UTF-8");
            LogUtils.e("strsign----utf8",""+strsign);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 组装好参数
        final String orderInfo = info + "&sign=\"" +strsign + "\"&" + getSignType();
        LogUtils.e("orderInfo----",""+orderInfo);
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PlaceOrderActivity.this);//支付宝接口了，支付宝现在把很多功能都封装
                String result = alipay.pay(orderInfo,true);//返回的结果
                mSetPayBtn.setEnabled(true);
                LogUtils.e("result-----------", "result = " + result);
                Message msg = new Message();
                msg.what = RQF_PAY;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            PayResult payResult = new PayResult((String) msg.obj);
            /**
             * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
             * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
             * docType=1) 建议商户依赖异步通知
             */
            LogUtils.e("payResult---",""+payResult);
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            LogUtils.e("resultInfo---",""+resultInfo);
            String resultStatus = payResult.getResultStatus();
            LogUtils.e("resultStatus----",""+resultStatus);
            // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
            switch (msg.what) {
                case RQF_PAY:
                    if (TextUtils.equals(resultStatus, "9000")) {
                        LogUtils.e("RQF_PAY---","9000");
                        IssueUiGoto.payment(PlaceOrderActivity.this);//支付结果页
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PlaceOrderActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "6001")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PlaceOrderActivity.this, "用户取消订单",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "6002")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PlaceOrderActivity.this, "网络连接错误",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "4000")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PlaceOrderActivity.this, "订单支付失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
                default:
                    break;
            }
        };
    };
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    //获得订单信息的方法
    private String getNewOrderInfo(List<PaypayEntity> data) {


        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + data.get(0).getPartnerID() + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + data.get(0).getSellerAccount()+ "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + data.get(0).getOrderNum() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + data.get(0).getProductName() + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + data.get(0).getProductName() + "\"";

//        // 商品金额
        orderInfo += "&total_fee=" + "\"" + data.get(0).getAmount() + "\"";
        // 商品金额
//        orderInfo += "&total_fee=" + "\"" + "0.01" + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + AppConfig.BASE_URL+"/notify_url.aspx" + "\"";
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }


    // 启动手机相机拍摄照片
    private void choseHeadImageFromCameraCapture() {
        LogUtils.e("Intent-uri","Intent");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CODE_CAMERA_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ContentResolver resolver = getContentResolver();
        LogUtils.e("data---------", "" + data);
        // 用户没有进行有效的设置操作，返回
//        if (resultCode == RESULT_CANCELED) {
//            LogUtils.e("RESULT_CANCELED---------", "RESULT_CANCELED");
//            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
//            return;
//        }

        switch (requestCode) {
            case UIHelper.SEND_REQUEST:
                mSendAddress.setText(AppContext.get("Dis_province_name", "") + AppContext.get("Dis_province_phone", ""));
                mSendVity.setText(AppContext.get("Dis_province_city", "")+AppContext.get("Dis_province_province", ""));
                mSendAdd.setText(AppContext.get("Dis_province_area", "")+AppContext.get("Dis_province_address", ""));
                AppContext.set("Dis_province_name", "");
                AppContext.set("Dis_province_city", "");
                AppContext.set("Dis_province_area", "");
                AppContext.set("Dis_province_phone", "");
                AppContext.set("Dis_province_province", "");
                AppContext.set("Dis_province_address", "");
                break;

            case CODE_CAMERA_REQUEST:
                LogUtils.e("CODE_CAMERA_REQUEST----","CODE_CAMERA_REQUEST");
                if(data==null){
                    LogUtils.e("data----CODE_CAMERA_REQUEST",""+data);
                    return;
                }else {
                    LogUtils.e("data----else","else");
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
                    if (bitmap != null) {
                        mLrderLin.setVisibility(View.GONE);
                        mFrame.setVisibility(View.VISIBLE);
                    } else {
                        mLrderLin.setVisibility(View.VISIBLE);
                        mFrame.setVisibility(View.GONE);
                    }

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
                    mImgPon.setImageBitmap(bitmap);// 将图片显示在ImageView里
                    mMemberheadimg = ImageLoaderUtils.bitmaptoString(bm);
                    LogUtils.e("mMemberheadimg------------", "" + mMemberheadimg);
                }
                break;

            case CODE_RESULT_REQUEST:
                if (data != null) {
                    setImageToHeadView(data);
                }
                break;

            case UIHelper.SELECT_REQUEST:
                if (!TextUtils.isEmpty(consignee.getDeliveredMobile())) {
                    LogUtils.e("consignee---if", consignee + "");
                    mConName = consignee.getConsigneeName();
                    mConMobile = consignee.getDeliveredMobile();
                    mConCity = consignee.getProvincialCity();
                    mConAddress = consignee.getAddressInDetail();
                    mAddressName.setText(mConName + mConMobile);
                    mAddressCity.setText(mConCity);
                    mAddressAdd.setText(mConAddress);
                    consignee.setConsigneeName("");
                    consignee.setDeliveredMobile("");
                    consignee.setProvincialCity("");
                    consignee.setAddressInDetail("");
                    consignee.setConsigneeCode("");

                } else {
                    LogUtils.e("consignee---else", consignee + "");
                    mAddressName.setText("");
                    mAddressCity.setText("");
                    mAddressAdd.setText("");
                }

                break;

            case UIHelper.COUPON_REQUEST:
                if (TextUtils.isEmpty(AppContext.get("CouponType", ""))) {

                } else {
                    LogUtils.e("mPlaceCoupon----",""+AppContext.get("Novice", ""));
                    mPlaceCoupon.setText(AppContext.get("Novice", ""));//代金劵
                    mCouponType = AppContext.get("CouponType", "");
                    mDiscountNumber = AppContext.get("DiscountNumber", "");
                    mCouponMoneyEqual = AppContext.get("CouponMoneyEqual", "");
                    mCouponmoney = AppContext.get("Couponmoney", "");
                    if (mCouponType.equals("1001")) {
                        DecimalFormat df = new DecimalFormat("######0.00");
                        double pirce = Double.parseDouble(mPrice.replace(".00", ""));
                        double number = Double.parseDouble(mDiscountNumber.replace(".00", ""));
                        double toal = (number / 10) * pirce;
                        String total = df.format(toal);
                        String cou = df.format(pirce - toal);
                        mPlaceTotal.setText(total);
                        mTvNm.setText(total);
                        mCoupon = cou;
                    } else if (mCouponType.equals("1002")) {
                        int price = Integer.valueOf(mPrice.replace(".00", ""));
                        int equal = Integer.valueOf(mCouponMoneyEqual.replace(".00", ""));
                        int toa = price - equal;
                        mPlaceTotal.setText(String.valueOf(toa) + ".00");
                        mTvNm.setText(String.valueOf(toa) + ".00");
                        mCoupon = mCouponMoneyEqual;
                    } else if (mCouponType.equals("1003")) {
                        mPlaceTotal.setText("0");
                        mTvNm.setText("0");
                        mCoupon = mPrice;
                    } else if (mCouponType.equals("1004")) {
                        int price = Integer.valueOf(mPrice.replace(".00", ""));
                        int equal = Integer.valueOf(mCouponMoneyEqual);
                        if (price < equal) {
                            mPlaceTotal.setText("0");
                            mTvNm.setText("0");

                        } else {
                            int pri = price - equal;
                            mPlaceTotal.setText(String.valueOf(pri));
                            mTvNm.setText(String.valueOf(pri));
                        }
                        mCoupon = mCouponMoneyEqual;
                    } else if (mCouponType.equals("1005")) {
                        int money = Integer.valueOf(mCouponmoney.replace(".00", ""));
                        int equal = Integer.valueOf(mCouponMoneyEqual.replace(".00", ""));
                        int toal = money - equal;
                        mPlaceTotal.setText(mCouponMoneyEqual);
                        mTvNm.setText(mCouponMoneyEqual);
                        mCoupon = String.valueOf(toal);
                    }
                    if(mToggle.isChecked()){
                        String str = mPlaceTotal.getText().toString().replace(".00","");
                        int in = Integer.parseInt(str);
                        mTvNm.setText(String.valueOf(in-t));
                    }

                }

                AppContext.set("Novice", "");
                AppContext.set("CouponType", "");
                AppContext.set("DiscountNumber", "");
                AppContext.set("CouponMoneyEqual", "");
                AppContext.set("Couponmoney", "");
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

    @Override
    protected void onStop() {
        super.onStop();

    }
}
