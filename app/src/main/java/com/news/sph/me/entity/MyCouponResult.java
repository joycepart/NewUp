package com.news.sph.me.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class MyCouponResult extends BaseEntity {
    public List<MyCouponEntity> getData() {
        return data;
    }

    public void setData(List<MyCouponEntity> data) {
        this.data = data;
    }

    List<MyCouponEntity> data;
}
