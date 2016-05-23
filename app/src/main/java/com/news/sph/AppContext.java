package com.news.sph;

import android.content.Context;
import android.content.SharedPreferences;

import com.news.sph.common.base.BaseApplication;
import com.news.sph.common.utils.SerializableUtils;
import com.news.sph.me.entity.User;

import java.io.IOException;
import java.io.StreamCorruptedException;


/**
 */
public class AppContext  extends BaseApplication {
    private static AppContext instance;
    public static final String USER = "user";
    private static User mUser = null;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    /**
     * 清单文件名称
     */
    public static final String SP_NAME = "qingshe_app";

//    public AppContext(Context mContext) {
//        this.sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
//        this.editor = this.sp.edit();
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.sp = this.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        this.editor = this.sp.edit();
    }

    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }
    /**
     * 保存用户信息
     * @param user
     */
    public void setUser(User user) {
        String str = "";
        try {
            str = SerializableUtils.obj2Str(user);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        this.editor.putString(USER, str);
        this.editor.commit();
        mUser = user;
    }
    /**
     * 获取用户信息
     * @return
     */
    public User getUser() {
        if (mUser == null) {
            mUser = new User();
            // 获取序列化的数据
            String str = this.sp.getString(USER, "");

            try {
                Object obj = SerializableUtils.str2Obj(str);
                if (obj != null) {
                    mUser = (User) obj;
                }

            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mUser;
    }
}
