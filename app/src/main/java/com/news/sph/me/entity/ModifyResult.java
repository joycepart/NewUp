package com.news.sph.me.entity;

import com.news.sph.common.entity.BaseEntity;
import com.news.sph.home.entity.LoginEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/19.
 */
public class ModifyResult extends BaseEntity {
    List<ModifyEntity> data;

    public List<ModifyEntity> getData() {
        return data;
    }

    public void setData(List<ModifyEntity> data) {
        this.data = data;
    }
}
