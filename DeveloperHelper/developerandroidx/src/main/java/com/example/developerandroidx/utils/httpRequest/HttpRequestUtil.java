package com.example.developerandroidx.utils.httpRequest;

import java.util.Map;

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
     * get请求
     *
     * @param library  请求使用的library{@link RequestLibrary}
     * @param url      请求的url
     * @param callBack 请求结果回调
     */
    public void get(RequestLibrary library, String url, RequestCallBack callBack) {
        switch (library) {
            case OK_HTTP:
                requestByOkHttpGet(url, callBack);
                break;
            case VOLLEY:
                requestByVolleyGet(url, callBack);
                break;
            case RETROFIT:

                break;
        }
    }

    /**
     * post请求
     *
     * @param library  请求使用的库
     * @param url      请求的url
     * @param params   请求参数
     * @param callBack 请求回调
     */
    public void post(RequestLibrary library, String url, Map<String, String> params, RequestCallBack callBack) {
        switch (library) {
            case OK_HTTP:
                requestByOkHttpPost(url, params, callBack);
                break;
            case VOLLEY:
                requestByVolleyPost(url, params, callBack);
                break;
            case RETROFIT:

                break;
        }
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

    /**
     * @param url      请求的url
     * @param params   请求的参数
     * @param callBack 请求结果回调
     */
    public void requestByVolleyPost(String url, Map<String, String> params, RequestCallBack callBack) {
        VolleyRequest.getInstance().post(url, params, callBack);
    }

    /**
     * @param url      请求的url
     * @param callBack 请求结果回调
     */
    public void requestByVolleyGet(String url, RequestCallBack callBack) {
        VolleyRequest.getInstance().get(url, callBack);
    }
}
