package com.news.sph.unused.adapter;

import android.widget.ImageView;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.R;
import com.news.sph.common.utils.ImageLoaderUtils;
import com.news.sph.unused.entity.HotTopEntity;

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
        ImageLoaderUtils.displayImage(hotTopEntity.getmSpecPic(),mUnusedImg);
    }



}
