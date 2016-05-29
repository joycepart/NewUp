package com.news.sph.home.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.common.base.SimplePage;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.ImageLoaderUtils;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.common.utils.UIHelper;
import com.news.sph.home.HomeUiGoto;
import com.news.sph.home.dto.PayDTO;
import com.news.sph.home.entity.PayResult;

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
    @Bind(R.id.placeorder_tv)
    TextView mTv;
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


    String mTitle;
    String mUrl;
    String mNum;



    @Override
    protected int getContentResId() {
        return R.layout.activity_placeorder;

    }

    @Override
    public void initView() {
        setTitleText("提交订单");
        mPlaceTitlt.setText(mTitle);
        mPlacordDan.setText("¥ "+mNum+"*1="+mNum);
        mDanNum.setText("¥ "+mNum);
        mPlaceTotal.setText("¥ "+mNum+".00");
        mTv.setText("¥ ");
        mTvNm.setText(mNum+".00");
        ImageLoaderUtils.displayImage(mUrl,mPlaceImg);
        mSetPayBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.place_take_delivery:
                baseGoBack();//取送方式
                break;
            case R.id.place_address:
                HomeUiGoto.receiptAddress(this);//选择地址
                break;
            case R.id.place_send:
                HomeUiGoto.send(this);//寄送地址之选择地址
                break;
            case R.id.place_coupon:
                UIHelper.showFragment(this, SimplePage.TRANSACTION_DETAIL);//优惠劵
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
}
