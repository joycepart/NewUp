package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/6/25.
 */
public class SignEntity extends BaseEntity {
    /*
               签到天数
                */
    private String DataNum;
    /*
                  当前是否已经签到0否1是
                   */
    private String IsSignIn;

    public String getDataNum() {
        return DataNum;
    }

    public void setDataNum(String dataNum) {
        DataNum = dataNum;
    }

    public String getIsSignIn() {
        return IsSignIn;
    }

    public void setIsSignIn(String isSignIn) {
        IsSignIn = isSignIn;
    }


}
