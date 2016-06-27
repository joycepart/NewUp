package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/24.
 */
public class IntegralResult extends BaseEntity {
    List<IntegralEntity> data;

    public List<IntegralEntity> getData() {
        return data;
    }

    public void setData(List<IntegralEntity> data) {
        this.data = data;
    }
}
