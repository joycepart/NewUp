package com.news.sph.me.fragment;

import android.view.View;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.me.MeUiGoto;
import com.news.sph.me.adapter.TransactionDetailAdapter;
import com.news.sph.me.entity.TransactionEntity;
import com.news.sph.me.entity.TransactionResult;

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
