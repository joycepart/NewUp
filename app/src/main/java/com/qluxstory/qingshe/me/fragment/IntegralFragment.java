package com.qluxstory.qingshe.me.fragment;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseListFragment;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.EmptyLayout;
import com.qluxstory.qingshe.me.adapter.IntegralDetailsAdapter;
import com.qluxstory.qingshe.me.entity.IntegralDetailEntity;
import com.qluxstory.qingshe.me.entity.IntegralDetailResult;

import java.io.Serializable;
import java.util.List;

/**
 *积分明细的fragment
 */
public class IntegralFragment extends BaseListFragment<IntegralDetailEntity> {

    @Override
    public BaseRecyclerAdapter<IntegralDetailEntity> createAdapter() {
        return new IntegralDetailsAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "IntegralFragment";
    }

    @Override
    public List<IntegralDetailEntity> readList(Serializable seri) {
        return ((IntegralDetailResult)seri).getData();
    }


    @Override
    public void initData() {
    }


    @Override
    protected void sendRequestData() {
        BaseDTO bdto=new BaseDTO();
        bdto.setMembermob(AppContext.get("mobileNum",""));
        bdto.setPageSize(PAGE_SIZE);
        bdto.setPageIndex(mCurrentPage);
        CommonApiClient.integralList(getActivity(), bdto, new CallBack<IntegralDetailResult>() {
            @Override
            public void onSuccess(IntegralDetailResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("积分获取列表成功");
                    mErrorLayout.setErrorMessage("暂无积分记录",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless2,mErrorLayout.FLAG_NODATA);
                    if(result.getData()==null){
                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }else {
                        requestDataSuccess(result);
                        setDataResult(result.getData());
                    }


                }
            }
        });

    }

    @Override
    public void retry() {

    }


    public boolean autoRefreshIn(){
        return true;
    }

}
