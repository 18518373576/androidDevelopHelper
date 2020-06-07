package com.example.developerandroidx.utils.httpRequest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @作者： zjf 2020/6/5 2:10 PM
 * @参考：
 * @描述：数据请求
 */
public class HttpRequestUtil {

    private RequestHandler handler;

    public HttpRequestUtil() {
        handler = new RequestHandler();
    }

    private static class HttpRequestUtilInstance {
        public static final HttpRequestUtil INSTANCE = new HttpRequestUtil();
    }

    public static HttpRequestUtil getInstance() {
        return HttpRequestUtilInstance.INSTANCE;
    }

    /**
     * 使用OkHttp请求数据
     * post请求
     */
    public void requestByOkHttpPost(String url, Map<String, String> params, RequestCallBack callBack) {
        handler.setCallBack(callBack);
        OkHttp.getInstance().post(url, params, handler);
    }

    /**
     * 使用OkHttp请求数据
     * get请求
     */
    public void requestByOkHttpGet(String url, RequestCallBack callBack) {
        handler.setCallBack(callBack);
        OkHttp.getInstance().get(url, handler);
    }
}
