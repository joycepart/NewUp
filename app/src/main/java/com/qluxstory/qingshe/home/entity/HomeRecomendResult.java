package com.qluxstory.qingshe.home.entity;

import com.google.gson.annotations.SerializedName;
import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HomeRecomendResult extends BaseEntity {
    @SerializedName("sixrec")
    List<HomeRecommendEntity> data;

    public List<HomeRecommendEntity> getData() {
        return data;
    }

    public void setData(List<HomeRecommendEntity> data) {
        this.data = data;
    }
}
