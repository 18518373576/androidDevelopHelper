package com.example.developerandroidx.view.touchEventView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.developerandroidx.R;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * @作者： zjf 2020/6/2 4:50 PM
 * @参考：
 * @描述： 为了验证触摸事件的传递机制
 */
public class TouchEventView extends LinearLayout {

    private Context context;
    private ChildView childView;

    public boolean getInterceptReturn() {
        return interceptReturn;
    }

    public void setInterceptReturn(boolean interceptReturn) {
        this.interceptReturn = interceptReturn;
    }

    public boolean getVgTouchReturn() {
        return vgTouchReturn;
    }

    public void setVgTouchReturn(boolean vgTouchReturn) {
        this.vgTouchReturn = vgTouchReturn;
    }

    public boolean getCvTouchReturn() {
        return childView.getCvTouchReturn();
    }

    public void setCvTouchReturn(boolean cvTouchReturn) {
        childView.setCvTouchReturn(cvTouchReturn);
    }

    //触摸事件以及触摸拦截事件，默认设置为true
    private boolean interceptReturn = true;
    private boolean vgTouchReturn = true;

    public TouchEventView(Context context) {
        super(context);
        initView(context);
    }

    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TouchEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化操作
     *
     * @param context
     */
    private void initView(Context context) {
        this.context = context;
        addView();
    }

    /**
     * 向viewgroup添加一个view
     */
    private void addView() {

        childView = new ChildView(context);
        LayoutParams params = new LayoutParams(PixelTransformForAppUtil.dip2px(250), PixelTransformForAppUtil.dip2px(250));
        addView(childView, params);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                childView.setText("ViewGroup：ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                childView.setText("ViewGroup：ACTION_MOVE");
//                break;
            case MotionEvent.ACTION_UP:
                childView.setText("ViewGroup onTouchEvent：ACTION_UP");
                break;
        }
        return vgTouchReturn;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                childView.setText("ViewGroup onInterceptTouchEvent：ACTION_DOWN");
                break;
//            case MotionEvent.ACTION_MOVE:
//                childView.setText("ViewGroup Intercept：ACTION_MOVE");
//                break;
            case MotionEvent.ACTION_UP:
                childView.setText("ViewGroup onInterceptTouchEvent：ACTION_UP");
                break;
        }
        return interceptReturn;
    }
}
