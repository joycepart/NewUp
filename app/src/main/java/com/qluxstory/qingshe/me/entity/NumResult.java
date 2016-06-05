package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/31.
 */
public class NumResult extends BaseEntity {
    List<NumEntity> data;
    public List<NumEntity> getData() {
        return data;
    }

    public void setData(List<NumEntity> data) {
        this.data = data;
    }
}
