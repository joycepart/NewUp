package com.qluxstory.qingshe.common.utils;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.qluxstory.qingshe.R;

/**
 * Created by lenovo on 2016/5/28.
 */
public class TimeCountDown extends CountDownTimer {
    private View mCountView;
    private static final long MILLIS_IN_FUTURE = 60000;
    private static final long COUNT_DOWN_INTERVAL = 1000;
    private static final String FINISH_TEXT = "重新获取";
    private static final String PROGRESS_TEXT = "秒重新获取";
    private String finishText;
    private String progressText;
    private long countDownInterval;

    /**
     * 构造函数
     *
     * @param mCountView
     *            倒计时的View
     * @param millisInFuture
     *            总时长
     * @param countDownInterval
     *            倒计时间隔事件
     * @param finishText
     *            结束时的文本
     * @param processText
     *            过程中显示的文本
     */
    public TimeCountDown(View mCountView, long millisInFuture,
                         long countDownInterval, String finishText, String processText) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        this.mCountView = mCountView;
        this.finishText = finishText;
        this.progressText = processText;
        this.countDownInterval = countDownInterval;
    }

    /**
     * 构造函数
     *
     * @param mCountView
     *            倒计时的View
     * @param finishText
     *            结束时的文本
     * @param progressText
     *            过程中显示的文本
     */
    public TimeCountDown(View mCountView, String finishText, String progressText) {
        this(mCountView, MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL, finishText,
                progressText);
    }

    /**
     * 构造函数
     *
     * @param mCountView
     *            倒计时的View
     */
    public TimeCountDown(View mCountView) {
        this(mCountView, MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL, FINISH_TEXT,
                PROGRESS_TEXT);
    }

    @Override
    public void onFinish() {
        // 计时完毕时触发
        if (mCountView instanceof TextView) {
            ((TextView) mCountView).setText(finishText);
            ((TextView) mCountView).setClickable(true);
            ((TextView) mCountView).setBackgroundResource(R.drawable.getcode);
        }
    }


    @Override
    public void onTick(long millisUntilFinished) {
        // 计时过程显示
        if (mCountView instanceof TextView) {
            ((TextView) mCountView).setText(millisUntilFinished
                    / countDownInterval + progressText);
            ((TextView) mCountView).setClickable(false);
            ((TextView) mCountView).setBackgroundResource(R.drawable.user_btn_click_no);
        }
    }
}
