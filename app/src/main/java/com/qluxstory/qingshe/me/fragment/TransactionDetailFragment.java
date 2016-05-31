package com.qluxstory.qingshe.me.fragment;

import android.view.View;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.common.base.BaseListFragment;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
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
    private String strPhoneNum;
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
    public void initView(View view) {
        strPhoneNum = AppContext.getInstance().getUser().getmUserMobile();
        super.initView(view);
    }

    @Override
    public void initData() {

    }
    @Override
    protected void sendRequestData() {
        BaseDTO bdto=new BaseDTO();
        bdto.setMembermob(AppContext.get("mobileNum",""));
        bdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.getTransaction(getActivity(), bdto, new CallBack<TransactionResult>() {
            @Override
            public void onSuccess(TransactionResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("交易明细成功");
                    requestDataSuccess(result);//获取到数据后调用该语句，进行数据缓存
                    setDataResult(result.getData());//设置数据

                }
            }
        });

    }

    public boolean autoRefreshIn(){
        return true;
    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
        MeUiGoto.details(getActivity());
    }
}
