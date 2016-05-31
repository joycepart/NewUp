package com.qluxstory.qingshe.issue.fragment;

import android.view.View;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.common.base.BaseListFragment;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.issue.adapter.ToAnnounceAdapter;
import com.qluxstory.qingshe.issue.dto.ToAnnounceDTO;
import com.qluxstory.qingshe.issue.entity.ToAnnounceEntity;
import com.qluxstory.qingshe.issue.entity.ToAnnounceResult;

import java.io.Serializable;
import java.util.List;

/**
 * 往期揭晓的fragment
 */
public class ToAnnounceFragment extends BaseListFragment<ToAnnounceEntity> {
    @Override
    public BaseRecyclerAdapter<ToAnnounceEntity> createAdapter() {
        return new ToAnnounceAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "ToAnnounceFragment";
    }

    @Override
    public List<ToAnnounceEntity> readList(Serializable seri) {
        return ((ToAnnounceResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        ToAnnounceDTO gdto=new ToAnnounceDTO();
        gdto.setPageSize(PAGE_SIZE);
        gdto.setPageIndex(mCurrentPage);
        gdto.setSna_code("");
        CommonApiClient.toAnnounce(this, gdto, new CallBack<ToAnnounceResult>() {
            @Override
            public void onSuccess(ToAnnounceResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("往期揭晓成功");
                    requestDataSuccess(result);//获取到数据后调用该语句，进行数据缓存
                    setDataResult(result.getData());//设置数据
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
        super.onItemClick(itemView, itemBean, position);
        UIHelper.showFragment(getActivity(), SimplePage.PAST_DETAILSF);
    }
}
