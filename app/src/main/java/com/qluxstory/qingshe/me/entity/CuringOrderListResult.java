package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class CuringOrderListResult extends BaseEntity {
    List<CuringOrderListEntity> data;
    public List<CuringOrderListEntity> getData() {
        return data;
    }

    public void setData(List<CuringOrderListEntity> data) {
        this.data = data;
    }


}
