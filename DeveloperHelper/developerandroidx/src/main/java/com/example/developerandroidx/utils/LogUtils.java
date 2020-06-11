package com.example.developerandroidx.utils;

import android.util.Log;

/**
 * @作者： zjf 2020/5/20 11:33
 * @参考：
 * @描述：
 */
public class LogUtils {

    public static void e(String TAG, String log) {
        if (Constant.isDebug) {
            Log.e(TAG, log);
        }
    }

    public static void d(String TAG, String log) {
        if (Constant.isDebug) {
            Log.d(TAG, log);
        }
    }
}
