package com.qluxstory.qingshe.me.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qluxstory.qingshe.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/6/5.
 */
public class BindListAdapter extends BaseAdapter {


    private final Context context;
    private final String[] list;

    public BindListAdapter(Context  context, String[] list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView == null) {
            convertView =  LayoutInflater.from(context).inflate(R.layout.item_pop_withdrawals, parent, false);
            ViewGroup.LayoutParams params = convertView.getLayoutParams();
            convertView.setLayoutParams(params);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mName.setText(list[position]);

        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.tv_item)
        TextView mName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
