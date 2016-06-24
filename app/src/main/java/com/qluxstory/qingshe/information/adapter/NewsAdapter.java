package com.qluxstory.qingshe.information.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.information.entity.InformationEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/6/23.
 */
public class NewsAdapter extends BaseAdapter {
    private final Context context;
    private final List<InformationEntity> entity;
    private final ArrayList<String> tList;
    private String read;

    public NewsAdapter(Context context, List<InformationEntity> entity, ArrayList<String> tList) {
        this.context = context;
        this.entity = entity;
        this.tList = tList;
    }

    @Override
    public int getCount() {
        return entity.size();
    }

    @Override
    public InformationEntity getItem(int position) {
        return entity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_information_fragment, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        InformationEntity item = getItem(position);
        holder.tv1.setText(item.getNews_big_title());
        holder.tv2.setText(item.getNews_small_title());
        read = tList.get(position);
        LogUtils.e("getIsread---",""+tList.get(position));
        LogUtils.e("position---",""+position);
//        LogUtils.e("AppContext---",""+AppContext.get("position",-1));
//        if(position == AppContext.get("position",-1)){
//            read = "1";
//        }
        if(read.equals("1")){
            holder.tv1.setTextColor(context.getResources().getColor(R.color.color_f9));
            holder.tv2.setTextColor(context.getResources().getColor(R.color.color_f9));
        }else if(read.equals("0")){
            holder.tv1.setTextColor(context.getResources().getColor(R.color.color_00));
            holder.tv2.setTextColor(context.getResources().getColor(R.color.color_00));
        }

        ImageLoaderUtils.displayImage(item.getNews_pic_url(), holder.iv);
        return convertView;
    }

    class ViewHolder {
        LinearLayout lin;
        ImageView iv;
        TextView tv1,tv2;

        public ViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.information_img);
            tv1 = (TextView) view.findViewById(R.id.information_tv1);
            tv2 = (TextView) view.findViewById(R.id.information_tv2);
            lin = (LinearLayout) view.findViewById(R.id.information_lin);
            view.setTag(this);
        }
    }
}
