package com.example.developerandroidx.ui.android.rxJava;

import androidx.annotation.Nullable;

import com.example.developerandroidx.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： zjf 2020/6/8 4:15 PM
 * 参考：
 * 描述：
 */
public class RxJavaSampleViewModel extends BaseViewModel<List<String>> {

    @Override
    protected void initData(@Nullable String... param) {
        List<String> list = new ArrayList<>();
        switch (param[0]) {
            case "操作符":
                list.add("1.创建一个事件\n\nObservable\n      .just()");
                list.add("2.从集合创建事件\n\nObservable\n      .fromIterable()");
                list.add("3.从一个整数范围内创建事件\n\nObservable\n      .range(1, 10)\n      .filter(integer -> integer % 2 == 0)");
                list.add("4.转换事件数据类型\n\nObservable\n      .range(1, 5)\n      .map(integer -> \"数据:\" + integer)");
                list.add("5.将事件转换为新的事件进行发送\n\nObservable\n      .range(1, 3)\n      .flatMap(integer -> \n      Observable.just(\"事件被转换:\" + integer))");
                list.add("6.设置事件发送缓存\n\nObservable\n      .just(1, 2, 3, 4, 5)\n      .buffer(2, 2)");
                list.add("7.事件延迟发送\n\nObservable\n      .timer(2, TimeUnit.SECONDS)\n      .map(aLong -> \"延迟两秒:\" + aLong)");
                list.add("8.组合多个被观察者,按发送顺序串行执行\n\nObservable\n      .concat(Observable.just(1, 2),\n      Observable.just(3, 4))");
                list.add("9.组合多个被观察者,按时间顺序并行执行\n\nObservable\n      .merge(\n      Observable.intervalRange(1, 2, 0, 2, \n      TimeUnit.SECONDS),\n      Observable.intervalRange(3, 2, 1, 2, \n      TimeUnit.SECONDS))");
                list.add("10.合并多个线程的被观察者事件内容\n\nObservable\n      .zip(observable1, observable2,\n      (aLong, aLong2) ->\n      aLong + \"、\" + aLong2)");
                list.add("11.将先发送数据的Observable的最后一个数据，与后发送数据的Observable中的每一个数据组合，最终基于组合后的数据发送结果\n\n" +
                        "Observable\n       .combineLatest(\n       Observable1,\n       Observable2,\n       (integer, aLong) -> integer + aLong)");
                list.add("12.把被观察者发送的事件合并为一个事件发送\n\nObservable\n      .just(1, 2, 3, 4)\n      .reduce((integer, integer2)\n      -> integer * integer2)");
                list.add("13.将被观察者Observable发送的数据事件组合到一个数据结构里\n\n" +
                        "Observable\n      .just(1, 2, 3, 4, 5)\n      .collect(()\n      -> new ArrayList<>(),\n      (BiConsumer<List<Integer>, Integer>)\n      (integers, integer)\n      -> integers.add(integer))\n");
                list.add("14.在被观察者发送事件之前追加事件\n\nObservable\n      .just(4, 5, 6)\n      .startWith(Observable.just(1, 2, 3))");
                list.add("15.统计被观察者发送事件的数量\n\nObservable.just(1, 2, 3)\n      .count()");
                break;
            case "实际应用":
                list.add("(无条件)网络请求轮询");
                list.add("(有条件)网络请求轮询");
                list.add("嵌套网络请求,首个接口请求成功后接着请求第二个");
                list.add("合并多个请求的请求结果");
                list.add("请求失败后,尝试切换IP继续访问");
                list.add("请求失败后,尝试重新请求");
                break;

        }
        setData(list);
    }
}
