package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/6/25.
 */
public class IntegralDetailEntity extends BaseEntity {
    /***
     * 积分数
     */
    private String IntegralNum;
    /***
     * 积分来源 0签到1消费
     */
    private String IntegralSource;
    /***
     * 积分获得(1)或支出(0)
     */
    private String IntegralExpen;
    /***
     * 积分处理时间 yyyy-MM-dd HH:mm:ss
     */
    private String IntegralData;

    public String getIntegralNum() {
        return IntegralNum;
    }

    public void setIntegralNum(String integralNum) {
        IntegralNum = integralNum;
    }

    public String getIntegralSource() {
        return IntegralSource;
    }

    public void setIntegralSource(String integralSource) {
        IntegralSource = integralSource;
    }

    public String getIntegralExpen() {
        return IntegralExpen;
    }

    public void setIntegralExpen(String integralExpen) {
        IntegralExpen = integralExpen;
    }

    public String getIntegralData() {
        return IntegralData;
    }

    public void setIntegralData(String integralData) {
        IntegralData = integralData;
    }



}
