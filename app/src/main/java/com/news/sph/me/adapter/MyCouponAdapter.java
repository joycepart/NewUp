package com.news.sph.me.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.sph.R;

/**
 * Created by lenovo on 2016/5/18.
 */
public class MyCouponAdapter extends BaseAdapter {
    private String[] mNoviceTv,mCouponTv,mItemTv,mTimeTv,mDataTv;
    private int[] mCouponImg;
    private Context mcontext;
    public MyCouponAdapter(Context mcontext, String[] mNoviceTv, String[] mCouponTv, String[] mItemTv, String[] mTimeTv, String[] mDataTv,int[] mCouponImg) {
        this.mcontext = mcontext;
        this.mNoviceTv = mNoviceTv;
        this.mCouponTv = mNoviceTv;
        this.mItemTv = mNoviceTv;
        this.mTimeTv = mNoviceTv;
        this.mDataTv = mNoviceTv;
        this.mCouponImg = mCouponImg;
    }

    @Override
    public int getCount() {
        return mNoviceTv.length;
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder {
        private TextView mNoviceTv,mCouponTv,mItemTv,mTimeTv,mDataTv;
        private ImageView mCouponImg;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item_novice_securities, null);
            viewHolder.mNoviceTv = (TextView) convertView
                    .findViewById(R.id.novice_tv);
            viewHolder.mCouponTv = (TextView) convertView
                    .findViewById(R.id.coupon_vt);
            viewHolder.mItemTv = (TextView) convertView
                    .findViewById(R.id.item_tv);
            viewHolder.mTimeTv = (TextView) convertView
                    .findViewById(R.id.tv_time);
            viewHolder.mDataTv = (TextView) convertView
                    .findViewById(R.id.tv_data);
            viewHolder.mCouponImg = (ImageView) convertView
                    .findViewById(R.id.coupon_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mNoviceTv.setText(mNoviceTv[position]);
        viewHolder.mCouponTv.setText(mCouponTv[position]);
        viewHolder.mItemTv.setText(mItemTv[position]);
        viewHolder.mTimeTv.setText(mTimeTv[position]);
        viewHolder.mDataTv.setText(mDataTv[position]);
        return convertView;
    }
}
