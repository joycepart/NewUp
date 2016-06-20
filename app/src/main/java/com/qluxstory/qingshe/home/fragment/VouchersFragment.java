package com.qluxstory.qingshe.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
    private TextView tv;


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
                tv = holder.getView(R.id.novice_tv);
                if(vouchersEntity.getCouponType().equals("1001")){
                    tv.setText(vouchersEntity.getDiscountNumber()+"折");
                }else if(vouchersEntity.getCouponType().equals("1002")){
                    tv.setText("满"+vouchersEntity.getCouponmoney().replace(".00","")+"减"+vouchersEntity.getCouponMoneyEqual().replace(".00",""));
                }
                else if(vouchersEntity.getCouponType().equals("1003")){
                    tv.setText("免费");
                }
                else if(vouchersEntity.getCouponType().equals("1004")){
                    tv.setText("直减"+vouchersEntity.getCouponMoneyEqual().replace(".00",""));
                }
                else if(vouchersEntity.getCouponType().equals("1005")){
                    tv.setText(vouchersEntity.getCouponMoneyEqual().replace(".00","")+"抵用"+vouchersEntity.getCouponmoney().replace(".00",""));
                }
                if(vouchersEntity.getCouponRangeOfUse().equals("2001")){
                    holder.setText(R.id.coupon_vt,"优惠劵");
                }else if(vouchersEntity.getCouponRangeOfUse().equals("2002")){
                    holder.setText(R.id.coupon_vt,"服务劵");
                }



                holder.setText(R.id.item_tv,vouchersEntity.getCouponRedeemName());
                holder.setText(R.id.tv_time,vouchersEntity.getCouponExpirationTime());
            }


        };
        mVouchersList.setAdapter(mVouchersListAdapter);
        mVouchersListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {
                VouchersEntity entity = (VouchersEntity) itemBean;
                AppContext.set("Novice",tv.getText().toString());
                AppContext.set("CouponType",entity.getCouponType());
                AppContext.set("DiscountNumber",entity.getDiscountNumber());
                AppContext.set("CouponMoneyEqual",entity.getCouponMoneyEqual());
                AppContext.set("Couponmoney",entity.getCouponmoney());
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
                    if(null==result.getData()){
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
