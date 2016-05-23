package com.news.sph.issue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.news.sph.R;

/**
 * Created by lenovo on 2016/5/16.
 */
public class ToAnnounceAdapter extends BaseAdapter{
    private Context context;
    private String[] str1,str2,str3,str4,str5;
    public ToAnnounceAdapter(Context context, String[] str1, String[] str2, String[] str3, String[] str4, String[] str5) {
        this.context = context;
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;
        this.str4 = str4;
        this.str5 = str5;
    }

    @Override
    public int getCount() {
        return 0;
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
        private TextView tView1,tView2,tView3,tView4,tView5;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_toannounce, null);
            viewHolder.tView1 = (TextView) convertView
                    .findViewById(R.id.toannounce_tv1);
            viewHolder.tView2 = (TextView) convertView
                    .findViewById(R.id.toannounce_tv2);
            viewHolder.tView3 = (TextView) convertView
                    .findViewById(R.id.toannounce_tv3);
            viewHolder.tView4 = (TextView) convertView
                    .findViewById(R.id.toannounce_tv4);
            viewHolder.tView5 = (TextView) convertView
                    .findViewById(R.id.toannounce_tv5);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tView1.setText(str1[position]);
        viewHolder.tView2.setText(str2[position]);
        viewHolder.tView3.setText(str3[position]);
        viewHolder.tView4.setText(str4[position]);
        viewHolder.tView5.setText(str5[position]);
        return convertView;
    }
}
