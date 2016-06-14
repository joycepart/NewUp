package com.qluxstory.qingshe.common.utils.camera;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by lenovo on 2016/6/13.
 */
public class DropDownList {
    private String[] strArray;
    /**
     * 单选框
     */
    private OnCallBackAlertDialog onCallBackAlertDialog;
    private Context mContext;
    /**
     * 复选框
     */
    private boolean[] selected;
    private OnCallBackMultiDialog onCallBackMultiDialog;

    public DropDownList(Context context) {
        mContext = context;
    }

    /**
     * 单选回调函数
     *
     * @author r
     *
     */
    public interface OnCallBackAlertDialog {
        public void callBack(int position);
    }

    /**
     * 复选回调函数
     *
     * @author r
     *
     */
    public interface OnCallBackMultiDialog {
        public void callBack(int position, boolean[] selected);
    }

    /**
     * 创建普通样式的AlertDialog单选
     *
     * @param title
     * @param array
     * @param callBackAlertDialog
     */
    public void createAlertDialog(String title, String[] array,
                                  OnCallBackAlertDialog callBackAlertDialog) {
        this.strArray = array;
        this.onCallBackAlertDialog = callBackAlertDialog;

        showAlertDialog(title);
    }

    /**
     * 创建普通样式的AlertDialog单选
     *
     * @param title
     * @param strlist
     * @param callBackAlertDialog
     */
    public void createAlertDialog(String title, List<String> strlist,
                                  OnCallBackAlertDialog callBackAlertDialog) {
        this.strArray = strlist.toArray(new String[strlist.size()]);
        this.onCallBackAlertDialog = callBackAlertDialog;
        showAlertDialog(title);
    }

    private void showAlertDialog(String title) {
        AlertDialog dialog = new AlertDialog.Builder(mContext).setTitle(title)
                .setItems(strArray, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onCallBackAlertDialog.callBack(which);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();

        dialog.show();
        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object alertController = mAlert.get(dialog);

            Field mTitleView = alertController.getClass().getDeclaredField(
                    "mTitleView");
            mTitleView.setAccessible(true);

            // TextView text = (TextView) mTitleView.get(alertController);
            // text.setTextColor(0xff33b5e5);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void createMultiDialog(String title, String[] array,
                                  OnCallBackMultiDialog callBackMultiDialog) {
        this.strArray = array;
        this.onCallBackMultiDialog = callBackMultiDialog;
        this.selected = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            selected[i] = false;
        }
        showMultiDialog(title);
    }

    public void createMultiDialog(String title, List<String> strList,
                                  OnCallBackMultiDialog callBackMultiDialog) {
        this.strArray = strList.toArray(new String[strList.size()]);
        this.onCallBackMultiDialog = callBackMultiDialog;
        this.selected = new boolean[strList.size()];
        for (int i = 0; i < strList.size(); i++) {
            selected[i] = false;
        }
        showMultiDialog(title);
    }

    private void showMultiDialog(String title) {
        // TODO Auto-generated method stub
        new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMultiChoiceItems(strArray, selected,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                // TODO Auto-generated method stub
                                selected[which] = isChecked;
                            }
                        })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        StringBuffer str = new StringBuffer("您选中了：");
                        for (int i = 0; i < 3; i++) {
                            if (selected[i]) {
                                str.append(strArray[i] + ",");
                            }
                        }
                        onCallBackMultiDialog.callBack(which, selected);
                        str.deleteCharAt(str.length() - 1);
                        Toast.makeText(mContext, str, Toast.LENGTH_SHORT)
                                .show();
                    }
                }).setNegativeButton("取消", null).show();
    }

    /**
     * 创建向上渐入向下渐出了dialog
     *
     * @param context
//     * @param 自定义布局继承于MyDialogView
     * @return
     */
//    public static Dialog showDialog(Context context, MyDialogView view) {
//
//        final Dialog dlg = new Dialog(context, R.style.MMTheme_DataSheet);
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//        view.setExitDialog(new MyDialogView.ExitDialog() {
//
//            @Override
//            public void exitDialog() {
//                dlg.dismiss();
//            }
//        });
//        WindowManager m = ((Activity) context).getWindowManager();
//        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
//
//        dlg.setCanceledOnTouchOutside(true);
//        dlg.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//        dlg.show();
//        Window w = dlg.getWindow();
//        WindowManager.LayoutParams lp = w.getAttributes();
//        lp.x = 0;
//        final int cMakeBottom = -1000;
//        lp.y = cMakeBottom;
//        lp.gravity = Gravity.BOTTOM;
//        lp.width = d.getWidth();
//        dlg.onWindowAttributesChanged(lp);
//        return dlg;
//
//
//    }
}
