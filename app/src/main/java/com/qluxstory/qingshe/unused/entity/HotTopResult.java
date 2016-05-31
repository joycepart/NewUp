package com.qluxstory.qingshe.unused.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HotTopResult extends BaseEntity {
    List<HotTopEntity> data;

    public List<HotTopEntity> getData() {
        return data;
    }

    public void setData(List<HotTopEntity> data) {
        this.data = data;
    }


}
