package com.qluxstory.qingshe.information.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseFragment;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.EmptyLayout;
import com.qluxstory.qingshe.information.InformationUiGoto;
import com.qluxstory.qingshe.information.adapter.NewsAdapter;
import com.qluxstory.qingshe.information.dto.DeleteDTO;
import com.qluxstory.qingshe.information.dto.InformationDTO;
import com.qluxstory.qingshe.information.entity.InformationEntity;
import com.qluxstory.qingshe.information.entity.InformationResult;
import com.qluxstory.qingshe.swipemenulistview.SwipeMenu;
import com.qluxstory.qingshe.swipemenulistview.SwipeMenuCreator;
import com.qluxstory.qingshe.swipemenulistview.SwipeMenuItem;
import com.qluxstory.qingshe.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

/*
消息的fragment
*/

public class InformationFragment extends BaseFragment{
    List<InformationEntity> entity;
    private NewsAdapter mAdapter;
    private SwipeMenuListView mListView;
    InformationEntity item;
    private String mNewsBigTitle,mNewsCode,mUrl;
    ArrayList<String>  tList ;

    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_news_infor;
    }

    @Override
    public void initView(View view) {
        LogUtils.e("initView-----","initView");
        tList = new ArrayList<>();
        mListView = (SwipeMenuListView) view.findViewById(R.id.listView);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                LogUtils.e("create-----","create");
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                item = entity.get(position);
                switch (index) {
                    case 0:
                        LogUtils.e("case-----","0");
                        tList.remove(position);
                        entity.remove(position);
                        mAdapter.notifyDataSetChanged();
                        reqDelete();
                        break;
                }
            }
        });

        // set SwipeListener
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // other setting
//		listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                return false;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mNewsBigTitle =entity.get(position).getNews_big_title();
                mNewsCode = entity.get(position).getNews_code();
                mUrl= AppConfig.BASE_URL+AppConfig.News_Html+mNewsCode;
                reqRead();//阅读新闻
                LogUtils.e("position---",""+position);
                LogUtils.e("bool---",""+bool);
                if(bool){
                    AppContext.set("position",position);
                    tList.set(position,"1");
                    InformationUiGoto.newsDetail(getActivity(),mNewsBigTitle,mUrl);//新闻详情页，h5页面
                    mAdapter.notifyDataSetChanged();
                }


            }
        });


    }

    boolean bool = false;

    private void reqRead() {
        LogUtils.e("reqRead-----","reqRead");

        DeleteDTO gdto=new DeleteDTO();
        gdto.setUserPhone(AppContext.get("mobileNum",""));
        gdto.setNewsCode(mNewsCode);
        CommonApiClient.read(this, gdto, new CallBack<InformationResult>() {
            @Override
            public void onSuccess(InformationResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("阅读新闻成功");
                    bool = true;
                }

            }
        });
    }

    @Override
    public void initData() {
        reqInformation();//获取推送新闻

    }

    private void reqDelete() {
        LogUtils.e("reqDelete-----","reqDelete");
        DeleteDTO gdto=new DeleteDTO();
        gdto.setUserPhone(AppContext.get("mobileNum",""));
        gdto.setNewsCode(item.getNews_code());
        CommonApiClient.delete(this, gdto, new CallBack<InformationResult>() {
            @Override
            public void onSuccess(InformationResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("删除新闻成功");
                }

            }
        });
    }


    private void reqInformation() {
        LogUtils.e("reqInformation-----","reqInformation");
        InformationDTO gdto=new InformationDTO();
        gdto.setUserPhone(AppContext.get("mobileNum",""));
        gdto.setPageSize(PAGE_SIZE);
        gdto.setPageIndex(PAGE_INDEX);
        CommonApiClient.getNews(this, gdto, new CallBack<InformationResult>() {
            @Override
            public void onSuccess(InformationResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("获取新闻成功");
                    mErrorLayout.setErrorMessage("暂无新闻",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                    if(null==result.getData()){
                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }else {
                        entity = result.getData();
                        LogUtils.e("entity----",""+entity);
                        LogUtils.e("entity.size()=",""+entity.size());
                        for(int i = 0;i<entity.size();i++){
                            tList.add(i,entity.get(i).getIsread());
                        }
                        mAdapter = new NewsAdapter(getActivity(),entity,tList);
                        mListView.setAdapter(mAdapter);

                    }

                }

            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
