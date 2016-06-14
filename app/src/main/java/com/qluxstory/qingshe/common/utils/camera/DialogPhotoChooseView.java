package com.qluxstory.qingshe.common.utils.camera;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.qluxstory.qingshe.R;

/**
 * Created by lenovo on 2016/6/13.
 */
public class DialogPhotoChooseView extends MyDialogView {
    private Button btnCamera, btnAlbum, btnCancle;
    private OnClickListener cameraListener, ablumListener;

    public DialogPhotoChooseView(Context context) {
        super(context);
        initView();
    }

    public DialogPhotoChooseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        removeAllViews();
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.widget_choose_picture, null);
        btnCamera = (Button) layout.findViewById(R.id.btn_take_picture);
        btnAlbum = (Button) layout.findViewById(R.id.btn_choose_picture);
        btnCancle = (Button) layout.findViewById(R.id.btn_cancel);
        btnCamera.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(cameraListener != null) {
                    cameraListener.onClick(v);
                }
                if (mExitDialog != null)
                    mExitDialog.exitDialog();
            }
        });
        btnAlbum.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(ablumListener != null) {
                    ablumListener.onClick(v);
                }
                if (mExitDialog != null)
                    mExitDialog.exitDialog();
            }
        });
        btnCancle.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mExitDialog != null)
                    mExitDialog.exitDialog();
            }
        });
        this.addView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    /**
     * 照相
     * @param clickListener
     * @return
     */
    public DialogPhotoChooseView setCameraListener(OnClickListener clickListener) {
        cameraListener = clickListener;
        return this;
    }

    /**
     * 从相册选择
     * @param clickListener
     * @return
     */
    public DialogPhotoChooseView setAlbumListener(OnClickListener clickListener) {
        ablumListener = clickListener;
        return this;
    }
}
