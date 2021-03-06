package com.qluxstory.qingshe.home.entity;

import com.google.gson.annotations.SerializedName;
import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/7.
 */
public class TimeResult extends BaseEntity {
    @SerializedName("times")
    List<TimeEntity> data;

    public List<TimeEntity> getData() {
        return data;
    }

    public void setData(List<TimeEntity> data) {
        this.data = data;
    }
}
