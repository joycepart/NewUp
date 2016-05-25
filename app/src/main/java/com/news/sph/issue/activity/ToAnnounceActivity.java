package com.news.sph.issue.activity;

import android.view.View;
import android.widget.ListView;

import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.issue.adapter.ToAnnounceAdapter;
import com.news.sph.common.utils.ToastUtils;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 往期揭晓主页面
 */
public class ToAnnounceActivity extends BaseTitleActivity {
    @Bind(R.id.ptr_frame_issue_toannounce)
    PtrFrameLayout ptr_frame_issue_toannounce;
    @Bind(R.id.issue_toannounce_list)
    ListView issue_toannounce_list;
    private String[] str1= new String[] {
            "1","2","3","4","5","6","7","8",
    };
    private String[] str2= new String[] {
            "2016-05-16 13:58:29.790","2016-05-17 13:58:29.790","2016-05-18 13:58:29.790",
            "2016-05-19 13:58:29.790","2016-05-20 13:58:29.790","2016-05-21 13:58:29.790","2016-05-22 13:58:29.790","2016-05-23 13:58:29.790",
    };
    private String[] str3= new String[] {
            "12345678900","12345678901","12345678902","12345678903","12345678904","12345678905","12345678906","12345678907",
    };
    private String[] str4= new String[] {
            "12345678900","12345678901","12345678902","12345678903","12345678904","12345678905","12345678906","12345678907",
    };
    private String[] str5= new String[] {
            "12345678900","12345678901","12345678902","12345678903","12345678904","12345678905","12345678906","12345678907",
    };



    @Override
    protected int getContentResId() {
        return R.layout.activity_issue_toannounce;
    }

    @Override
    public void initView() {
        setTitleText("往期揭晓");
        issue_toannounce_list.setAdapter(new ToAnnounceAdapter(getApplicationContext(),str1,str2,str3,str4,str5));
        ptr_frame_issue_toannounce.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ToastUtils.showShort(getApplicationContext(), "开始刷新");
                ptr_frame_issue_toannounce.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShort(getApplicationContext(), "刷新完成");
                        ptr_frame_issue_toannounce.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }

    @Override
    public void initData() {

    }
}
