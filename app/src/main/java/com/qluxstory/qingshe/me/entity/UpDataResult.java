package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/8.
 */
public class UpDataResult extends BaseEntity {
    List<UpDataEntity> data;
    public List<UpDataEntity> getData() {
        return data;
    }

    public void setData(List<UpDataEntity> data) {
        this.data = data;
    }
}
