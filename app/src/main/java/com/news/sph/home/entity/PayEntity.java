package com.news.sph.home.entity;

import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/28.
 */
public class PayEntity extends BaseEntity {
    /*本地交易号*/
    private String OrderNum;
    /*支付方式*/
    private String ApplyType;
    /*1完成返回首页，0按支付方式跳转*/
    private String status;

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

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }


}
