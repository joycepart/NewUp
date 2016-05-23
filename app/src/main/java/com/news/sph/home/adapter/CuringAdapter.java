package com.news.sph.home.adapter;

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
public class CuringAdapter  extends BaseAdapter {
    private int[] mImgpic;
    private String[] mStr1,mStr2;
    private Context mcontext;
    public CuringAdapter(Context mcontext, int[] mImgpic, String[] mStr1, String[] mStr2) {
        this.mcontext = mcontext;
        this.mImgpic = mImgpic;
        this.mStr1 = mStr1;
        this.mStr2 = mStr2;
    }

    @Override
    public int getCount() {
        return mImgpic.length;
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
        private TextView mView1,mView2;
        private ImageView mIView1;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item_list_curing, null);
            viewHolder.mView1 = (TextView) convertView
                    .findViewById(R.id.list_cur_tv1);
            viewHolder.mView2 = (TextView) convertView
                    .findViewById(R.id.list_cur_tv2);
            viewHolder.mIView1 = (ImageView) convertView
                    .findViewById(R.id.list_cur_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mView1.setText(mStr1[position]);
        viewHolder.mView2.setText(mStr2[position]);
        viewHolder.mIView1.setImageResource(mImgpic[position]);
        return convertView;
    }
}
