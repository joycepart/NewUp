package com.qluxstory.qingshe.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qluxstory.qingshe.me.activity.CuringOrderActivity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/17.
 */
public class TabListAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private CuringOrderActivity context;
    private List<Fragment> fragmentList;


    public TabListAdapter(FragmentManager fm, CuringOrderActivity context, String[] titles,
                          List<Fragment> fragmentList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
