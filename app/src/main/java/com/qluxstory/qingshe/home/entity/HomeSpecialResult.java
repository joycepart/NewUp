package com.qluxstory.qingshe.home.entity;

import com.google.gson.annotations.SerializedName;
import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HomeSpecialResult extends BaseEntity {
    @SerializedName("foursp")
    List<HomeSpecialEntity> data;

    public List<HomeSpecialEntity> getData() {
        return data;
    }

    public void setData(List<HomeSpecialEntity> data) {
        this.data = data;
    }
}
