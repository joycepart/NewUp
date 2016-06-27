package com.qluxstory.qingshe.curing.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qluxstory.qingshe.curing.fragment.CuringFragment;

import java.util.List;

/**
 * Created by lenovo on 2016/6/24.
 */
public class CuringTabListAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private CuringFragment context;
    private List<Fragment> fragmentList;


    public CuringTabListAdapter(FragmentManager fm, CuringFragment context, String[] titles,
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
