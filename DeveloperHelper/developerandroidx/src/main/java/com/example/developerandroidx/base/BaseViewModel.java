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

    private MutableLiveData<T> myData = new MutableLiveData<>();

    /**
     * 初始化数据
     *
     * @param dataType 数据类型，根据此参数确认获取的数据
     *                 例：获取登录数据或者其他接口的数据
     *                 在getData(Object dataType)传入获取的数据类型即可
     *                 <p>
     *                 在initData方法里面务必实现setData(T data)方法
     */
    protected abstract void initData(Object dataType);

    /**
     * 设置数据
     *
     * @param data 要设置的数据
     */
    public void setData(T data) {
        myData.setValue(data);
    }

    /**
     * 获取数据
     *
     * @param dataType 数据类型，根据此参数确认获取的数据
     *                 例：获取登录数据或者其他接口的数据
     *                 在getData(Object dataType)传入获取的数据类型即可
     * @return 带有数据的LiveData
     */
    public LiveData<T> getData(Object dataType) {
        initData(dataType);
        return myData;
    }

    /**
     * 如果数据单一用此方法获取数据
     *
     * @return
     */
    public LiveData<T> getData() {
        initData(null);
        return myData;
    }
}
