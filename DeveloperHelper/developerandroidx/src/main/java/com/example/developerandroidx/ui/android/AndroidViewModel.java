package com.example.developerandroidx.ui.android;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import java.util.Date;

public class AndroidViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private final String TAG = "AndroidViewModel";

    public AndroidViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getAdapterList() {
        return mText;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        Log.e(TAG, new Date().getTime() + "：调用了onStart方法");
    }

}