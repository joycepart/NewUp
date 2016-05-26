package com.news.sph.issue.entity;

import com.google.gson.annotations.SerializedName;
import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class AdvertisingResult extends BaseEntity {
    @SerializedName("xzpic")
    List<AdvertisingEntity> data;
    public List<AdvertisingEntity> getData() {
        return data;
    }

    public void setData(List<AdvertisingEntity> data) {
        this.data = data;
    }


}
