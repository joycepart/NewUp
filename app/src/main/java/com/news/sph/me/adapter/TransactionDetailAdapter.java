package com.news.sph.me.adapter;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.R;
import com.news.sph.me.entity.TransactionEntity;

/**
 * Created by lenovo on 2016/5/25.
 */
public class TransactionDetailAdapter extends BaseSimpleRecyclerAdapter<TransactionEntity> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.fragment_transaction_detail;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, TransactionEntity transactionEntity, int position) {
        holder.setText(R.id.tran_detail_tv,transactionEntity.getComName());
        holder.setText(R.id.tran_detail_data,transactionEntity.getComTradingData());
        holder.setText(R.id.tran_detail_money,transactionEntity.getComPrice());

    }
}
