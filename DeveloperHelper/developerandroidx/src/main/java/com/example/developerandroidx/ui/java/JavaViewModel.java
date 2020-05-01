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
        functionList.add(new FunctionItemBean("数据类型", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("操作符", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("流程控制", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("继承", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("修饰符", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("接口", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("异常处理", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("类的生命周期", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("对象的生命周期", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("内部类", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("多线程", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("数组", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("集合", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("泛型", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("Lmabda表达式", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("I/O", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("java常用类", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("Annotatio注解", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("代理", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("反射", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("设计模式", R.mipmap.icon_bluetooth, ""));
    }

    public LiveData<List<FunctionItemBean>> getAdapterList() {
        return mList;
    }
}