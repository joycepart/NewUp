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
    public BaseRecyclerAdapter<CuringOrderListEntity> createAdapter() {
        return new CuringOrderAdapter(getActivity());
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
        if(type ==1){
            cdto.setAppreqtype("000");
        }else if(type ==2){
            cdto.setAppreqtype("0");
        }else if(type ==3){
            cdto.setAppreqtype("10");
        }else if(type ==4){
            cdto.setAppreqtype("5");
        }
        cdto.setSign(AppConfig.SIGN_1);
        cdto.setTimestamp(TimeUtils.getSignTime());
        CommonApiClient.CuringOrderList(this, cdto, new CallBack<CuringOrderListResult>() {
            @Override
            public void onSuccess(CuringOrderListResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("养护订单成功");
                    mErrorLayout.setErrorMessage("暂无订单记录",mErrorLayout.FLAG_NODATA);
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
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
        CuringOrderListEntity entitiy = (CuringOrderListEntity) itemBean;
        Bundle b = new Bundle();
        b.putSerializable("entitiy",entitiy);
        MeUiGoto.curingOrderdetails(getActivity(),b);//养护订单详情

    }
}
