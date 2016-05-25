package com.news.sph.common.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.news.sph.AppConfig;
import com.news.sph.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;



public class ImageLoaderUtils {


    /**
     * 获取默认图片方法
     *
     * @param imageUrl  图片URL
     * @param imageView 显示图片控件View
     */
    public static void displayImage(String imageUrl, ImageView imageView) {
        if(imageUrl!=null&&!imageUrl.contains("http://")){
            imageUrl= AppConfig.BASE_URL+imageUrl;
        }
        ImageLoader.getInstance().displayImage(imageUrl,
                imageView, getDefaultOptions());
    }

    /**
     * 默认图片加载
     */
    public static DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        return options;
    }

    /**
     * 获取圆形头像
     */
    public static DisplayImageOptions getAvatarOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true).cacheInMemory(true)
                .showImageForEmptyUri(R.drawable.pic_6)
                .showImageOnFail(R.drawable.pic_0)
                .cacheOnDisk(true).displayer(new RoundedBitmapDisplayer(200))
                .considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }

}
