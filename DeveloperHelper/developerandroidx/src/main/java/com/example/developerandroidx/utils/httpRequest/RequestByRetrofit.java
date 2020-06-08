package com.example.developerandroidx.utils.httpRequest;

import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.api.Api;
import com.example.developerandroidx.utils.api.RetrofitApi;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者： zjf 2020/6/8 9:23 AM
 * 参考：
 * 描述：使用Retrofit请求网络
 */
public class RequestByRetrofit {
    private Retrofit retrofit;
    private RetrofitApi api;

    private RequestByRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        api = retrofit.create(RetrofitApi.class);
    }

    private static class RetrofitInstance {
        public static final RequestByRetrofit INSTANCE = new RequestByRetrofit();
    }

    public static RequestByRetrofit getInstance() {
        return RetrofitInstance.INSTANCE;
    }

    /**
     * get请求
     *
     * @param url
     * @param callBack
     */
    public void get(String url, RequestCallBack callBack) {

        Call<String> call = api.get(url);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callBack.onSuc(response.body());
                LogUtils.e("数据访问成功", call.request().url().toString() + ":" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtils.e("数据访问失败", call.request().url().toString() + ":" + t.getMessage());
                callBack.onFail(t.getMessage());
            }
        });
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void post(String url, Map<String, String> params, RequestCallBack callBack) {
        Call<String> call = api.post(url, params);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callBack.onSuc(response.body());
                LogUtils.e("数据访问成功", call.request().url().toString() + ":" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtils.e("数据访问失败", call.request().url().toString() + ":" + t.getMessage());
                callBack.onFail(t.getMessage());
            }
        });
    }
}
