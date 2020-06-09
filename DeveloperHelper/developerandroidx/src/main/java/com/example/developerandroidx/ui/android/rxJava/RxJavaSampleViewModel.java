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
                list.add("Observable\n      .just()");
                list.add("Observable\n      .fromIterable()");
                list.add("Observable\n      .range(1, 10)\n     .filter(integer -> integer % 2 == 0)");
                list.add("Observable\n      .range(1, 5)\n      .map(integer -> \"数据:\" + integer)");
                list.add("Observable\n      .range(1, 3)\n      .flatMap(integer -> \n      Observable.just(\"事件被转换:\" + integer))");
                list.add("Observable\n      .just(1, 2, 3, 4, 5).buffer(2, 2)");
                list.add("Observable\n      .timer(2, TimeUnit.SECONDS)\n      .map(aLong -> \"延迟两秒:\" + aLong)");
                list.add("Observable\n      .concat(Observable.just(1, 2),\n      Observable.just(3, 4))");
                list.add("Observable\n      .merge(\n      Observable.intervalRange(1, 2, 0, 2, \n      TimeUnit.SECONDS),\n      Observable.intervalRange(3, 2, 1, 2, \n      TimeUnit.SECONDS))");
                list.add("Observable\n      .zip(observable1, observable2,\n      (aLong, aLong2) ->\n      aLong + \"、\" + aLong2)");
                break;
            case "实际应用":
                list.add("网络请求轮询");
                break;

        }
        setData(list);
    }
}
