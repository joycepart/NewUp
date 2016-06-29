package com.qluxstory.qingshe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.qluxstory.qingshe.common.base.BaseFragment;
import com.qluxstory.qingshe.common.base.BaseHomeTitleActivity;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TextViewUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.curing.fragment.CuringFragment;
import com.qluxstory.qingshe.home.fragment.HomeFragment;
import com.qluxstory.qingshe.issue.fragment.IssueFragment;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.fragment.MeFragment;
import com.qluxstory.qingshe.special.fragment.UnusedFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/*
整个程序的MainActivity，入口
*/

public class MainActivity extends BaseHomeTitleActivity {

    public static final int TAB_NUM = 5;
//    private TextView mBaseEnsure, mBaseBack;

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
    boolean bool;

    public LocationClient mLocationClient = null;

    private TextView[] mTabViews = new TextView[TAB_NUM];
    private FragmentManager fragmentManager;
    private List<BaseFragment> fragmentList=new ArrayList<>();
    /**
     * Tab图片没有选中的状态资源ID
     */
    private int[] mTabIconNors = {
            R.mipmap.tab_home_n,
            R.mipmap.tab_qs_n,
            R.mipmap.tab_yun_n,
            R.mipmap.tab_zt_n,
            R.mipmap.tab_me_n};
    /**
     * Tab图片选中的状态资源ID
     */
    private int[] mTabIconSels = {
            R.mipmap.tab_home_h,
            R.mipmap.tab_qs_h,
            R.mipmap.tab_yun_h,
            R.mipmap.tab_zt_h,
            R.mipmap.tab_me_h};


    private int currentTab=-1; // 当前Tab页面索引

    private LinearLayout mBaseEnsure,mBaseBack;


    @Override
    public void initView() {

        mBaseEnsure = (LinearLayout) findViewById(R.id.base_home_titlebar_back);
        mBaseEnsure.setOnClickListener(this);
        mBaseBack = (LinearLayout) findViewById(R.id.base_home_titlebar_ensure);
        mBaseBack.setOnClickListener(this);

        bool = AppContext.get("isLogin",false);
        LogUtils.e("bool---",""+bool);
        requestLocationInfo();//发请定位
        registerMessageReceiver();  // used for receive msg
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
                fragment = new CuringFragment();
                break;
            case 2:
                fragment = new IssueFragment();
                break;
            case 3:
                fragment = new UnusedFragment();
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
        LogUtils.e("intent-----",intent+"");
        if(intent != null) {
            if(intent.getExtras()!=null){
                int tag = intent.getExtras().getInt("tag");
                LogUtils.e("tag-----",tag+"");
                showTab(tag);
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
                mTabViews[i].setTextColor(ContextCompat.getColor(this, R.color.color_3c));
                TextViewUtils.setTextViewIcon(this, mTabViews[i],
                        mTabIconSels[i], R.dimen.bottom_tab_icon_width,
                        R.dimen.bottom_tab_icon_height, TextViewUtils.DRAWABLE_TOP);
            } else {
                mTabViews[i].setTextColor(ContextCompat.getColor(this, R.color.color_f5));
                TextViewUtils.setTextViewIcon(this, mTabViews[i],
                        mTabIconNors[i], R.dimen.bottom_tab_icon_width,
                        R.dimen.bottom_tab_icon_height, TextViewUtils.DRAWABLE_TOP);
            }
        }
        currentTab = idx; // 更新目标tab为当前tab
        getTitleLayout().setVisibility(View.VISIBLE);
//        mBaseBack = (TextView) findViewById(R.id.base_titlebar_back);
//        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        getTitleLayout().setVisibility(View.VISIBLE);
        switch (currentTab){
            case 0:
                setTitleText("首页");

                break;
            case 1:
                setTitleText("专业养护");
                break;
            case 2:
                setTitleText("云购");
                break;
            case 3:
                setTitleText("热门专题");
                break;
            case 4:
                getTitleLayout().setVisibility(View.GONE);
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
            LogUtils.e("LOFIN_REQUEST----","mPictruePath:"+AppContext.get("mPictruePath","")+"mUserName:"+AppContext.get("mUserName",""));
            FragmentManager fragmentManager = getSupportFragmentManager();
            MeFragment meFragment = (MeFragment)fragmentManager.findFragmentByTag("tag4");
            meFragment.initView(null);
        }
        if(requestCode == MeUiGoto.USERINFORMATION_REQUEST)
        {
            LogUtils.e("USERINFORMATION_REQUEST----","mPictruePath:"+AppContext.get("mPictruePath","")+"mUserName:"+AppContext.get("mUserName",""));
            FragmentManager fragmentManager = getSupportFragmentManager();
            MeFragment meFragment = (MeFragment)fragmentManager.findFragmentByTag("tag4");
            meFragment.initView(null);
        }
    }


