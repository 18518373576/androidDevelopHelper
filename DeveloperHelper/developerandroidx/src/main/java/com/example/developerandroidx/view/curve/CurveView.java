package com.example.developerandroidx.view.curve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.developerandroidx.utils.LogUtils;
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

    //要描绘的点的取值范围
    private float pointNumRangeX;
    private float pointNumRangeY;

    //坐标轴x和y的取值范围
    private String rangeText;

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

        startX = 0;
        startY = 0;
        endX = width;
        endY = height;

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
        linePaint.setColor(Color.rgb(255, 114, 86));
        linePaint.setStrokeWidth(5);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeCap(Paint.Cap.ROUND);

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
        //画表格
        for (int i = 1; i < 20; i++) {
            if (i == 10) {
                //画坐标轴
                continue;
            }
            canvas.drawLine(0, interval * i, width, interval * i, formPaint);
            canvas.drawLine(interval * i, 0, interval * i, height, formPaint);
        }
        //坐标轴颜色加粗
        formPaint.setColor(Color.LTGRAY);
        canvas.drawLine(0, interval * 10, width, interval * 10, formPaint);
        canvas.drawLine(interval * 10, 0, interval * 10, height, formPaint);

        drawLinkPoints(canvas);
    }

    /**
     * 连接各个点
     *
     * @param canvas
     */
    private void drawLinkPoints(Canvas canvas) {
        if (points == null || points.size() == 0) {
            return;
        }
        canvas.drawText(rangeText, 30, 30, textPaint);

        for (Point point : points) {
            //开始的x轴和结束的x轴
            float sx = originX + (point.getX() / pointNumRangeX) * (endX - startX);
            float ex = sx;
            //开始的y轴和结束的y轴
            float sy = originY;
            float ey = originY - (point.getY() / pointNumRangeY) * (endY - startY);
            canvas.drawLine(sx, sy, ex, ey, linePaint);
//            LogUtils.d("连接各个点", sx + "#" + ex + "#" + sy + "#" + ey + "\n");
        }
    }

    /**
     * 坐标轴描述
     *
     * @param points         坐标轴的各个点
     * @param pointNumRangeX x轴取值范围 也就是最大值和最小值得差
     * @param pointNumRangeY y轴取值范围
     * @param rangeText      取值范围文字描述
     */
    public void setPoints(List<Point> points, float pointNumRangeX, float pointNumRangeY, String rangeText) {
        this.points = points;
        this.pointNumRangeX = pointNumRangeX;
        this.pointNumRangeY = pointNumRangeY;
        this.rangeText = rangeText;
        invalidate();
    }
}
