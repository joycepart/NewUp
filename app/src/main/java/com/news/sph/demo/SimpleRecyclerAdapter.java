package com.news.sph.demo;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.R;

/**
 */
public class SimpleRecyclerAdapter extends BaseSimpleRecyclerAdapter<String> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, String t, int position) {
        holder.setText(R.id.textView,t);
    }

}
