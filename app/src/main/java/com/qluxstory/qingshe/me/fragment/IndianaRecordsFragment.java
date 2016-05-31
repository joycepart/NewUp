package com.qluxstory.qingshe.me.fragment;

import android.view.View;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.common.base.BaseListFragment;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.adapter.RecordsAdapter;
import com.qluxstory.qingshe.me.dto.IndianaRecordsDTO;
import com.qluxstory.qingshe.me.entity.RecordsEntity;
import com.qluxstory.qingshe.me.entity.RecordsResult;

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
        IndianaRecordsDTO gdto=new IndianaRecordsDTO();
        gdto.setUserPhone(AppContext.get("mobileNum",""));
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
    public boolean autoRefreshIn(){
        return true;
    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
        MeUiGoto.indianaRecords(getActivity());//夺宝详情
    }
}
