package com.news.sph.home.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.common.base.SimplePage;
import com.news.sph.common.utils.UIHelper;
import com.news.sph.home.HomeUiGoto;

import butterknife.Bind;

/**
 * 提交订单主页面
 */
public class PlaceOrderActivity extends BaseTitleActivity {
    @Bind(R.id.base_titlebar_ensure)
    TextView mBaseTitlebarEnsure;
    @Bind(R.id.place_take_delivery)
    TextView mPlaceTake;
    @Bind(R.id.place_address)
    TextView mPlaceAddress;
    @Bind(R.id.place_coupon)
    TextView mPlaceCoupon;
    @Bind(R.id.palce_pay_wx)
    TextView mPlacePayWx;
    @Bind(R.id.palce_pay_alipay)
    TextView mPlacePayAli;
    @Bind(R.id.palce_pay_balance)
    TextView mPlacePyaBan;
    @Bind(R.id.pay_Btn)
    TextView mPayBtn;
    @Bind(R.id.place_send)
    LinearLayout mPlaceSend;


    @Override
    protected int getContentResId() {
        return R.layout.activity_placeorder;

    }

    @Override
    public void initView() {
        setTitleText("提交订单");
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
                baseGoBack();
                break;
            case R.id.place_address:
                HomeUiGoto.receiptAddress(this);//选择地址
                break;
            case R.id.place_send:
                HomeUiGoto.send(this);//寄送地址之选择地址
                break;
            case R.id.place_coupon:
                UIHelper.showFragment(this, SimplePage.TRANSACTION_DETAIL);//代金劵
                break;
            case R.id.pay_Btn:

                break;
            default:
                break;
        }
        super.onClick(v);
    }
}
