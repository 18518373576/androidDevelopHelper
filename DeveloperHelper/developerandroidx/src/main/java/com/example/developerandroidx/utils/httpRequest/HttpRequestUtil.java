package com.example.developerandroidx.utils.httpRequest;

import com.example.developerandroidx.utils.api.Api;

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
                requestByOkHttpGet(Api.BASE_URL + url, callBack);
                break;
            case VOLLEY:
                requestByVolleyGet(Api.BASE_URL + url, callBack);
                break;
            case RETROFIT:
                requestByRetrofitGet(url, callBack);
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
                requestByOkHttpPost(Api.BASE_URL + url, params, callBack);
                break;
            case VOLLEY:
                requestByVolleyPost(Api.BASE_URL + url, params, callBack);
                break;
            case RETROFIT:
                requestByRetrofitPost(url, params, callBack);
                break;
        }
    }

    /**
     * 使用OkHttp请求数据
     * post请求
     */
    public void requestByOkHttpPost(String url, Map<String, String> params, RequestCallBack callBack) {
        handler.setCallBack(callBack);
        RequestByOkHttp.getInstance().post(url, params, handler);
    }

    /**
     * 使用OkHttp请求数据
     * get请求
     */
    public void requestByOkHttpGet(String url, RequestCallBack callBack) {
        handler.setCallBack(callBack);
        RequestByOkHttp.getInstance().get(url, handler);
    }

    /**
     * 使用volley请求数据
     *
     * @param url      请求的url
     * @param params   请求的参数
     * @param callBack 请求结果回调
     */
    public void requestByVolleyPost(String url, Map<String, String> params, RequestCallBack callBack) {
        RequestByVolley.getInstance().post(url, params, callBack);
    }

    /**
     * 使用volley请求数据
     *
     * @param url      请求的url
     * @param callBack 请求结果回调
     */
    public void requestByVolleyGet(String url, RequestCallBack callBack) {
        RequestByVolley.getInstance().get(url, callBack);
    }

    /**
     * 使用retrofit请求数据
     *
     * @param url
     * @param callBack
     */
    public void requestByRetrofitGet(String url, RequestCallBack callBack) {
        RequestByRetrofit.getInstance().get(url, callBack);
    }

    /**
     * 使用retrofit请求数据
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void requestByRetrofitPost(String url, Map<String, String> params, RequestCallBack callBack) {
        RequestByRetrofit.getInstance().post(url, params, callBack);
    }
}
