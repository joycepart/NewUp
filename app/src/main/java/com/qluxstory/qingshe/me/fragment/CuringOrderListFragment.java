package com.qluxstory.qingshe.me.fragment;

import android.os.Bundle;
import android.view.View;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.common.base.BaseListFragment;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.activity.CuringOrderActivity;
import com.qluxstory.qingshe.me.adapter.CuringOrderAdapter;
import com.qluxstory.qingshe.me.dto.CuringOrderListDTO;
import com.qluxstory.qingshe.me.entity.CuringOrderListEntity;
import com.qluxstory.qingshe.me.entity.CuringOrderListResult;

import java.io.Serializable;
import java.util.List;

/**
 *养护订单的fragment
 */
public class CuringOrderListFragment extends BaseListFragment<CuringOrderListEntity> {
    private static final String TYPE = "type";
    private int type;
    private String mOrderNum;
    List<CuringOrderListEntity> entity;


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
        return "CuringOrderListFragment"+type+"_";
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
        cdto.setAppreqtype(type);
        cdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.CuringOrderList(this, cdto, new CallBack<CuringOrderListResult>() {
            @Override
            public void onSuccess(CuringOrderListResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("养护订单成功");
                    requestDataSuccess(result);
                    setDataResult(result.getData());
                    entity = result.getData();
//                    mOrderNum = result.getData().get(0).getOrderNum();
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
        Bundle b = new Bundle();
        CuringOrderListEntity entity=(CuringOrderListEntity)itemBean;
        b.putSerializable("entity",entity);
        MeUiGoto.curingOrderdetails(getActivity(),b);

    }
}
