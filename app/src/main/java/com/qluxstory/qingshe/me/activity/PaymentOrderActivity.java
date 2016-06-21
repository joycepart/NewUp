package com.qluxstory.qingshe.me.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.alipay.Keys;
import com.qluxstory.qingshe.alipay.PayResult;
import com.qluxstory.qingshe.alipay.Rsa;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.SecurityUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.home.dto.PayDTO;
import com.qluxstory.qingshe.home.entity.BalanceResult;
import com.qluxstory.qingshe.home.entity.PaypayEntity;
import com.qluxstory.qingshe.home.entity.PaypayResult;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.me.dto.CuringOrderDetailsDTO;
import com.qluxstory.qingshe.me.entity.PaymentOrderEntity;
import com.qluxstory.qingshe.me.entity.PaymentOrderResult;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import butterknife.Bind;

/**
 * 支付订单页面
 */
public class PaymentOrderActivity extends BaseTitleActivity {
    @Bind(R.id.placement_address_time)
    RelativeLayout mTime;
    @Bind(R.id.placement_delivery)
    LinearLayout mPlaceTake;

    @Bind(R.id.place_address)
    LinearLayout mPlaceAddress;
    @Bind(R.id.place_send)
    LinearLayout mPlaceSend;

    @Bind(R.id.placement_tv)
    TextView mPlaTv;
    @Bind(R.id.placement_tv1)
    TextView mPlaTv1;

    @Bind(R.id.place_tv_address)
    TextView mTvAddress;
    @Bind(R.id.place_tv_address_name)
    TextView mAddressName;
    @Bind(R.id.place_tv_address_city)
    TextView mAddressCity;
    @Bind(R.id.place_tv_address_add)
    TextView mAddressAdd;
    @Bind(R.id.place_send_tv)
    TextView mPlaceSendTv;
    @Bind(R.id.place_send_num)
    TextView mSendAddress;
    @Bind(R.id.place_send_tv_city)
    TextView mSendVity;
    @Bind(R.id.place_send_tv_add)
    TextView mSendAdd;
    @Bind(R.id.placement_tv_address_time)
    TextView mPlacementTime;
    @Bind(R.id.placement_tv_add_time)
    TextView mAddTime;
    @Bind(R.id.pay_tit_img)
    ImageView mPayImg;
    @Bind(R.id.pay_curing_img)
    ImageView mImg;
    @Bind(R.id.tv_balance)
    TextView mTvBalance;
    @Bind(R.id.pay_total)
    TextView mPayTotal;
    @Bind(R.id.pay_curing_tv1)
    TextView mPayTv1;
    @Bind(R.id.pay_tv2)
    TextView mPayTv2;
    @Bind(R.id.pay_curing_tv3)
    TextView mPayTv3;
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
    @Bind(R.id.pay_tv)
    TextView mPayTv;
    @Bind(R.id.place_coupon)
    TextView mCoupon;
    @Bind(R.id.pay_tv_btn)
    TextView mPayTvBtn;
    PaymentOrderEntity paymentEntity;
    private String mOrdNum;
    private static final int RQF_PAY = 1;
    @Override
    protected int getContentResId() {
        return R.layout.activity_paymentorder;
    }

    @Override
    public void initView() {
        setTitleText("支付订单");
        Intent intent = getIntent();
        if(intent!=null){
            mOrdNum = intent.getBundleExtra("bundle").getString("mOrdNum");
            LogUtils.e("mOrdNum----",mOrdNum);
        }
        mPayWx.setOnClickListener(this);
        mPayAlipay.setOnClickListener(this);
        mPayBalance.setOnClickListener(this);
        mPayTvBtn.setOnClickListener(this);

    }

