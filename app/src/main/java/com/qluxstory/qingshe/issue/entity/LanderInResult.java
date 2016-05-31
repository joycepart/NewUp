package com.qluxstory.qingshe.issue.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/27.
 */
public class LanderInResult  extends BaseEntity {
    List<LanderInEntity> data;
    public List<LanderInEntity> getData() {
        return data;
    }

    public void setData(List<LanderInEntity> data) {
        this.data = data;
    }


}
