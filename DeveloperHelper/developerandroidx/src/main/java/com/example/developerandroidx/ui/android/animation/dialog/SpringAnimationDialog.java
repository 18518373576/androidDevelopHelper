package com.example.developerandroidx.ui.android.animation.dialog;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/28 9:08
 * 参考:
 * 描述: 弹簧效果动画
 */
public class SpringAnimationDialog implements FunctionDialogInterface, CompoundButton.OnCheckedChangeListener {
    private ImageView iv_basketball;
    private CheckBox cb_scale, cb_transition, cb_rotate, cb_alpha;

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_spring_animation, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {

                iv_basketball = rootView.findViewById(R.id.iv_basketball);
                cb_scale = rootView.findViewById(R.id.cb_scale);
                cb_transition = rootView.findViewById(R.id.cb_transition);
                cb_rotate = rootView.findViewById(R.id.cb_rotate);
                cb_alpha = rootView.findViewById(R.id.cb_alpha);

                cb_scale.setOnCheckedChangeListener(SpringAnimationDialog.this);
                cb_transition.setOnCheckedChangeListener(SpringAnimationDialog.this);
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
                    animationProperty_01 = new SpringAnimation(iv_basketball, new FloatPropertyCompat<ImageView>("scaleX") {
                        @Override
                        public float getValue(ImageView object) {
                            return object.getScaleX();
                        }

                        @Override
                        public void setValue(ImageView object, float value) {

                            object.setScaleX(value);
                        }
                    }, 2.0f);
                    animationProperty_02 = new SpringAnimation(iv_basketball, new FloatPropertyCompat<ImageView>("scaleY") {
                        @Override
                        public float getValue(ImageView object) {
                            return object.getScaleY();
                        }

                        @Override
                        public void setValue(ImageView object, float value) {

                            object.setScaleY(value);
                        }
                    }, 2.0f);
                } else {
                    animationProperty_01 = new SpringAnimation(iv_basketball, new FloatPropertyCompat<ImageView>("scaleX") {
                        @Override
                        public float getValue(ImageView object) {
                            return object.getScaleX();
                        }

                        @Override
                        public void setValue(ImageView object, float value) {

                            object.setScaleX(value);
                        }
                    }, 1.0f);
                    animationProperty_02 = new SpringAnimation(iv_basketball, new FloatPropertyCompat<ImageView>("scaleY") {
                        @Override
                        public float getValue(ImageView object) {
                            return object.getScaleY();
                        }

                        @Override
                        public void setValue(ImageView object, float value) {

                            object.setScaleY(value);
                        }
                    }, 1.0f);
                }
                //不设置无效果
                animationProperty_01.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE);
                animationProperty_02.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE);

                break;
            case R.id.cb_transition:
                animationProperty_01 = new SpringAnimation(iv_basketball, new FloatPropertyCompat<ImageView>("trnsitionY") {
                    @Override
                    public float getValue(ImageView object) {
                        return object.getTranslationY();
                    }

                    @Override
                    public void setValue(ImageView object, float value) {
                        object.setTranslationY(value);
                    }
                });
                //设置Z轴无效果，不知道是不是使用方法不对
                if (isChecked) {
                    animationProperty_01.setSpring(new SpringForce(iv_basketball.getTranslationY() - 300));
                } else {
                    animationProperty_01.setSpring(new SpringForce(iv_basketball.getTranslationY() + 300));
                }
                animationProperty_01.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_PIXELS);
                break;
            case R.id.cb_rotate:
                animationProperty_01 = new SpringAnimation(iv_basketball, new FloatPropertyCompat<ImageView>("rotationY") {
                    @Override
                    public float getValue(ImageView object) {
                        return object.getRotationY();
                    }

                    @Override
                    public void setValue(ImageView object, float value) {
                        object.setRotationY(value);
                    }
                });
                if (isChecked) {
                    animationProperty_01.setSpring(new SpringForce(360));
                } else {
                    animationProperty_01.setSpring(new SpringForce(0));
                }
                animationProperty_01.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_ROTATION_DEGREES);
                break;
            case R.id.cb_alpha:

                animationProperty_01 = new SpringAnimation(iv_basketball, new FloatPropertyCompat<ImageView>("alpha") {
                    @Override
                    public float getValue(ImageView object) {
                        return object.getAlpha();
                    }

                    @Override
                    public void setValue(ImageView object, float value) {
                        object.setAlpha(value);
                    }
                });

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
