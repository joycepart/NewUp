package com.qluxstory.qingshe.home.entity;

import com.google.gson.annotations.SerializedName;
import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/30.
 */
public class TakeResult extends BaseEntity {
    @SerializedName("distribution")
    List<TakeEntity> data;

    public List<TakeEntity> getData() {
        return data;
    }

    public void setData(List<TakeEntity> data) {
        this.data = data;
    }
}
