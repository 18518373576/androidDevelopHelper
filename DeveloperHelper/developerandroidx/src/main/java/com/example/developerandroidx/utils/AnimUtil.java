package com.example.developerandroidx.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;

/**
 * @作者： zjf 2020/5/12 16:42
 * @参考：
 * @描述： 动画
 */
public class AnimUtil {

    private AnimUtil() {
    }

    /**
     * 使用静态内部类实现单例，静态内部类不会随着类的加载而加载，既可以保证线程安全，又可以减小程序启动的负担
     */
    private static class AnimutilInstance {
        public static final AnimUtil INSTANCE = new AnimUtil();
    }

    public static AnimUtil getInstance() {
        return AnimutilInstance.INSTANCE;
    }

    /**
     * 缩放动画
     *
     * @param duration
     * @param startOffset
     * @return
     */
    public Animation getScaleAnim(int duration, int startOffset) {
        /**
         *
         * @param fromX 起始x轴位置，0为最小，1为原始，float形
         * @param toX 同上
         * @param fromY 同上T
         * @param toY 同上
         * @param pivotXType
         * 用来约束pivotXValue的取值。取值有三种：Animation.ABSOLUTE，Animation.RELATIVE_TO_SELF，Animation.RELATIVE_TO_PARENT
         * Type：Animation.ABSOLUTE：绝对，如果设置这种类型，后面pivotXValue取值就必须是像素点；比如：控件X方向上的中心点，pivotXValue的取值mIvScale.getWidth() / 2f
         *      Animation.RELATIVE_TO_SELF：相对于控件自己，设置这种类型，后面pivotXValue取值就会去拿这个取值是乘上控件本身的宽度；比如：控件X方向上的中心点，pivotXValue的取值0.5f
         *      Animation.RELATIVE_TO_PARENT：相对于它父容器（这个父容器是指包括这个这个做动画控件的外一层控件）， 原理同上，
         * @param pivotXValue  配合pivotXType使用，原理在上面
         * @param pivotYType 同from/to
         * @param pivotYValue 原理同上
         */
        Animation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(duration);
        animation.setStartOffset(startOffset);
        animation.setInterpolator(new AccelerateInterpolator());
        return animation;
    }

    /**
     * 获取缩放动画
     *
     * @param fromX
     * @param toX
     * @param fromY
     * @param toY
     * @param duration
     * @param startOffset
     * @param interpolator
     * @return
     */
    public Animation getScaleAnim(float fromX, float toX, float fromY, float toY, int duration, int startOffset, Interpolator interpolator) {
        Animation animation = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(duration);
        animation.setStartOffset(startOffset);
        if (interpolator != null) {
            animation.setInterpolator(interpolator);
        }
        return animation;
    }

    /**
     * 获取平移动画
     *
     * @param fromXDelta
     * @param toXDelta
     * @param fromYDelta
     * @param toYDelta
     * @param duration
     * @param startOffset
     * @return
     */
    public Animation getTranslateAnim(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, int duration, int startOffset) {
        Animation animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        animation.setDuration(duration);
        animation.setStartOffset(startOffset);
        return animation;
    }

    /**
     * 获取抖动动画
     *
     * @return
     */
    public Animation getShakeAnim() {
        Animation animation = AnimationUtils.loadAnimation(App.context, R.anim.shake);
        animation.setInterpolator(new CycleInterpolator(3));
        return animation;
    }

    /**
     * 缩放动画，animator
     *
     * @param target
     * @param duration
     * @param values
     */
    public void startScaleAnimator(View target, int duration, float... values) {
        startScaleAnimator(target, duration, null, values);
    }

    /**
     * 缩放动画with插值器
     *
     * @param target
     * @param duration
     * @param interpolator
     * @param values
     */
    public void startScaleAnimator(View target, int duration, Interpolator interpolator, float... values) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(target, "scaleX", values);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(target, "scaleY", values);
        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.setDuration(duration);
        if (interpolator != null) {
            animatorSet.setInterpolator(interpolator);
        }
        animatorSet.start();
    }

    /**
     * 开启缩放动画with弹簧插值器
     *
     * @param target
     * @param duration
     * @param values
     */
    public void startSpringScaleAnimator(View target, int duration, float... values) {
        startScaleAnimator(target, duration, new SpringInterpolator(0.3f), values);
    }

    /**
     * 弹簧动画插值器
     */
    private class SpringInterpolator implements Interpolator {

        private float factor;

        /**
         * 数值为0-1；数值越小回弹次数越多
         *
         * @param factor
         */
        public SpringInterpolator(float factor) {
            this.factor = factor;
        }

        @Override
        public float getInterpolation(float input) {
            //factor = 0.4
//        pow(2, -10 * x) * sin((x - factor / 4) * (2 * PI) / factor) + 1

//            LogUtils.e("插值器input值：", (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1) + "");
            return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
        }
    }
}
