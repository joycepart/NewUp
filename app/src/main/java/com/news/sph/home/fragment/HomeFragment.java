package com.news.sph.home.fragment;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BasePullFragment;
import com.news.sph.common.bean.ViewFlowBean;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.ImageLoaderUtils;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.common.widget.FullyLinearLayoutManager;
import com.news.sph.common.widget.ViewFlowLayout;
import com.news.sph.home.adapter.HorizontalScrollViewAdapter;
import com.news.sph.home.entity.HomeAdcerEntity;
import com.news.sph.home.entity.HomeAdcerResult;
import com.news.sph.home.entity.HomeRecomendResult;
import com.news.sph.home.entity.HomeSpecialEntity;
import com.news.sph.home.entity.HomeSpecialResult;
import com.news.sph.home.widget.MyHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 首页的fragment
 */
public class HomeFragment extends BasePullFragment {
    @Bind(R.id.vf_layout)
    ViewFlowLayout mVfLayout;
    @Bind(R.id.hsv)
    MyHorizontalScrollView mHsv;
    @Bind(R.id.special_list)
    RecyclerView mSpecialList;
    @Bind(R.id.nsv)
    NestedScrollView mScrollView;

    HorizontalScrollViewAdapter mHsvAdapter;
    BaseSimpleRecyclerAdapter mSpecialListAdapter;
    int reqCount;
    @Bind(R.id.img_lf)
    ImageView mImgLf;
    @Bind(R.id.img_rg)
    ImageView mImgRg;


    @Override
    public void setsetPtrHandler() {
        getPtf().setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                sendRequestData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (pulltoRefresh()) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame, mScrollView, header);
                }
                return pulltoRefresh();
            }
        });
    }

    //view_home_view
    @Override
    public void initView(View view) {
        super.initView(view);

        mVfLayout.setOnItemClickListener(new ViewFlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


            }
        });
        mHsvAdapter = new HorizontalScrollViewAdapter(getActivity());
        mImgLf.setOnClickListener(this);
        mImgRg.setOnClickListener(this);
        mSpecialList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mSpecialListAdapter=new BaseSimpleRecyclerAdapter<HomeSpecialEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_special;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, HomeSpecialEntity homeSpecialEntity, int position) {
                ImageView iv=holder.getView(R.id.iv);
                ImageLoaderUtils.displayImage(homeSpecialEntity.getSpec_pic(),iv);
            }


        };
        mSpecialList.setAdapter(mSpecialListAdapter);
    }

    private void reqAdver() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeAdcer(getActivity(), dto, new CallBack<HomeAdcerResult>() {
            @Override
            public void onSuccess(HomeAdcerResult result) {
                checkPullRefreshComplete();
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页广告成功");
                    List<HomeAdcerEntity> mAdDatas = result.getData();
                    if (mAdDatas != null && mAdDatas.size() != 0) {
                        ArrayList<ViewFlowBean> list = new ArrayList<>();
                        for (int i = 0; i < mAdDatas.size(); i++) {
                            ViewFlowBean bean = new ViewFlowBean();
                            bean.setImgUrl(AppConfig.BASE_URL + mAdDatas.get(i).getSpecPic());
                            list.add(bean);
                        }
                        mVfLayout.updateView(list);
                    }
                }
            }
        });
    }

    public void checkPullRefreshComplete() {
        reqCount++;
        if (reqCount == 3) {
            refreshComplete();
        }
    }



    private void reqRecommend() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeRecommend(getActivity(), dto, new CallBack<HomeRecomendResult>() {
            @Override
            public void onSuccess(HomeRecomendResult result) {
                checkPullRefreshComplete();
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页推荐成功");
                    mHsvAdapter.append(result.getData());
                    mHsv.initDatas(mHsvAdapter);
                }

            }
        });
    }


    private void reqSpecial() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeSpecial(getActivity(), dto, new CallBack<HomeSpecialResult>() {
            @Override
            public void onSuccess(HomeSpecialResult result) {
                checkPullRefreshComplete();
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页专题成功");
                    mSpecialListAdapter.removeAll();
                    mSpecialListAdapter.append(result.getData());
                }

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_lf:
                mHsv.smoothScrollBy(-mHsv.getScroolw(),0);
                break;
            case R.id.img_rg:
                mHsv.smoothScrollBy(mHsv.getScroolw(),0);
                break;
        }
        super.onClick(v);
    }


    @Override
    public void initData() {
        sendRequestData();
    }

    @Override
    protected void sendRequestData() {
        reqAdver();//首页广告
        reqRecommend();//首页推荐
        reqSpecial();//首页专题列表

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public boolean pulltoRefresh() {
        return true;
    }


}
