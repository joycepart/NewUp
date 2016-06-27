package com.qluxstory.qingshe.common.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TextViewUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.me.MeUiGoto;

/**
 * home带有标题的基类
 */
public abstract class BaseHomeTitleActivity extends BaseActivity {
    private TextView mBaseTitle, mBaseEnsure, mBaseBack;
    private View mTitleLayout;
    protected int mCurrentPage = 1;
    protected final static int PAGE_SIZE = 6;
    boolean bool;


    protected void onAfterSetContentLayout() {
        LinearLayout llContent = (LinearLayout) findViewById(R.id.base_titlebar_content);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(getContentResId(), null);
        llContent.addView(v, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        baseInitView();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.base_titlebar_activity;
    }

    public View getTitleLayout() {
        return mTitleLayout;
    }

    private void baseInitView() {
        bool = AppContext.get("isLogin",false);
        LogUtils.e("bool----",""+bool);
        mTitleLayout = findViewById(R.id.base_titlebar_layout);
        mBaseTitle = (TextView) findViewById(R.id.base_titlebar_text);
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        mBaseEnsure.setText("系统消息");
        mBaseEnsure.setTextSize(12);
        mBaseEnsure.setOnClickListener(this);
        mBaseBack = (TextView) findViewById(R.id.base_titlebar_back);
        mBaseBack.setText("七日签到");
        mBaseBack.setTextSize(12);
        mBaseBack.setOnClickListener(this);

        // 初始化返回按钮图片大小
        TextViewUtils.setTextViewIcon(this, mBaseBack, R.drawable.qiandao,
                R.dimen.common_titlebar_icon_width,
                R.dimen.common_titlebar_icon_height, TextViewUtils.DRAWABLE_TOP);

         //初始化分享按钮图片大小
        TextViewUtils.setTextViewIcon(this, mBaseEnsure, R.drawable.xiaoxu,
                R.dimen.common_titlebar_icon_width,
                R.dimen.common_titlebar_icon_height, TextViewUtils.DRAWABLE_TOP);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.base_titlebar_ensure:
                baseGoEnsure();
                break;
            default:
                break;
        }
        super.onClick(v);
    }


    /**
     * 设置标题文字
     *
     * @param title
     */
    public void setTitleText(String title) {
        mBaseTitle.setText(title);
    }

    /**
     * 设置标题文字
     *
     * @param resid
     */
    public void setTitleText(int resid) {
        mBaseTitle.setText(resid);
    }

    /**
     * 获取标题控件
     *
     * @return
     */
    public TextView getTitleTextView() {
        return mBaseTitle;
    }

    /**
     * 设置右侧文字
     *
     * @param text
     */
    public void setEnsureText(String text) {
        mBaseEnsure.setText(text);
    }

    /**
     * 设置右侧点击按钮图片
     *
     * @param resId
     */
    public void setEnsureDrawable(int resId, int where) {
        TextViewUtils.setTextViewIcon(this, mBaseEnsure, resId,
                R.dimen.common_titlebar_right_icon_width,
                R.dimen.common_titlebar_right_icon_height, where);
    }

    public void setEnsureText(int resid) {
        mBaseEnsure.setText(resid);
    }

    public TextView getEnsureView() {
        return mBaseEnsure;
    }

    public void setEnsureEnable(boolean flag) {
        mBaseEnsure.setClickable(flag);
        mBaseEnsure.setEnabled(flag);
    }

    /**
     * 设置右侧文字
     *
     * @param text
     */
    public void setBaseGoBackText(String text) {
        mBaseBack.setText(text);
    }

    /**
     * 左侧的按钮事件
     */
    protected void baseGoBack() {
        if(bool){
            MeUiGoto.myIntegral(this);//我的积分
        }else {
            MeUiGoto.login(this);//登录
        }
    }

    /**
     * 右侧的按钮事件
     */
    protected void baseGoEnsure() {
        if(bool){
            UIHelper.showFragment(this, SimplePage.INFORMATION);
        }else {
            MeUiGoto.login(this);//登录
        }

    }


    /**
     * 布局文件
     *
     * @return
     */
    protected abstract int getContentResId();
}