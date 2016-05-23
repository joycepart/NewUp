package com.news.sph.home.activity;

import android.view.View;
import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseActivity;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.utils.TextViewUtils;

import butterknife.Bind;

/**
 * 专题/广告详情主页面  (交互h5页面)
 */
public class SpecialDetailsActivity extends BaseTitleActivity {
    @Bind(R.id.base_titlebar_text)
    TextView base_titlebar_text;
    @Bind(R.id.base_titlebar_back)
    TextView base_titlebar_back;
    @Bind(R.id.base_titlebar_ensure)
    TextView base_titlebar_ensure;


    @Override
    protected int getContentResId() {
        return R.layout.activity_specialdetails;
    }

    @Override
    public void initView() {
        setTitleText("新闻详情");

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
            case R.id.base_titlebar_ensure:
                baseGoBack();
                break;
            default:
                break;
        }
        super.onClick(v);
    }
}
