package com.example.developerandroidx.ui.android.animation.dialog;

import android.content.Context;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;

/**
 * Date: 2020/5/29 15:52
 * 参考:
 * 描述: 场景切换动画
 */
public class SceneTranSitionDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_scene_transition);
    }
}
