package com.example.developerandroidx.ui.java.operator;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.developerandroidx.bean.OperatorItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2020/5/4 21:08
 * 参考:
 * 描述:
 */
public class OperatorViewModel extends ViewModel {

    private MediatorLiveData<List<OperatorItemBean>> mList;

    private LifecycleOwner lifecycleOwner;

    public OperatorViewModel() {
        mList = new MediatorLiveData<>();

        mList.setValue(initData());
    }

    /**
     * 绑定activity生命周期
     *
     * @param lifecycleOwner
     */
    public void bindLifeCircle(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        this.lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                switch (event) {
                    case ON_START:
                        break;
                }
            }
        });
    }

    private List<OperatorItemBean> initData() {
        List<OperatorItemBean> operatorItemBeans = new ArrayList<>();
        operatorItemBeans.add(new OperatorItemBean("+", "(加法)将两个数相加", "算数操作符", true, false));
        operatorItemBeans.add(new OperatorItemBean("++", "(自增)将表示数值的变量加1", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("-", "(求相反数或者减法)作为一元操作符时，返回操作元的相反数。" +
                "作为两元操作符时，将两个数相减", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("--", "(自减)将表示数值的变量减1", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("*", "(乘法)将两个数相乘", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("/", "(除法)将两个数相除", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("%", "(求余)获得两个数相除的余数", null, false, true));
        operatorItemBeans.add(new OperatorItemBean("+", "(字符串加法)连接两个字符串", "字符串操作符", true, false));
        operatorItemBeans.add(new OperatorItemBean("+=", "连接两个字符串,并将结果赋给第一个字符串变量", null, false, true));
        return operatorItemBeans;
    }

    public LiveData<List<OperatorItemBean>> getAdapterList() {
        return mList;
    }
}
