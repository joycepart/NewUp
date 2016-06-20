package com.qluxstory.qingshe.issue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.issue.entity.LanderInEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/15.
 */
public class GridProductAdapter extends BaseAdapter {
    private final List<LanderInEntity> mEntity;
    private Context mContext;
    public GridProductAdapter(Context context, List<LanderInEntity> mLanderInEntity) {
        this.mContext = context;
        this.mEntity = mLanderInEntity;

    }

    @Override
    public int getCount() {
        return mEntity.size();
    }

    @Override
    public Object getItem(int position) {
        return mEntity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        private TextView text;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid,
                    null);
            viewHolder.text = (TextView) convertView.findViewById(R.id.item_tv);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(mEntity.get(position).getReceive_ran_num());
        return convertView;
    }

}
