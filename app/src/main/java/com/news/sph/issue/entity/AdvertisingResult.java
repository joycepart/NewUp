package com.news.sph.issue.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class AdvertisingResult extends BaseEntity {
    public List<AdvertisingEntity> getData() {
        return data;
    }

    public void setData(List<AdvertisingEntity> data) {
        this.data = data;
    }

    List<AdvertisingEntity> data;
}
