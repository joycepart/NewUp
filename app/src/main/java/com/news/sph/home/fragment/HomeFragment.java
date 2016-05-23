package com.news.sph.home.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.ptrrecyclerview.PtrRecyclerView;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BaseFragment;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.viewflow.CircleFlowIndicator;
import com.news.sph.common.viewflow.ViewFlow;
import com.news.sph.home.adapter.HomeSpecialAdapter;
import com.news.sph.home.adapter.MyViewPagerAdapter;
import com.news.sph.home.entity.HomeAdcerEntity;
import com.news.sph.home.entity.HomeAdcerResult;
import com.news.sph.home.entity.HomeRecomendResult;
import com.news.sph.home.entity.HomeRecommendEntity;
import com.news.sph.home.entity.HomeSpecialEntity;
import com.news.sph.home.entity.HomeSpecialResult;
import com.news.sph.home.utils.HomeUiGoto;
import com.news.sph.issue.entity.WinningResult;
import com.news.sph.unused.fragment.UnusedFragment;
import com.news.sph.utils.LayoutUtil;
import com.news.sph.utils.LogUtils;
import com.news.sph.utils.TDevice;
import com.news.sph.utils.ToastUtils;
import com.news.sph.utils.ViewScrollConflictUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 首页的fragment
 */
public class HomeFragment extends BaseListFragment<HomeSpecialResult> {

    LinearLayout mTopLl;
    PtrFrameLayout mPtrFrameHome;
    ViewFlow vf;
    /***
     * 顶部轮播控件
     **/
    CircleFlowIndicator indic;
    /***
     * 顶部轮播控件指针
     **/
    @Bind(R.id.home_list)
    ListView mHomeList;

    ViewPager mViewPager;
    ImageView mImgLeft, mImgRight;

    List<HomeAdcerEntity> mAdData;
    List<HomeRecommendEntity> mRecomendData;
    int[] mAdList = new int[]{};
    private List<View> views;
    private View mView;
    LinearLayout mTopHomeXianXia,mViewHomeFrame;
    ImageView mHomeImg1, mHomeImg3, mHomeImgDian;
    private String mUrlSpecial;
    private String mSpecialTitle;

    private String mUrlOffline;
    private String mOfflineTitle;
    private String mUrlHelp;
    private String mHelpTitle;

    private String mSpecSrc;
    List<HomeSpecialEntity> mSpecilaData;
    List<HomeRecommendEntity> mRecomData;
    PtrRecyclerView mRecyclerview;


//    int[] mImgList = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};


    @Override
    public void initView(View view) {
        mTopLl = (LinearLayout) view.findViewById(R.id.top_ll);
        mTopLl.setVisibility(View.GONE);
        mRecyclerview = (PtrRecyclerView) view.findViewById(R.id.base_recyclerview);

        View header = LayoutInflater.from(getActivity()).inflate(
                R.layout.view_home_viewflow, null);
        initTopView(header);

        mRecyclerview.addHeaderView(header);

        getQequest();

        mViewHomeFrame = (LinearLayout) view.findViewById(R.id.top_carousel);
        mHomeImg1 = (ImageView) view.findViewById(R.id.home_img1);
        mHomeImg3 = (ImageView) view.findViewById(R.id.home_img3);
        mHomeImgDian = (ImageView) view.findViewById(R.id.home_img_dian);
        mTopHomeXianXia = (LinearLayout) view.findViewById(R.id.top_home_xianxia);

        mHomeImg1.setOnClickListener(this);
        mHomeImg3.setOnClickListener(this);
        mHomeImgDian.setOnClickListener(this);
        mViewHomeFrame.setOnClickListener(this);
        mTopHomeXianXia.setOnClickListener(this);


    }

    @Override
    public BaseRecyclerAdapter<HomeSpecialResult> createAdapter() {
        return new HomeSpecialAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "HomeFragment";
    }

    @Override
    public List<HomeSpecialResult> readList(Serializable seri) {
        return null;
    }

    @Override
    protected void sendRequestData() {

    }

    private void getQequest() {
        homeAdver();//首页广告
        homeSpecial();//首页专题列表
    }

