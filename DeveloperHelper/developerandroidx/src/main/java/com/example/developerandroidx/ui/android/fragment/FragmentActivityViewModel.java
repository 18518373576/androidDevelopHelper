package com.example.developerandroidx.ui.android.fragment;

import com.example.developerandroidx.base.BaseViewModel;

/**
 * Date: 2020/6/1 10:27
 * 参考:
 * 描述:
 */
public class FragmentActivityViewModel extends BaseViewModel<String> {

    @Override
    protected String initData(Object dataType) {
        String data = "测试使用ViewModel进行fragment间的通信";
        return data;
    }
}
