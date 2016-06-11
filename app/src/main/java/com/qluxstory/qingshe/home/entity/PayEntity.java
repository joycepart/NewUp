package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/28.
 */
public class PayEntity extends BaseEntity {
    /*本地交易号*/
    private String OrderNum;
    /*支付方式*/
    private String ApplyType;

    public String getApplyType() {
        return ApplyType;
    }

    public void setApplyType(String applyType) {
        ApplyType = applyType;
    }

    public String getOrderNum() {
        return OrderNum;
    }

    public void setOrderNum(String orderNum) {
        OrderNum = orderNum;
    }


}
