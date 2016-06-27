package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/25.
 */
public class SignResult extends BaseEntity {
    List<SignEntity> data;
    public List<SignEntity> getData() {
        return data;
    }

    public void setData(List<SignEntity> data) {
        this.data = data;
    }
}
