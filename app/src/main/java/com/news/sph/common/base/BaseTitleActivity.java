package com.news.sph.common.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.utils.TextViewUtils;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 带有标题的的基类
 */
public abstract class BaseTitleActivity extends BaseActivity {
    private TextView mBaseTitle, mBaseEnsure, mBaseBack;
    private View mTitleLayout;
    private PtrClassicFrameLayout ptf;

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

    /**
     * 是否打开下拉刷新。默认关闭
     *
     * @return
     */
    public boolean pulltoRefresh() {
        return false;
    }

    public void refreshComplete() {
        ptf.refreshComplete();
    }

    //请求数据方法
    protected void sendRequestData() {

    }

    private void baseInitView() {

        ptf = (PtrClassicFrameLayout) findViewById(R.id.base_ptr_frame);
        ptf.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                sendRequestData();

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (pulltoRefresh()) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                }
                return pulltoRefresh();
            }
        });
        mTitleLayout = findViewById(R.id.base_titlebar_layout);
        mBaseTitle = (TextView) findViewById(R.id.base_titlebar_text);
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        mBaseEnsure.setOnClickListener(this);
        mBaseBack = (TextView) findViewById(R.id.base_titlebar_back);
        mBaseBack.setOnClickListener(this);

        // 初始化返回按钮图片大小
        TextViewUtils.setTextViewIcon(this, mBaseBack, R.drawable.back,
                R.dimen.common_titlebar_icon_width,
                R.dimen.common_titlebar_icon_height, TextViewUtils.DRAWABLE_LEFT);
        // 初始化分享按钮图片大小
        TextViewUtils.setTextViewIcon(this, mBaseEnsure, R.drawable.detaile_share,
                R.dimen.common_titlebar_icon_width,
                R.dimen.common_titlebar_icon_height, TextViewUtils.DRAWABLE_RIGHT);
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
     * 左侧的按钮事件
     */
    protected void baseGoBack() {
        finish();
    }

    /**
     * 右侧的按钮事件
     */
    protected void baseGoEnsure() {

    }

    /**
     * 布局文件
     *
     * @return
     */
    protected abstract int getContentResId();
}