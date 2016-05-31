package com.qluxstory.qingshe.issue.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/26.
 */
public class PicResult extends BaseEntity {

    List<PicEntity> data;
    public List<PicEntity> getData() {
        return data;
    }

    public void setData(List<PicEntity> data) {
        this.data = data;
    }
}
