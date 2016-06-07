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
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.home.dto.PayDTO;
import com.qluxstory.qingshe.home.entity.PayResult;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.me.dto.CuringOrderDetailsDTO;
import com.qluxstory.qingshe.me.entity.CuringOrderDetailsEntity;
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
    PaymentOrderEntity payEntity;

    CuringOrderDetailsEntity entity;
    @Override
    protected int getContentResId() {
        return R.layout.activity_paymentorder;
    }

    @Override
    public void initView() {
        setTitleText("支付订单");
        Intent intent = getIntent();
        if(intent!=null){
            entity = (CuringOrderDetailsEntity) intent.getBundleExtra("bundle").getSerializable("entitiy");
        }
        mPayWx.setOnClickListener(this);
        mPayAlipay.setOnClickListener(this);
        mPayBalance.setOnClickListener(this);
        mPayTvBtn.setOnClickListener(this);

    }

    @Override
    public void initData() {
        reqCuringOrderDetails();//养护订单详情

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
        payEntity = data.get(0);
        mPayGet.setText(payEntity.getConsigneeType());
        if(payEntity.getConsigneeType().equals("全国包回邮")){
            mPayMode.setText("收货地址");
            mPaySend.setText("寄送地址：");
            mPayAddress.setText(payEntity.getConsigneeName()+payEntity.getDeliveredMobile());
            mPayPri.setText(payEntity.getProvincialCity());
            mPayDetails.setText(payEntity.getAddressInDetail());
            mPayTvSend.setText(payEntity.getSto_name()+payEntity.getSto_phone());
            mPayCity.setText(payEntity.getDis_cityAddress());

        }else if(payEntity.getConsigneeType().equals("上门取送")){
            mPayMode.setText("上门地址");
            mPaySend.setText("预约上门时间：");
            mPayAddress.setText(payEntity.getConsigneeName()+payEntity.getDeliveredMobile());
            mPayPri.setText(payEntity.getProvincialCity());
            mPayDetails.setText(payEntity.getAddressInDetail());
            mPayTvSend.setText(payEntity.getOrderSingleTime()+"10:00 - 18:00");

        }else if(payEntity.getConsigneeType().equals("自送门店")){
            mPayMode.setText("门店地址");
            mPaySend.setText("门店工作时间：");
            mPayTvSend.setText(payEntity.getConsigneeName()+payEntity.getDeliveredMobile());
            mPayCity.setText(payEntity.getProvincialCity());

        }
        mPayTv1.setText(payEntity.getComName());
        mPayTv2.setText("¥"+payEntity.getComPrice()+"*1="+payEntity.getComPrice());
        mPayTv3.setText("¥"+payEntity.getComPrice());
        mPayTv.setText(payEntity.getOrderMoney());
        mCoupon.setText(payEntity.getCouponPrice());
        mPayTotal.setText(payEntity.getOrderMoney());
        mPayTv.setText("¥"+payEntity.getOrderMoney());
        LogUtils.e("curingOrderListEntity.getServerKHImg()----",payEntity.getServerKHImg());
        LogUtils.e("curingOrderListEntity.getApp_show_pic()()----",payEntity.getApp_show_pic());
        ImageLoaderUtils.displayImage(payEntity.getServerKHImg(),mPayImg);
        ImageLoaderUtils.displayImage(payEntity.getApp_show_pic(),mPayCuringImg);
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
        dto.setConsigneeType(payEntity.getConsigneeType());
        dto.setConsigneeCode(payEntity.getConsigneeCode());
        dto.setConsigneeName(payEntity.getConsigneeName());
        dto.setDeliveredMobile(payEntity.getDeliveredMobile());
        dto.setProvincialCity(payEntity.getProvincialCity());
        dto.setAddressInDetail(payEntity.getAddressInDetail());
        dto.setComOnlyCode(payEntity.getComOnlyCode());
        dto.setOrderMoney(payEntity.getOrderMoney());
        dto.setComCount(payEntity.getComCount());
        dto.setCouponPrice(payEntity.getCouponPrice());
        dto.setMemberIDCoupon(payEntity.getMemberIDCoupon());
        dto.setCouponCode(payEntity.getUserCouponCode());
        dto.setMemMobile(AppContext.get("mobileNum", ""));
        dto.setOrderType("养护");
        dto.setTimeToAppointmen(payEntity.getTimeToAppointmen());
        dto.setServerYJCode(payEntity.getServerYJCode());
        if (mCbWx.isChecked()) {
            dto.setApplyType("微信");
        } else if (mCbZhi.isChecked()) {
            dto.setApplyType("支付宝");
        } else {
            dto.setApplyType("会员");
        }
        dto.setServiceMoney(payEntity.getServiceMoney());
        dto.setReqType("service");
        dto.setOldOrderNum(payEntity.getOrderManCode());
        dto.setShoudamoney(payEntity.getShoudanMoney());
        String base64 = payEntity.getServerKHImg();
        dto.setBase64string(ImageLoaderUtils.imgToBase64(base64,null,null));
        dto.setServerName(payEntity.getServerName());
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
