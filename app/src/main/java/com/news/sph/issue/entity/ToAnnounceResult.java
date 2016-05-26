package com.news.sph.issue.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/26.
 */
public class ToAnnounceResult extends BaseEntity {
    List<ToAnnounceEntity> data;
    public List<ToAnnounceEntity> getData() {
        return data;
    }

    public void setData(List<ToAnnounceEntity> data) {
        this.data = data;
    }
}
