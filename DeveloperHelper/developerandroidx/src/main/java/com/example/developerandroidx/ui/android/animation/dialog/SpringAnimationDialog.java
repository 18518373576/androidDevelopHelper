package com.example.developerandroidx.ui.android.animation.dialog;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/28 9:08
 * 参考: https://developer.android.google.cn/guide/topics/graphics/spring-animation
 * https://github.com/yizhanzjz/springdemo
 * 描述: 弹簧效果动画
 * <p>
 * 调用 start()，或调用 animateToFinalPosition() 方法。这两种方法都需要对主线程调用。
 * <p>
 * animateToFinalPosition() 方法会执行两项任务：
 * <p>
 * 设置弹簧的最终位置。
 * 启动动画（如果尚未启动）。
 * 由于此方法会更新弹簧的最终位置并根据需要启动动画，因此您可以随时通过调用此方法来更改动画过程。
 * 例如，在链接的弹簧动画中，一个视图的动画依赖于另一个视图。对于此类动画，使用 animateToFinalPosition()
 * 方法更为便捷。在链接的弹簧动画中使用此方法后，您便无需担心接下来要更新的动画当前是否正在运行。
 */
public class SpringAnimationDialog implements FunctionDialogInterface, CompoundButton.OnCheckedChangeListener {
    private ImageView iv_basketball;
    private CheckBox cb_scale, cb_translation, cb_rotate, cb_alpha;

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_spring_animation, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {

                iv_basketball = rootView.findViewById(R.id.iv_basketball);
                cb_scale = rootView.findViewById(R.id.cb_scale);
                cb_translation = rootView.findViewById(R.id.cb_translation);
                cb_rotate = rootView.findViewById(R.id.cb_rotate);
                cb_alpha = rootView.findViewById(R.id.cb_alpha);

                cb_scale.setOnCheckedChangeListener(SpringAnimationDialog.this);
                cb_translation.setOnCheckedChangeListener(SpringAnimationDialog.this);
                cb_rotate.setOnCheckedChangeListener(SpringAnimationDialog.this);
                cb_alpha.setOnCheckedChangeListener(SpringAnimationDialog.this);

            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        SpringAnimation animationProperty_01 = null;//执行动画的视图属性1
        SpringAnimation animationProperty_02 = null;//执行动画的视图属性2，例：scaleX，scaleY
        switch (buttonView.getId()) {
            case R.id.cb_scale://缩放
                if (isChecked) {
                    //可以这样定义Animation
//                    animationProperty_01 = new SpringAnimation(iv_basketball, new FloatPropertyCompat<ImageView>("scaleX") {
//                        @Override
//                        public float getValue(ImageView object) {
//                            return object.getScaleX();
//                        }
//
//                        @Override
//                        public void setValue(ImageView object, float value) {
//
//                            object.setScaleX(value);
//                        }
//                    }, 2.0f);

                    //也可以这样定义Animation
                    animationProperty_01 = new SpringAnimation(iv_basketball, DynamicAnimation.SCALE_X, 2.0f);
                    animationProperty_02 = new SpringAnimation(iv_basketball, DynamicAnimation.SCALE_Y, 2.0f);
                } else {
                    animationProperty_01 = new SpringAnimation(iv_basketball, DynamicAnimation.SCALE_X, 1.0f);
                    animationProperty_02 = new SpringAnimation(iv_basketball, DynamicAnimation.SCALE_Y, 1.0f);
                }
                //不设置无效果
                animationProperty_01.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE);
                animationProperty_02.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE);

                break;
            case R.id.cb_translation://平移
                animationProperty_01 = new SpringAnimation(iv_basketball, DynamicAnimation.TRANSLATION_Y);
                //设置Z轴无效果
                if (isChecked) {
                    animationProperty_01.setSpring(new SpringForce(iv_basketball.getTranslationY() - 300));
                } else {
                    animationProperty_01.setSpring(new SpringForce(iv_basketball.getTranslationY() + 300));
                }
                animationProperty_01.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_PIXELS);
                break;
            case R.id.cb_rotate://旋转
                animationProperty_01 = new SpringAnimation(iv_basketball, DynamicAnimation.ROTATION_Y);
                if (isChecked) {
                    animationProperty_01.setSpring(new SpringForce(360));
                } else {
                    animationProperty_01.setSpring(new SpringForce(0));
                }
                animationProperty_01.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_ROTATION_DEGREES);
                break;
            case R.id.cb_alpha://淡入淡出

                animationProperty_01 = new SpringAnimation(iv_basketball, DynamicAnimation.ALPHA);

                if (isChecked) {
                    animationProperty_01.setSpring(new SpringForce(0f));
                } else {
                    animationProperty_01.setSpring(new SpringForce(1f));
                }
                animationProperty_01.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_ALPHA);
                break;
        }
        if (animationProperty_01 != null) {
            animationProperty_01.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
            animationProperty_01.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
            animationProperty_01.start();
        }
        if (animationProperty_02 != null) {
            animationProperty_02.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
            animationProperty_02.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
            animationProperty_02.start();
        }
    }
}
