package com.news.sph.home.activity;

import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseActivity;

/**
 * 今日上新主页面   暂时用不到
 */
public class NewTodayActivity extends BaseActivity {
    TextView tv;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home_newtoday;
    }
    @Override
    public void initView() {
        tv= (TextView)findViewById(R.id.top_top_tv);
        tv.setText("今日上新");

    }

    @Override
    public void initData() {

    }
}
