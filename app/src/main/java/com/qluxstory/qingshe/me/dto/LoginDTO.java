package com.qluxstory.qingshe.me.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 *
 */
public class LoginDTO extends BaseDTO {
    private String memberverifycode;

    public String getMemberverifycode() {
        return memberverifycode;
    }

    public void setMemberverifycode(String memberverifycode) {
        this.memberverifycode = memberverifycode;
    }
}
