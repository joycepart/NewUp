package com.qluxstory.qingshe.common.entity;

import java.io.Serializable;

/**
实体类基类
 */
public class BaseEntity implements Serializable{

    private String status;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
