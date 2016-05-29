package com.news.sph.me.fragment;

import android.view.View;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.me.MeUiGoto;
import com.news.sph.me.adapter.RecordsAdapter;
import com.news.sph.me.entity.RecordsEntity;
import com.news.sph.me.entity.RecordsResult;

import java.io.Serializable;
import java.util.List;

/**
 * 夺宝记录的fragment
 */
public class IndianaRecordsFragment extends BaseListFragment<RecordsEntity> {
    @Override
    public BaseRecyclerAdapter<RecordsEntity> createAdapter() {
        return new RecordsAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "IndianaRecordsFragment";
    }

    @Override
    public List<RecordsEntity> readList(Serializable seri) {
        return ((RecordsResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        BaseDTO gdto=new BaseDTO();
        gdto.setMembermob("");
        gdto.setSign(AppConfig.SIGN_1);
        gdto.setPageSize(PAGE_SIZE);
        gdto.setPageIndex(mCurrentPage);
        CommonApiClient.records(this, gdto, new CallBack<RecordsResult>() {
            @Override
            public void onSuccess(RecordsResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("个人夺宝记录成功");
                    requestDataSuccess(result);//获取到数据后调用该语句，进行数据缓存
                    setDataResult(result.getData());//设置数据
                }

            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
        MeUiGoto.indianaRecords(getActivity());//夺宝详情
    }
}
