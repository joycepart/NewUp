package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/25.
 */
public class IntegralDetailResult extends BaseEntity {
    List<IntegralDetailEntity> data;

    public List<IntegralDetailEntity> getData() {
        return data;
    }

    public void setData(List<IntegralDetailEntity> data) {
        this.data = data;
    }
}
