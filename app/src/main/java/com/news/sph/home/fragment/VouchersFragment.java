package com.news.sph.home.fragment;

import android.view.View;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.home.adapter.VouchersAdapter;
import com.news.sph.home.entity.VouchersEntity;
import com.news.sph.home.entity.VouchersResult;

import java.io.Serializable;
import java.util.List;

/**
 * 代金劵的fragent
 */
public class VouchersFragment extends BaseListFragment<VouchersEntity> {
    @Override
    public BaseRecyclerAdapter<VouchersEntity> createAdapter() {
        return new VouchersAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "VouchersFragment";
    }

    @Override
    public List<VouchersEntity> readList(Serializable seri) {
        return ((VouchersResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
    }
}
