package com.news.sph.home.entity;

import com.news.sph.common.entity.BaseEntity;

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