    private void homeRecommend() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeRecommend(getActivity(), dto, new CallBack<HomeRecomendResult>() {
            @Override
            public void onSuccess(HomeRecomendResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页推荐成功");
                    homeRecomendResult(result);

                }

            }
        });
    }

    private void homeRecomendResult(HomeRecomendResult result) {
        mRecomendData = result.getData();
    }

    private void homeAdver() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeAdcer(getActivity(), dto, new CallBack<HomeAdcerResult>() {
            @Override
            public void onSuccess(HomeAdcerResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页广告成功");
                    homeAdcerResult(result);

                }

            }
        });
    }

    private void homeAdcerResult(HomeAdcerResult data) {
        mAdData = data.getData();

    }

    private void homeSpecial() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeSpecial(getActivity(), dto, new CallBack<HomeSpecialResult>() {
            @Override
            public void onSuccess(HomeSpecialResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页专题成功");
                    homeSpecialResult(result);

                }

            }
        });
    }

    private void homeSpecialResult(HomeSpecialResult result) {
        mSpecilaData = result.getData();
        result.setData(mSpecilaData);
        mAdapter.append(result);

    }

    @Override
    public void initData() {

    }


    public void initTopView(View view) {
        vf = (ViewFlow) view.findViewById(R.id.vf_top);
        indic = (CircleFlowIndicator) view.findViewById(R.id.vfi_top);
        LayoutUtil.reMesureHeight(getActivity(), vf,
                TDevice.getDisplayMetrics(getActivity()).widthPixels,
                280, 540);
        vf.setOnTouchListener(new ViewScrollConflictUtil(vf));

        Adapter topAdapter = new BaseAdapter() {

            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public Object getItem(int position) {
                return mAdList[position % mAdList.length];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder vh;
                if (convertView == null) {
                    vh = new ViewHolder();
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_flow, null);
                    vh.iv = (ImageView) convertView.findViewById(R.id.iv);
                    convertView.setTag(vh);
                } else {
                    vh = (ViewHolder) convertView.getTag();
                }
                vh.iv.setImageDrawable(ContextCompat.getDrawable(getContext(), (int) getItem(position)));
                return convertView;
            }

            class ViewHolder {
                ImageView iv;
            }
        };
        vf.setAdapter(topAdapter);
        vf.setmSideBuffer(mAdList.length);//实际图片张数
        vf.setFlowIndicator(indic);
        indic.requestLayout();
        indic.invalidate();
        vf.setTimeSpan(4000);//设置轮播时间间隔
        vf.setSelection(5 * 1000); // 设置初始位置,这里让viewflow的item位置放到5000,可以循环滑动
        vf.startAutoFlowTimer(); // 启动自动播放

        initPagerView(view);



    }

    private void initPagerView(View view) {

        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mImgLeft = (ImageView) view.findViewById(R.id.home_img_lf);
        mImgRight = (ImageView) view.findViewById(R.id.home_img_rg);
        mImgLeft.setOnClickListener(this);
        mImgRight.setOnClickListener(this);
        views = new ArrayList<View>();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        mView = inflater.inflate(R.layout.item_home_viewpager, null);
        homeRecommend();
        for (int i = 0; i < mRecomendData.size(); i++) {
            switch (i % 3) {
                case 0: {
                    break;
                }
                case 1: {
                    //第二小块
                    break;
                }
                case 2: {
                    //第三小块
                    views.add(mView);
                    break;
                }
            }
            if (i == mRecomendData.size() - 1&&(i+1)%3!=0) {
                views.add(mView);
            }
        }
        mViewPager.setAdapter(new MyViewPagerAdapter(views));
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        public void onPageScrollStateChanged(int arg0) {


        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        public void onPageSelected(int arg0) {

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_home_xianxia:
                mUrlOffline = AppConfig.URL_OFFLINE;
                mOfflineTitle = "线下门店 - 倾奢";
                HomeUiGoto.OfflineStore(getActivity(), mUrlOffline, mOfflineTitle);
                break;
            case R.id.home_img1:
                HomeUiGoto.curing(getActivity());
                break;
            case R.id.home_img3:
                mUrlHelp = AppConfig.URL_TRANSACTION;
                mHelpTitle = "交易帮助 - 倾奢";
                HomeUiGoto.help(getActivity(), mUrlSpecial, mSpecialTitle);
                break;
//            case R.id.view_home_frame:
//                mUrlSpecial = AppConfig.URL_SPECIAL;
//                mSpecialTitle= "专题/广告详情";
//                HomeUiGoto.special(getActivity(),mUrlSpecial,mSpecialTitle);
//                break;
            case R.id.home_img_lf:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);

                break;
            case R.id.home_img_rg:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                break;
            case R.id.home_img_dian:
                UnusedFragment unusedFragment = new UnusedFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.realtabcontent, unusedFragment);
                transaction.commit();
                break;
            default:
                break;

        }
        super.onClick(v);
    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        mSpecSrc = mSpecilaData.get(position).getSpec_src();
        HomeUiGoto.special(getActivity(),mSpecSrc);
        super.onItemClick(itemView, itemBean, position);
    }
}
