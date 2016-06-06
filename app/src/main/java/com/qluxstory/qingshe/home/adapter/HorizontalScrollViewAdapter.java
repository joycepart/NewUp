package com.qluxstory.qingshe.home.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.home.entity.HomeRecommendEntity;

import java.util.ArrayList;
import java.util.List;

public class HorizontalScrollViewAdapter
{

	private Context mContext;
	private LayoutInflater mInflater;
	private List<HomeRecommendEntity> mDatas=new ArrayList<>();

	public HorizontalScrollViewAdapter(Context context)
	{
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	public void append(List<HomeRecommendEntity> mDatas){
		this.mDatas=mDatas;
	}

	public int getCount()
	{
		return mDatas.size();
	}

	public HomeRecommendEntity getItem(int position)
	{
		return mDatas.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder = null;
		if (convertView == null)
		{
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(
					R.layout.activity_index_gallery_item, parent, false);
			viewHolder.mItemRel = (RelativeLayout) convertView
					.findViewById(R.id.gallery_item_rel);
			viewHolder.mImg = (ImageView) convertView
					.findViewById(R.id.id_img);
			viewHolder.mTitle = (TextView) convertView
					.findViewById(R.id.id_title);
			viewHolder.mQishu = (TextView) convertView
					.findViewById(R.id.id_qishu);


			convertView.setTag(viewHolder);
		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		ImageLoaderUtils.displayImage(AppConfig.BASE_URL+getItem(position).getPic_url(),viewHolder.mImg);
		viewHolder.mTitle.setText(getItem(position).getSna_title());
		viewHolder.mQishu.setText("第"+getItem(position).getSna_term()+"期");
		viewHolder.mItemRel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle b = new Bundle();
				String bat = getItem(position).getBat_code();
				String sna = getItem(position).getSna_code();
				b.putString("bat",bat);
				b.putString("sna",sna);
				UIHelper.showFragment(mContext, SimplePage.PRODUCT_DETAILS,b);//夺宝商品详情
			}
		});
		return convertView;
	}

	static class ViewHolder
	{
		ImageView mImg;
		TextView mTitle;
		TextView mQishu;
		RelativeLayout mItemRel;
	}

}
