package com.news.sph.common;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.news.sph.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 */
public class YncPullRefreshHeader extends FrameLayout implements PtrUIHandler {

    public LayoutInflater inflater;

    // 下拉刷新视图（头部视图）
    public ViewGroup headView;

    private ImageView mImageView;
    private AnimationDrawable mAnimation;

    float percent = 0;
    float startScale = 0.0f;

//    Animation animation;

    public YncPullRefreshHeader(Context context) {
        this(context, null);
    }

    public YncPullRefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YncPullRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void init(Context context) {
        inflater = LayoutInflater.from(context);
        headView = (ViewGroup) inflater.inflate(R.layout.view_ync_pull_refresh_header, this, true);
        mImageView = (ImageView) findViewById(R.id.iv);
        mImageView.setBackgroundResource(R.drawable.pic_0);
    }

    /**
     * Content 重新回到顶部， Header 消失，整个下拉刷新过程完全结束以后，重置 View。
     *
     * @param frame
     */
    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mImageView.setBackgroundResource(R.drawable.pic_0);
        percent = 0.0f;
        startScale = 0.0f;
    }

    /**
     * 准备刷新，Header 将要出现时调用。
     *
     * @param frame
     */
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    /**
     * 开始刷新，Header 进入刷新状态之前调用。
     *
     * @param frame
     */
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mImageView.setBackgroundResource(R.drawable.animationlist);
        mAnimation = (AnimationDrawable) mImageView.getBackground();
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();
            }
        });
    }

    /**
     * 刷新结束，Header 开始向上移动之前调用。
     *
     * @param frame
     */
    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        if (mAnimation.isRunning()) {
            mAnimation.stop();
            mImageView.setBackgroundResource(R.drawable.pic_6);
        }
    }

    /**
     * 下拉过程中位置变化回调。
     *
     * @param frame
     * @param isUnderTouch
     * @param status
     * @param ptrIndicator
     */
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        if (percent == 1.0) {
            return;
        }
        percent = ptrIndicator.getCurrentPercent();
        if (percent >= 1.0) {
            percent = 1.0f;
        }
        ScaleAnimation animation = new ScaleAnimation(startScale, percent, startScale, percent,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(0);
        animation.setFillAfter(true);
        mImageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startScale = percent;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
