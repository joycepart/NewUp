package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/30.
 */
public class CuringOrderDetailsResult extends BaseEntity {
    List<CuringOrderDetailsEntity> data;
    public List<CuringOrderDetailsEntity> getData() {
        return data;
    }

    public void setData(List<CuringOrderDetailsEntity> data) {
        this.data = data;
    }
}
