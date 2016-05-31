package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class MyCouponResult extends BaseEntity {
    List<MyCouponEntity> data;
    public List<MyCouponEntity> getData() {
        return data;
    }

    public void setData(List<MyCouponEntity> data) {
        this.data = data;
    }


}
