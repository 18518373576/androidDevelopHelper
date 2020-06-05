package com.example.developerandroidx.ui.android.activity;

import androidx.lifecycle.LiveData;

import com.example.developerandroidx.base.BaseViewModel;

/**
 * Date: 2020/5/6 21:26
 * 参考:
 * 描述: 记录activity的生命周期
 */
public class ActivityAnalysisViewModel extends BaseViewModel<StringBuffer> {

    private StringBuffer buffer = new StringBuffer();

    @Override
    protected StringBuffer initData(Object dataType) {
        return buffer;
    }

    //activity生命周期变动，更新数据
    public void onLifecyleChanged(String lyfecyleInfo) {
        buffer.append(lyfecyleInfo);
        setData(buffer);
    }

    //获取生命周期变化数据
    public LiveData<StringBuffer> getLifecycleBuffer() {
        return getData();
    }
}