    /**
     * 发起定位
     */
    public void requestLocationInfo(){
        setLocationOption();
        if (mLocationClient != null && !mLocationClient.isStarted()){
            mLocationClient.start();
        }
        if (mLocationClient != null && mLocationClient.isStarted()){
            mLocationClient.requestLocation();
        }
    }

    /**
     * 设置相关参数
     */
    private void setLocationOption(){
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener( new MyLocationListener());    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);        //是否打开GPS
        option.setCoorType("bd09ll");       //设置返回值的坐标类型。
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setProdName("LocationDemo"); //设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
        mLocationClient.setLocOption(option);
    }

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                LogUtils.e("location_failed----","location_failed");
                return;
            } else {
                int locType = location.getLocType();
                LogUtils.i("locType:",""+locType);
                LogUtils.i("province:","" + location.getProvince());
                LogUtils.i("city:" ,""+ location.getCity());
                LogUtils.i("district:" ,""+ location.getDistrict());
                LogUtils.i("AddrStr:" ,""+ location.getAddrStr());
                String city = location.getCity();
                String province = location.getProvince();
                String district = location.getDistrict();
                if (TextUtils.isEmpty(city)) {
                    LogUtils.e("locType----","定位失败");
                    mLocationClient.start();
                } else {
                    AppContext.set("mCity",city);
                    AppContext.set("mProvince",province);
                    AppContext.set("mDistrict",district);
                    mLocationClient.stop();
                }
            }

        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
//            case R.id.base_titlebar_back:
//                baseGoBack();
//                break;
//            case R.id.base_titlebar_ensure:
//                baseGoEnsure();
//                break;
            case R.id.base_home_titlebar_back:
                baseGoBack();
                break;
            case R.id.base_home_titlebar_ensure:
                baseGoEnsure();
                break;
            default:
                break;
        }
    }
    /**
     * 左侧的事件
     */
    protected void baseGoBack() {
        LogUtils.e("baseGoBack---","baseGoBack");
        bool = AppContext.get("isLogin",false);
        if(bool){
            MeUiGoto.myIntegral(this);//我的积分
        }else {
            MeUiGoto.login(this);//登录
        }
    }

    /**
     * 右侧的事件
     */
    protected void baseGoEnsure() {
        LogUtils.e("baseGoEnsure---","baseGoEnsure");
        bool = AppContext.get("isLogin",false);
        if(bool){
            UIHelper.showFragment(this, SimplePage.INFORMATION);
        }else {
            MeUiGoto.login(this);//登录
        }

    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppContext.set("mCity","");
        AppContext.set("mProvince","");
        AppContext.set("mDistrict","");
        unregisterReceiver(mMessageReceiver);
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stop();
            mLocationClient = null;
        }
    }

    public static boolean isForeground = false;
    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!TextUtils.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
//                setCostomMsg(showMsg.toString());
            }
        }
    }

//    private void setCostomMsg(String msg){
//        if (null != msgText) {
//            msgText.setText(msg);
//            msgText.setVisibility(android.view.View.VISIBLE);
//        }
//    }


}
