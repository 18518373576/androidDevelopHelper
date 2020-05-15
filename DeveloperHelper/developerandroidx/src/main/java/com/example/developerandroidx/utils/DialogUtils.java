package com.example.developerandroidx.utils;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * @作者： zjf 2020/5/15 9:17
 * @描述： 弹框工具类 使用单例模式减少过多的使用静态方法
 * <p>
 * 使用时犯了一个错误，Context上下文变量在构造函数进行了赋值，导致上下文不会改变，弹窗失败
 */
public class DialogUtils {

    private static DialogUtils dialogUtils;


    private DialogUtils() {
    }

    public static DialogUtils getInstance() {
        if (dialogUtils == null) {
            dialogUtils = new DialogUtils();
        }
        return dialogUtils;
    }

    public interface OnFullScreenDialogBindView {
        void onBind(FullScreenDialog dialog, View rootView);
    }

    //全屏对话框展示,需要处理回调
    public void shouFullScreenDialog(Context context, int layoutId, OnFullScreenDialogBindView onFullScreenDialogBindView) {
        FullScreenDialog.show((AppCompatActivity) context, layoutId, new FullScreenDialog.OnBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                if (onFullScreenDialogBindView != null)
                    onFullScreenDialogBindView.onBind(dialog, rootView);
            }
        });
    }

    //全屏对话框展示,不需要处理回调
    public void shouFullScreenDialog(Context context, int layoutId) {
        shouFullScreenDialog(context, layoutId, null);
    }
}
