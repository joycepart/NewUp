package com.news.sph.home.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.sph.R;

/**
 * Created by lenovo on 2016/5/14.
 */
public class HomeHorizontalScrollViewAdapter extends BaseAdapter {
    private int[] imgpic;
    private String[] str1,str2;
    private Context context;
    public HomeHorizontalScrollViewAdapter(Context context, int[] img_pic, String[] str1, String[] str2) {
        this.context = context;
        this.imgpic = imgpic;
        this.str1 = str1;
        this.str2 = str2;
    }

    @Override
    public int getCount() {
        return imgpic.length;
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
        protected ImageView iView;
        private TextView tView1,tView2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            viewHolder.iView = (ImageView) convertView
//                    .findViewById(R.id.hor_img);
//            viewHolder.tView1 = (TextView) convertView
//                    .findViewById(R.id.hor_tv1);
//            viewHolder.tView2 = (TextView) convertView
//                    .findViewById(R.id.hor_tv2);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        viewHolder.iView.setImageResource(imgpic[position]);
//        viewHolder.tView1.setText(str1[position]);
//        viewHolder.tView2.setText(str2[position]);
        return convertView;
    }
}
