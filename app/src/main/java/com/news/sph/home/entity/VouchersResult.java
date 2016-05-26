package com.news.sph.home.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/26.
 */
public class VouchersResult extends BaseEntity {
    List<VouchersEntity> data;
    public List<VouchersEntity> getData() {
        return data;
    }

    public void setData(List<VouchersEntity> data) {
        this.data = data;
    }


}
