package com.qluxstory.qingshe.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.bean.ViewFlowBean;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;

import java.util.ArrayList;

/**
 * 轮播图
 */
public class SplViewFlowLayout extends RelativeLayout {
    private Context context_;
    private ViewFlipper flipper;
    private LinearLayout linear;
    private float startX;

    public SplViewFlowLayout(Context context) {
        super(context);
        this.context_ = context;
        initLayout();
    }

    public SplViewFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context_ = context;
        initLayout();
    }

    private void initLayout() {
        LayoutInflater inflater = LayoutInflater.from(context_);
        View viewfolw = inflater.inflate(R.layout.view_viewflow_spl_layout, null);
        addView(viewfolw, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        flipper = (ViewFlipper) viewfolw.findViewById(R.id.spl_vf_flipper);
        linear = (LinearLayout) viewfolw.findViewById(R.id.spl_linear);

    }

    private void startListen() {
        autoScrollHandler.removeCallbacks(runnable);
        autoScrollHandler.postDelayed(runnable, 3000);
    }

    private Handler autoScrollHandler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            myShowNext();
        }
    };

    private void myShowNext() {
        flipper.setInAnimation(context_, R.anim.push_left_in);
        flipper.setOutAnimation(context_, R.anim.push_left_out);
        flipper.showNext();
        setupDotImage();
        startListen();
    }

    private void myShowPrevious() {
        flipper.setInAnimation(context_, R.anim.push_right_in);
        flipper.setOutAnimation(context_, R.anim.push_right_out);
        flipper.showPrevious();
        setupDotImage();
        startListen();
    }

    private void setupDotImage() {
        int currentIndex = flipper.getDisplayedChild();
        if (linear.getChildCount() != 0) {
            for (int i = 0; i < linear.getChildCount(); i++) {
                if (i == currentIndex) {
                    ((ImageView) linear.getChildAt(i)).setImageResource(R.drawable.discount_dot_sel);
                } else {
                    ((ImageView) linear.getChildAt(i)).setImageResource(R.drawable.discount_dot_unsel);
                }

            }
        }

    }


    int count;


    public void setLoadCompleteListener(LoadCompleteListener loadCompleteListener) {
        this.loadCompleteListener = loadCompleteListener;
    }

    LoadCompleteListener loadCompleteListener;
    public interface  LoadCompleteListener{
        void loadComplete();
    }

    int cc=0;
    int ccsize=0;

    public void checkSplView(){
        if(cc==ccsize&&loadCompleteListener!=null){
            loadCompleteListener.loadComplete();
        }
    }

    public void updateSplView(ArrayList<ViewFlowBean> beans) {
        flipper.removeAllViews();
        linear.removeAllViews();
        final int size = beans.size();
        ccsize=size;
        for (int i = 0; i < size; i++) {
            final ViewFlowBean bean = beans.get(i);
            final ImageView imageView = new ImageView(context_);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setTag(bean);
            LogUtils.e("bean----",""+bean);
            ImageLoader.getInstance().loadImage(bean.getImgUrl(), ImageLoaderUtils.getDefaultOptions(), new SimpleImageLoadingListener() {

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    imageView.setImageBitmap(loadedImage);
                    flipper.addView(imageView);
                    startListen();
                    cc++;
                    checkSplView();
                }

            });
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}
