package com.news.sph.me.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseActivity;
import com.news.sph.common.base.BaseTitleActivity;

import butterknife.Bind;

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
        mTopMyincomeImg.setOnClickListener(this);
        mTopMyincomeRingt.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_myincome_img:
                baseGoBack();
                break;
            case R.id.top_myincome_ringt:
                baseGoBack();
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
