package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/25.
 */
public class UserPicEntity extends BaseEntity {
    /*
         头像路径(头像路径，路径需要改变，或者改变图片名)
                */

    private String memberHeadimg;
    public String getMemberHeadimg() {
        return memberHeadimg;
    }

    public void setMemberHeadimg(String memberHeadimg) {
        this.memberHeadimg = memberHeadimg;
    }


}
