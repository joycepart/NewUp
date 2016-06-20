package com.qluxstory.qingshe.home.fragment;

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
import com.qluxstory.qingshe.common.widget.EmptyLayout;
import com.qluxstory.qingshe.home.HomeUiGoto;
import com.qluxstory.qingshe.home.activity.CuringActivity;
import com.qluxstory.qingshe.home.adapter.CuringAdapter;
import com.qluxstory.qingshe.home.dto.CuringDTO;
import com.qluxstory.qingshe.home.entity.CuringEntity;
import com.qluxstory.qingshe.home.entity.CuringResult;
import com.qluxstory.qingshe.home.entity.ProductDetails;

import java.io.Serializable;
import java.util.List;

/**
 * 专业养护的fragment
 */
public class CuringFragment extends BaseListFragment<CuringEntity> {
    private static final String TYPE = "type";
    private int type;
    private  String mUrl;
    ProductDetails mProductDetails;

    public static CuringFragment newInstance(int type) {
        CuringFragment fragment = new CuringFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductDetails = AppContext.getInstance().getProductDetails();
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE, CuringActivity.TAB_A);

        }
    }


    @Override
    public BaseRecyclerAdapter<CuringEntity> createAdapter() {
        return new CuringAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "CuringFragment"+type+"_";
    }

    @Override
    public List<CuringEntity> readList(Serializable seri) {
        return ((CuringResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        CuringDTO dto = new CuringDTO();
        dto.setSort(type);
        dto.setPageIndex(mCurrentPage);
        dto.setPageSize(PAGE_SIZE);
        CommonApiClient.curing(this, dto, new CallBack<CuringResult>() {
            @Override
            public void onSuccess(CuringResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("专业养护成功");
                    LogUtils.e("result----",""+result);
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

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
        CuringEntity curingEntity = (CuringEntity) itemBean;
        mUrl= AppConfig.BASE_URL+AppConfig.Server_Html+curingEntity.getSell_only_code();
        mProductDetails.setSellName(curingEntity.getSell_name());
        mProductDetails.setSellOnlyCode(curingEntity.getSell_only_code());
        mProductDetails.setSellPrice(curingEntity.getSell_price());
        mProductDetails.setSellPic(curingEntity.getSell_pic());
        mProductDetails.setSellSort(curingEntity.getSell_sort());
        HomeUiGoto.curingProductDetails(getActivity(),mUrl,curingEntity.getSell_pic(),curingEntity.getSell_only_code(),curingEntity.getSell_name());//专业养护之商品详情
    }

    @Override
    public void initData() {

    }


    public boolean autoRefreshIn(){
        return true;
    }
}
