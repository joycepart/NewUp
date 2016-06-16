package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/15.
 */
public class PaypayResult extends BaseEntity {
    List<PaypayEntity> data;

    public List<PaypayEntity> getData() {
        return data;
    }

    public void setData(List<PaypayEntity> data) {
        this.data = data;
    }
}
