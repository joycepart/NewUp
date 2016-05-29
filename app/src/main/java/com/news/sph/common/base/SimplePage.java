package com.news.sph.common.base;

import com.news.sph.R;
import com.news.sph.home.fragment.VouchersFragment;
import com.news.sph.issue.fragment.CalculationFragment;
import com.news.sph.issue.fragment.PastDetailsFragment;
import com.news.sph.issue.fragment.ProductDetailsFragment;
import com.news.sph.issue.fragment.ToAnnounceFragment;
import com.news.sph.me.fragment.IndianaRecordsFragment;
import com.news.sph.me.fragment.MyCouponFragment;
import com.news.sph.me.fragment.TransactionDetailFragment;


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
//    VOUCHERS(9, R.string.vouchers, VouchersFragment.class),
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
