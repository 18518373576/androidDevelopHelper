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
        functionList.add(new FunctionItemBean("Activity", R.mipmap.activity_icon,""));
        functionList.add(new FunctionItemBean("Service", R.mipmap.service_icon,""));
        functionList.add(new FunctionItemBean("Broadcast Receiver", R.mipmap.broadcast_receiver_icon,""));
        functionList.add(new FunctionItemBean("Content Provider", R.mipmap.content_provider_icon,""));
        functionList.add(new FunctionItemBean("Bluetooth", R.mipmap.bluetooth_icon,""));
        functionList.add(new FunctionItemBean("NFC", R.mipmap.android,""));
        functionList.add(new FunctionItemBean("Camera", R.mipmap.android,""));
        functionList.add(new FunctionItemBean("ActionBar", R.mipmap.android,""));
        functionList.add(new FunctionItemBean("ConstraintLayout", R.mipmap.android,""));
        functionList.add(new FunctionItemBean("RecyclerView", R.mipmap.android,""));
        functionList.add(new FunctionItemBean("Sqlite", R.mipmap.android,""));
        functionList.add(new FunctionItemBean("Animation", R.mipmap.android,""));
        functionList.add(new FunctionItemBean("JNI", R.mipmap.android,""));
        functionList.add(new FunctionItemBean("CardView", R.mipmap.android,""));
    }

    public LiveData<List<FunctionItemBean>> getAdapterList() {
        return mList;
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    void onStart() {
//        Log.e(TAG, new Date().getTime() + "：调用了onStart方法");
//    }

}