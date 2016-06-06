package com.qluxstory.qingshe;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.qluxstory.qingshe.common.base.BaseFragment;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TextViewUtils;
import com.qluxstory.qingshe.home.HomeUiGoto;
import com.qluxstory.qingshe.home.fragment.HomeFragment;
import com.qluxstory.qingshe.information.fragment.InformationFragment;
import com.qluxstory.qingshe.issue.fragment.IssueFragment;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.fragment.MeFragment;
import com.qluxstory.qingshe.unused.fragment.UnusedFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/*
整个程序的MainActivity，入口
*/

public class MainActivity extends BaseTitleActivity {

    public static final int TAB_NUM = 5;
    private TextView mBaseEnsure, mBaseBack;

    @Bind(R.id.tv_tab_home)
    TextView mTvTabHome;
    @Bind(R.id.tv_tab_unused)
    TextView mTvTabUnused;
    @Bind(R.id.tv_tab_information)
    TextView mTvTabInformatin;
    @Bind(R.id.tv_tab_mine)
    TextView mTvTabMine;
    @Bind(R.id.tv_tab_issue)
    TextView mTvTabIssue;

    private TextView[] mTabViews = new TextView[TAB_NUM];
    private FragmentManager fragmentManager;
    private List<BaseFragment> fragmentList=new ArrayList<>();
    /**
     * Tab图片没有选中的状态资源ID
     */
    private int[] mTabIconNors = {
            R.mipmap.tab_home_n,
            R.mipmap.tab_unused_n,
            R.mipmap.tab_issue_n,
            R.mipmap.tab_information_n,
            R.mipmap.tab_me_n};
    /**
     * Tab图片选中的状态资源ID
     */
    private int[] mTabIconSels = {
            R.mipmap.tab_home_h,
            R.mipmap.tab_unused_h,
            R.mipmap.tab_issue_icon,
            R.mipmap.tab_information_h,
            R.mipmap.tab_me_h};


    private int currentTab=-1; // 当前Tab页面索引


    @Override
    public void initView() {
        fragmentManager = getSupportFragmentManager();
        mTabViews[0] = mTvTabHome;
        mTabViews[1] = mTvTabUnused;
        mTabViews[2] = mTvTabIssue;
        mTabViews[3] = mTvTabInformatin;
        mTabViews[4] = mTvTabMine;

        for (int i = 0; i < mTabViews.length; i++) {
            fragmentList.add(null);
            final int j = i;
            mTabViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTab(j);
                }
            });
        }
        showTab(0); // 显示目标tab
    }

    /**
     *
     * @param fragment 除了fragment，其他的都hide
     */
    private void hideAllFragments(BaseFragment fragment) {
        for (int i = 0; i < TAB_NUM; i++) {
            Fragment f = fragmentManager.findFragmentByTag("tag" + i);
            if (f != null&&f.isAdded()&&f!=fragment) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.hide(f);
                transaction.commitAllowingStateLoss();
                f.setUserVisibleHint(false);
            }
        }
    }

    private BaseFragment addFragment(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        BaseFragment fragment = null;
        switch (index) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new UnusedFragment();
                break;
            case 2:
                fragment = new IssueFragment();
                break;
            case 3:
                fragment = new InformationFragment();
                break;
            case 4:
                fragment = new MeFragment();
                break;
        }
        fragmentList.add(index,fragment);
        transaction.add(R.id.realtabcontent, fragment, "tag" + index);
        transaction.commitAllowingStateLoss();
       // fragmentManager.executePendingTransactions();
        return fragment;
    }


    private void showFragment(BaseFragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commitAllowingStateLoss();
        fragment.setUserVisibleHint(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if(intent != null) {
            int tag = intent.getExtras().getInt("tag");
            LogUtils.e("tag-----",tag+"");
            if(Integer.valueOf(tag)!=null){
                showTab(tag);
            }else {
                return;
            }

        }
    }

    /**
     * 切换tab
     *
     * @param idx
     */
    private void showTab(int idx) {
        if(currentTab==idx){return;}
        BaseFragment targetFragment = (BaseFragment) fragmentManager
                .findFragmentByTag("tag" + idx);
        if (targetFragment == null || !targetFragment.isAdded()) {
            if(idx<fragmentList.size()&&fragmentList.get(idx)!=null) {
                targetFragment = fragmentList.get(idx);
            }else{
                targetFragment=addFragment(idx);
            }
        }
        hideAllFragments(targetFragment);
        showFragment(targetFragment);
        for (int i = 0; i < TAB_NUM; i++) {
            if (idx == i) {
                mTabViews[i].setTextColor(ContextCompat.getColor(this, R.color.color_c0));
                TextViewUtils.setTextViewIcon(this, mTabViews[i],
                        mTabIconSels[i], R.dimen.bottom_tab_icon_width,
                        R.dimen.bottom_tab_icon_height, TextViewUtils.DRAWABLE_TOP);
            } else {
                mTabViews[i].setTextColor(ContextCompat.getColor(this, R.color.color_00));
                TextViewUtils.setTextViewIcon(this, mTabViews[i],
                        mTabIconNors[i], R.dimen.bottom_tab_icon_width,
                        R.dimen.bottom_tab_icon_height, TextViewUtils.DRAWABLE_TOP);
            }
        }
        currentTab = idx; // 更新目标tab为当前tab
        getTitleLayout().setVisibility(View.VISIBLE);
        mBaseBack = (TextView) findViewById(R.id.base_titlebar_back);
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        getTitleLayout().setVisibility(View.VISIBLE);
        switch (currentTab){
            case 0:
                setTitleText("首页");
                setEnsureText("线下门店");
                mBaseBack.setVisibility(View.GONE);
                mBaseEnsure.setVisibility(View.VISIBLE);
                TextViewUtils.setTextViewIcon(this, mBaseEnsure, R.drawable.home_door,
                        R.dimen.common_titlebar_icon_width,
                        R.dimen.common_titlebar_icon_height, TextViewUtils.DRAWABLE_TOP);
                mBaseEnsure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HomeUiGoto.OfflineStore(getApplication(),AppConfig.URL_OFFLINE,"线下门店 - 倾奢");
                    }
                });

                break;
            case 1:
                setTitleText("热门专题");
                mBaseBack.setVisibility(View.GONE);
                mBaseEnsure.setVisibility(View.GONE);
                break;
            case 2:
                setTitleText("夺宝岛");
                mBaseBack.setVisibility(View.GONE);
                mBaseEnsure.setVisibility(View.GONE);
                break;
            case 3:
                setTitleText("系统通知");
                mBaseBack.setVisibility(View.GONE);
                mBaseEnsure.setVisibility(View.GONE);
                break;
            case 4:
                getTitleLayout().setVisibility(View.GONE);
                mBaseEnsure.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public void initData() {

    }


    public int getCurrentTab() {
        return currentTab;
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MeUiGoto.LOFIN_REQUEST&&resultCode==1001)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            MeFragment meFragment = (MeFragment)fragmentManager.findFragmentByTag("tag4");
            meFragment.initView(null);
        }
        if(requestCode == MeUiGoto.USERINFORMATION_REQUEST&&resultCode==1002)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            MeFragment meFragment = (MeFragment)fragmentManager.findFragmentByTag("tag4");
            meFragment.initView(null);
        }
    }
}
