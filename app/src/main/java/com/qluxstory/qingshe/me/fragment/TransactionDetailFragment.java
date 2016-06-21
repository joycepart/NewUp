package com.qluxstory.qingshe.me.fragment;

import android.os.Bundle;
import android.view.View;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseListFragment;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.widget.EmptyLayout;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.adapter.TransactionDetailAdapter;
import com.qluxstory.qingshe.me.entity.TransactionEntity;
import com.qluxstory.qingshe.me.entity.TransactionResult;

import java.io.Serializable;
import java.util.List;

/**
 *交易明细的fragment
 */
public class TransactionDetailFragment extends BaseListFragment<TransactionEntity> {

    @Override
    public BaseRecyclerAdapter<TransactionEntity> createAdapter() {
        return new TransactionDetailAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "TransactionDetailFragment";
    }

    @Override
    public List<TransactionEntity> readList(Serializable seri) {
        return ((TransactionResult)seri).getData();
    }


    @Override
    public void initData() {
    }


    @Override
    protected void sendRequestData() {
        BaseDTO bdto=new BaseDTO();
        String time = TimeUtils.getSignTime();
        bdto.setMembermob(AppContext.get("mobileNum",""));
        bdto.setSign(time+AppConfig.SIGN_1);
        bdto.setTimestamp(time);
        CommonApiClient.getTransaction(getActivity(), bdto, new CallBack<TransactionResult>() {
            @Override
            public void onSuccess(TransactionResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("交易明细成功");
                    mErrorLayout.setErrorMessage("暂无明细记录",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless2,mErrorLayout.FLAG_NODATA);
                    if(result.getData().get(0).getComTradingTime()==null){
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
    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
        TransactionEntity entity = (TransactionEntity) itemBean;
        Bundle b  = new Bundle();
        b.putSerializable("entity",entity);
        MeUiGoto.details(getActivity(),b);
    }
}
