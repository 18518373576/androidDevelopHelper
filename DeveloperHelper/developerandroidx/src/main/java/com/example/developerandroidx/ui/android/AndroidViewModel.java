package com.example.developerandroidx.ui.android;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.developerandroidx.R;
import com.example.developerandroidx.bean.FunctionItemBean;
import com.example.developerandroidx.ui.android.activity.ActivityAnalysisActivity;
import com.example.developerandroidx.ui.android.architecture.ArchitectureIndexActivity;
import com.example.developerandroidx.ui.android.dialog.DialogIndexActivity;
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.RouteUtil;

import java.util.ArrayList;
import java.util.List;

public class AndroidViewModel extends ViewModel {

    private MutableLiveData<List<FunctionItemBean>> mList;
    private final String TAG = "AndroidViewModel";

    public AndroidViewModel() {
        mList = new MutableLiveData<>();
        mList.setValue(initData());
    }

    private List<FunctionItemBean> initData() {
        List<FunctionItemBean> functionList = new ArrayList<>();
        functionList.add(new FunctionItemBean("Activity", R.mipmap.icon_activity, RouteUtil.getDestination(ActivityAnalysisActivity.class)));
        functionList.add(new FunctionItemBean("Service", R.mipmap.icon_service, ""));
        functionList.add(new FunctionItemBean("Broadcast Receiver", R.mipmap.icon_broadcast_receiver, ""));
        functionList.add(new FunctionItemBean("Content Provider", R.mipmap.icon_content_provider, ""));
        functionList.add(new FunctionItemBean("Fragment", R.mipmap.icon_fragment, ""));
        functionList.add(new FunctionItemBean("Dialog", R.mipmap.icaon_dialog, RouteUtil.getDestination(DialogIndexActivity.class)));
        functionList.add(new FunctionItemBean("Bluetooth", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("NFC", R.mipmap.icon_nfc, ""));
        functionList.add(new FunctionItemBean("Camera", R.mipmap.icon_camera, ""));
        functionList.add(new FunctionItemBean("ConstraintLayout", R.mipmap.icon_constrain_layout, ""));
        functionList.add(new FunctionItemBean("Sqlite", R.mipmap.icon_sqlite, ""));
        functionList.add(new FunctionItemBean("Animation", R.mipmap.icon_animation, ""));
        functionList.add(new FunctionItemBean("JNI", R.mipmap.icon_jni, ""));
        functionList.add(new FunctionItemBean("扫码", R.mipmap.icon_scan, ""));
        functionList.add(new FunctionItemBean("架构", R.mipmap.icon_architecture, RouteUtil.getDestination(ArchitectureIndexActivity.class)));
        functionList.add(new FunctionItemBean("DataBinding", R.mipmap.icon_data_binding, ""));
        functionList.add(new FunctionItemBean("Butter Knife", R.mipmap.icon_butter_knife, ""));
        functionList.add(new FunctionItemBean("地图", R.mipmap.icon_map, ""));
        functionList.add(new FunctionItemBean("性能优化", R.mipmap.icon_performance_optimization,
                RouteUtil.getDestination(CodeViewActivity.class), CodeVariate.getInstance().getCode_3()));

        return functionList;
    }

    public LiveData<List<FunctionItemBean>> getAdapterList() {
        return mList;
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    void onStart() {
//        Log.e(TAG, new Date().getTime() + "：调用了onStart方法");
//    }

}