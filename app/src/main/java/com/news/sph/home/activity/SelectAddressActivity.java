package com.news.sph.home.activity;

import android.view.View;
import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseActivity;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.home.utils.HomeUiGoto;

import butterknife.Bind;

/**
 * 选择地址主页面
 */
public class SelectAddressActivity extends BaseTitleActivity {
    @Bind(R.id.base_titlebar_ensure)
    TextView mBaseTitlebarEnsure;
    @Bind(R.id.select_address)
    TextView mSelectAddress;


    @Override
    protected int getContentResId() {
        return R.layout.activity_selectaddress;
    }

    @Override
    public void initView() {
        setTitleText("选择地址");
        mBaseTitlebarEnsure.setVisibility(View.GONE);
        mSelectAddress.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_Btn:
                HomeUiGoto.receiptAddress(this);//新增地址

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
