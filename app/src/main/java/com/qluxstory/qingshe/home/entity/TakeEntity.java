package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/30.
 */
public class TakeEntity extends BaseEntity {
    /*配送类型编号*/
    private String dis_type_code;
    /*配送类型名称*/
    private String dis_type_name;

    public String getDis_type_code() {
        return dis_type_code;
    }

    public void setDis_type_code(String dis_type_code) {
        this.dis_type_code = dis_type_code;
    }

    public String getDis_type_name() {
        return dis_type_name;
    }

    public void setDis_type_name(String dis_type_name) {
        this.dis_type_name = dis_type_name;
    }


}
