package com.qluxstory.qingshe;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.home.HomeUiGoto;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {
    // 定义ViewPager对象
    private ViewPager viewPager;
    // 定义ViewPager适配器
    private ViewPagerAdapter vpAdapter;
    // 定义一个ArrayList来存放View
    private ArrayList<View> views;
    // 引导图片资源
    private static final int[] pics = { R.drawable.guide03, R.drawable.guide02,
            R.drawable.guide01};

    // 记录当前选中位置
    private int currentIndex;
    ImageView iv;
    boolean guide = false;
    ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        guide = AppContext.get("guide",false);
        LogUtils.e("guide----",""+guide);
        if(guide){
            HomeUiGoto.gotoSplash(GuideActivity.this);

        }else {
            guide = true;
            AppContext.set("guide", guide);
            setContentView(R.layout.activity_guide);
            initViews();

        }


    }

    private void initViews() {

        new Thread(new ThreadShow()).start();


    }

    // handler类接收数据
    Handler tHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    initData();
                    break;
            }
        };
    };

    // 线程类
    class ThreadShow implements Runnable {
        @Override
        public void run() {
            try {
                LogUtils.e("ThreadShow----","ThreadShow");
                img = (ImageView) findViewById(R.id.guide_img);
                img.setVisibility(View.VISIBLE);
                // 实例化ViewPager
                viewPager = (ViewPager) findViewById(R.id.viewpager);
                viewPager.setVisibility(View.GONE);
                Thread.sleep(3000);
                Message msg = new Message();
                msg.what = 1;
                tHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initData() {
        LogUtils.e("initData----","initData");
        img.setVisibility(View.GONE);
        viewPager.setVisibility(View.VISIBLE);
        // 实例化ArrayList对象
        views = new ArrayList<View>();

        // 实例化ViewPager适配器
        vpAdapter = new ViewPagerAdapter(views);
        // 定义一个布局并设置参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        // 初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            //防止图片不能填满屏幕
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            //加载图片资源
            iv.setImageResource(pics[i]);
            views.add(iv);

        }

        // 设置数据
        viewPager.setAdapter(vpAdapter);
        // 设置监听
        viewPager.setOnPageChangeListener(this);


    }



    /**
     * 设置当前页面的位置
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        viewPager.setCurrentItem(position);
    }


    // 当滑动状态改变时调用


    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    // 当当前页面被滑动时调用


    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    // 当新的页面被选中时调用


    @Override
    public void onPageSelected(int arg0) {
//        if(arg0 ==2){
//            viewPager.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    HomeUiGoto.gotoSplash(GuideActivity.this);
//                    finish();
//                }
//            });
//
//        }

    }


    class ViewPagerAdapter extends PagerAdapter{
        //界面列表
        private ArrayList<View> views;
        public ViewPagerAdapter(ArrayList<View> views)
        {
            this.views = views;
        }

        /**
         * 获得当前界面数
         */
        @Override
        public int getCount() {
            if (views != null) {
                return views.size();
            }
            else return 0;
        }

        /**
         * 判断是否由对象生成界面
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }

        /**
         * 销毁position位置的界面
         */
        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        /**
         * 初始化position位置的界面
         */
        @Override
        public Object instantiateItem(View container, int position) {
            if(position==2){
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HomeUiGoto.gotoMain(GuideActivity.this);
                        finish();
                    }
                });

            }

            ((ViewPager) container).addView(views.get(position), 0);
            return views.get(position);
        }

    }
    }
