package com.news.sph.me.dto;

import com.news.sph.common.dto.BaseDTO;

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
