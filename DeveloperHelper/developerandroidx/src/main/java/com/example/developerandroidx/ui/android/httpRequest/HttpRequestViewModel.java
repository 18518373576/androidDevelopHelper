package com.example.developerandroidx.ui.android.httpRequest;

import com.example.developerandroidx.base.BaseViewModel;

/**
 * @作者： zjf 2020/6/5 10:23 AM
 * @参考：
 * @描述： 网络请求
 */
public class HttpRequestViewModel extends BaseViewModel<String> {

    @Override
    protected String initData(Object dataType) {
        switch ((String) dataType) {
            case "OkHttp":

                break;
            case "Volley":

                break;
            case "Retrofit":

                break;
        }
        return (String) dataType;
    }

}
