package com.news.sph.home.fragment;

import android.os.Bundle;
import android.view.View;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.home.activity.CuringActivity;
import com.news.sph.home.adapter.CuringAdapter;
import com.news.sph.home.dto.CuringDTO;
import com.news.sph.home.entity.CuringEntity;
import com.news.sph.home.entity.CuringResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2016/5/18.
 */
public class CuringFragment extends BaseListFragment<CuringEntity> {
    private static final String TYPE = "type";
    public static final int TYPE_TAB_1=1;
    public static final int TYPE_TAB_2=2;
    public static final int TYPE_TAB_3=3;
    private int type;

    public static CuringFragment newInstance(int type) {
        CuringFragment fragment = new CuringFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public BaseRecyclerAdapter<CuringEntity> createAdapter() {
        return new CuringAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "CuringFragment"+type+"_";
    }

    @Override
    public List<CuringEntity> readList(Serializable seri) {
        return ((CuringResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        CuringDTO dto = new CuringDTO();
        dto.setSort(type);
        dto.setPageIndex(mCurrentPage);
        dto.setPageSize(PAGE_SIZE);
        CommonApiClient.curing(this, dto, new CallBack<CuringResult>() {
            @Override
            public void onSuccess(CuringResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("专业养护成功");
                    requestDataSuccess(result);
                    setDataResult(result.getData());
                }

            }
        });

    }

    public boolean autoRefreshIn(){
        return true;
    }


    @Override
    public void initData() {

    }

    @Override
    public void initView(View view) {
        super.initView(view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE, CuringActivity.TAB_A);
        }
    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
    }
}
