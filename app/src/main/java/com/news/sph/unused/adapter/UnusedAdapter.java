package com.news.sph.unused.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.unused.entity.HotTopEntity;
import com.news.sph.unused.entity.HotTopResult;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;

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
        ImageView mInformationImg=holder.getView( R.id.unused_list_img);
        String mPicUrl = AppConfig.BASE_URL+hotTopEntity.getSpec_pic();
        ImageLoader.getInstance().displayImage(mPicUrl, mInformationImg, getImageOptions());
    }

    public DisplayImageOptions  getImageOptions(){
        return  new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }


}
