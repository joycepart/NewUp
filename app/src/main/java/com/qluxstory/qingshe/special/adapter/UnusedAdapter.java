package com.qluxstory.qingshe.special.adapter;

import android.widget.ImageView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.special.entity.HotTopEntity;

/**
 * Created by lenovo on 2016/5/13.
 */
public class UnusedAdapter extends BaseSimpleRecyclerAdapter<HotTopEntity>{

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_unused_fragment;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, HotTopEntity hotTopEntity, int position) {
        ImageView mUnusedImg=holder.getView( R.id.unused_list_img);
        ImageLoaderUtils.displayImage(hotTopEntity.getSpec_pic(),mUnusedImg);
    }



}
