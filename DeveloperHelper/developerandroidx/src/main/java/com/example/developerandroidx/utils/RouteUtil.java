package com.example.developerandroidx.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;

import java.util.Map;

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

    /**
     * 路由到哪里去,一个字符串参数
     *
     * @param context
     * @param activityClassName
     * @param paramStr
     */
    public static void goTo(Context context, String activityClassName, String paramStr) throws ClassNotFoundException {
        intent = new Intent(context, Class.forName(activityClassName));
        intent.putExtra(Constant.IntentParams.INTENT_PARAM, paramStr);
        context.startActivity(intent);
    }

    /**
     * paramsMap为空，或paramStr为空
     *
     * @param context
     * @param activityClassName
     * @param paramsMap
     * @param paramStr
     * @throws ClassNotFoundException
     */
    public static void goTo(Context context, String activityClassName, Map<String, String> paramsMap, String paramStr) throws ClassNotFoundException {

        if (paramsMap == null && TextUtils.isEmpty(paramStr)) {
            goTo(context, activityClassName);
        } else if (paramsMap == null) {
            goTo(context, activityClassName, paramStr);
        } else {

        }
    }

    /**
     * 路由到代码展示界面
     *
     * @param context
     * @param code
     */
    public static void goToCodeView(Context context, String code) {
        intent = new Intent(context, CodeViewActivity.class);
        intent.putExtra(Constant.IntentParams.INTENT_PARAM, code);
        context.startActivity(intent);
    }
}
