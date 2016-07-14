package com.qluxstory.qingshe.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.me.adapter.CuringOrderTabListAdapter;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.widget.SlidingTabLayout;
import com.qluxstory.qingshe.me.fragment.CuringOrderNewListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 养护订单主页面
 */
public class CuringOrderActivity extends BaseTitleActivity {
    public static final int TAB_A = 1;
    public static final int TAB_B = 2;
    public static final int TAB_C = 3;
    public static final int TAB_D = 4;
    @Bind(R.id.curing_tab)
    SlidingTabLayout mCuringTab;
    @Bind(R.id.curing_content)
    ViewPager mCuringContent;
    private CuringOrderTabListAdapter mAdapter;
    private List<Fragment> fragmentList;

    @Override
    public void initView() {
        setTitleText("养护订单列表");
        fragmentList = new ArrayList<Fragment>();
//        fragmentList.add(CuringOrderListFragment.newInstance(TAB_A));
//        fragmentList.add(CuringOrderListFragment.newInstance(TAB_B));
//        fragmentList.add(CuringOrderListFragment.newInstance(TAB_C));
//        fragmentList.add(CuringOrderListFragment.newInstance(TAB_D));

        fragmentList.add(CuringOrderNewListFragment.newInstance(TAB_A));
        fragmentList.add(CuringOrderNewListFragment.newInstance(TAB_B));
        fragmentList.add(CuringOrderNewListFragment.newInstance(TAB_C));
        fragmentList.add(CuringOrderNewListFragment.newInstance(TAB_D));
        String titles[] = getResources().getStringArray(R.array.curing_order_tab);
        mAdapter = new CuringOrderTabListAdapter(getSupportFragmentManager(),this,titles,fragmentList);
        mCuringContent.setAdapter(mAdapter);
        mCuringContent.setOffscreenPageLimit(fragmentList.size());
        mCuringTab.setSelectedIndicatorColors(getResources().getColor(R.color.color_33));
        mCuringTab.setDistributeEvenly(true);
        mCuringTab.setViewPager(mCuringContent);

    }

    @Override
    public void initData() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            default:
                break;
        }
        super.onClick(v);
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_me_curingorder;
    }
}
