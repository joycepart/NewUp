package com.qluxstory.qingshe.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.home.dto.PayDTO;
import com.qluxstory.qingshe.home.dto.TakeDTO;
import com.qluxstory.qingshe.home.entity.BalanceResult;
import com.qluxstory.qingshe.home.entity.PayResult;
import com.qluxstory.qingshe.home.entity.SendAddress;
import com.qluxstory.qingshe.home.entity.TakeEntity;
import com.qluxstory.qingshe.home.entity.TakeResult;

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
    SendAddress sa;
    private String rturn;



    @Override
    protected int getContentResId() {
        return R.layout.activity_placeorder;

    }

    @Override
    public void initView() {
        Intent mIntent = getIntent();
        if (mIntent != null) {
            mPrice = mIntent.getStringExtra("mPrice");
            mName = mIntent.getStringExtra("mName");
            mPic = mIntent.getStringExtra("mPic");
            mCode = mIntent.getStringExtra("mCode");
        }
        setTitleText("提交订单");
        mPlaceTitlt.setText(mName);
        mPlacordDan.setText("¥ "+mPrice+"*1="+mPrice);
        mDanNum.setText("¥ "+mPrice);
        mPlaceTotal.setText("¥ "+mPrice+".00");
        mTv.setText("¥ ");
        mTvNm.setText(mPrice+".00");
        ImageLoaderUtils.displayImage(mPic,mPlaceImg);
        mSetPayBtn.setOnClickListener(this);
        mPlaceOrderImg.setOnClickListener(this);
        mPlaceTake.setOnClickListener(this);
        mPlaceAddress.setOnClickListener(this);
        mPlaceSend.setOnClickListener(this);
        mRelCoupon.setOnClickListener(this);
        mPayWx.setOnClickListener(this);
        mPayAlipay.setOnClickListener(this);
        mPayBalance.setOnClickListener(this);
        mPlaceCoupon.setOnClickListener(this);
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
                UIHelper.showFragment(this, SimplePage.VOUCHERS,b);//优惠劵
                break;
            case R.id.set_pay_Btn:
                if(mPlaTvAdd.getText().toString()==null){

                }
                if(mSendAddress.getText().toString()==null){

                }
                if(!mCbWx.isChecked()&&!mCbZhi.isChecked()&&!mVbHui.isChecked()){

                }
                reqPay();//去支付

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
            default:
                break;
        }
        super.onClick(v);
    }

    private void reqBalance() {
        BaseDTO dto=new BaseDTO();
        dto.setSign(AppConfig.SIGN_1);
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
        dto.setCity("");
        CommonApiClient.take(this, dto, new CallBack<TakeResult>() {
            @Override
            public void onSuccess(TakeResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("取送方式成功");
                    TakeEntity  entity = result.getData().get(0);
                    rturn = entity.getDis_type_code();
                    mPlaTv.setText(entity.getDis_type_name());

                }

            }
        });
    }


    private void reqPay() {
        PayDTO dto=new PayDTO();
        dto.setAddressInDetail("");
        dto.setComOnlyCode("");
        dto.setConsigneeCode("");
        dto.setConsigneeName("");
        dto.setConsigneeType("");
        dto.setOrderMoney("");
        dto.setProvincialCity("");
        dto.setDeliveredMobile("");
        CommonApiClient.pay(this, dto, new CallBack<PayResult>() {
            @Override
            public void onSuccess(PayResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("去支付成功");

                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == UIHelper.SEND_REQUEST)
        {
            mSendAddress.setText(sa.getAddress());
        }
        if(requestCode == UIHelper.SELECT_REQUEST)
        {
            mPlaTvAdd.setText(sa.getAddress());
        }

    }
}
