package com.news.sph.widget;

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

import com.news.sph.R;
import com.news.sph.common.bean.ViewFlowBean;
import com.news.sph.utils.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

/**
 * 轮播图
 */
public class ViewFlowLayout extends RelativeLayout {
    private Context context_;
    private ViewFlipper flipper;
    private LinearLayout linear;
    private float startX;

    public ViewFlowLayout(Context context) {
        super(context);
        this.context_ = context;
        initLayout();
    }

    public ViewFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context_ = context;
        initLayout();
    }

    private void initLayout() {
        LayoutInflater inflater = LayoutInflater.from(context_);
        View viewfolw = inflater.inflate(R.layout.view_viewflow_layout, null);
        addView(viewfolw, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        flipper = (ViewFlipper) viewfolw.findViewById(R.id.id_vf);
        linear = (LinearLayout) viewfolw.findViewById(R.id.id_linear);

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

    public void updateView(ArrayList<ViewFlowBean> beans) {
        flipper.removeAllViews();
        linear.removeAllViews();
        final int size = beans.size();
        for (int i = 0; i < size; i++) {
            final ViewFlowBean bean = beans.get(i);
            final ImageView imageView = new ImageView(context_);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(bean);

            ImageLoader.getInstance().loadImage(bean.getImgUrl(), ImageLoaderUtils.getDefaultOptions(), new SimpleImageLoadingListener() {

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    count++;
                    imageView.setImageBitmap(loadedImage);
                    flipper.addView(imageView);

                    ImageView dot = new ImageView(context_);
                    dot.setImageResource(R.drawable.discount_dot_unsel);
                    linear.addView(dot);
                    if (count == size) {
                        flipper.setDisplayedChild(0);
                        ((ImageView) linear.getChildAt(0)).setImageResource(R.drawable.discount_dot_sel);
                        //图片全部加载完毕
                        startListen();

                    }
                }

            });

        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (event.getX() - startX > 100) {
                    myShowPrevious();
                } else if (startX - event.getX() > 100) {
                    myShowNext();
                } else if (Math.abs(startX - event.getX()) <= 10) {
                    if (onItemClickListener != null) {
                        int position = flipper.getDisplayedChild();
                        View itemView = flipper.getChildAt(position);
                        if (itemView != null) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
        }
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
