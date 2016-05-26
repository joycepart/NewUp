package com.news.sph.home.activity;

import android.view.View;
import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.home.HomeUiGoto;

import butterknife.Bind;

/**
 * 收货地址之选择地址主页面
 */
public class SelectAddressActivity extends BaseTitleActivity {
    @Bind(R.id.select_address)
    TextView mSelectAddress;


    @Override
    protected int getContentResId() {
        return R.layout.activity_selectaddress;
    }

    @Override
    public void initView() {
        setTitleText("选择地址");
        mSelectAddress.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_Btn:
                HomeUiGoto.addAddress(this);//新增地址
                break;
            default:
                break;
        }
        super.onClick(v);
    }

    @Override
    public void initData() {

    }
}
