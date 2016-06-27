package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/6/24.
 */
public class IntegralEntity extends BaseEntity {
    /***
     * 积分
     */
    private String PointsIntegral;
    /***
     * 积分规则
     */
    private String IntegralState;

    public String getPointsIntegral() {
        return PointsIntegral;
    }

    public void setPointsIntegral(String pointsIntegral) {
        PointsIntegral = pointsIntegral;
    }

    public String getIntegralState() {
        return IntegralState;
    }

    public void setIntegralState(String integralState) {
        IntegralState = integralState;
    }


}
