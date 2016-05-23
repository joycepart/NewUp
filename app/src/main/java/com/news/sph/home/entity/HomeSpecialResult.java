package com.news.sph.home.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HomeSpecialResult extends BaseEntity {
    List<HomeSpecialEntity> data;

    public List<HomeSpecialEntity> getData() {
        return data;
    }

    public void setData(List<HomeSpecialEntity> data) {
        this.data = data;
    }
}
