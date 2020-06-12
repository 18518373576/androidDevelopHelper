package com.example.developerandroidx.view.curve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;

import java.util.List;

/**
 * 作者： zjf 2020/6/12 11:04 AM
 * 参考：
 * 描述：拿到一个二维曲线公式,画出曲线,如:X=Y
 */
public class CurveView extends View {

    private Context context;
    private int width;
    private int height;

    //网格画笔
    private Paint formPaint;
    //突出坐标轴
    private Paint linePaint;
    //写字画笔
    private Paint textPaint;

    private List<Point> points;
    //网格每天线的间隔
    private float interval;

    //实际能画线的画布大小
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    //原点
    private float originX;
    private float originY;

    public CurveView(Context context) {
        this(context, null);
    }

    public CurveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        width = PixelTransformForAppUtil.getDiaplayWidth();
        height = width;
        interval = width / 20f;
        startX = interval;
        startY = interval;
        endX = width - interval;
        endY = height - interval;

        originX = width / 2f;
        originY = height / 2f;

        initPaint();
    }

    private void initPaint() {
        formPaint = new Paint();
        formPaint.setColor(Color.rgb(229, 229, 229));
        formPaint.setStrokeWidth(5);
        formPaint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setStrokeWidth(5);
        linePaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.DKGRAY);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(30);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        formPaint.setColor(Color.rgb(229, 229, 229));
        for (int i = 1; i < 20; i++) {
            if (i == 10) {
                continue;
            }
            canvas.drawLine(0, interval * i, width, interval * i, formPaint);
            canvas.drawLine(interval * i, 0, interval * i, height, formPaint);
        }
        formPaint.setColor(Color.LTGRAY);
        canvas.drawLine(0, interval * 10, width, interval * 10, formPaint);
        canvas.drawLine(interval * 10, 0, interval * 10, height, formPaint);

        canvas.drawText("X: -100~100  Y: -100~100", 30, 30, textPaint);
        drawLinkPoints(canvas);
    }

    /**
     * 连接各个点
     *
     * @param canvas
     */
    private void drawLinkPoints(Canvas canvas) {
//        if (points == null || points.size() == 0) {
//            return;
//        }
//        for (Point point : points) {
//
//        }
        canvas.drawLine(originX, originY, endX, startY, linePaint);

    }

    public void setPoints(List<Point> points) {
        this.points = points;
        invalidate();
    }
}
