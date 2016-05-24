package com.news.sph.home.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.issue.activity.ToAnnounceActivity;
import com.news.sph.issue.adapter.ProductDetailsAdapter;
import com.news.sph.utils.LayoutUtil;
import com.news.sph.utils.TDevice;
import com.news.sph.utils.ToastUtils;
import com.news.sph.utils.ViewScrollConflictUtil;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 商品详情主页面
 */
public class ProductDetailsActivity extends BaseTitleActivity {

    @Bind(R.id.ptr_frame_issue_product)
    PtrFrameLayout ptr_frame_issue_product;
    @Bind(R.id.issue_product_list)
    ListView issue_product_list;
    LinearLayout issue_product_ll01,issue_product_ll02;
    Button product_Btn;
    private String[] str1= new String[] {
            "12345678900","12345678901","12345678902","12345678903","12345678904","12345678905","12345678906","12345678907",
    };

    private String[] str2= new String[] {
            "1","2","3","4","5","6","7","8",
    };
    private String[] str3= new String[] {
            "2016-05-16 13:58:29.790","2016-05-17 13:58:29.790","2016-05-18 13:58:29.790",
            "2016-05-19 13:58:29.790","2016-05-20 13:58:29.790","2016-05-21 13:58:29.790","2016-05-22 13:58:29.790","2016-05-23 13:58:29.790",
    };


    @Override
    protected int getContentResId() {
        return R.layout.activity_issue_productdetail;
    }

    @Override
    public void initView() {
        setTitleText("商品详情");

        View header = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.view_issue_product, null);
        View foot = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.item_indiana_productdetail_bottom, null);
        issue_product_ll01 = (LinearLayout) findViewById(R.id.issue_product_ll01);
        issue_product_ll02 = (LinearLayout) findViewById(R.id.issue_product_ll02);
//        issue_product_ll01.setOnClickListener(this);
//        issue_product_ll02.setOnClickListener(this);
        issue_product_list.addHeaderView(header);
        issue_product_list.addFooterView(foot);
        product_Btn = (Button) findViewById(R.id.product_Btn);
//        product_Btn.setOnClickListener(this);
        issue_product_list.setAdapter(new ProductDetailsAdapter(getApplicationContext(),str1,str2,str3));
        ptr_frame_issue_product.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ToastUtils.showShort(getApplicationContext(), "开始刷新");
                ptr_frame_issue_product.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShort(getApplicationContext(), "刷新完成");
                        ptr_frame_issue_product.refreshComplete();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.issue_product_ll01:
                break;
            case R.id.issue_product_ll02:
                Intent intent2 = new Intent(getApplicationContext(),ToAnnounceActivity.class);
                startActivity(intent2);
                break;
            case R.id.product_Btn:
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.base_titlebar_ensure:
                baseGoEnsure();
                break;
            default:
                break;
        }
        super.onClick(v);
    }

    @Override
    public void initData() {

    }
}
