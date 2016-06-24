package com.qluxstory.qingshe.issue.activity;

import android.view.View;
import android.widget.TextView;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.issue.IssueUiGoto;

import butterknife.Bind;

/**
 * 支付成功界面
 */
public class PaymentSuccessActivity extends BaseTitleActivity {
    @Bind(R.id.payment_tv)
    TextView mTv;
    @Override
    protected int getContentResId() {
        return R.layout.activity_payment;
    }

    @Override
    public void initView() {
        setTitleText("支付结果");
        mTv.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.payment_tv:
                IssueUiGoto.home(this);//首页
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }

    @Override
    protected void baseGoBack() {
        super.baseGoBack();
        IssueUiGoto.home(this);//首页
    }
}
