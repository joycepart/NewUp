package com.news.sph.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.utils.ImageLoaderUtils;
import com.news.sph.home.entity.HomeRecommendEntity;

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

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder = null;
		if (convertView == null)
		{
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(
					R.layout.activity_index_gallery_item, parent, false);
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
		viewHolder.mQishu.setText(getItem(position).getSna_term());
		return convertView;
	}

	static class ViewHolder
	{
		ImageView mImg;
		TextView mTitle;
		TextView mQishu;
	}

}
