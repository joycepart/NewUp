package com.qluxstory.qingshe.curing.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseFragment;
import com.qluxstory.qingshe.common.widget.SlidingTabLayout;
import com.qluxstory.qingshe.curing.adapter.CuringTabListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 专业养护的fragment
 */
public class CuringFragment extends BaseFragment {
    public static final int TAB_A = 1;
    public static final int TAB_B = 2;
    public static final int TAB_C = 3;
    @Bind(R.id.cur_tab)
    SlidingTabLayout mCurTab;
    @Bind(R.id.cur_content)
    ViewPager mCurContent;
    private CuringTabListAdapter mAdapter;
    private List<Fragment> fragmentList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void initView(View view) {
        fragmentList = new ArrayList<Fragment>();
//        fragmentList.add(MajorCuringFragment.newInstance(TAB_A));
//        fragmentList.add(MajorCuringFragment.newInstance(TAB_B));
//        fragmentList.add(MajorCuringFragment.newInstance(TAB_C));

        fragmentList.add(MajorNewFragment.newInstance(TAB_A));
        fragmentList.add(MajorNewFragment.newInstance(TAB_B));
        fragmentList.add(MajorNewFragment.newInstance(TAB_C));

        String titles[] = getResources().getStringArray(R.array.curing_major_tab);
        mAdapter = new CuringTabListAdapter(getChildFragmentManager(), this, titles, fragmentList);
        mCurContent.setAdapter(mAdapter);
        mCurContent.setOffscreenPageLimit(fragmentList.size());
        mCurTab.setSelectedIndicatorColors(getResources().getColor(R.color.color_00));
        mCurTab.setDistributeEvenly(true);
        mCurTab.setViewPager(mCurContent);


    }

    @Override
    public void initData() {

    }

    @Override
    protected void retry() {

    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_curing;
    }

}
