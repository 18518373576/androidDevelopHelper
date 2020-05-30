package com.example.developerandroidx.ui.android.animation.dialog;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.transition.ChangeBounds;
import androidx.transition.Scene;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/29 15:52
 * 参考:
 * 描述: 场景切换动画
 */
public class SceneTranSitionDialog implements FunctionDialogInterface, View.OnClickListener {
    private FrameLayout fl_root_scene;
    private ImageView iv_switch_to;
    private Scene scene1;
    private Scene scene2;

    private Scene currentScene;

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_scene_transition, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                fl_root_scene = rootView.findViewById(R.id.fl_root_scene);
                iv_switch_to = rootView.findViewById(R.id.iv_switch_to);
                iv_switch_to.setOnClickListener(SceneTranSitionDialog.this);

                scene1 = Scene.getSceneForLayout(fl_root_scene, R.layout.scene_layout_1, context);
                scene2 = Scene.getSceneForLayout(fl_root_scene, R.layout.scene_layout_2, context);
                currentScene = scene1;
            }
        });
    }

    @Override
    public void onClick(View v) {
        TransitionSet transitionSet = new TransitionSet();
        /**
         * ChangeBounds检测view的位置边界创建移动和缩放动画
         * ChangeTransform检测view的scale和rotation创建缩放和旋转动画
         * ChangeClipBounds检测view的剪切区域的位置边界，和ChangeBounds类似。不过ChangeBounds针对的是view而ChangeClipBounds针对的是view的剪切区域(setClipBound(Rect rect) 中的rect)。如果没有设置则没有动画效果
         * ChangeImageTransform检测ImageView（这里是专指ImageView）的尺寸，位置以及ScaleType，并创建相应动画。
         *
         *
         *  TransitionManager.go()会把scene1和scene2的id相同的view平移过去
         *  还可以使用以下元素进行匹配，此处未做实现
         *  instance 匹配同一引用
         *  transitionName 匹配同一transitionName
         *  itemId 匹配ListView中adapter id
         */
        transitionSet.addTransition(new ChangeBounds());
        if (currentScene == scene1) {
            TransitionManager.go(scene2, transitionSet);
            currentScene = scene2;
        } else {
            TransitionManager.go(scene1, transitionSet);
            currentScene = scene1;
        }
    }
}
