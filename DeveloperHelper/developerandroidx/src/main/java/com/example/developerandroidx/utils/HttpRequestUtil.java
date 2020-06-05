package com.example.developerandroidx.utils;

import android.os.Handler;
import android.os.Message;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @作者： zjf 2020/6/5 2:10 PM
 * @参考：
 * @描述：数据请求
 */
public class HttpRequestUtil {

    private OkHttpClient client;
    private String url;
    private OkHttpCallBack callBack;
    private MyHandler handler;

    private final int SUC = 200;
    private final int FAIL = 404;

    /**
     * 请求类型
     */
    public enum RequestType {
        GET, POST
    }

    public HttpRequestUtil() {
        client = new OkHttpClient();
        handler = new MyHandler();
    }

    private static class HttpRequestUtilInstance {
        public static final HttpRequestUtil INSTANCE = new HttpRequestUtil();
    }

    public static HttpRequestUtil getInstance() {
        return HttpRequestUtilInstance.INSTANCE;
    }

    /**
     * 数据访问成功回调
     */
    public interface OkHttpCallBack {
        void onFail(String msg);

        void onSuc(String result);
    }

    /**
     * 异步通信
     */
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUC:
                    LogUtils.e("数据访问成功：", msg.obj.toString());
                    callBack.onSuc(msg.obj.toString());
                    break;
                case FAIL:
                    LogUtils.e("数据访问失败：", msg.obj.toString());
                    callBack.onFail(msg.obj.toString());
                    break;
            }
        }
    }

    /**
     * 使用OkHttp请求数据
     *
     * @param requestType
     * @param url
     * @param callBack
     */
    public void requestByOkHttp(RequestType requestType, String url, OkHttpCallBack callBack) {
        this.url = url;
        this.callBack = callBack;
        switch (requestType) {
            case GET:
                requestByOkHttpGet();
                break;
            case POST:
                requestByOkHttpPost();
                break;
        }

    }

    /**
     * post请求
     */
    private void requestByOkHttpPost() {

    }

    /**
     * get请求
     */
    private void requestByOkHttpGet() {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Message message = new Message();
                message.what = FAIL;
                message.obj = e.toString();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                Message message = new Message();
                try {
                    message.what = SUC;
                    message.obj = response.body().string();
                } catch (IOException e) {
                    message.what = FAIL;
                    message.obj = e.toString();
                    e.printStackTrace();
                }

                handler.sendMessage(message);
            }
        });
    }
}
