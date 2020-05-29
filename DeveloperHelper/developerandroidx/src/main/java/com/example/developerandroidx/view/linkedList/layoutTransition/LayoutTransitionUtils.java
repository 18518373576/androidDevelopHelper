package com.example.developerandroidx.view.linkedList.layoutTransition;

import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;

/**
 * Date: 2020/5/29 9:49
 * 参考:https://blog.csdn.net/harvic880925/article/details/50985596
 * 描述:自定义viewGroup内元素变化时的过渡动画
 */
public class LayoutTransitionUtils {


    private LayoutTransitionUtils() {
    }

    private static class LayoutTransitionUtilsInstance {
        public static LayoutTransitionUtils INSTANCE = new LayoutTransitionUtils();
    }

    public static LayoutTransitionUtils getInstance() {
        return LayoutTransitionUtilsInstance.INSTANCE;
    }

    /**
     * 目标实现：使用LayoutTransition在增加view的时候，让view由0~1执行缩放动画，删除view的时候让view从1~0执行缩放动画
     * 问题描述：添加view的时候view先展示在ViewGroup中，然后才执行动画，过程为从1突然到0~1
     * 暂时使用 android:animateLayoutChanges="true"使用默认动画
     *
     * @return
     */
    public LayoutTransition getScaleTransition() {
        LayoutTransition transition = new LayoutTransition();

        AnimatorSet animatorSetOut = new AnimatorSet();
        ObjectAnimator objectAnimatorOutX = ObjectAnimator.ofFloat(null, "scaleX", 0f, 1f);
        ObjectAnimator objectAnimatorOutY = ObjectAnimator.ofFloat(null, "scaleY", 0f, 1f);
        animatorSetOut.playTogether(objectAnimatorOutX, objectAnimatorOutY);

        AnimatorSet animatorSetGone = new AnimatorSet();
        ObjectAnimator objectAnimatorGoneX = ObjectAnimator.ofFloat(null, "scaleX", 1f, 0f);
        ObjectAnimator objectAnimatorGoneY = ObjectAnimator.ofFloat(null, "scaleY", 1f, 0f);
        animatorSetGone.playTogether(objectAnimatorGoneX, objectAnimatorGoneY);

        transition.setAnimator(LayoutTransition.APPEARING, animatorSetOut);
        transition.setAnimator(LayoutTransition.DISAPPEARING, animatorSetGone);
        return transition;
    }
}