    @Override
    public void initData() {
        reqCuringOrderDetails();//养护订单详情
        reqBalance();//会员余额

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
    private void reqCuringOrderDetails() {
        CuringOrderDetailsDTO cdto = new CuringOrderDetailsDTO();
        String time = TimeUtils.getSignTime();
        cdto.setMembermob(AppContext.get("mobileNum", ""));
        cdto.setOrderNum(mOrdNum);
        cdto.setSign(time+AppConfig.SIGN_1);
        cdto.setTimestamp(time);
        CommonApiClient.curingOrderPays(this, cdto, new CallBack<PaymentOrderResult>() {
            @Override
            public void onSuccess(PaymentOrderResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("支付订单成功");
                    bindResult(result.getData());
                }

            }
        });
    }
    private void bindResult(List<PaymentOrderEntity> data) {
        paymentEntity = data.get(0);
        mPlaTv.setText(paymentEntity.getConsigneeType());
        if(paymentEntity.getConsigneeType().equals("全国包回邮")){
            mPlaceAddress.setVisibility(View.VISIBLE);
            mPlaceSend.setVisibility(View.VISIBLE);
            mTime.setVisibility(View.GONE);
            mTvAddress.setText("收货地址");
            mPlaceSendTv.setText("寄送地址：");
            mAddressName.setText(paymentEntity.getConsigneeName()+ paymentEntity.getDeliveredMobile());
            mAddressCity.setText(paymentEntity.getProvincialCity());
            mAddressAdd.setText(paymentEntity.getAddressInDetail());
            mSendAddress.setText(paymentEntity.getSto_name()+ paymentEntity.getSto_phone());
            mSendVity.setText(paymentEntity.getDis_cityAddress());

        }else if(paymentEntity.getConsigneeType().equals("上门取送")){
            mPlaceAddress.setVisibility(View.VISIBLE);
            mPlaceSend.setVisibility(View.GONE);
            mTime.setVisibility(View.VISIBLE);
            mTvAddress.setText("上门地址");
            mPlacementTime.setText("预约上门时间：");
            mAddressName.setText(paymentEntity.getConsigneeName()+ paymentEntity.getDeliveredMobile());
            mAddressCity.setText(paymentEntity.getProvincialCity());
            mAddressAdd.setText(paymentEntity.getAddressInDetail());
            mAddTime.setText(paymentEntity.getOrderSingleTime()+"10:00 - 18:00");

        }else if(paymentEntity.getConsigneeType().equals("自送门店")){
            mPlaceAddress.setVisibility(View.GONE);
            mPlaceSend.setVisibility(View.VISIBLE);
            mTime.setVisibility(View.VISIBLE);
            mPlaceSendTv.setText("门店地址");
            mPlacementTime.setText("门店工作时间：");
            mSendAddress.setText(paymentEntity.getConsigneeName()+ paymentEntity.getDeliveredMobile());
            mSendVity.setText(paymentEntity.getProvincialCity());
            mAddTime.setText("10:00 - 18:00");

        }
        mPayTv1.setText(paymentEntity.getComName());
        mPayTv2.setText("¥"+ paymentEntity.getComPrice()+"*1="+ paymentEntity.getComPrice());
        mPayTv3.setText("¥"+ paymentEntity.getComPrice());
        mPayTv.setText(paymentEntity.getOrderMoney());
        mCoupon.setText(paymentEntity.getCouponPrice());
        mPayTotal.setText(paymentEntity.getOrderMoney());
        mPayTv.setText("¥"+ paymentEntity.getOrderMoney());
        LogUtils.e("curingOrderListEntity.getServerKHImg()----", paymentEntity.getServerKHImg());
        LogUtils.e("curingOrderListEntity.getApp_show_pic()()----", paymentEntity.getApp_show_pic());
        ImageLoaderUtils.displayImage(paymentEntity.getServerKHImg(),mPayImg);
        ImageLoaderUtils.displayImage(paymentEntity.getApp_show_pic(),mImg);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.pay_tv_btn:
                if (!mCbWx.isChecked() && !mCbZhi.isChecked() && !mVbHui.isChecked()) {
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


        }
    }

