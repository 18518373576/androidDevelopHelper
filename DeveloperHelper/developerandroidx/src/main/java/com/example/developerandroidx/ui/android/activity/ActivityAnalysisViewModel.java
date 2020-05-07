package com.example.developerandroidx.ui.android.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Date: 2020/5/6 21:26
 * 参考:
 * 描述:
 */
public class ActivityAnalysisViewModel extends ViewModel {

    private StringBuffer buffer;
    private MediatorLiveData<StringBuffer> liveDataBuffer;

    public ActivityAnalysisViewModel() {
        buffer = new StringBuffer();
        liveDataBuffer = new MediatorLiveData<>();
        liveDataBuffer.setValue(buffer);
    }

    //activity生命周期变动，更新数据
    public void onLifecyleChanged(String lyfecyleInfo) {
        buffer.append(lyfecyleInfo);
        liveDataBuffer.setValue(buffer);
    }

    //获取生命周期变化数据
    public LiveData<StringBuffer> getLifecycleBuffer() {
        return liveDataBuffer;
    }
}