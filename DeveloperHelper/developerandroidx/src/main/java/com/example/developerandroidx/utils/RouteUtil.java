package com.example.developerandroidx.utils;

import android.content.Context;
import android.content.Intent;

import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;

public class RouteUtil {
    private static Intent intent;

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
     * 路由到哪里去,通用
     *
     * @param context
     * @param activityClassName
     */
    public static void goTo(Context context, String activityClassName) throws ClassNotFoundException {
        intent = new Intent(context, Class.forName(activityClassName));
        context.startActivity(intent);
    }


    public static void goToCodeView(Context context, String code) {
        intent = new Intent(context, CodeViewActivity.class);
        intent.putExtra(Constant.IntentParams.CODE, code);
        context.startActivity(intent);
    }
}
