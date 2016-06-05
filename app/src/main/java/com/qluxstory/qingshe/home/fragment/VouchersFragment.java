package com.qluxstory.qingshe.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BasePullFragment;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.widget.EmptyLayout;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.home.dto.VouchersDTO;
import com.qluxstory.qingshe.home.entity.VouchersEntity;
import com.qluxstory.qingshe.home.entity.VouchersResult;

import butterknife.Bind;

/**
 * 代金劵的fragent
 */
public class VouchersFragment extends BasePullFragment {
    @Bind(R.id.vouchers_list)
    RecyclerView mVouchersList;
    BaseSimpleRecyclerAdapter mVouchersListAdapter;
    private  String mCode;
    private  String mPrice;


    @Override
    public void initView(View view) {
        super.initView(view);
        Bundle b  = getArguments();
        if(b!=null){
            mCode = b.getString("mCode");
            mPrice = b.getString("mPrice");
        }
        mVouchersList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mVouchersListAdapter=new BaseSimpleRecyclerAdapter<VouchersEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_vouchers;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, VouchersEntity vouchersEntity, int position) {
                holder.setText(R.id.vouchers_tv,vouchersEntity.getCouponMoneyEqual());
                holder.setText(R.id.coupon_vouchers,vouchersEntity.getCouponRangeOfUse());
                holder.setText(R.id.item_vouchers,vouchersEntity.getDiscountNumber());
                holder.setText(R.id.vouchers_time,vouchersEntity.getCouponExpirationTime());
            }


        };
        mVouchersList.setAdapter(mVouchersListAdapter);
        mVouchersListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {
                VouchersEntity entity = (VouchersEntity) itemBean;
                AppContext.set("vouchers",entity.getCouponMoneyEqual());
                getActivity().finish();
            }
        });

    }


    @Override
    public void initData() {
        reqVouchers();

    }

    private void reqVouchers() {
        VouchersDTO bdto=new VouchersDTO();
        bdto.setMembermob(AppContext.get("mobileNum",""));
        bdto.setSign(AppConfig.SIGN_1);
        bdto.setTimestamp(TimeUtils.getSignTime());
        bdto.setComallmoney(mPrice);
        bdto.setCombrand("");
        bdto.setComserviceonlycode(mCode);
        bdto.setComsort("");
        bdto.setVertype("service");
        CommonApiClient.vouchers(getActivity(), bdto, new CallBack<VouchersResult>() {
            @Override
            public void onSuccess(VouchersResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("使用优惠劵成功");
                    mErrorLayout.setErrorMessage("暂无代金劵",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                    if(result.getData().get(0).getCouponExpirationTime()==null){
                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }else {
                        mVouchersListAdapter.removeAll();
                        mVouchersListAdapter.append(result.getData());
                    }

                }
            }
        });
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_vouchers;
    }
}
