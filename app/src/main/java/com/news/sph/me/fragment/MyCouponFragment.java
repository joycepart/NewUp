package com.news.sph.me.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.R;
import com.news.sph.common.base.BasePullScrollViewFragment;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.common.widget.FullyLinearLayoutManager;
import com.news.sph.me.entity.MyCouponEntity;
import com.news.sph.me.entity.MyCouponResult;

import butterknife.Bind;

/**
 *我的优惠劵 fragment
 */
public class MyCouponFragment extends BasePullScrollViewFragment {
    @Bind(R.id.mycoupon_list)
    RecyclerView mMycouponList;
    BaseSimpleRecyclerAdapter mListAdapter;
    private String strPhoneNum;

    @Override
    public void initView(View view) {
        super.initView(view);
        strPhoneNum = AppContext.getInstance().getUser().getmUserMobile();
        mMycouponList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mListAdapter=new BaseSimpleRecyclerAdapter<MyCouponEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_novice_securities;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, MyCouponEntity myCouponEntity, int position) {
                holder.setText(R.id.novice_tv,myCouponEntity.getCouponmoney());
                holder.setText(R.id.coupon_vt,myCouponEntity.getCouponType());
                holder.setText(R.id.item_tv,myCouponEntity.getCouponRedeemName());
                holder.setText(R.id.tv_time,myCouponEntity.getCouponExpirationTime());
            }


        };
        mMycouponList.setAdapter(mListAdapter);

    }


    @Override
    protected void sendRequestData() {
        BaseDTO bdto=new BaseDTO();
        bdto.setMembermob(strPhoneNum);
        bdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.getCoupon(getActivity(), bdto, new CallBack<MyCouponResult>() {
            @Override
            public void onSuccess(MyCouponResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("优惠劵请求成功");
                    mListAdapter.removeAll();
                    mListAdapter.append(result.getData());
                }
            }
        });

    }

    @Override
    public void initData() {
        sendRequestData();

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mycoupon;
    }
}
