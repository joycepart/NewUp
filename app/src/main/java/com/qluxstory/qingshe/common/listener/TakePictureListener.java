package com.qluxstory.qingshe.common.listener;

import android.app.Activity;
import android.view.View;

import com.qluxstory.qingshe.common.utils.CameraUtils;
import com.qluxstory.qingshe.common.utils.ToastUtils;

/**
 * Created by lenovo on 2016/6/13.
 */
public class TakePictureListener implements View.OnClickListener  {
    private Activity context;
    public TakePictureListener(Activity context){
        this.context = context;
    }
    @Override
    public void onClick(View v) {
        try {
            context.startActivityForResult(CameraUtils
                            .takePicture(context),
                    CameraUtils.REQUEST_CODE_TAKE_PICTURE);
        }catch (Exception e){
            ToastUtils.showShort(context, "当前模式拍照不可用");
            e.printStackTrace();
        }

    }
}
