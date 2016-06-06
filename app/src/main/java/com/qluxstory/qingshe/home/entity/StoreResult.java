package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/5.
 */
public class StoreResult extends BaseEntity {
    List<StoreEntity> data;

    public List<StoreEntity> getData() {
        return data;
    }

    public void setData(List<StoreEntity> data) {
        this.data = data;
    }
}
