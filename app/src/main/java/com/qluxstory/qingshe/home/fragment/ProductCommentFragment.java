package com.qluxstory.qingshe.home.fragment;

import android.os.Bundle;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseListFragment;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.EmptyLayout;
import com.qluxstory.qingshe.home.adapter.ProductCommentAdapter;
import com.qluxstory.qingshe.home.dto.ProductCommentDTO;
import com.qluxstory.qingshe.home.entity.ProductCommentEntity;
import com.qluxstory.qingshe.home.entity.ProductCommentResult;

import java.io.Serializable;
import java.util.List;

/**
 * 养护之商品详情之评论
 */
public class ProductCommentFragment extends BaseListFragment<ProductCommentEntity> {
    private static final String TYPE = "type";
    private int type;

    public static ProductCommentFragment newInstance(int type, String code) {
        ProductCommentFragment fragment = new ProductCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public BaseRecyclerAdapter<ProductCommentEntity> createAdapter() {
        return new ProductCommentAdapter(getActivity());
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "ProductCommentFragment";
    }

    @Override
    public List<ProductCommentEntity> readList(Serializable seri) {
        return ((ProductCommentResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        Bundle b = getArguments();
        LogUtils.e("sendRequestData--b----",""+b);
        ProductCommentDTO gdto=new ProductCommentDTO();
        gdto.setCom_sell_type("0");
        gdto.setCode(b.getString("code"));
        gdto.setPageSize(PAGE_SIZE);
        gdto.setPageIndex(mCurrentPage);
        CommonApiClient.productComment(this, gdto, new CallBack<ProductCommentResult>() {
            @Override
            public void onSuccess(ProductCommentResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("商品详情之评论成功");
                    mErrorLayout.setErrorMessage("暂无评论记录", mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1, mErrorLayout.FLAG_NODATA);
//                    if(null==result.getData()){
//                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
//                    }else {
                    requestDataSuccess(result);
                    setDataResult(result.getData());
//                    }
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
}
