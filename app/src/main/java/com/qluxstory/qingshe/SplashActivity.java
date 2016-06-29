package com.qluxstory.qingshe;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qluxstory.qingshe.common.base.BaseActivity;
import com.qluxstory.qingshe.common.bean.ViewFlowBean;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.widget.SplViewFlowLayout;
import com.qluxstory.qingshe.home.HomeUiGoto;
import com.qluxstory.qingshe.home.dto.SplashDTO;
import com.qluxstory.qingshe.home.entity.SplashResult;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by lenovo on 2016/6/10.
 */
public class SplashActivity extends BaseActivity {
    @Bind(R.id.spl_vf_layout)
    SplViewFlowLayout mVfLayout;
    @Bind(R.id.ad_tv)
    Button mBtn;
    @Bind(R.id.splash_img)
    ImageView mImg;
    int tm;
    private File cache;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        mBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        new Thread(new ThreadShow()).start();


    }

//    Handler tHandler = new Handler();
//    Runnable runnable = new Runnable(){
//        @Override
//        public void run() {
//            // 在此处添加执行的代码
//            handler.postDelayed(runnable, 50);// 50是延时时长
//        }
//    };
//    tHandler.removeCallbacks(runnable);// 关闭定时器处理


    // handler类接收数据
    Handler tHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    reqImgs();
                    break;
            }
        };
    };

    // 线程类
    class ThreadShow implements Runnable {
        @Override
        public void run() {
                try {
                    Thread.sleep(2000);
                    Message msg = new Message();
                    msg.what = 1;
                    tHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    private void reqImgs() {
        // 获取屏幕宽高（方法1）
//        int screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
//        int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）
        SplashDTO dto = new SplashDTO();
        String time = TimeUtils.getSignTime();
        dto.setDevicetype("2");
        dto.setPheight("1920");
        dto.setPwidth("1080");
        dto.setSign(time+AppConfig.SIGN_1);
        dto.setTimestamp(time);
        CommonApiClient.getImgs(this, dto, new CallBack<SplashResult>() {
            @Override
            public void onSuccess(SplashResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("启动页图片成功");
                    if (null == result.getData()) {
                        LogUtils.e("启动页图片成功----","null");
                        HomeUiGoto.gotoMain(SplashActivity.this);
                    }
                    else {
                        tm = result.getData().size();
                        ArrayList<ViewFlowBean> list = new ArrayList<>();
                        for (int i = 0; i < result.getData().size(); i++) {

                            ViewFlowBean bean = new ViewFlowBean();
                            bean.setImgUrl(AppConfig.BASE_URL + result.getData().get(i).getPicturesUrl());
                            list.add(bean);
                        }
                        LogUtils.e("else----",""+result.getData().size());

                        mVfLayout.setLoadCompleteListener(new SplViewFlowLayout.LoadCompleteListener() {
                            @Override
                            public void loadComplete() {
//                                mVfLayout.startListen();
                                preparation();
                            }
                        });

                        mVfLayout.updateSplView(list);
//                        preparation();

                    }

                }

            }
        });


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        LogUtils.e("onClick----","mText");
        gotoMain();
    }

    /**
     * 准备
     */
    private void preparation() {
        mImg.setVisibility(View.GONE);
        mBtn.setVisibility(View.VISIBLE);
        handler = new ReadyHandler(tm*3, new ReadyListener() {

            @Override
            public void onGoMain(int time) {
                if (time > 0) {
                    Button btn = (Button) findViewById(R.id.ad_tv);
                    LogUtils.e("time----","> 0");
                    btn.setText("跳过" + String.valueOf(time));
                } else {
                    gotoMain();
                }
            }
        });
        handler.sendEmptyMessage(1);
    }

    private ReadyHandler handler = null;

    private class ReadyHandler extends Handler {
        private int outTime;
        private ReadyListener listener;

        public ReadyHandler(int outTime, ReadyListener l) {
            this.outTime = outTime;
            this.listener = l;
        }
        public ReadyHandler() {
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    this.listener.onGoMain(--outTime);
                    if (outTime > 0) {
                        handler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
            }

            super.handleMessage(msg);
        }
    }
    private interface ReadyListener {
        void onGoMain(int time);
    }

    public void gotoMain(){
            LogUtils.e("gotoMain----","true");
            HomeUiGoto.gotoMain(SplashActivity.this);
            overridePendingTransition(0, 0);
            finish();

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
