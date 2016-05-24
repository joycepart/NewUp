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
import com.news.sph.utils.ImageLoaderUtils;
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
        holder.setText(R.id.issue_tv1,indianaListEntity.getmSnaRemark());
        holder.setText(R.id.issue_tv2,indianaListEntity.getmSnaTitle());
        holder.setText(R.id.issue_tv3,indianaListEntity.getmSnaTerm());
        mPicUrl = indianaListEntity.getmPicUrl();
        mUrl = AppConfig.BASE_URL+mPicUrl;
        ImageLoaderUtils.displayImage(mUrl,mIssueImg);
    }


}
