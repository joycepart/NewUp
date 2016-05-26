package com.news.sph.home.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/26.
 */
public class TranResult extends BaseEntity {
    List<TranEntity> data;

    public List<TranEntity> getData() {
        return data;
    }

    public void setData(List<TranEntity> data) {
        this.data = data;
    }
}
