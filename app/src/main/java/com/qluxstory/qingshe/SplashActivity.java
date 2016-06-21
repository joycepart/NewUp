package com.qluxstory.qingshe;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.common.base.BaseActivity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.home.HomeUiGoto;
import com.qluxstory.qingshe.home.dto.SplashDTO;
import com.qluxstory.qingshe.home.entity.SplashResult;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by lenovo on 2016/6/10.
 */
public class SplashActivity extends BaseActivity {
    private int currentItem = 0; // 当前图片的索引号
    private GestureDetector gestureDetector; // 用户滑动
    /** 记录当前分页ID */
    private int flaggingWidth;// 互动翻页所需滚动的长度是当前屏幕宽度的1/3
    private View mSplashImg;
    ViewPager mViewPager;
    ViewGroup mViewGroup;
    TextView mText;
    private String[] imgIdArray;
    private ImageView[] tips;
    public static final int ANIM_END = 10;
    public static final int ANIM_END_NO = 11;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        gestureDetector = new GestureDetector(new GuideViewTouch());
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        flaggingWidth = dm.widthPixels / 3;
        mSplashImg = findViewById(R.id.common_splash_img);

    }

    boolean endcompleted=false;

    @Override
    public void initData() {
        mViewGroup = (ViewGroup) findViewById(R.id.ad_viewgroup);
        mViewPager = (ViewPager) findViewById(R.id.ad_viewpager);
        mText = (TextView) findViewById(R.id.ad_tv);
        mText.setVisibility(View.GONE);
        reqImgs();//启动页图片

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
                        HomeUiGoto.gotoMain(SplashActivity.this);
                    }
                    else {
                    if (result.getData().size() > 0 && result.getData() != null) {
                        imgIdArray = new String[result.getData().size()];
                        for (int i = 0; i < result.getData().size(); i++) {
                            imgIdArray[i] = result.getData().get(i).getPicturesUrl();
                        }
                        tips = new ImageView[imgIdArray.length];
                        if (tips.length > 1) {
                            for (int i = 0; i < tips.length; i++) {
                                ImageView imageView = new ImageView(
                                        SplashActivity.this);
                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT);
                                lp.setMargins(10, 10, 10, 10);
                                imageView.setLayoutParams(lp);
                                tips[i] = imageView;
                                tips[i].getLayoutParams().height = 16;
                                tips[i].getLayoutParams().width = 16;
                                if (i == 0) {
                                    tips[i].setBackgroundResource(R.drawable.widget_dot_selected);
                                } else {
                                    tips[i].setBackgroundResource(R.drawable.widget_dot_normal);
                                }
                                // 修改引导界面后取消加入的小点
                                mViewGroup.addView(imageView);


                            }
                        }
                        // 设置Adapter
                        mViewPager.setAdapter(new MyPagerAdapter());
                        // 设置监听，主要是设置点点的背景
//                        mViewPager.setOnPageChangeListener(SplashActivity.this);
                        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this,
                                R.anim.splash_fade); // 动画对象
                        mText.setVisibility(View.VISIBLE);
                        mText.setAnimation(animation);
                        mText.startAnimation(animation);
                        mText.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                gotoMain();
                            }
                        });
                        Animation splashAnimation = AnimationUtils.loadAnimation(
                                SplashActivity.this, R.anim.splash_fade);
                        splashAnimation.setStartOffset(1000);
                        splashAnimation.setFillAfter(true);
                        mSplashImg.setAnimation(splashAnimation);
                        splashAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                Message msg = new Message();
                                msg.what = ANIM_END;
                                handler.sendMessage(msg);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    } else {
                        Animation splashAnimation = AnimationUtils.loadAnimation(
                                SplashActivity.this, R.anim.splash_fade);
                        splashAnimation.setStartOffset(1000);
                        splashAnimation.setFillAfter(true);
                        mSplashImg.setAnimation(splashAnimation);
                        splashAnimation.start();
                        splashAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                handler = new ReadyHandler();
                                Message msg = new Message();
                                msg.what = ANIM_END_NO;
                                handler.sendMessage(msg);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                    }
                }

                }




            }
        });


    }

    private class GuideViewTouch extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            if (currentItem == 3) {
                if (Math.abs(e1.getX() - e2.getX()) > Math.abs(e1.getY()
                        - e2.getY())
                        && (e1.getX() - e2.getX() <= (-flaggingWidth) || e1
                        .getX() - e2.getX() >= flaggingWidth)) {
                    if (e1.getX() - e2.getX() >= flaggingWidth) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgIdArray.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(((View) object));
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = View.inflate(SplashActivity.this,
                    R.layout.splash_pager, null);
            final ImageView iv = (ImageView) view
                    .findViewById(R.id.common_ad_img);

            Button button = (Button) view.findViewById(R.id.common_splash_btn);
            Animation animation = AnimationUtils.loadAnimation(SplashActivity.this,
                    R.anim.splash_fade); // 动画对象
            button.setAnimation(animation);
            button.startAnimation(animation);
            ImageLoaderUtils.displayImage(imgIdArray[position], iv);
            if (position == imgIdArray.length - 1) {
                // button.setVisibility(View.VISIBLE);
                preparation();
            } else {
                if (handler != null) {
                    handler.sendEmptyMessage(2);
                }
            }

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    gotoMain();
                }
            });

            container.addView(view);
            return view;
        }

    }

    /**
     * 准备
     */
    private void preparation() {
        handler = new ReadyHandler(9, new ReadyListener() {

            @Override
            public void onGoMain(int time) {
                if (time > 0) {
                    mText.setText("跳过" + String.valueOf(time));
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
                case ANIM_END:
                    mSplashImg.setVisibility(View.GONE);
                    if (outTime > 0) {
                        handler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
                case ANIM_END_NO:
                    gotoMain();
                    break;
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
        if(!endcompleted){
            endcompleted=true;
            HomeUiGoto.gotoMain(SplashActivity.this);
            overridePendingTransition(0, 0);
            finish();
        }

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
