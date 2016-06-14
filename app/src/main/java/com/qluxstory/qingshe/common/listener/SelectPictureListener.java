package com.qluxstory.qingshe.common.listener;

import android.app.Activity;
import android.view.View;

import com.qluxstory.qingshe.common.utils.CameraUtils;

/**
 * Created by lenovo on 2016/6/13.
 */
public class SelectPictureListener implements View.OnClickListener {
    private Activity context;
    public SelectPictureListener(Activity context){
        this.context = context;
    }
    @Override
    public void onClick(View v) {
        context.startActivityForResult(CameraUtils
                        .selectPicture(context),
                CameraUtils.REQUEST_CODE_SELECT_PICTURE);
    }
}
