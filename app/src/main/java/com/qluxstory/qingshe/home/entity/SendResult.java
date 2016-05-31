package com.qluxstory.qingshe.home.entity;

import com.google.gson.annotations.SerializedName;
import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/30.
 */
public class SendResult extends BaseEntity {
    @SerializedName("address")
    List<SendEntity> data;

    public List<SendEntity> getData() {
        return data;
    }

    public void setData(List<SendEntity> data) {
        this.data = data;
    }
}
