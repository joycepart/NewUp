package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/28.
 */
public class PayResult extends BaseEntity {
    List<PayEntity> data;

    public List<PayEntity> getData() {
        return data;
    }

    public void setData(List<PayEntity> data) {
        this.data = data;
    }
}
