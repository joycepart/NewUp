package com.qluxstory.qingshe.me.fragment;

import android.os.Bundle;
import android.view.View;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseListFragment;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.widget.EmptyLayout;
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
        return new RecordsAdapter(getActivity());
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
        String time = TimeUtils.getSignTime();
        gdto.setUserPhone(AppContext.get("mobileNum",""));
        gdto.setTime(time);
        gdto.setSign(time+AppConfig.SIGN_1);
        gdto.setPageSize(PAGE_SIZE);
        gdto.setPageIndex(mCurrentPage);
        CommonApiClient.records(this, gdto, new CallBack<RecordsResult>() {
            @Override
            public void onSuccess(RecordsResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("个人夺宝记录成功");
                    mErrorLayout.setErrorMessage("暂无夺宝记录",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                    if(null==result.getData()){
                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }else {
                        requestDataSuccess(result);
                        setDataResult(result.getData());
                    }
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
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
        RecordsEntity entity = (RecordsEntity) itemBean;
        Bundle b = new Bundle();
        b.putSerializable("entity",entity);
        MeUiGoto.indianaRecords(getActivity(),b);//夺宝详情
    }
}
