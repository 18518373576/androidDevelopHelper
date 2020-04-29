package com.example.developerandroidx.ui.java;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.developerandroidx.R;
import com.example.developerandroidx.bean.FunctionItemBean;

import java.util.ArrayList;
import java.util.List;

public class JavaViewModel extends ViewModel {

    private MutableLiveData<List<FunctionItemBean>> mList;
    private List<FunctionItemBean> functionList;

    public JavaViewModel() {
        mList = new MutableLiveData<>();
        initData();
        mList.setValue(functionList);
    }

    private void initData() {
        functionList = new ArrayList<>();
        functionList.add(new FunctionItemBean("单例模式", R.mipmap.icon_bluetooth,""));
        functionList.add(new FunctionItemBean("工厂模式", R.mipmap.icon_bluetooth,""));
        functionList.add(new FunctionItemBean("注解", R.mipmap.icon_bluetooth,""));
        functionList.add(new FunctionItemBean("泛型", R.mipmap.icon_bluetooth,""));
    }

    public LiveData<List<FunctionItemBean>> getAdapterList() {
        return mList;
    }
}