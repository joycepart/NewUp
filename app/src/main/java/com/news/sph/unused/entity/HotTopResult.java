package com.news.sph.unused.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HotTopResult extends BaseEntity {
    public List<HotTopEntity> getData() {
        return data;
    }

    public void setData(List<HotTopEntity> data) {
        this.data = data;
    }

    List<HotTopEntity> data;
}
