package com.qluxstory.qingshe.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class ImageLoaderUtils {


    /**
     * 获取默认图片方法
     *
     * @param imageUrl  图片URL
     * @param imageView 显示图片控件View
     */
    public static void displayImage(String imageUrl, ImageView imageView) {
        if(imageUrl!=null&&!imageUrl.contains("http://")){
            imageUrl= ((AppConfig.BASE_URL).trim()+imageUrl.trim()).trim();
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
     * 获取圆形图片方法
     *
     * @param imageUrl  图片URL
     * @param imageView 显示图片控件View
     */
    public static void displayAvatarImage(String imageUrl, ImageView imageView) {
        if(imageUrl!=null&&!imageUrl.contains("http://")){
            imageUrl= ((AppConfig.BASE_URL).trim()+imageUrl.trim()).trim();
        }
        ImageLoader.getInstance().displayImage(imageUrl,
                imageView, getAvatarOptions());
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

    public static String bitmaptoString(Bitmap bitmap) {

        // 将Bitmap转换成字符串

        String string = null;

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);

        byte[] bytes = bStream.toByteArray();

        string = Base64.encodeToString(bytes, Base64.DEFAULT);

        return string;

    }

    /**
     * 把图片转换成Base64字符串
     * @param imgPath
     * @param bitmap
     * @param imgFormat 图片格式
     * @return
     */
    public static String imgToBase64(String imgPath, Bitmap bitmap,String imgFormat) {
        if (imgPath !=null && imgPath.length() > 0) {
            bitmap = readBitmap(imgPath);
        }
        if(bitmap == null){
            //bitmap not found!!
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Bitmap readBitmap(String imgPath) {
        try {
            return BitmapFactory.decodeFile(imgPath);
        } catch (Exception e) {
            return null;
        }

    }

}
