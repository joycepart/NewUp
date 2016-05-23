package com.news.sph.unused.fragment;

import android.view.View;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.sph.AppConfig;
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

public class UnusedFragment extends BaseListFragment<HotTopResult> {

    private String mSpecPic;
    private String mSpecSrc;
    List<HotTopEntity> data;

    @Override
    public void initView(View view) {
       // setTitleText("热门专题");
        super.initView(view);

    }

    @Override
    public BaseRecyclerAdapter<HotTopResult> createAdapter() {
        return new UnusedAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "UnusedFragment";
    }

    @Override
    public List<HotTopResult> readList(Serializable seri) {
        return null;
    }

    @Override
    protected void sendRequestData() {

    }

    private void unused() {
        BaseDTO udto=new BaseDTO();
        udto.setSign("fcc4620476bcd8b90e73bc80c2cac40b");
        udto.setPageSize(4);
        udto.setPageIndex(1);
        CommonApiClient.hotTopics(getActivity(), udto, new CallBack<HotTopResult>() {
            @Override
            public void onSuccess(HotTopResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("热门专题成功");
                    unusedResult(result);
                }

            }
        });
    }

    private void unusedResult(HotTopResult result) {
        data = result.getData();
        result.setData(data);
        mAdapter.append(result);
    }


    @Override
    public void initData() {
        unused();
    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        mSpecSrc = data.get(position).getSpec_src();
        UnusedUiGoto.special(getActivity(),mSpecSrc);//专题页面
        super.onItemClick(itemView, itemBean, position);
    }
}
