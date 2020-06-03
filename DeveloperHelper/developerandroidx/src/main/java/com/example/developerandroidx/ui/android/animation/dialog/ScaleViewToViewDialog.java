package com.example.developerandroidx.ui.android.animation.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
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

    private ImageView iv_from;
    private ImageView iv_to;
    private Property startProperty;
    private Property toProperty;
    private FromLayoutListener fromLayoutListener;
    private ToLayoutListener toLayoutListener;

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_scale_view_to_view, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                iv_from = rootView.findViewById(R.id.iv_from);
                iv_to = rootView.findViewById(R.id.iv_to);
                fromLayoutListener = new FromLayoutListener();
                iv_from.getViewTreeObserver().addOnGlobalLayoutListener(fromLayoutListener);
                toLayoutListener = new ToLayoutListener();
                iv_to.getViewTreeObserver().addOnGlobalLayoutListener(toLayoutListener);

                iv_from.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iv_from.getViewTreeObserver().removeOnGlobalLayoutListener(fromLayoutListener);
                        iv_to.getViewTreeObserver().removeOnGlobalLayoutListener(toLayoutListener);
                        ScaleTo(true);
                    }
                });
                iv_to.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ScaleTo(false);
                    }
                });
            }
        });
    }

    private class FromLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            startProperty = new Property(iv_from.getX(), iv_from.getY(), iv_from.getWidth(), iv_from.getHeight());
            iv_from.getViewTreeObserver().removeOnGlobalLayoutListener(fromLayoutListener);
        }
    }

    private class ToLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            toProperty = new Property(iv_to.getX(), iv_to.getY(), iv_to.getWidth(), iv_to.getHeight());
            iv_to.getViewTreeObserver().removeOnGlobalLayoutListener(toLayoutListener);
        }
    }

    public class Property {
        float X;
        float Y;
        float width;
        float height;

        public Property(float x, float y, float width, float height) {
            X = x;
            Y = y;
            this.width = width;
            this.height = height;
        }
    }

    /**
     * 设置估值器
     */
    private class ScaleEvaLuator implements TypeEvaluator<Property> {

        @Override
        public Property evaluate(float fraction, Property startValue, Property endValue) {

            return new Property(compute(startValue.X, endValue.X, fraction),
                    compute(startValue.Y, endValue.Y, fraction),
                    compute(startValue.width, endValue.width, fraction),
                    compute(startValue.height, endValue.height, fraction));
        }

        /**
         * 根据进度计算当前属性值
         *
         * @param startValue
         * @param endValue
         * @param fraction
         * @return
         */
        private float compute(float startValue, float endValue, float fraction) {
            return startValue + (endValue - startValue) * fraction;
        }
    }

    private void ScaleTo(boolean isToBig) {

        if (isToBig) {
            iv_to.setX(startProperty.X);
            iv_to.setY(startProperty.Y);
            iv_to.getLayoutParams().width = (int) startProperty.width;
            iv_to.getLayoutParams().height = (int) startProperty.height;
            iv_to.requestLayout();

            ValueAnimator valueAnimator = ValueAnimator.ofObject(new ScaleEvaLuator(), startProperty, toProperty);
            valueAnimator.setDuration(300);
            valueAnimator.setStartDelay(100);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Property property = (Property) animation.getAnimatedValue();
                    iv_to.setVisibility(View.VISIBLE);
                    iv_to.setX(property.X);
                    iv_to.setY(property.Y);
                    iv_to.getLayoutParams().width = (int) property.width;
                    iv_to.getLayoutParams().height = (int) property.height;
                    iv_to.requestLayout();
                }
            });
            valueAnimator.start();
        } else {
            ValueAnimator valueAnimator = ValueAnimator.ofObject(new ScaleEvaLuator(), toProperty, startProperty);
            valueAnimator.setDuration(300);
            valueAnimator.setStartDelay(100);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Property property = (Property) animation.getAnimatedValue();
                    iv_to.setX(property.X);
                    iv_to.setY(property.Y);
                    iv_to.getLayoutParams().width = (int) property.width;
                    iv_to.getLayoutParams().height = (int) property.height;
                    iv_to.requestLayout();
                }
            });
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    iv_to.setVisibility(View.INVISIBLE);
                }
            });
            valueAnimator.start();
        }
    }
}
