package com.news.sph.home.fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.ptrrecyclerview.PtrRecyclerView;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.bean.ViewFlowBean;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.home.adapter.HomeSpecialAdapter;
import com.news.sph.home.adapter.MyViewPagerAdapter;
import com.news.sph.home.entity.HomeAdcerEntity;
import com.news.sph.home.entity.HomeAdcerResult;
import com.news.sph.home.entity.HomeRecomendResult;
import com.news.sph.home.entity.HomeRecommendEntity;
import com.news.sph.home.entity.HomeSpecialEntity;
import com.news.sph.home.entity.HomeSpecialResult;
import com.news.sph.home.utils.HomeUiGoto;
import com.news.sph.unused.fragment.UnusedFragment;
import com.news.sph.utils.LogUtils;
import com.news.sph.widget.ViewFlowLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页的fragment
 */
public class HomeFragment extends BaseListFragment<HomeSpecialResult> {

    LinearLayout mTopLl;

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
    ViewFlowLayout mVfLayout;
    private String mSpecSrc;
    List<HomeSpecialEntity> mSpecilaData;
    List<HomeRecommendEntity> mRecomData;
    PtrRecyclerView mRecyclerview;

    @Override
    public void initView(View view) {
//        mTopLl = (LinearLayout) view.findViewById(R.id.top_ll);
//        mTopLl.setVisibility(View.GONE);
        mRecyclerview = (PtrRecyclerView) view.findViewById(R.id.base_recyclerview);

        mVfLayout=new ViewFlowLayout(getActivity());
        mRecyclerview.addHeaderView(mVfLayout);

        mVfLayout.setOnItemClickListener(new ViewFlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });



//        mViewHomeFrame = (LinearLayout) view.findViewById(R.id.top_carousel);
//        mHomeImg1 = (ImageView) view.findViewById(R.id.home_img1);
//        mHomeImg3 = (ImageView) view.findViewById(R.id.home_img3);
//        mHomeImgDian = (ImageView) view.findViewById(R.id.home_img_dian);
//        mTopHomeXianXia = (LinearLayout) view.findViewById(R.id.top_home_xianxia);
//
//        mHomeImg1.setOnClickListener(this);
//        mHomeImg3.setOnClickListener(this);
//        mHomeImgDian.setOnClickListener(this);
//        mViewHomeFrame.setOnClickListener(this);
//        mTopHomeXianXia.setOnClickListener(this);

       // initPagerView(view);
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
                    mAdData = result.getData();
                    if(mAdData!=null&&mAdData.size()!=0) {
                        ArrayList<ViewFlowBean> list = new ArrayList<>();
                        for (int i = 0; i < mAdData.size(); i++) {
                            ViewFlowBean bean = new ViewFlowBean();
                            bean.setImgUrl(mAdData.get(i).getSpec_pic());
                            list.add(bean);
                        }
                        mVfLayout.updateView(list);
                    }
                }
            }
        });
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
        getQequest();
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
//        mSpecSrc = mSpecilaData.get(position).getSpec_src();
//        HomeUiGoto.special(getActivity(),mSpecSrc);
//        super.onItemClick(itemView, itemBean, position);
    }
}
