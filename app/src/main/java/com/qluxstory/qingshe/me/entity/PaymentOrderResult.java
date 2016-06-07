package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/7.
 */
public class PaymentOrderResult extends BaseEntity {
    List<PaymentOrderEntity> data;
    public List<PaymentOrderEntity> getData() {
        return data;
    }

    public void setData(List<PaymentOrderEntity> data) {
        this.data = data;
    }
}
