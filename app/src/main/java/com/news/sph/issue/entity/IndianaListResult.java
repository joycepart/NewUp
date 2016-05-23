package com.news.sph.issue.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class IndianaListResult extends BaseEntity{
    public List<IndianaListEntity> getData() {
        return data;
    }

    public void setData(List<IndianaListEntity> data) {
        this.data = data;
    }

    List<IndianaListEntity> data;
}
