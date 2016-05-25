package com.news.sph.home.adapter;

import android.widget.ImageView;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.home.entity.HomeSpecialEntity;
import com.news.sph.common.utils.ImageLoaderUtils;

/**
 * Created by lenovo on 2016/5/24.
 */
public class HomeRecyclerAdapter extends BaseSimpleRecyclerAdapter<HomeSpecialEntity> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_home_list;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, HomeSpecialEntity homeSpecialEntity, int position) {
        ImageView mInformationImg=holder.getView( R.id.information_img);
        String mPicUrl = AppConfig.BASE_URL+homeSpecialEntity.getmSpecPic();
        ImageLoaderUtils.displayImage(mPicUrl, mInformationImg);
    }


}
