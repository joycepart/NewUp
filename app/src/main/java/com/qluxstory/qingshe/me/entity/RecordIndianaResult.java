package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/26.
 */
public class RecordIndianaResult extends BaseEntity {
    List<RecordIndianaEntity> data;
    public List<RecordIndianaEntity> getData() {
        return data;
    }

    public void setData(List<RecordIndianaEntity> data) {
        this.data = data;
    }

}
