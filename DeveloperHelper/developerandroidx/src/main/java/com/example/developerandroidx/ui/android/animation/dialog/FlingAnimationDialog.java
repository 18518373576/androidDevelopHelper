package com.example.developerandroidx.ui.android.animation.dialog;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.view.GestureDetectorCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * @作者： zjf 2020/5/25 15:24
 * @参考：
 * @描述： fling抛掷动画
 * <p>
 * https://blog.csdn.net/linghu_java/article/details/46544811
 * 在oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，这是因为View组件布局要在onResume回调后完成。
 * 所以现在需要使用getViewTreeObserver().addOnGlobalLayoutListener()来获得宽度或者高度。这是获得一个view的宽度和高度的方法之一。
 * <p>
 * OnGlobalLayoutListener 是ViewTreeObserver的内部类，当一个视图树的布局发生改变时，可以被ViewTreeObserver监听到，
 * 这是一个注册监听视图树的观察者(observer)，在视图树的全局事件改变时得到通知。ViewTreeObserver不能直接实例化，而是通过getViewTreeObserver()获得。
 * <p>
 * 除了OnGlobalLayoutListener ，ViewTreeObserver还有如下内部类：
 * <p>
 * interface ViewTreeObserver.OnGlobalFocusChangeListener
 * 当在一个视图树中的焦点状态发生改变时，所要调用的回调函数的接口类
 * interface ViewTreeObserver.OnGlobalLayoutListener
 * 当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时，所要调用的回调函数的接口类
 * interface ViewTreeObserver.OnPreDrawListener
 * 当一个视图树将要绘制时，所要调用的回调函数的接口类
 * interface ViewTreeObserver.OnScrollChangedListener
 * 当一个视图树中的一些组件发生滚动时，所要调用的回调函数的接口类
 * interface ViewTreeObserver.OnTouchModeChangeListener
 * 当一个视图树的触摸模式发生改变时，所要调用的回调函数的接口类
 * 其中，我们可以利用OnGlobalLayoutListener来获得一个视图的真实高度。
 */
public class FlingAnimationDialog implements FunctionDialogInterface {
    private ImageView iv_fling;
    private GestureDetectorCompat gestureDetectorCompat;
    private LinearLayout ll_bg;
    private int max;

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_fling_animation, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                iv_fling = rootView.findViewById(R.id.iv_fling);
                iv_fling.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        gestureDetectorCompat.onTouchEvent(event);
                        return true;
                    }
                });
                ll_bg = rootView.findViewById(R.id.ll_bg);
                ll_bg.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (rootView.getHeight() > 0) {
                            max = ll_bg.getHeight() - iv_fling.getHeight();
                            gestureDetectorCompat = new GestureDetectorCompat(context, new MyGestureListener());
                            ll_bg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        });
    }


    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            FlingAnimation flingAnimation = new FlingAnimation(iv_fling, DynamicAnimation.Y);
            //设置为DynamicAnimation.Y，下面的方法的含义为：
            //setMinValue被移动的view的y点可在父控件移动的最小值
            //setMaxValue被移动的view的y点可在父控件移动的最大值，这里设置了view的高度的偏移量，以免移到屏幕外面
            //setStartVelocity动画起始速率，这里根据手指的滑动确定速度
            //setFriction设置摩擦力
            flingAnimation.setMinValue(0f).setMaxValue(max).setStartVelocity(velocityY).setFriction(2.0f).start();
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
