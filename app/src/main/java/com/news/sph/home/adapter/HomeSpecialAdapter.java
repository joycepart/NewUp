package com.news.sph.home.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.home.entity.HomeSpecialEntity;
import com.news.sph.home.entity.HomeSpecialResult;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;


public class HomeSpecialAdapter extends BaseAdapter {

    private  Context ctx;
    private  List<HomeSpecialEntity> dataList;

    public HomeSpecialAdapter(Context ctx, List<HomeSpecialEntity> dataList) {
        super();
        this.ctx = ctx;
        this.dataList = dataList;
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public HomeSpecialEntity getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder{
        ImageView mHomeListImg;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(ctx).inflate(
                    R.layout.item_home_list, null);
            holder.mHomeListImg = (ImageView) convertView.findViewById(R.id.home_list_img);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        bindData(getItem(position), holder);
        return convertView;
    }

    private void bindData(HomeSpecialEntity item, Holder holder) {
        String mPicUrl = AppConfig.BASE_URL + item.getmSpecPic();
        ImageLoader.getInstance().displayImage(mPicUrl, holder.mHomeListImg, getImageOptions());
    }

    public DisplayImageOptions getImageOptions() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    /**
     * 添加item
     *
     */
    public void addContent(List<HomeSpecialEntity> dataList) {
        this.dataList.addAll(dataList);
    }
}
