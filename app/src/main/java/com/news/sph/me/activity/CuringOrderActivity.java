package com.news.sph.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.common.adapter.TabListAdapter;
import com.news.sph.me.fragment.CuringOrderListFragment;
import com.news.sph.widget.SlidingTabLayout;

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
    @Bind(R.id.base_titlebar_ensure)
    TextView mBaseTitlebarEnsure;
    private TabListAdapter mAdapter;
    private List<Fragment> fragmentList;
    @Bind(R.id.curing_tab)
    SlidingTabLayout mCuringTab;
    @Bind(R.id.curing_content)
    ViewPager mCuringContent;



    @Override
    public void initView() {
        setTitleText("养护订单列表");
        mBaseTitlebarEnsure.setVisibility(View.GONE);

        fragmentList = new ArrayList<Fragment>();
//        fragmentList.add(CuringOrderListFragment.newInstance(TAB_B));
        fragmentList.add(CuringOrderListFragment.newInstance(TAB_D));
        fragmentList.add(CuringOrderListFragment.newInstance(TAB_D));
        fragmentList.add(CuringOrderListFragment.newInstance(TAB_D));
        String titles[] = getResources().getStringArray(R.array.curing_major_tab);
        mAdapter = new TabListAdapter(getSupportFragmentManager(),this,titles,fragmentList);
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
