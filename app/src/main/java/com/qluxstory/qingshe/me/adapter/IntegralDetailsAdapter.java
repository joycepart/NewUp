package com.qluxstory.qingshe.me.adapter;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.me.entity.IntegralDetailEntity;

/**
 * Created by lenovo on 2016/6/25.
 */
public class IntegralDetailsAdapter extends BaseSimpleRecyclerAdapter<IntegralDetailEntity> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.fragment_integral_detail;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, IntegralDetailEntity integralDetailEntity, int position) {
        holder.setText(R.id.tran_detail_data,integralDetailEntity.getIntegralData());
        holder.setText(R.id.tran_detail_money,integralDetailEntity.getIntegralNum());

    }


}
