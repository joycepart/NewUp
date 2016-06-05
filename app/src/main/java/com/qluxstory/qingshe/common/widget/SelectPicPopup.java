package com.qluxstory.qingshe.common.widget;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.PhotoSystemUtils;

import java.io.File;

import butterknife.Bind;

/**
 * Created by lenovo on 2016/6/1.
 */
public class SelectPicPopup extends Activity implements OnClickListener {
    @Bind(R.id.btn_alter_pic_camera)
    TextView mCamera;
    @Bind(R.id.btn_alter_pic_photo)
    TextView mPhoto;
    @Bind(R.id.btn_alter_exit)
    TextView mExit;
    private Intent intent;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_pic);
        intent = getIntent();
        mCamera = (TextView) this.findViewById(R.id.btn_alter_pic_camera);
        mPhoto = (TextView) this.findViewById(R.id.btn_alter_pic_photo);
        mExit = (TextView) this.findViewById(R.id.btn_alter_exit);

        // 添加按钮监听
        mCamera.setOnClickListener(this);
        mPhoto.setOnClickListener(this);
        mExit.setOnClickListener(this);
    }

    // 实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        LogUtils.e("data-----------",data+"");
        if(data != null) {
            //选择完或者拍完照后会在这里处理，然后我们继续使用setResult返回Intent以便可以传递数据和调用
            if (data.getExtras() != null) {
                intent.putExtras(data.getExtras());
            }
            if (data.getData() != null) {
                intent.setData(data.getData());
            }
            setResult(1, intent);
        }
        finish();

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alter_pic_camera:
                try {
                    //拍照我们用Action为MediaStore.ACTION_IMAGE_CAPTURE，
                    //有些人使用其他的Action但我发现在有些机子中会出问题，所以优先选择这个
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 判断存储卡是否可用，存储照片文件
                    if (PhotoSystemUtils.hasSdcard()) {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                                .fromFile(new File(Environment
                                        .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                    }
                    startActivityForResult(intent, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_alter_pic_photo:
                try {
                    //选择照片的时候也一样，我们用Action为Intent.ACTION_GET_CONTENT，
                    //有些人使用其他的Action但我发现在有些机子中会出问题，所以优先选择这个
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 2);
                } catch (ActivityNotFoundException e) {

                }
                break;
            case R.id.btn_alter_exit:
                finish();
                break;
            default:
                break;
        }

    }
}
