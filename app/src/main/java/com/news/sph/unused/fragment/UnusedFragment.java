package com.news.sph.unused.fragment;

import android.view.View;
import android.widget.TextView;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.unused.adapter.UnusedAdapter;
import com.news.sph.unused.entity.HotTopEntity;
import com.news.sph.unused.entity.HotTopResult;
import com.news.sph.unused.utils.UnusedUiGoto;
import com.news.sph.utils.LogUtils;

import java.io.Serializable;
import java.util.List;
/*
闲置的fragment
        */

public class UnusedFragment extends BaseListFragment<HotTopEntity> {
    TextView mTopTv;

    private String mName;
    private String mUrl;

    @Override
    public void initView(View view) {
        mTopTv = (TextView) view.findViewById(R.id.top_tv);
        mTopTv.setText("热门专题");
        super.initView(view);

    }

    @Override
    public BaseRecyclerAdapter<HotTopEntity> createAdapter() {
        return new UnusedAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "UnusedFragment";
    }

    @Override
    public List<HotTopEntity> readList(Serializable seri) {
        return ((HotTopResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        BaseDTO udto=new BaseDTO();
        udto.setPageSize(PAGE_SIZE);
        udto.setPageIndex(mCurrentPage);
        CommonApiClient.hotTopics(getActivity(), udto, new CallBack<HotTopResult>() {
            @Override
            public void onSuccess(HotTopResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("热门专题成功");
                }

            }
        });

    }



    @Override
    public void initData() {
        sendRequestData();
    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        mUrl = AppConfig.URL_SPECIAL;
        mName = "专题/广告详情";
        UnusedUiGoto.special(getActivity(),mUrl,mName);//   专题/广告详情页面
        super.onItemClick(itemView, itemBean, position);
    }
}
