package com.example.developerandroidx.ui.android;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.developerandroidx.R;
import com.example.developerandroidx.bean.FunctionItemBean;

import java.util.ArrayList;
import java.util.List;

public class AndroidViewModel extends ViewModel {

    private MutableLiveData<List<FunctionItemBean>> mList;
    private final String TAG = "AndroidViewModel";

    private List<FunctionItemBean> functionList;

    public AndroidViewModel() {
        mList = new MutableLiveData<>();
        initData();

        mList.setValue(functionList);
    }

    private void initData()
    {
        functionList = new ArrayList<>();
        functionList.add(new FunctionItemBean("Activity", R.mipmap.icon_activity,""));
        functionList.add(new FunctionItemBean("Service", R.mipmap.icon_service,""));
        functionList.add(new FunctionItemBean("Broadcast Receiver", R.mipmap.icon_broadcast_receiver,""));
        functionList.add(new FunctionItemBean("Content Provider", R.mipmap.icon_content_provider,""));
        functionList.add(new FunctionItemBean("Bluetooth", R.mipmap.icon_bluetooth,""));
        functionList.add(new FunctionItemBean("NFC", R.mipmap.icon_nfc,""));
        functionList.add(new FunctionItemBean("Camera", R.mipmap.icon_camera,""));
        functionList.add(new FunctionItemBean("ConstraintLayout", R.mipmap.icon_constrain_layout,""));
        functionList.add(new FunctionItemBean("Sqlite", R.mipmap.icon_sqlite,""));
        functionList.add(new FunctionItemBean("Animation", R.mipmap.icon_animation,""));
        functionList.add(new FunctionItemBean("JNI", R.mipmap.icon_jni,""));
        functionList.add(new FunctionItemBean("扫二维码", R.mipmap.icon_scan,""));
    }

    public LiveData<List<FunctionItemBean>> getAdapterList() {
        return mList;
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    void onStart() {
//        Log.e(TAG, new Date().getTime() + "：调用了onStart方法");
//    }

}