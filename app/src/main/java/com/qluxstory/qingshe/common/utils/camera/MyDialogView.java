package com.qluxstory.qingshe.common.utils.camera;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by lenovo on 2016/6/13.
 */
public class MyDialogView extends LinearLayout {
    protected Context context;
    protected ExitDialog mExitDialog;

    public interface ExitDialog {
        public void exitDialog();
    }

    public void setExitDialog(ExitDialog exitDialog) {
        mExitDialog = exitDialog;
    }

    public MyDialogView(Context context) {
        super(context);
        this.context = context;
    }

    public MyDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
}
