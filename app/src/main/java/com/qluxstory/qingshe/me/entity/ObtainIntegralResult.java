package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/25.
 */
public class ObtainIntegralResult extends BaseEntity {
    List<ObtainIntegralEntity> data;
    public List<ObtainIntegralEntity> getData() {
        return data;
    }

    public void setData(List<ObtainIntegralEntity> data) {
        this.data = data;
    }
}
