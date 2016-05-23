package com.news.sph;

import com.news.sph.common.base.BaseActivity;

public class MainActivity extends BaseActivity {
   // TextView tv git add . ;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
//        tv=(TextView)findViewById(R.id.id_tv);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UIHelper.showFragment(MainActivity.this, SimplePage.SIMPLE_LIST_TEST);
//            }
//        });
    }

    @Override
    public void initData() {
        loadData();
    }

    public void loadData(){
        /**
         * 模拟加载数据
         */
        mLoadingAndRetryManager.showLoading();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                int v = new Random().nextInt(3);
//
//                if (v == 0) {
//                    mLoadingAndRetryManager.showRetry();
//                } else if (v == 1) {
//                    mLoadingAndRetryManager.showContent();
//                } else {
//                    mLoadingAndRetryManager.showEmpty();
//                }
                mLoadingAndRetryManager.showContent();
            }
        }.start();
    }

    public void onRetry(){
        loadData();
    }
}