    private void reqPay() {
        mPayTvBtn.setEnabled(false);
        PayDTO dto = new PayDTO();
        String time = TimeUtils.getSignTime();
        dto.setConsigneeType(paymentEntity.getConsigneeType());
        dto.setConsigneeCode(paymentEntity.getConsigneeCode());
        dto.setConsigneeName(paymentEntity.getConsigneeName());
        dto.setDeliveredMobile(paymentEntity.getDeliveredMobile());
        dto.setProvincialCity(paymentEntity.getProvincialCity());
        dto.setAddressInDetail(paymentEntity.getAddressInDetail());
        dto.setComOnlyCode(paymentEntity.getComOnlyCode());
        dto.setOrderMoney(paymentEntity.getOrderMoney());
        dto.setComCount(paymentEntity.getComCount());
        dto.setCouponPrice(paymentEntity.getCouponPrice());
        dto.setMemberIDCoupon(paymentEntity.getMemberIDCoupon());
        dto.setCouponcode(paymentEntity.getUserCouponCode());
        dto.setMemMobile(AppContext.get("mobileNum", ""));
        dto.setOrderType("养护");
        dto.setTimeToAppointmen(paymentEntity.getTimeToAppointmen());
        dto.setServerYJCode(paymentEntity.getServerYJCode());
        if (mCbWx.isChecked()) {
            dto.setApplyType("微信");
        } else if (mCbZhi.isChecked()) {
            dto.setApplyType("支付宝");
        } else if(mVbHui.isChecked()){
            dto.setApplyType("会员");
        }
        dto.setServiceMoney(paymentEntity.getServiceMoney());
        dto.setReqType("service");
        dto.setOldOrderNum(paymentEntity.getOrderManCode());
        dto.setShoudamoney(paymentEntity.getShoudanMoney());
        String base64 = paymentEntity.getServerKHImg();
        dto.setBase64string(ImageLoaderUtils.imgToBase64(base64,null,null));
        dto.setServerName(paymentEntity.getServerName());
        dto.setSign(time+AppConfig.SIGN_1);
        dto.setTimestamp(time);
        CommonApiClient.pay(this, dto, new CallBack<PaypayResult>() {
            @Override
            public void onSuccess(PaypayResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("去支付成功");
                }

                if(mCbZhi.isChecked()){
                    reqAlipayPay(result.getData());
                    mPayTvBtn.setEnabled(true);
                }else if(mCbWx.isChecked()){
                    reqWx(result.getData());
                    mPayTvBtn.setEnabled(true);

                }else if(mVbHui.isChecked()){
                    IssueUiGoto.payment(PaymentOrderActivity.this);//支付结果页
                    mPayTvBtn.setEnabled(true);
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
                String mWxKey = data.get(0).getPrivateKey();
                PayReq req = new PayReq();
                req.appId = AppConfig.Wx_App_Id;
                req.partnerId = data.get(0).getPartnerID();
                req.prepayId = data.get(0).getPrepayid();
                req.packageValue = "Sign=WXPay";
                req.nonceStr = TimeUtils.getSignTime();
                String time =  TimeUtils.getSignTime();
                req.timeStamp = time;
                String str = "appid="+AppConfig.Wx_App_Id
                        +"&noncestr="+TimeUtils.getSignTime()
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
                PayTask alipay = new PayTask(PaymentOrderActivity.this);//支付宝接口了，支付宝现在把很多功能都封装
                String result = alipay.pay(orderInfo,true);//返回的结果
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
                        LogUtils.e("RQF_PAY----","9000");
                        IssueUiGoto.payment(PaymentOrderActivity.this);//支付结果页
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PaymentOrderActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "6001")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PaymentOrderActivity.this, "用户取消订单",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "6002")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PaymentOrderActivity.this, "网络连接错误",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "4000")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PaymentOrderActivity.this, "订单支付失败",
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

         //商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + mOrdNum + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + data.get(0).getProductName() + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + data.get(0).getProductName() + "\"";

        // 商品金额
//        orderInfo += "&total_fee=" + "\"" + data.get(0).getAmount() + "\"";
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + "0.01" + "\"";

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
}
