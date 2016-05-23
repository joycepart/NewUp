package com.news.sph.home.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseActivity;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.home.utils.HomeUiGoto;

import butterknife.Bind;

/**
 * 新增地址主页面
 */
public class AddAddressActivity extends BaseTitleActivity {
    @Bind(R.id.base_titlebar_ensure)
    TextView mBaseTitlebarEnsure;
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
        mAddBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_Btn:
                HomeUiGoto.addAddress(this);//选择地址

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
