package com.qluxstory.qingshe.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.home.dto.PayDTO;
import com.qluxstory.qingshe.home.entity.BalanceResult;
import com.qluxstory.qingshe.home.entity.PayResult;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.me.dto.CuringOrderDetailsDTO;
import com.qluxstory.qingshe.me.entity.CuringOrderListEntity;
import com.qluxstory.qingshe.me.entity.PaymentOrderEntity;
import com.qluxstory.qingshe.me.entity.PaymentOrderResult;

import java.util.List;

import butterknife.Bind;

/**
 * 支付订单页面
 */
public class PaymentOrderActivity extends BaseTitleActivity {
    @Bind(R.id.pay_tit_img)
    ImageView mPayImg;
    @Bind(R.id.pay_tv_get)
    TextView mPayGet;
    @Bind(R.id.tv_balance)
    TextView mTvBalance;
    @Bind(R.id.pay_tv_address)
    TextView mPayAddress;
    @Bind(R.id.pay_tv_pri)
    TextView mPayPri;
    @Bind(R.id.pay_tv_details)
    TextView mPayDetails;
    @Bind(R.id.pay_tv_send)
    TextView mPayTvSend;
    @Bind(R.id.pay_tv_city)
    TextView mPayCity;
    @Bind(R.id.pay_curing_img)
    ImageView mPayCuringImg;
    @Bind(R.id.pay_curing_tv1)
    TextView mPayTv1;
    @Bind(R.id.pay_tv2)
    TextView mPayTv2;
    @Bind(R.id.pay_mode)
    TextView mPayMode;
    @Bind(R.id.pay_curing_tv3)
    TextView mPayTv3;
    @Bind(R.id.pay_send)
    TextView mPaySend;
    @Bind(R.id.pay_total)
    TextView mPayTotal;
    @Bind(R.id.palce_pay_wx)
    LinearLayout mPayWx;
    @Bind(R.id.palce_pay_alipay)
    LinearLayout mPayAlipay;
    @Bind(R.id.palce_pay_balance)
    LinearLayout mPayBalance;
    @Bind(R.id.place_address_time)
    LinearLayout mPlaceAddressTime;
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

    CuringOrderListEntity entity;
    @Override
    protected int getContentResId() {
        return R.layout.activity_paymentorder;
    }

    @Override
    public void initView() {
        setTitleText("支付订单");
        Intent intent = getIntent();
        if(intent!=null){
            entity = (CuringOrderListEntity) intent.getBundleExtra("bundle").getSerializable("entitiy");
        }
        mPayWx.setOnClickListener(this);
        mPayAlipay.setOnClickListener(this);
        mPayBalance.setOnClickListener(this);
        mPayTvBtn.setOnClickListener(this);
        mPlaceAddressTime.setOnClickListener(this);

    }

    @Override
    public void initData() {
        reqCuringOrderDetails();//养护订单详情
        reqBalance();//会员余额

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
    private void reqCuringOrderDetails() {
        CuringOrderDetailsDTO cdto = new CuringOrderDetailsDTO();
        cdto.setMembermob(AppContext.get("mobileNum", ""));
        cdto.setOrderNum(entity.getOrderNum());
        cdto.setSign(AppConfig.SIGN_1);
        cdto.setTimestamp(TimeUtils.getSignTime());
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
        mPayGet.setText(paymentEntity.getConsigneeType());
        if(paymentEntity.getConsigneeType().equals("全国包回邮")){
            mPayMode.setText("收货地址");
            mPaySend.setText("寄送地址：");
            mPayAddress.setText(paymentEntity.getConsigneeName()+ paymentEntity.getDeliveredMobile());
            mPayPri.setText(paymentEntity.getProvincialCity());
            mPayDetails.setText(paymentEntity.getAddressInDetail());
            mPayTvSend.setText(paymentEntity.getSto_name()+ paymentEntity.getSto_phone());
            mPayCity.setText(paymentEntity.getDis_cityAddress());

        }else if(paymentEntity.getConsigneeType().equals("上门取送")){
            mPayMode.setText("上门地址");
            mPaySend.setText("预约上门时间：");
            mPayAddress.setText(paymentEntity.getConsigneeName()+ paymentEntity.getDeliveredMobile());
            mPayPri.setText(paymentEntity.getProvincialCity());
            mPayDetails.setText(paymentEntity.getAddressInDetail());
            mPayTvSend.setText(paymentEntity.getOrderSingleTime()+"10:00 - 18:00");

        }else if(paymentEntity.getConsigneeType().equals("自送门店")){
            mPayMode.setText("门店地址");
            mPaySend.setText("门店工作时间：");
            mPayTvSend.setText(paymentEntity.getConsigneeName()+ paymentEntity.getDeliveredMobile());
            mPayCity.setText(paymentEntity.getProvincialCity());

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
        ImageLoaderUtils.displayImage(paymentEntity.getApp_show_pic(),mPayCuringImg);
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
        PayDTO dto = new PayDTO();
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
        } else {
            dto.setApplyType("会员");
        }
        dto.setServiceMoney(paymentEntity.getServiceMoney());
        dto.setReqType("service");
        dto.setOldOrderNum(paymentEntity.getOrderManCode());
        dto.setShoudamoney(paymentEntity.getShoudanMoney());
        String base64 = paymentEntity.getServerKHImg();
        dto.setBase64string(ImageLoaderUtils.imgToBase64(base64,null,null));
        dto.setServerName(paymentEntity.getServerName());
        dto.setSign(AppConfig.SIGN_1);
        dto.setTimestamp(TimeUtils.getSignTime());
        CommonApiClient.pay(this, dto, new CallBack<PayResult>() {
            @Override
            public void onSuccess(PayResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("去支付成功");
                    IssueUiGoto.payment(PaymentOrderActivity.this);//支付结果页

                }

            }
        });
    }
}
