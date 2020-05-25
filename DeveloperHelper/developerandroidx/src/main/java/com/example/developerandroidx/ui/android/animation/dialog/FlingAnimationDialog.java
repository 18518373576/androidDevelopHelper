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
 */
public class FlingAnimationDialog implements FunctionDialogInterface {
    private ImageView iv_fling;
    private GestureDetectorCompat gestureDetectorCompat;
    private LinearLayout ll_bg;
    private int max;

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().shouFullScreenDialog(context, R.layout.dialog_fling_animation, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                iv_fling = rootView.findViewById(R.id.iv_fling);
                iv_fling.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        gestureDetectorCompat.onTouchEvent(event);
                        return false;
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

            FlingAnimation flingAnimation = new FlingAnimation(iv_fling, DynamicAnimation.SCROLL_Y);
            flingAnimation.setMinValue(0f).setMaxValue(max).setStartVelocity(velocityY).setFriction(1.1f).start();
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
