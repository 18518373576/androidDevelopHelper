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
    private List<OperatorItemBean> operatorItemBeans;

    private LifecycleOwner lifecycleOwner;

    public OperatorViewModel() {
        mList = new MediatorLiveData<>();
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
                        initData();
                        mList.setValue(operatorItemBeans);
                        break;
                }
            }
        });
    }

    private void initData() {
        operatorItemBeans = new ArrayList<>();
        operatorItemBeans.add(new OperatorItemBean("算数操作符", "+", "(加法)将两个数相加"));
    }

    public LiveData<List<OperatorItemBean>> getAdapterList() {
        return mList;
    }
}
