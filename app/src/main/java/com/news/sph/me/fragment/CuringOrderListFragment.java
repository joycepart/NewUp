package com.news.sph.me.fragment;

import android.os.Bundle;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.me.activity.CuringOrderActivity;
import com.news.sph.me.adapter.CuringOrderAdapter;
import com.news.sph.me.dto.CuringOrderListDTO;
import com.news.sph.me.entity.CuringOrderListEntity;
import com.news.sph.me.entity.CuringOrderListResult;

import java.io.Serializable;
import java.util.List;

/**
 *养护订单的fragment
 */
public class CuringOrderListFragment extends BaseListFragment<CuringOrderListEntity> {
    private static final String TYPE = "type";
    private int type;


    public static CuringOrderListFragment newInstance(int type) {
        CuringOrderListFragment fragment = new CuringOrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE, CuringOrderActivity.TAB_A);

        }
    }

    @Override
    public void retry() {

    }

    @Override
    public BaseRecyclerAdapter<CuringOrderListEntity> createAdapter() {
        return new CuringOrderAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "CuringFragment"+type+"_";
    }

    @Override
    public List<CuringOrderListEntity> readList(Serializable seri) {
        return ((CuringOrderListResult)seri).getData();
    }

    @Override
    public void initData() {
    }

    @Override
    protected void sendRequestData() {
        CuringOrderListDTO cdto=new CuringOrderListDTO();
        cdto.setMembermob(AppContext.get("mobileNum",""));
        cdto.setAppreqtype("");
        cdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.CuringOrderList(this, cdto, new CallBack<CuringOrderListResult>() {
            @Override
            public void onSuccess(CuringOrderListResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("养护订单成功");
                    requestDataSuccess(result);
                    setDataResult(result.getData());
                }

            }
        });

    }

    public boolean autoRefreshIn(){
        return true;
    }

}
