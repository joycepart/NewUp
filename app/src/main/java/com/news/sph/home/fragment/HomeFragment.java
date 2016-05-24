package com.news.sph.home.fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.PtrRecyclerView;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BaseFragment;
import com.news.sph.common.bean.ViewFlowBean;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.home.adapter.HomeRecyclerAdapter;
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
import com.news.sph.utils.ImageLoaderUtils;
import com.news.sph.utils.LogUtils;
import com.news.sph.widget.ViewFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页的fragment
 */
public class HomeFragment extends BaseFragment {

    PtrRecyclerView mRecyclerview;
    ViewFlowLayout mVfLayout;

    private HomeRecyclerAdapter mHomeRecycler;

    ViewPager mViewPager;
    ImageView mImgLeft, mImgRight, mVpagerImg1, mVpagerImg2, mVpagerImg3;
    TextView mVpagerTv1, mVpagerTv2, mVpagerTv3, mVpagerTvt1, mVpagerTvt2, mVpagerTvt3;
    private HomeSpecialAdapter specialAdapter;
    List<HomeAdcerEntity> mAdData;
    List<HomeRecommendEntity> mRecomendData;
    List<HomeSpecialEntity> mHomeSpecial;
    private List<View> views;
    private View mView;

    private String mUrlSpecial;
    private String mSpecialTitle;

    private String mUrlOffline;
    private String mOfflineTitle;
    private String mUrlHelp;
    private String mHelpTitle;

    private String mSpecSrc;

    View header;

