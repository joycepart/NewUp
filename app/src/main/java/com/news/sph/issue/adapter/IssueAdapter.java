package com.news.sph.issue.adapter;

import android.widget.ImageView;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.issue.entity.IndianaListEntity;
import com.news.sph.common.utils.ImageLoaderUtils;

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
        holder.setText(R.id.issue_tv1,indianaListEntity.getSna_remark());
        holder.setText(R.id.issue_tv2,indianaListEntity.getSna_title());
        holder.setText(R.id.issue_tv3,indianaListEntity.getSna_term());
        mPicUrl = indianaListEntity.getPic_url();
        mUrl = AppConfig.BASE_URL+mPicUrl;
        ImageLoaderUtils.displayImage(mUrl,mIssueImg);
    }


}
