package com.qluxstory.qingshe.me.adapter;

import android.widget.ImageView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.me.entity.RecordsEntity;

/**
 * Created by lenovo on 2016/5/26.
 */
public class RecordsAdapter extends BaseSimpleRecyclerAdapter<RecordsEntity> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_records;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, RecordsEntity recordsEntity, int position) {
        holder.setText(R.id.records_payment,recordsEntity.getRec_state());
        holder.setText(R.id.records_title,recordsEntity.getSna_title());
        holder.setText(R.id.records_term,recordsEntity.getRec_term());
        holder.setText(R.id.records_yn,recordsEntity.getRec_pay_type());
        holder.setText(R.id.records_my,recordsEntity.getRec_pay_balance());
        ImageView mRecordsImg=holder.getView( R.id.records_img);
        ImageLoaderUtils.displayImage(recordsEntity.getPic_url(), mRecordsImg);

    }
}
