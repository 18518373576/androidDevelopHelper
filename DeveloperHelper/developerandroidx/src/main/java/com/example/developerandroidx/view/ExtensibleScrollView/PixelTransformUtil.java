package com.example.developerandroidx.view.ExtensibleScrollView;

import android.content.Context;

/**
 * @作者： zjf 2020/5/11 11:04
 * @参考：
 * @描述： dp与px互相转换
 */
public class PixelTransformUtil {

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
