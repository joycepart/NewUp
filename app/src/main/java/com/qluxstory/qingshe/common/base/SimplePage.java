package com.qluxstory.qingshe.common.base;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.home.fragment.SelectFragment;
import com.qluxstory.qingshe.home.fragment.SendAddressFragment;
import com.qluxstory.qingshe.home.fragment.StoreFragment;
import com.qluxstory.qingshe.home.fragment.VouchersFragment;
import com.qluxstory.qingshe.information.fragment.InformationFragment;
import com.qluxstory.qingshe.issue.fragment.CalculationFragment;
import com.qluxstory.qingshe.issue.fragment.PastDetailsFragment;
import com.qluxstory.qingshe.issue.fragment.ProductDetailsFragment;
import com.qluxstory.qingshe.issue.fragment.ToAnnounceFragment;
import com.qluxstory.qingshe.me.fragment.IndianaRecordsFragment;
import com.qluxstory.qingshe.me.fragment.IntegralFragment;
import com.qluxstory.qingshe.me.fragment.MyCouponFragment;
import com.qluxstory.qingshe.me.fragment.TransactionDetailFragment;


public enum SimplePage{

//    SIMPLE_LIST_TEST(1, R.string.demo_simple_title, SimpleListDemoFragment.class),
    TRANSACTION_DETAIL(2, R.string.me_income_detail, TransactionDetailFragment.class),
    PRODUCT_DETAILS(3, R.string.product_details, ProductDetailsFragment.class),
    TOANNOUNCE(4, R.string.issue_announce, ToAnnounceFragment.class),
    PAST_DETAILSF(5, R.string.announce_details, PastDetailsFragment.class),
    INDIANA_RECORDS(6, R.string.tab_me_n, IndianaRecordsFragment.class),
    MY_COUPON(7, R.string.tab_me_f, MyCouponFragment.class),
    VOUCHERS(8, R.string.vouchers, VouchersFragment.class),
    CALULATION(9, R.string.calculation_details, CalculationFragment.class),
    SEND_ADDRESS(10, R.string.order_receipt_choice, SendAddressFragment.class),
    SELECT_ADDRESS(11, R.string.order_receipt_choice, SelectFragment.class),
    STORE(12, R.string.order_receipt_store, StoreFragment.class),
    INTERARAL(13, R.string.rule_details, IntegralFragment.class),
    INFORMATION(14, R.string.home_xx, InformationFragment.class),
    ;

    private int title;
    private Class<?> clz;
    private int value;

    SimplePage(int value, int title, Class<?> clz) {
        this.value = value;
        this.title = title;
        this.clz = clz;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static SimplePage getPageByValue(int val) {
        for (SimplePage p : values()) {
            if (p.getValue() == val)
                return p;
        }
        return null;
    }
}
