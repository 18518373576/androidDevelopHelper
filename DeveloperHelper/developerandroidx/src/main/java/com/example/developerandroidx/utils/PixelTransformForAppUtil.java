package com.example.developerandroidx.utils;

import android.content.Context;

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
}
