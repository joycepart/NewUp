package com.qluxstory.qingshe.me.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qluxstory.qingshe.R;

/**
 * Created by lenovo on 2016/6/5.
 */
public class PopWithdrawalsAdapter extends BaseAdapter {
    private Context context;
    private  String[] mStr;
    private LayoutInflater inflater;

    public PopWithdrawalsAdapter(Context context, String[] mStr) {
        this.context = context;
        this.mStr = mStr;
    }


    @Override
    public int getCount() {
        return mStr.length;
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
        private TextView mView;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_popwith, null);
            viewHolder.mView = (TextView) convertView
                    .findViewById(R.id.item_popwith_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mView.setText(mStr[position]);
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mStr[position];
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("data",str);
                msg.setData(b);
                msg.what = 1;

            }
        });

        return convertView;

    }
}
