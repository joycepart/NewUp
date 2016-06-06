package com.qluxstory.qingshe.home.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.R;

/**
 * Created by lenovo on 2016/6/4.
 */
public class VerticalScrollTextActivity extends Activity {

    String[] texts=new String[]{"学朴槿惠穿衣风格","能穿出N中风格的打底衫","卫衣+小短裤,穿出时尚风格","奇葩零食大盘点"};

    private int flag=0;//0代表 text1 out
    private int index=2;
    LinearLayout ll_text1,ll_text2;
    TextView ll_text1_tv,ll_text2_tv;
    Animation in,out;
    Handler mHandler=new Handler();

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (flag==0) {
                ll_text1.startAnimation(out);
                ll_text2_tv.setText(texts[index++%texts.length]);
                ll_text2.startAnimation(in);
                flag=1;
            }else {
                ll_text1_tv.setText(texts[index++%texts.length]);
                ll_text1.startAnimation(in);
                ll_text2.startAnimation(out);
                flag=0;
            }
            mHandler.postDelayed(this,5000);
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);
        ll_text1=(LinearLayout) findViewById(R.id.ll_text1);
        ll_text2=(LinearLayout) findViewById(R.id.ll_text2);
        ll_text1_tv=(TextView) findViewById(R.id.ll_text1_tv);
        ll_text2_tv=(TextView) findViewById(R.id.ll_text2_tv);

        in= AnimationUtils.loadAnimation(this, R.anim.in);
        out=AnimationUtils.loadAnimation(this, R.anim.out);
        mHandler.postDelayed(runnable,5000);
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(runnable);
        super.onStop();
    }

}
