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
        list.add("一个简单的示例");
        list.add("遍历集合数据");
        setData(list);
    }
}
