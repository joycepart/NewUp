package com.qluxstory.qingshe.me.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 *
 */
public class LoginDTO extends BaseDTO {
    private String memberverifycode;
    private String registerFrom ;


    public String getRegisterFrom() {
        return registerFrom;
    }

    public void setRegisterFrom(String registerFrom) {
        this.registerFrom = registerFrom;
    }

    public String getMemberverifycode() {
        return memberverifycode;
    }

    public void setMemberverifycode(String memberverifycode) {
        this.memberverifycode = memberverifycode;
    }
}
