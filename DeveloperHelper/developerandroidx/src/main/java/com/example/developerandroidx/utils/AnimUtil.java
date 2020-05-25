package com.example.developerandroidx.utils;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

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

    public Animation getScaleAnim(float fromX, float toX, float fromY, float toY, int duration, int startOffset, Interpolator interpolator) {
        Animation animation = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(duration);
        animation.setStartOffset(startOffset);
        if (interpolator != null) {
            animation.setInterpolator(interpolator);
        }
        return animation;
    }

    public Animation getTranslateAnim(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, int duration, int startOffset) {
        Animation animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        animation.setDuration(duration);
        animation.setStartOffset(startOffset);
        return animation;
    }
}
