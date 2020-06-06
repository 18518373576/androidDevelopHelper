package com.example.developerandroidx.ui.android.fragment;

import androidx.annotation.Nullable;

import com.example.developerandroidx.base.BaseViewModel;

/**
 * Date: 2020/6/1 10:27
 * 参考:
 * 描述: Fragment之间共享数据
 */
public class FragmentActivityViewModel extends BaseViewModel<String> {

    @Override
    protected void initData(@Nullable String... param) {
        String data = "测试使用ViewModel进行fragment间的通信";
        setData(data);
    }
}
