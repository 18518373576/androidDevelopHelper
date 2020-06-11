package com.example.developerandroidx.ui.android.rxJava.smaple;

import android.content.Context;

import com.example.developerandroidx.model.BlogListBean;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.api.Api;
import com.example.developerandroidx.utils.api.RxJavaApi;
import com.example.developerandroidx.utils.enumPkg.TipType;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者： zjf 2020/6/11 10:59 AM
 * 参考：
 * 描述：rxjava实际应用示例
 */
public class RxJavaSmaple {

    private Retrofit retrofit;
    private RxJavaApi rxJavaApi;
    private Context context;

    private String TAG = "RxJava";

    public RxJavaSmaple(Context context) {
        this.context = context;
        //创建Retrofit对象
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //获取接口请求实例
        rxJavaApi = retrofit.create(RxJavaApi.class);
    }

    /**
     * 无条件轮询请求网络,轮询3次，间隔2秒
     */
    public void getBlogList_1() {
        Observable
                .intervalRange(0, 3, 0, 2, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Observable<BlogListBean> observable = rxJavaApi.getBlogList();
                        observable
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new io.reactivex.Observer<BlogListBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onNext(BlogListBean blogListBean) {
                                        DialogUtils.getInstance().showTip(context, TipType.SUC, "获取数据size:" + blogListBean.data.size());
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        DialogUtils.getInstance().showTip(context, TipType.ERR, e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                });
                    }
                })
                .subscribe(new io.reactivex.Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "事件结束");
                    }
                });
    }

    /**
     * 有条件轮询
     */
    public void getBlogList_2() {

    }
}
