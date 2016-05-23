package com.news.sph.information.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.information.entity.InformationEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by lenovo on 2016/5/14.
 */
public class InformationAdapter extends BaseSimpleRecyclerAdapter<InformationEntity> {


    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_information_fragment;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, InformationEntity informationEntity, int position) {
        holder.setText(R.id.information_tv1,informationEntity.getNews_big_title());
        holder.setText(R.id.information_tv2,informationEntity.getNews_small_title());
        ImageView mInformationImg=holder.getView( R.id.information_img);
        String mPicUrl = AppConfig.BASE_URL+informationEntity.getNews_pic_url();
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
