package com.example.developerandroidx.utils.httpRequest;

import android.os.Handler;
import android.os.Message;

import com.example.developerandroidx.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @作者： zjf 2020/6/5 2:10 PM
 * @参考：
 * @描述：数据请求
 */
public class HttpRequestUtil {

    private OkHttpClient client;
    private OkHttpCallBack callBack;
    private MyHandler handler;

    private final int SUC = 200;
    private final int FAIL = 404;

    public HttpRequestUtil() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
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
     * post请求
     */
    public void requestByOkHttpPost(String url, Map<String, String> params, OkHttpCallBack callBack) {
        this.callBack = callBack;
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (String key : params.keySet()) {
            bodyBuilder.add(key, params.get(key));
        }
        RequestBody requestBody = bodyBuilder.build();

        Request request = new Request.Builder().url(url).post(requestBody).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Message message = new Message();
                message.what = FAIL;
                message.obj = e.toString();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
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

    /**
     * 使用OkHttp请求数据
     * get请求
     */
    public void requestByOkHttpGet(String url, OkHttpCallBack callBack) {
        this.callBack = callBack;
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
