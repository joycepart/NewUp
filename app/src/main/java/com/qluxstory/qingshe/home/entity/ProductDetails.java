package com.qluxstory.qingshe.home.entity;

/**
 * Created by lenovo on 2016/6/3.
 */
public class ProductDetails {
    /*服务编号*/
    private String sellOnlyCode;
    /*服务名称*/
    private String sellName;
    /*服务描述*/
    private String sellDescription;
    /*服务价格*/
    private String sellPrice;
    /*服务图片*/
    private String sellPic;
    /*服务名称 专业 维护（3）/清洗（ 1）/修复（2）*/
    private String sellSort;
    public String getSellSort() {
        return sellSort;
    }

    public void setSellSort(String sellSort) {
        this.sellSort = sellSort;
    }


    public String getSellDescription() {
        return sellDescription;
    }

    public void setSellDescription(String sellDescription) {
        this.sellDescription = sellDescription;
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }

    public String getSellOnlyCode() {
        return sellOnlyCode;
    }

    public void setSellOnlyCode(String sellOnlyCode) {
        this.sellOnlyCode = sellOnlyCode;
    }

    public String getSellPic() {
        return sellPic;
    }

    public void setSellPic(String sellPic) {
        this.sellPic = sellPic;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }


}
