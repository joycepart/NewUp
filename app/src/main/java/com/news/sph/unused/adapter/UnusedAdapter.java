package com.news.sph.unused.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BaseListAdapter;
import com.news.sph.information.entity.Information;
import com.news.sph.unused.entity.HotTopResult;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;

/**
 * Created by lenovo on 2016/5/13.
 */
public class UnusedAdapter extends BaseSimpleRecyclerAdapter<HotTopResult>{
    @Bind(R.id.unused_list_img)
    ImageView mUnusedListImg;
    private String mSpecPic;
    private String mPicUrl;

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_unused_fragment;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, HotTopResult result, int position) {
        mSpecPic = result.getData().get(position).getSpec_pic();
        loadImage();


    }

    private void loadImage() {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mPicUrl = AppConfig.BASE_URL+mSpecPic;
        ImageLoader.getInstance().displayImage(mPicUrl, mUnusedListImg, options);
    }


}
