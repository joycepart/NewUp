package com.qluxstory.qingshe.home.adapter;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.home.entity.VouchersEntity;

/**
 * Created by lenovo on 2016/5/26.
 */
public class VouchersAdapter extends BaseSimpleRecyclerAdapter<VouchersEntity> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_vouchers;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, VouchersEntity vouchersEntity, int position) {
        holder.setText(R.id.vouchers_tv,vouchersEntity.getStatus());
        holder.setText(R.id.coupon_vouchers,vouchersEntity.getStatus());
        holder.setText(R.id.item_vouchers,vouchersEntity.getStatus());
        holder.setText(R.id.vouchers_time,vouchersEntity.getStatus());

    }
}
