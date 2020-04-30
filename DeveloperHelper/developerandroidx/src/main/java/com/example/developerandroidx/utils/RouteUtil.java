package com.example.developerandroidx.utils;

import android.content.Context;
import android.content.Intent;

public class RouteUtil {
    /**
     * 获取要路由的activity类名
     *
     * @param activityClass
     * @return
     */
    public static String getDestination(Class activityClass) {
        return activityClass.getName();
    }

    /**
     * 路由到哪里去
     *
     * @param context
     * @param activityClassName
     */
    public static void goTo(Context context, String activityClassName) throws ClassNotFoundException {
        Intent intent = new Intent(context, Class.forName(activityClassName));
        context.startActivity(intent);
    }
}
