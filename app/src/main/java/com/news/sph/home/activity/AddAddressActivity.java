package com.news.sph.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.home.HomeUiGoto;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增地址主页面
 */
public class AddAddressActivity extends BaseTitleActivity {
    @Bind(R.id.base_titlebar_ensure)
    TextView mBaseTitlebarEnsure;
    @Bind(R.id.et_consignee)
    EditText mEtConsignee;
    @Bind(R.id.et_num)
    EditText mEtNum;
    @Bind(R.id.et_city)
    EditText mEtCity;
    @Bind(R.id.et_address)
    EditText mEtAddress;
    @Bind(R.id.add_Btn)
    Button mAddBtn;

    @Override
    protected int getContentResId() {
        return R.layout.activity_addaddress;
    }

    @Override
    public void initView() {
        setTitleText("新增地址");
        mBaseTitlebarEnsure.setVisibility(View.GONE);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_Btn)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_Btn:
                HomeUiGoto.addAddress(this);//选择地址
                break;
            default:
                break;
        }
    }
}
