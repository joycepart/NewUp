package com.news.sph.me.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 2016/5/18.
 */
public class MyIncomeEntity {
    public String getCashamountmoney() {
        return cashamountmoney;
    }

    public void setCashamountmoney(String cashamountmoney) {
        this.cashamountmoney = cashamountmoney;
    }

    public String getAccumulatedmoney() {
        return accumulatedmoney;
    }

    public void setAccumulatedmoney(String accumulatedmoney) {
        this.accumulatedmoney = accumulatedmoney;
    }

    public String getPresentState() {
        return presentState;
    }

    public void setPresentState(String presentState) {
        this.presentState = presentState;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 可提现余额
     */
    private String cashamountmoney;
    /**
     * 累计收入
     */
    private String accumulatedmoney;
    /**
     * 状态
     */
    private String presentState;
    @SerializedName("RongyunToken")
    private String token;
}
