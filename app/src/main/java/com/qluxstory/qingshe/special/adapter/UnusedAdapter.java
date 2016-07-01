package com.qluxstory.qingshe.special.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppContext;
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
        int screenWidth = AppContext.get("screenWidth",0);
        ImageView mUnusedImg=holder.getView( R.id.unused_list_img);
        ViewGroup.LayoutParams layoutParam = mUnusedImg.getLayoutParams();
        layoutParam.height = screenWidth/2;
        layoutParam.width = screenWidth;
        mUnusedImg.setLayoutParams(layoutParam);
        ImageLoaderUtils.displayImage(hotTopEntity.getSpec_pic(),mUnusedImg);
    }



}
