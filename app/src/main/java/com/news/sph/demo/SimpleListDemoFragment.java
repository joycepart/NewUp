package com.news.sph.demo;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.utils.TDevice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class SimpleListDemoFragment extends BaseListFragment<String>{
    ArrayList<String> mDatas;

    @Override
    public BaseRecyclerAdapter<String> createAdapter() {
        return new SimpleRecyclerAdapter();
    }

    @Override
    public void initView(View view) {
        super.initView(view);
    }


    @Override
    protected void sendRequestData(){
        if(!TDevice.hasInternet(getContext())){
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> list=new ArrayList();
                    for (int i = 0; i < 10; i++) {
                        list.add("" + i);
                }
                DemoEntity entity=new DemoEntity();
                entity.setData(list);

                requestDataSuccess(entity);//获取到数据后调用该语句，进行数据缓存
                setDataResult(list);//设置数据

                if(mAdapter.getItemCount()>50){
                    mPtrRecyclerView.noMoreData(); //没有更多数据了，这里只是测试用，不需要我们调用，已经封装在基类，自动判断了
                }
            }
        }, 1500);
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 30; i++)
        {
            mDatas.add("" + i);
        }

        mAdapter.append(mDatas);

    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        Toast.makeText(getActivity(),"data:"+itemBean+"    position:"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "SimpleListDemoFragment";
    }

    @Override
    public List<String> readList(Serializable seri) {
        return ((DemoEntity)seri).getData();
    }

}
