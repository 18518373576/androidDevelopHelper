package com.example.developerandroidx.utils.api;

import com.example.developerandroidx.model.BlogListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 作者： zjf 2020/6/11 9:32 AM
 * 参考：https://www.jianshu.com/p/11b3ec672812
 * 描述：使用RxJava结合Retrofit
 */
public interface RxJavaApi {

    /**
     * 注解里面传入部分url地址
     *
     * @return
     */
    @GET("wxarticle/chapters/json")
    Observable<BlogListBean> getBlogList();
}
