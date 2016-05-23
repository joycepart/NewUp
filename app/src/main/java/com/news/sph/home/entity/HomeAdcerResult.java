package com.news.sph.home.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HomeAdcerResult extends BaseEntity {
    List<HomeAdcerEntity> data;

    public List<HomeAdcerEntity> getData() {
        return data;
    }

    public void setData(List<HomeAdcerEntity> data) {
        this.data = data;
    }
}
