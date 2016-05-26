package com.news.sph.me.entity;

import com.news.sph.common.entity.BaseEntity;

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
