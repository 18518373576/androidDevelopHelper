package com.example.developerandroidx.view.curve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 2020/6/12 11:04 AM
 * 参考：
 * 描述：拿到一个二维曲线公式,画出曲线,如:X=Y
 */
public class CurveView extends View {

    private Context context;
    private int width;
    private int height;

    private Paint formPaint;

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

        initPaint();
    }

    private void initPaint() {
        formPaint = new Paint();
        formPaint.setColor(Color.rgb(229, 229, 229));
        formPaint.setStrokeWidth(5);
        formPaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float interval = width / 20f;
        for (int i = 1; i < 20; i++) {
            canvas.drawLine(0, interval * i, width, interval * i, formPaint);
            canvas.drawLine(interval * i, 0, interval * i, height, formPaint);
        }
    }
}
