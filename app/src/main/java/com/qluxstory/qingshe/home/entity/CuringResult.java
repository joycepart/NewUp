package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/25.
 */
public class CuringResult extends BaseEntity {
    List<CuringEntity> data;

    public List<CuringEntity> getData() {
        return data;
    }

    public void setData(List<CuringEntity> data) {
        this.data = data;
    }
}