    private BaseRecyclerAdapter mBaseRecycler = new BaseRecyclerAdapter() {
        @Override
        public void bindData(BaseRecyclerViewHolder holder, Object itemBean, int position, int viewType) {

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }
    };
    @Override
    public void initView(View view) {
        mRecyclerview = (PtrRecyclerView) view.findViewById(R.id.base_recyclerview);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mVfLayout = new ViewFlowLayout(getActivity());
        ViewFlowLayout.LayoutParams lp = new ViewFlowLayout.LayoutParams(ViewFlowLayout.LayoutParams.MATCH_PARENT, ViewFlowLayout.LayoutParams.WRAP_CONTENT);
        mVfLayout.setLayoutParams(lp);
         header = LayoutInflater.from(getActivity()).inflate(
                R.layout.view_home_view, mRecyclerview,false);
        initHeader(header);
        homeSpecial();
        mRecyclerview.setAdapter(mBaseRecycler);
        mVfLayout.setOnItemClickListener(new ViewFlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mUrlSpecial = AppConfig.URL_TEMPLATE;
                mSpecialTitle = "暂无标题";
                HomeUiGoto.productDetails(getActivity(), mUrlSpecial, mSpecialTitle);

            }
        });


    }

    private void homeAdver() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeAdcer(getActivity(), dto, new CallBack<HomeAdcerResult>() {
            @Override
            public void onSuccess(HomeAdcerResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页广告成功");
                    mAdData = result.getData();
                    if (mAdData != null && mAdData.size() != 0) {
                        ArrayList<ViewFlowBean> list = new ArrayList<>();
                        for (int i = 0; i < mAdData.size(); i++) {
                            ViewFlowBean bean = new ViewFlowBean();
                            bean.setImgUrl(AppConfig.BASE_URL + mAdData.get(i).getmSpecPic());
                            list.add(bean);
                        }
                        mVfLayout.updateView(list);
                        mRecyclerview.addHeaderView(mVfLayout);
                        mRecyclerview.addHeaderView(header);
                    }
                }
            }
        });
    }

    private void initHeader(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mImgLeft = (ImageView) view.findViewById(R.id.home_img_lf);
        mImgRight = (ImageView) view.findViewById(R.id.home_img_rg);
        mImgLeft.setOnClickListener(this);
        mImgRight.setOnClickListener(this);


    }


    private void homeRecommend() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeRecommend(getActivity(), dto, new CallBack<HomeRecomendResult>() {
            @Override
            public void onSuccess(HomeRecomendResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页推荐成功");
//                    setRecomendResult(result.getData());

                }

            }
        });
    }

    private void setRecomendResult(List<HomeRecommendEntity> result) {

        views = new ArrayList<View>();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        mView = inflater.inflate(R.layout.item_home_viewpager, null);
        mVpagerImg1 = (ImageView) mView.findViewById(R.id.home_vpager_img1);
        mVpagerImg2 = (ImageView) mView.findViewById(R.id.home_vpager_img2);
        mVpagerImg3 = (ImageView) mView.findViewById(R.id.home_vpager_img3);
        mVpagerTv1 = (TextView) mView.findViewById(R.id.home_vpager_tv1);
        mVpagerTvt1 = (TextView) mView.findViewById(R.id.home_vpager_tv01);
        mVpagerTv2 = (TextView) mView.findViewById(R.id.home_vpager_tv2);
        mVpagerTvt2 = (TextView) mView.findViewById(R.id.home_vpager_tv02);
        mVpagerTv3 = (TextView) mView.findViewById(R.id.home_vpager_tv3);
        mVpagerTvt3 = (TextView) mView.findViewById(R.id.home_vpager_tv03);
        if (result != null && result.size() != 0) {

            for (int i = 0; i < result.size(); i++) {

                switch (i % 3) {
                    case 0: {
                        mVpagerTv1.setText(result.get(i).getmSnaRemark());
                        mVpagerTvt1.setText(result.get(i).getmSnaTerm());
                        String mPicUrl = AppConfig.BASE_URL + result.get(i).getmPicUrl();
                        ImageLoaderUtils.displayImage(mPicUrl, mVpagerImg1);
                        break;
                    }
                    case 1: {
                        mVpagerTv2.setText(result.get(i).getmSnaRemark());
                        mVpagerTvt2.setText(result.get(i).getmSnaTerm());
                        String mPicUrl = AppConfig.BASE_URL + result.get(i).getmPicUrl();
                        ImageLoaderUtils.displayImage(mPicUrl, mVpagerImg2);
                        break;
                    }
                    case 2: {
                        mVpagerTv3.setText(result.get(i).getmSnaRemark());
                        mVpagerTvt3.setText(result.get(i).getmSnaTerm());
                        String mPicUrl = AppConfig.BASE_URL + result.get(i).getmPicUrl();
                        ImageLoaderUtils.displayImage(mPicUrl, mVpagerImg3);
                        views.add(mView);
                        break;
                    }
                }
                if (i == mRecomendData.size() - 1 && (i + 1) % 3 != 0) {
                    views.add(mView);
                }
            }
        }

        mViewPager.setAdapter(new MyViewPagerAdapter(views));
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());

    }


    private void homeSpecial() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeSpecial(getActivity(), dto, new CallBack<HomeSpecialResult>() {
            @Override
            public void onSuccess(HomeSpecialResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页专题成功");
//                    homeSpecialResult(result.getData());

                }

            }
        });
    }

    private void homeSpecialResult(List<HomeSpecialEntity> result) {
        if (result != null && result.size() != 0) {
            ArrayList<HomeSpecialEntity> list = new ArrayList<>();
            for (int i = 0; i < mAdData.size(); i++) {
                HomeSpecialEntity special = new HomeSpecialEntity();
                special.setmSpecPic(mAdData.get(i).getmSpecPic());
                list.add(special);
            }
//            mRecyclerview.setAdapter(new HomeSpecialAdapter(getActivity(),list));//这里是走mRecyclerview的adapter走自己定义的？
//            specialAdapter.addContent(list);
//            specialAdapter.notifyDataSetChanged();

        }
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
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);

                break;
            case R.id.home_img_rg:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
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
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        homeAdver();//首页广告
//            homeSpecial();//首页专题列表
            homeRecommend();//首页推荐
    }


}
