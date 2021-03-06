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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.URL;


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
                .showImageForEmptyUri(R.drawable.logo)
                .showImageOnFail(R.drawable.logo)
                .cacheOnDisk(true).displayer(new RoundedBitmapDisplayer(200))
                .considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }

    public static String bitmaptoString(Bitmap bitmap) {

        // 将Bitmap转换成base64字符串

        String string = null;

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);

        byte[] bytes = bStream.toByteArray();

        string = Base64.encodeToString(bytes, Base64.DEFAULT);

        return string;

    }

    /**
     * 把图片转换成Base64字符串,其中要进行压缩图片
     * @param imgPath
     * @param bitmap
     * @param imgFormat 图片格式
     * @return
     */
    public static String imgToBase64(String imgPath, Bitmap bitmap,String imgFormat) {
        Bitmap bm = null;
        if (imgPath !=null && imgPath.length() > 0) {
            bitmap = readBitmap(imgPath);
            bm = PhotoSystemUtils.comp(bitmap);
            LogUtils.e("bitmap",""+bitmap);
        }
        if(bitmap == null){
            //bitmap not found!!
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);

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

    public static Bitmap readBitmap(String imgPath) {
        try {
            return BitmapFactory.decodeFile(imgPath);
        } catch (Exception e) {
            return null;
        }

    }

    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }


    /**
     * 得到本地或者网络上的bitmap url - 网络或者本地图片的绝对路径,比如:
     *
     * A.网络路径: url="http://blog.foreverlove.us/girl2.png" ;
     *
     * B.本地路径:url="file://mnt/sdcard/photo/image.png";
     *
     * C.支持的图片格式 ,png, jpg,bmp,gif等等
     *
     * @param url
     * @return
     */
    public static Bitmap GetLocalOrNetBitmap(String url)
    {
        Bitmap bitmap = null;
        InputStream in = null;
        BufferedOutputStream out = null;
        try
        {
            in = new BufferedInputStream(new URL(url).openStream(), 2*1024);
            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream, 2*1024);
            copy(in, out);
            out.flush();
            byte[] data = dataStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            data = null;
            return bitmap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static void copy(InputStream in, OutputStream out)
            throws IOException {
        byte[] b = new byte[2*1024];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }


  //读取sd卡下图片，由图片路径转换为bitmap
    public Bitmap convertToBitmap(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int)scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }



}
