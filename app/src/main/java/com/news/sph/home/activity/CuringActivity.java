package com.news.sph.home.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.common.adapter.TabListAdapter;
import com.news.sph.home.fragment.CuringFragment;
import com.news.sph.common.widget.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 专业养护主页面
 */
public class CuringActivity extends BaseTitleActivity {
    public static final int TAB_A = 1;
    public static final int TAB_B = 2;
    public static final int TAB_C = 3;
    @Bind(R.id.base_titlebar_ensure)
    TextView mBaseTitlebarEnsure;
    @Bind(R.id.cur_tab)
    SlidingTabLayout mCurTab;
    @Bind(R.id.cur_content)
    ViewPager mCurContent;
    private TabListAdapter mAdapter;
    private List<Fragment> fragmentList;


    @Override
    public void initView() {
        setTitleText("专业养护");
        mBaseTitlebarEnsure.setVisibility(View.GONE);

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(CuringFragment.newInstance(TAB_A));
        fragmentList.add(CuringFragment.newInstance(TAB_B));
        fragmentList.add(CuringFragment.newInstance(TAB_C));
        String titles[] = getResources().getStringArray(R.array.curing_major_tab);
        mAdapter = new TabListAdapter(getSupportFragmentManager(),this,titles, fragmentList);
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
        return R.layout.activity_curing;
    }
}
