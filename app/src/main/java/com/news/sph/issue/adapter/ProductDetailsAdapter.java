package com.news.sph.issue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.sph.R;

/**
 * Created by lenovo on 2016/5/16.
 */
public class ProductDetailsAdapter extends BaseAdapter {
    private Context context;
    private String[] str1,str2,str3;
    public ProductDetailsAdapter(Context context, String[] str1, String[] str2, String[] str3) {
        this.context = context;
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;

    }

    @Override
    public int getCount() {
        return str1.length;
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
        private TextView tView1,tView2,tView3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_issue_product, null);
            viewHolder.tView1 = (TextView) convertView
                    .findViewById(R.id.product_tv1);
            viewHolder.tView2 = (TextView) convertView
                    .findViewById(R.id.product_tv2);
            viewHolder.tView3 = (TextView) convertView
                    .findViewById(R.id.product_tv3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tView1.setText(str1[position]);
        viewHolder.tView2.setText(str2[position]);
        viewHolder.tView3.setText(str3[position]);
        return convertView;
    }
}
