package com.example.developerandroidx.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Date: 2020/5/27 11:53
 * 参考:
 * 描述: ViewModel的继承类，主要是想指定泛型，不用每次在livedata里面指定了
 * 实现getData()方法，子类只需实现initData()方法
 * 适用于数据类型单一的ViewModel
 */
public abstract class BaseViewModel<T> extends ViewModel {
    private MutableLiveData<T> myData;

    public BaseViewModel() {
        myData = new MutableLiveData<T>();
        myData.setValue(initData());
    }

    protected abstract T initData();

    public void setData(T data) {
        myData.setValue(data);
    }

    public LiveData<T> getData() {
        return myData;
    }
}
