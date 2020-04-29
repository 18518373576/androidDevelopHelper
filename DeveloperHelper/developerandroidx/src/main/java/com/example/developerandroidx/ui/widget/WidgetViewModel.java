package com.example.developerandroidx.ui.widget;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.developerandroidx.R;
import com.example.developerandroidx.bean.FunctionItemBean;

import java.util.ArrayList;
import java.util.List;

public class WidgetViewModel extends ViewModel {

    private MutableLiveData<List<FunctionItemBean>> mList;
    private List<FunctionItemBean> functionList;

    public WidgetViewModel() {
        mList = new MutableLiveData<>();
        initData();
        mList.setValue(functionList);
    }

    private void initData()
    {
        functionList = new ArrayList<>();
        functionList.add(new FunctionItemBean("ActionBar", R.mipmap.icon_activity,""));
        functionList.add(new FunctionItemBean("RecyclerView", R.mipmap.icon_activity,""));
        functionList.add(new FunctionItemBean("CardView", R.mipmap.icon_activity,""));
    }
    public LiveData<List<FunctionItemBean>> getAdapterList() {
        return mList;
    }
}