package com.example.developerandroidx.utils.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * 作者： zjf 2020/6/8 9:20 AM
 * 参考：
 * 描述：Retrofit接口
 */
public interface RetrofitApi {
    /**
     * get请求
     * (value = "path", encoded = true)
     *
     * @param urlStr 请求的url
     * @return Call对象
     */
    @GET("{url}")
    Call<String> get(@Path(value = "url", encoded = true) String urlStr);

    /**
     * post请求
     *
     * @param url
     * @param params
     * @return
     */
    @POST("{url}")
    Call<String> post(@Path(value = "url", encoded = true) String url, @QueryMap Map<String, String> params);
}
