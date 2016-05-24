package com.news.sph.me.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 2016/5/18.
 */
public class MyIncomeEntity {

    /**
     * 可提现余额
     */
    private String mCashamountmoney;
    /**
     * 累计收入
     */
    private String mAccumulatedmoney;
    /**
     * 状态
     */
    private String mPresentState;
    @SerializedName("RongyunToken")
    private String mToken;

    public String getmAccumulatedmoney() {
        return mAccumulatedmoney;
    }

    public void setmAccumulatedmoney(String mAccumulatedmoney) {
        this.mAccumulatedmoney = mAccumulatedmoney;
    }

    public String getmCashamountmoney() {
        return mCashamountmoney;
    }

    public void setmCashamountmoney(String mCashamountmoney) {
        this.mCashamountmoney = mCashamountmoney;
    }

    public String getmPresentState() {
        return mPresentState;
    }

    public void setmPresentState(String mPresentState) {
        this.mPresentState = mPresentState;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }




}
