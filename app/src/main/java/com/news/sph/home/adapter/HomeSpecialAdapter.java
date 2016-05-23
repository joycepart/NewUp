package com.news.sph.home.adapter;

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
import com.news.sph.home.entity.HomeSpecialResult;
import com.news.sph.information.entity.Information;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;


public class HomeSpecialAdapter extends BaseSimpleRecyclerAdapter<HomeSpecialResult> {
    @Bind(R.id.home_list_img)
    ImageView mHomeListImg;
    private String mSpecPic;
    private String mSpecUrl;

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_home_list;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, HomeSpecialResult homeSpecialResult, int position) {
        mSpecPic = homeSpecialResult.getData().get(position).getSpec_pic();
        loadImage();
    }
    private void loadImage() {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mSpecUrl = AppConfig.BASE_URL+mSpecPic;
        ImageLoader.getInstance().displayImage(mSpecUrl, mHomeListImg, options);
    }


}
