package com.news.sph.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 代金劵主页面
 */
public class VouchersActivity extends BaseTitleActivity {
    @Bind(R.id.base_titlebar_ensure)
    TextView mBaseTitlebarEnsure;
    @Bind(R.id.top_myincome_img)
    ImageView mTopMyincomeImg;
    @Bind(R.id.top_myincome_ringt)
    TextView mTopMyincomeRingt;


    @Override
    protected int getContentResId() {
        return R.layout.activity_vouchers;
    }

    @Override
    public void initView() {
        setTitleText("代金劵");
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

    @OnClick({R.id.top_myincome_img, R.id.top_myincome_ringt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_myincome_img:
                baseGoBack();
                break;
            case R.id.top_myincome_ringt:
                break;
        }
    }
}
