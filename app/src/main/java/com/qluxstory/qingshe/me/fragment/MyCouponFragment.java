package com.qluxstory.qingshe.me.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BasePullFragment;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.me.entity.MyCouponEntity;
import com.qluxstory.qingshe.me.entity.MyCouponResult;

import butterknife.Bind;

/**
 *我的优惠劵 fragment
 */
public class MyCouponFragment extends BasePullFragment {
    @Bind(R.id.mycoupon_list)
    RecyclerView mMycouponList;
    @Bind(R.id.mycoupon_et)
    EditText mMouponEt;
    @Bind(R.id.mycoupon_tv)
    TextView mCouponTv;
    BaseSimpleRecyclerAdapter mMycouponListAdapter;
    private String strPhoneNum;
    private String mMoupom;

    @Override
    public void initView(View view) {
        super.initView(view);
        strPhoneNum = AppContext.get("mobileNum","");
        mCouponTv.setOnClickListener(this);
        mMycouponList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mMycouponListAdapter=new BaseSimpleRecyclerAdapter<MyCouponEntity>() {
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
        mMycouponList.setAdapter(mMycouponListAdapter);

    }
    @Override
    public void initData() {
        reqCoupon();

    }

    protected void reqCoupon() {
        BaseDTO bdto=new BaseDTO();
        bdto.setMembermob(strPhoneNum);
        bdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.getCoupon(this, bdto, new CallBack<MyCouponResult>() {
            @Override
            public void onSuccess(MyCouponResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("优惠劵请求成功");
                    mMycouponListAdapter.removeAll();
                    mMycouponListAdapter.append(result.getData());
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.mycoupon_tv:
                mMoupom = mMouponEt.getText().toString();
                break;
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mycoupon;
    }
}
