package com.qluxstory.qingshe.issue.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/27.
 */
public class LanderInEntity extends BaseEntity {
    /*
                    随机数
                         */
    private String receive_ran_num;
    public String getReceive_ran_num() {
        return receive_ran_num;
    }

    public void setReceive_ran_num(String receive_ran_num) {
        this.receive_ran_num = receive_ran_num;
    }

}
