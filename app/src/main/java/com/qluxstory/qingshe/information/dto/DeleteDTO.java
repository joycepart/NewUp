package com.qluxstory.qingshe.information.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/6/23.
 */
public class DeleteDTO extends BaseDTO {
    private String userPhone ;
    private String newsCode ;

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
