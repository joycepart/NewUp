package com.news.sph.issue.activity;

import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseActivity;

/**
 * 夺宝商品详情主页面
 */
public class IndianaProductDetailActivity extends BaseActivity {
    TextView tv;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_indianaproductdetail;
    }
    @Override
    public void initView() {
        tv= (TextView)findViewById(R.id.top_share_tv);
        tv.setText("商品详情");

    }

    @Override
    public void initData() {

    }
}
