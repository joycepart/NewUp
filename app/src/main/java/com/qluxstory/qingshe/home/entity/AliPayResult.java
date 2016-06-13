package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/6/12.
 */
public class AliPayResult extends BaseEntity {
    private String result;
    private String resultStatus;

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }



    public AliPayResult(String s) {

    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
