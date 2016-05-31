package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/30.
 */
public class BalanceEntity  extends BaseEntity {
    /*会员余额*/
    private String CashAmountMoney;
    public String getCashAmountMoney() {
        return CashAmountMoney;
    }

    public void setCashAmountMoney(String cashAmountMoney) {
        CashAmountMoney = cashAmountMoney;
    }


}
