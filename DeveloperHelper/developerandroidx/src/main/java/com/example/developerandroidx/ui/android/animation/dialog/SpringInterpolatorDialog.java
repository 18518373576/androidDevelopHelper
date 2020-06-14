package com.example.developerandroidx.ui.android.animation.dialog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/28 12:42
 * 参考: https://github.com/yizhanzjz/springdemo
 * 描述: 使用插值器实现弹簧动画效果
 */
public class SpringInterpolatorDialog implements FunctionDialogInterface, CompoundButton.OnCheckedChangeListener {
    private ImageView iv_basketball;
    private CheckBox cb_scale, cb_translation, cb_rotate, cb_alpha;

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_spring_animation, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                TextView textView7 = rootView.findViewById(R.id.textView7);
                textView7.setText("使用Interpolator实现弹簧效果");

                iv_basketball = rootView.findViewById(R.id.iv_basketball);
                cb_scale = rootView.findViewById(R.id.cb_scale);
                cb_translation = rootView.findViewById(R.id.cb_translation);
                cb_rotate = rootView.findViewById(R.id.cb_rotate);
                cb_alpha = rootView.findViewById(R.id.cb_alpha);

                cb_scale.setOnCheckedChangeListener(SpringInterpolatorDialog.this);
                cb_translation.setOnCheckedChangeListener(SpringInterpolatorDialog.this);
                cb_rotate.setOnCheckedChangeListener(SpringInterpolatorDialog.this);
                cb_alpha.setOnCheckedChangeListener(SpringInterpolatorDialog.this);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ObjectAnimator objectAnimator_01 = null;
        ObjectAnimator objectAnimator_02 = null;
        AnimatorSet animatorSet = new AnimatorSet();

        switch (buttonView.getId()) {
            case R.id.cb_scale:
                if (isChecked) {
                    objectAnimator_01 = ObjectAnimator.ofFloat(iv_basketball, "scaleX", 1.0f, 2.0f);
                    objectAnimator_02 = ObjectAnimator.ofFloat(iv_basketball, "scaleY", 1.0f, 2.0f);
                } else {
                    objectAnimator_01 = ObjectAnimator.ofFloat(iv_basketball, "scaleX", 2.0f, 1.0f);
                    objectAnimator_02 = ObjectAnimator.ofFloat(iv_basketball, "scaleY", 2.0f, 1.0f);
                }
                animatorSet.playTogether(objectAnimator_01, objectAnimator_02);
                break;
            case R.id.cb_translation:
                if (isChecked) {
                    objectAnimator_01 = ObjectAnimator.ofFloat(iv_basketball, "translationY", iv_basketball.getTranslationY(), iv_basketball.getTranslationY() - 300);
                } else {
                    objectAnimator_01 = ObjectAnimator.ofFloat(iv_basketball, "translationY", iv_basketball.getTranslationY(), iv_basketball.getTranslationY() + 300);
                }
                animatorSet.playTogether(objectAnimator_01);
                break;
            case R.id.cb_rotate:
                if (isChecked) {
                    objectAnimator_01 = ObjectAnimator.ofFloat(iv_basketball, "rotation", 0, 360);
                } else {
                    objectAnimator_01 = ObjectAnimator.ofFloat(iv_basketball, "rotation", 360, 0);
                }
                animatorSet.playTogether(objectAnimator_01);
                break;
            case R.id.cb_alpha:
                if (isChecked) {
                    objectAnimator_01 = ObjectAnimator.ofFloat(iv_basketball, "alpha", 1, 0);
                } else {
                    objectAnimator_01 = ObjectAnimator.ofFloat(iv_basketball, "alpha", 0, 1);
                }
                animatorSet.playTogether(objectAnimator_01);
                break;
        }

        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new SpringInterpolator(0.3f));
        animatorSet.start();
    }

    private class SpringInterpolator implements Interpolator {

        private float factor;

        /**
         * @param factor 数值为0-1；数值越小回弹次数越多
         */
        public SpringInterpolator(float factor) {
            this.factor = factor;
        }

        @Override
        public float getInterpolation(float input) {
            //factor = 0.4
//        pow(2, -10 * x) * sin((x - factor / 4) * (2 * PI) / factor) + 1

//            LogUtils.e("插值器input值：", input + "#" + (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1) + "");
            return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
        }
    }
}
