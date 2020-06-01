package com.example.developerandroidx.ui.android.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * Date: 2020/6/1 11:08
 * 参考:
 * 描述: AndroidViewModel提供了Application的上下文实例
 */
public class TestAndroidViewModel extends AndroidViewModel {
    public TestAndroidViewModel(@NonNull Application application) {
        super(application);
    }
}
