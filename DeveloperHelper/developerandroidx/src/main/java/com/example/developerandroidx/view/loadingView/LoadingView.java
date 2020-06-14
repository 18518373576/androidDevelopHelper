package com.example.developerandroidx.view.loadingView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 2020/6/6 3:14 PM
 * 参考：
 * 描述：加载动画
 */
public class LoadingView extends View implements ValueAnimator.AnimatorUpdateListener {
    private Context context;
    private int size;
    private int paintColor;
    private Paint mPaint;

    private final int LINE_COUNT = 12;//画的线的数量
    private final int LINE_ANGLE = 360 / LINE_COUNT;//线旋转的角度
    private int animatorValue = 0;
    private ValueAnimator animator;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        //设置默认大小和颜色
        size = PixelTransformForAppUtil.dip2px(40);
        paintColor = Color.GRAY;
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(paintColor);
        //抗锯齿
        mPaint.setAntiAlias(true);
        //设置圆形画笔
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    /**
     * 设置loadingView大小,使用dp设置
     *
     * @param dpSize
     */
    public void setSize(int dpSize) {
        this.size = PixelTransformForAppUtil.dip2px(dpSize);
        //改变大小
        requestLayout();
    }

    /**
     * 设置颜色
     *
     * @param color 颜色值
     */
    public void setColor(int color) {
        paintColor = color;
        mPaint.setColor(color);
        invalidate();
    }

    /**
     * 绘制view大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(size, size);
    }

    /**
     * 绘制自定义view
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        drawLoading(canvas, animatorValue * LINE_ANGLE);
        canvas.restoreToCount(saveCount);
    }

    /**
     * 开始绘制
     *
     * @param canvas       画布
     * @param rotateDegree 动画每次旋转的角度
     */
    private void drawLoading(Canvas canvas, int rotateDegree) {
        int width = size / 12, height = size / 6;
        //设置画笔
        mPaint.setStrokeWidth(width);

        //画布根据动画 每次旋转的角度
        canvas.rotate(rotateDegree, size / 2, size / 2);


        canvas.translate(size / 2, size / 2);

        for (int i = 0; i < LINE_COUNT; i++) {
            canvas.rotate(LINE_ANGLE);
            mPaint.setAlpha((int) (255f * (i + 1) / LINE_COUNT));
            canvas.translate(0, -size / 2 + width / 2);
            canvas.drawLine(0, 0, 0, height, mPaint);

            canvas.translate(0, size / 2 - width / 2);

        }
    }


    /**
     * 停止动画
     */
    private void stop() {
        if (animator != null) {
            animator.removeUpdateListener(this);
            animator.removeAllUpdateListeners();
            animator.cancel();
            animator = null;
        }
    }

    /**
     * 执行动画，开始loading
     */
    private void start() {
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, LINE_COUNT);
            animator.addUpdateListener(this);
            animator.setDuration(800);
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.start();
        } else if (!animator.isStarted()) {
            animator.start();
        }
    }

    /**
     * 更新动画属性
     *
     * @param animation
     */
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        animatorValue = (int) animation.getAnimatedValue();
        invalidate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            start();
        } else {
            stop();
        }
    }

}
