package com.news.sph.issue.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class WinningResult extends BaseEntity{
    public List<WinningEntity> getData() {
        return data;
    }

    public void setData(List<WinningEntity> data) {
        this.data = data;
    }

    List<WinningEntity> data;
}
