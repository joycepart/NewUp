package com.qluxstory.qingshe.issue.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class WinningResult extends BaseEntity{
    List<WinningEntity> data;
    public List<WinningEntity> getData() {
        return data;
    }

    public void setData(List<WinningEntity> data) {
        this.data = data;
    }


}
