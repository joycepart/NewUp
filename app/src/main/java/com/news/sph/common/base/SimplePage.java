package com.news.sph.common.base;

import com.news.sph.R;
import com.news.sph.demo.SimpleListDemoFragment;


public enum SimplePage{

    SIMPLE_LIST_TEST(1, R.string.demo_simple_title, SimpleListDemoFragment.class);

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
