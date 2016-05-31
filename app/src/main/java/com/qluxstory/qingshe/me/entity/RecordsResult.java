package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/26.
 */
public class RecordsResult extends BaseEntity {
    List<RecordsEntity> data;
    public List<RecordsEntity> getData() {
        return data;
    }

    public void setData(List<RecordsEntity> data) {
        this.data = data;
    }
}
