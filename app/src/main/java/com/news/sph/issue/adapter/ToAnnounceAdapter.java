package com.news.sph.issue.adapter;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.R;
import com.news.sph.issue.entity.ToAnnounceEntity;

/**
 * Created by lenovo on 2016/5/16.
 */
public class ToAnnounceAdapter extends BaseSimpleRecyclerAdapter<ToAnnounceEntity> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_toannounce;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, ToAnnounceEntity toAnnounceEntity, int position) {
        holder.setText(R.id.toannounce_tv1,toAnnounceEntity.getSna_term());
        holder.setText(R.id.item_toan_tv,toAnnounceEntity.getSna_end_date());
        holder.setText(R.id.toannounce_tv3,toAnnounceEntity.getSna_lucky_people());
        holder.setText(R.id.toannounce_tv4,toAnnounceEntity.getSna_lucky_num());
        holder.setText(R.id.toannounce_tv5,toAnnounceEntity.getSna_participate_count());

    }
}
