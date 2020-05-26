package com.example.developerandroidx.ui.android.animation.dialog;

import android.content.Context;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;

/**
 * Date: 2020/5/24 16:38
 * 参考:
 * 描述: 使用此动画，可以实现控件状态切换以动画的形式过渡
 */
public class AnimatedStateListDrawableDialog implements FunctionDialogInterface {

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_animated_state_list_drawable);
    }
}
