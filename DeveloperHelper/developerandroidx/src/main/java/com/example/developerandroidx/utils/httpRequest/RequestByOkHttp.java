package com.example.developerandroidx.utils.httpRequest;

import android.os.Message;

import com.example.developerandroidx.utils.Constant;

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
 * 作者： zjf 2020/6/7 12:29 PM
 * 参考：
 * 描述：使用OkHttp获取数据
 */
public class RequestByOkHttp {
    private OkHttpClient client;

    private RequestByOkHttp() {
        client = new OkHttpClient.Builder()
                .connectTimeout(Constant.Internet.CONNECT_TIME_OUT_SECOND, TimeUnit.SECONDS)
                .readTimeout(Constant.Internet.CONNECT_TIME_OUT_SECOND, TimeUnit.SECONDS)
                .writeTimeout(Constant.Internet.CONNECT_TIME_OUT_SECOND, TimeUnit.SECONDS)
                .build();
    }

    private static class OkHttpInstance {
        public static final RequestByOkHttp INSTANCE = new RequestByOkHttp();
    }

    public static RequestByOkHttp getInstance() {
        return OkHttpInstance.INSTANCE;
    }


    /**
     * 使用OkHttp请求数据
     * post请求
     */
    public void post(String url, Map<String, String> params, RequestHandler handler) {
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
                message.what = Constant.Internet.FAIL;
                message.obj = e.toString();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                Message message = new Message();
                try {
                    message.what = Constant.Internet.SUC;
                    message.obj = response.body().string();
                } catch (IOException e) {
                    message.what = Constant.Internet.FAIL;
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
    public void get(String url, RequestHandler handler) {
        Request request = new Request.Builder().url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Message message = new Message();
                message.what = Constant.Internet.FAIL;
                message.obj = e.getMessage();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                Message message = new Message();
                try {
                    message.what = Constant.Internet.SUC;
                    message.obj = response.body().string();
                } catch (IOException e) {
                    message.what = Constant.Internet.FAIL;
                    message.obj = e.toString();
                    e.printStackTrace();
                }
                handler.sendMessage(message);
            }
        });
    }
}
