package com.example.developerandroidx.ui.android.httpRequest.dialog;

import android.content.Context;

import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.ui.android.httpRequest.HttpRequestActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.PreferenceUtils;
import com.example.developerandroidx.utils.RouteUtil;

/**
 * @作者： zjf 2020/6/5 10:14 AM
 * @参考：
 * @描述：数据请求库
 */
public class HttpRequestLibraryDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {

        DialogUtils.getInstance().showBottomMenu(context, new String[]{"OkHttp", "Volley", "Retrofit"}, new DialogUtils.OnItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                PreferenceUtils.getInstance().putStringValue(Constant.PreferenceKeys.HTTP_REQUEST_LIBRARY, text);
                RouteUtil.goTo(context, RouteUtil.getDestination(HttpRequestActivity.class));
            }
        });
    }
}
