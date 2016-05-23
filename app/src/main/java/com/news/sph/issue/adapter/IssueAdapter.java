package com.news.sph.issue.adapter;

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
import com.news.sph.issue.entity.IndianaListEntity;
import com.news.sph.issue.entity.IndianaListResult;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;

/**
 * Created by lenovo on 2016/5/14.
 */
public class IssueAdapter extends BaseSimpleRecyclerAdapter<IndianaListEntity> {
    @Bind(R.id.issue_img)
    ImageView mIssueImg;
    private String mPicUrl;
    private String mUrl;

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_issue_fragment;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, IndianaListEntity indianaListEntity, int position) {
        holder.setText(R.id.issue_tv1,indianaListEntity.getSna_term());
        holder.setText(R.id.issue_tv2,indianaListEntity.getSna_title());
        holder.setText(R.id.issue_tv3,indianaListEntity.getSna_term());
        mPicUrl = indianaListEntity.getPic_url();
        loadImg();
    }


    private void loadImg() {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mUrl = AppConfig.BASE_URL+mPicUrl;
        ImageLoader.getInstance().displayImage(mUrl, mIssueImg, options);
    }


}
