package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/14.
 */
public class SplashResult extends BaseEntity {
    List<SplashEntity> data;

    public List<SplashEntity> getData() {
        return data;
    }

    public void setData(List<SplashEntity> data) {
        this.data = data;
    }
}
