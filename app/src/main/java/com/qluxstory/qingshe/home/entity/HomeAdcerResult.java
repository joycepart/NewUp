package com.qluxstory.qingshe.home.entity;

import com.google.gson.annotations.SerializedName;
import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HomeAdcerResult extends BaseEntity {
    @SerializedName("fourad")
    List<HomeAdcerEntity> data;

    public List<HomeAdcerEntity> getData() {
        return data;
    }

    public void setData(List<HomeAdcerEntity> data) {
        this.data = data;
    }
}
