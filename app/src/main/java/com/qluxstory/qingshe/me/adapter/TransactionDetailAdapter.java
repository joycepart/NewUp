package com.qluxstory.qingshe.me.adapter;

import android.view.View;
import android.widget.LinearLayout;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.me.entity.TransactionEntity;

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
        LinearLayout lin = holder.getView(R.id.details_lin);
        if(transactionEntity.getComName().equals("提现")){
            lin.setVisibility(View.VISIBLE);

            if(transactionEntity.getTranState().equals("1002")){
                holder.setText(R.id.tran_detail_gone,"申请中");
            }
            if(transactionEntity.getTranState().equals("1004")){
                holder.setText(R.id.tran_detail_gone,"成功");
            }
        }else {
            lin.setVisibility(View.GONE);
        }
        holder.setText(R.id.tran_detail_name,transactionEntity.getComName());
        holder.setText(R.id.tran_detail_data,transactionEntity.getComTradingData());
        holder.setText(R.id.tran_detail_time,transactionEntity.getComTradingTime());
        holder.setText(R.id.tran_detail_money,transactionEntity.getComIncomeAmount());

    }
}
