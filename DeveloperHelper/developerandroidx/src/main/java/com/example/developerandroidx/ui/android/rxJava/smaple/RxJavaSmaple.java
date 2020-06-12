package com.example.developerandroidx.ui.android.rxJava.smaple;

import android.content.Context;

import com.example.developerandroidx.model.BlogListBean;
import com.example.developerandroidx.model.HistoryBlogBean;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.api.Api;
import com.example.developerandroidx.utils.api.RxJavaApi;
import com.example.developerandroidx.utils.enumPkg.TipType;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
                //定义发送间隔,从1开始,发送3次,首次发送延时0,每次发送间隔2,事件单位秒
                .intervalRange(1, 3, 0, 2, TimeUnit.SECONDS)
                //每次发送事件会执行此方法
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        //获取api的Observable对象
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
                                        DialogUtils.getInstance().showTip(context, TipType.SUC, "第" + aLong + "次获取数据:" + blogListBean.data.size());
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
    private int i = 1;

    public void getBlogList_2() {
        i = 1;
        Observable<BlogListBean> observable = rxJavaApi.getBlogList();
        observable
                //设置有条件重复执行
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {

                        return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Object o) throws Exception {
                                if (i > 3) {
                                    //返回error结束执行
                                    return Observable.error(new Throwable("结束数据获取")).delay(2, TimeUnit.SECONDS);
                                } else {
                                    //继续发送原Observable事件
                                    return Observable.just(1).delay(2, TimeUnit.SECONDS);
                                }
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BlogListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BlogListBean blogListBean) {
                        DialogUtils.getInstance().showTip(context, TipType.SUC, "第" + i + "次获取数据:" + blogListBean.data.size());
                        i++;

                    }

                    @Override
                    public void onError(Throwable e) {
                        DialogUtils.getInstance().showTip(context, TipType.WARNING, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 嵌套网络请求
     */
    public void getBlogList_3() {
        Observable<BlogListBean> observable1 = rxJavaApi.getBlogList();

        observable1
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //改变被观察者,从新发起事件
                .flatMap(new Function<BlogListBean, ObservableSource<HistoryBlogBean>>() {
                    @Override
                    public ObservableSource<HistoryBlogBean> apply(BlogListBean blogListBean) throws Exception {
                        //获取第一个接口的数据
                        String id = String.valueOf(blogListBean.data.get(0).id);
                        DialogUtils.getInstance().showTip(context, TipType.SUC, "获取ID:" + id);
                        //从新发起事件,获取第二个接口的数据
                        return rxJavaApi.getBlogHistoryList(id, "1")
                                .delay(2, TimeUnit.SECONDS)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new Observer<HistoryBlogBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HistoryBlogBean historyBlogBean) {
                        //获取第二个接口的结果
                        DialogUtils.getInstance().showTip(context, TipType.SUC, "根据ID获取列表:" + historyBlogBean.data.datas.size());
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

    /**
     * 合并多个请求的请求结果
     */
    public void getBlogList_4() {
        //结合getBlogList_3的例子实现,历史文章第一页和第二页的数据合并
        Observable<BlogListBean> observable1 = rxJavaApi.getBlogList();
        observable1
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<BlogListBean, ObservableSource<HistoryBlogBean>>() {
                    @Override
                    public ObservableSource<HistoryBlogBean> apply(BlogListBean blogListBean) throws Exception {
                        //获取第一个接口的数据¬
                        String id = String.valueOf(blogListBean.data.get(0).id);
                        DialogUtils.getInstance().showTip(context, TipType.SUC, "获取ID:" + id);

                        //发起第二个接口的请求事件,一个获取第一页的数据,一个获取第二页的数据
                        Observable<HistoryBlogBean> observableZip1 = rxJavaApi.getBlogHistoryList(id, "1").subscribeOn(Schedulers.newThread());
                        Observable<HistoryBlogBean> observableZip2 = rxJavaApi.getBlogHistoryList(id, "2").subscribeOn(Schedulers.newThread());

                        //合并两次请求事件
                        return Observable
                                .zip(observableZip1, observableZip2, new BiFunction<HistoryBlogBean, HistoryBlogBean, HistoryBlogBean>() {
                                    @Override
                                    public HistoryBlogBean apply(HistoryBlogBean historyBlogBean, HistoryBlogBean historyBlogBean2) throws Exception {
                                        historyBlogBean.data.datas.addAll(historyBlogBean2.data.datas);
                                        return historyBlogBean;
                                    }
                                })
                                .delay(2, TimeUnit.SECONDS)
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new Observer<HistoryBlogBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HistoryBlogBean historyBlogBean) {
                        //获取合并请求结果
                        DialogUtils.getInstance().showTip(context, TipType.SUC, "根据ID获取列表:" + historyBlogBean.data.datas.size());
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

    /**
     * 请求失败后,切换ip重新请求
     */
    public void getBlogList_5() {

        /**
         * 模拟一下请求失败
         */
        Observable<BlogListBean> errObservable = Observable
                .create(new ObservableOnSubscribe<BlogListBean>() {
                    @Override
                    public void subscribe(ObservableEmitter<BlogListBean> emitter) throws Exception {
                        emitter.onError(new Throwable("错误,继续请求"));
                    }
                })
                .subscribeOn(Schedulers.io());
        Observable<BlogListBean> sucObservable = rxJavaApi.getBlogList()
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS);

        errObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BlogListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BlogListBean blogListBean) {
                        DialogUtils.getInstance().showTip(context, TipType.SUC, String.valueOf(blogListBean.data.size()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        DialogUtils.getInstance().showTip(context, TipType.ERR, e.getMessage());
                        //没想到合适的操作符,只能在这里嵌套使用了
                        sucObservable
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<BlogListBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(BlogListBean blogListBean) {
                                        DialogUtils.getInstance().showTip(context, TipType.SUC, String.valueOf(blogListBean.data.size()));
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

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 请求失败重新请求
     */
    public void getBlogList_6() {
        DialogUtils.getInstance().showLoadingDialog(context, "正在加载...");
        rxJavaApi.getBlogList()
                .delay(2, TimeUnit.SECONDS)
                .retry(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BlogListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BlogListBean blogListBean) {
                        DialogUtils.getInstance().showTip(context, TipType.SUC, "获取数据:" + blogListBean.data.size());
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
}
