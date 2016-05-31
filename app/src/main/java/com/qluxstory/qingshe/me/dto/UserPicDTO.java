package com.qluxstory.qingshe.me.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/5/25.
 */
public class UserPicDTO extends BaseDTO {
    /*用户头像base64
           */
    private String memberheadimg;
    public String getMemberheadimg() {
        return memberheadimg;
    }

    public void setMemberheadimg(String memberheadimg) {
        this.memberheadimg = memberheadimg;
    }


}
