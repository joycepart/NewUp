package com.qluxstory.qingshe.common.entity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/10.
 */
public class AdsResult extends BaseEntity {
    List<AdsEntity> data;

    public List<AdsEntity> getData() {
        return data;
    }

    public void setData(List<AdsEntity> data) {
        this.data = data;
    }
}
