package com.news.sph.home.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HomeRecomendResult extends BaseEntity {
    List<HomeRecommendEntity> data;

    public List<HomeRecommendEntity> getData() {
        return data;
    }

    public void setData(List<HomeRecommendEntity> data) {
        this.data = data;
    }
}
