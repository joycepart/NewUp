package com.news.sph.issue.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/27.
 */
public class AnnouncedResult extends BaseEntity {
    List<AnnouncedEntity> data;
    public List<AnnouncedEntity> getData() {
        return data;
    }

    public void setData(List<AnnouncedEntity> data) {
        this.data = data;
    }


}
