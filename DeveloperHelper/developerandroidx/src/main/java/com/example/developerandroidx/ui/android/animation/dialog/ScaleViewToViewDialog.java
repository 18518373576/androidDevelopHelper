package com.example.developerandroidx.ui.android.animation.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * @作者： zjf 2020/5/26 8:28
 * @参考：
 * @描述： 使用缩放动画放大视图
 */
public class ScaleViewToViewDialog implements FunctionDialogInterface {

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_scale_view_to_view, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                ImageView iv_from = rootView.findViewById(R.id.iv_from);
                ImageView iv_to = rootView.findViewById(R.id.iv_to);
            }
        });
    }
}
