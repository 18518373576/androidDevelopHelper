package com.example.developerandroidx.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.R;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.interfaces.OnDismissListener;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.v3.BottomMenu;
import com.kongzue.dialog.v3.FullScreenDialog;
import com.kongzue.dialog.v3.MessageDialog;
import com.kongzue.dialog.v3.TipDialog;
import com.kongzue.dialog.v3.WaitDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @作者： zjf 2020/5/15 9:17
 * @描述： 弹框工具类 使用单例模式减少过多的使用静态方法
 * <p>
 * 错误：Context上下文变量在构造函数进行了赋值，导致上下文不会改变，弹窗失败
 */
public class DialogUtils {

    private DialogUtils() {
    }

    private static class DialogUtilsInstance {
        public static final DialogUtils INSTANCE = new DialogUtils();
    }

    public static DialogUtils getInstance() {
        return DialogUtilsInstance.INSTANCE;
    }

    public interface OnFullScreenDialogBindView {
        void onBind(FullScreenDialog dialog, View rootView);
    }

    //全屏对话框展示,需要处理回调
    public void showFullScreenDialog(Context context, int layoutId, OnFullScreenDialogBindView onFullScreenDialogBindView) {
        FullScreenDialog.show((AppCompatActivity) context, layoutId, new FullScreenDialog.OnBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                if (onFullScreenDialogBindView != null)
                    onFullScreenDialogBindView.onBind(dialog, rootView);
            }
        });
    }

    public interface OnEsvDialogBindView {
        void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content);
    }

    /**
     * 展示扩展scrollView的dialog
     *
     * @param context
     * @param onEsvDialogBindView
     */
    public void showEsvDialog(Context context, OnEsvDialogBindView onEsvDialogBindView) {

        FullScreenDialog.show((AppCompatActivity) context, R.layout.dialog_esv, new FullScreenDialog.OnBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                TextView tv_title = rootView.findViewById(R.id.tv_title);
                ExtensibleScrollView esv_content = rootView.findViewById(R.id.esv_content);
                onEsvDialogBindView.onBind(dialog, tv_title, esv_content);
            }
        });
    }

    //全屏对话框展示,不需要处理回调
    public void showFullScreenDialog(Context context, int layoutId) {
        showFullScreenDialog(context, layoutId, null);
    }

    /**
     * 显示提醒消息
     *
     * @param context
     */
    public void showTip(Context context, String msg) {
        TipDialog.show((AppCompatActivity) context, msg, TipDialog.TYPE.WARNING);
    }

    /**
     * 显示底部menu
     *
     * @param context
     * @param items
     * @param listener
     */
    public BottomMenu showBottomMenu(Context context, String[] items, OnItemClickListener listener) {
        return BottomMenu.show((AppCompatActivity) context, items, new OnMenuItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                listener.onClick(text, index);
            }
        });
    }

    public void showBottomMenu(Context context, String title, String[] items, OnItemClickListener listener) {
        BottomMenu.show((AppCompatActivity) context, title, items, new OnMenuItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                listener.onClick(text, index);
            }
        });
    }

    public interface OnItemClickListener {
        void onClick(String text, int index);
    }

    /**
     * 展示消息对话框
     *
     * @param context
     * @param title
     * @param msg
     */
    public void showMessageDialog(Context context, String title, String msg) {
        MessageDialog.build((AppCompatActivity) context)
                .setTitle(title)
                .setMessage(msg)
                .setStyle(DialogSettings.STYLE.STYLE_IOS)
                .show();
    }

    /**
     * 展示loading框
     *
     * @param context
     * @param loadingMsg
     */
    public void showLoadingDialog(Context context, String loadingMsg) {
        WaitDialog.build((AppCompatActivity) context)
                .setTipTime(1000 * 30)
                .setMessage(loadingMsg)
                .show();
    }

    public void dismissLoadingDialog() {
        WaitDialog.dismiss();
    }
}
