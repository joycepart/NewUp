package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/25.
 */
public class CuringEntity extends BaseEntity {
    /*服务编号*/
    private String sell_only_code;
    /*服务名称*/
    private String sell_name;
    /*服务描述*/
    private String sell_description;
    /*服务价格*/
    private String sell_price;
    /*服务图片*/
    private String sell_pic;
    /*优惠劵名称*/
    private String sell_first_discription;
    /*是否启用*/
    private String IsYesOrNo;

    public String getIsYesOrNo() {
        return IsYesOrNo;
    }

    public void setIsYesOrNo(String isYesOrNo) {
        IsYesOrNo = isYesOrNo;
    }

    public String getSell_description() {
        return sell_description;
    }

    public void setSell_description(String sell_description) {
        this.sell_description = sell_description;
    }

    public String getSell_first_discription() {
        return sell_first_discription;
    }

    public void setSell_first_discription(String sell_first_discription) {
        this.sell_first_discription = sell_first_discription;
    }

    public String getSell_name() {
        return sell_name;
    }

    public void setSell_name(String sell_name) {
        this.sell_name = sell_name;
    }

    public String getSell_only_code() {
        return sell_only_code;
    }

    public void setSell_only_code(String sell_only_code) {
        this.sell_only_code = sell_only_code;
    }

    public String getSell_pic() {
        return sell_pic;
    }

    public void setSell_pic(String sell_pic) {
        this.sell_pic = sell_pic;
    }

    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }


}
