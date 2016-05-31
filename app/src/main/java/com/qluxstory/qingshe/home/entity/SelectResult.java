package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/30.
 */
public class SelectResult extends BaseEntity {
    List<SelectEntity> data;

    public List<SelectEntity> getData() {
        return data;
    }

    public void setData(List<SelectEntity> data) {
        this.data = data;
    }
}
