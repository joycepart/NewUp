package com.news.sph.me.activity;

import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;

import butterknife.Bind;

/**
 * 明细详情主页面
 */
public class DetailActivity extends BaseTitleActivity {
    @Bind(R.id.details_tv1)
    TextView detailsTv1;
    @Bind(R.id.details_tv2)
    TextView detailsTv2;
    @Bind(R.id.details_tv3)
    TextView detailsTv3;
    @Bind(R.id.details_tv4)
    TextView detailsTv4;
    @Bind(R.id.details_tv5)
    TextView detailsTv5;
    @Bind(R.id.details_tv6)
    TextView detailsTv6;
    @Bind(R.id.details_tv7)
    TextView detailsTv7;
    @Bind(R.id.details_tv8)
    TextView detailsTv8;

    @Override
    protected int getContentResId() {
        return R.layout.activity_details;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

}
