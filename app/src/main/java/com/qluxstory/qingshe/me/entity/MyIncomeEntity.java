package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/18.
 */
public class MyIncomeEntity extends BaseEntity{

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


    public String getAccumulatedmoney() {
        return accumulatedmoney;
    }

    public void setAccumulatedmoney(String accumulatedmoney) {
        this.accumulatedmoney = accumulatedmoney;
    }

    public String getCashamountmoney() {
        return cashamountmoney;
    }

    public void setCashamountmoney(String cashamountmoney) {
        this.cashamountmoney = cashamountmoney;
    }

    public String getPresentState() {
        return presentState;
    }

    public void setPresentState(String presentState) {
        this.presentState = presentState;
    }


}
