package com.news.sph.issue.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/26.
 */
public class IssDetailsResult extends BaseEntity {
    List<IssDetailsEntity> data;
    public List<IssDetailsEntity> getData() {
        return data;
    }

    public void setData(List<IssDetailsEntity> data) {
        this.data = data;
    }

}
