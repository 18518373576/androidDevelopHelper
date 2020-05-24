package com.example.developerandroidx.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.example.developerandroidx.base.App;

/**
 * @作者： zjf 2020/5/11 11:04
 * @参考：
 * @描述： dp与px互相转换
 */
public class PixelTransformForAppUtil {

    public static int px2dip(float pxValue) {
        final float scale = App.context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(float dpValue) {
        final float scale = App.context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getDiaplayWidth() {
        //context的方法，获取windowManager
        WindowManager windowManager = (WindowManager) App.context.getSystemService(Context.WINDOW_SERVICE);
        //获取屏幕对象
        Display defaultDisplay = windowManager.getDefaultDisplay();
        //获取屏幕的宽、高，单位是像素
        Point point = new Point();
        defaultDisplay.getSize(point);
        int width = point.x;
        int height = defaultDisplay.getHeight();

        return width;
    }

    public static int getDiaplayHeight() {
        //context的方法，获取windowManager
        WindowManager windowManager = (WindowManager) App.context.getSystemService(Context.WINDOW_SERVICE);
        //获取屏幕对象
        Display defaultDisplay = windowManager.getDefaultDisplay();
        //获取屏幕的宽、高，单位是像素
        Point point = new Point();
        defaultDisplay.getSize(point);
        int width = point.x;
        int height = point.y;

        return height;
    }
}
