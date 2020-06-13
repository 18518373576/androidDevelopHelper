package com.example.developerandroidx.ui.android;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.FunctionItemBean;
import com.example.developerandroidx.ui.android.activity.ActivityAnalysisActivity;
import com.example.developerandroidx.ui.android.animation.AnimationActivity;
import com.example.developerandroidx.ui.android.broadcastReceiver.BroadcastReceiverActivity;
import com.example.developerandroidx.ui.android.camera.CameraActivity;
import com.example.developerandroidx.ui.android.fragment.FragmentActivity;
import com.example.developerandroidx.ui.android.rxJava.RxJavaSampleActivity;
import com.example.developerandroidx.ui.android.service.ServiceActivity;
import com.example.developerandroidx.ui.android.touchEvent.TouchEventActivity;
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.RouteUtil;

import java.util.ArrayList;
import java.util.List;

public class AndroidViewModel extends BaseViewModel<List<FunctionItemBean>> {

    private ArrayList<FunctionItemBean> functionList;

    @Override
    protected void initData(@Nullable String... param) {
        //无数据的时候,设置数据,有数据的时候直接取数据,不再进行设置
        //主要作用就是屏幕切换保存数据,无屏幕切换可忽略此判断
        if (functionList == null)
            setData(initData());
    }

    private List<FunctionItemBean> initData() {
        functionList = new ArrayList<>();
        functionList.add(new FunctionItemBean("Activity", R.mipmap.icon_activity, RouteUtil.getDestination(ActivityAnalysisActivity.class)));
        functionList.add(new FunctionItemBean("Service", R.mipmap.icon_service, RouteUtil.getDestination(ServiceActivity.class)));
        functionList.add(new FunctionItemBean("Broadcast Receiver", R.mipmap.icon_broadcast_receiver, RouteUtil.getDestination(BroadcastReceiverActivity.class)));
        functionList.add(new FunctionItemBean("Content Provider", R.mipmap.icon_content_provider, ""));
        functionList.add(new FunctionItemBean("Fragment", R.mipmap.icon_fragment, RouteUtil.getDestination(FragmentActivity.class)));
        functionList.add(new FunctionItemBean("Dialog", R.mipmap.icon_dialog, ""));
        functionList.add(new FunctionItemBean("Notification", R.mipmap.icon_notification, ""));
        functionList.add(new FunctionItemBean("Touch event", R.mipmap.icon_touch_event, RouteUtil.getDestination(TouchEventActivity.class)));
        functionList.add(new FunctionItemBean("Bluetooth", R.mipmap.icon_bluetooth, ""));
        functionList.add(new FunctionItemBean("NFC", R.mipmap.icon_nfc, ""));
        functionList.add(new FunctionItemBean("Camera", R.mipmap.icon_camera, RouteUtil.getDestination(CameraActivity.class)));
        functionList.add(new FunctionItemBean("Layout", R.mipmap.icon_constrain_layout, ""));
        functionList.add(new FunctionItemBean("Sqlite", R.mipmap.icon_sqlite, ""));
        functionList.add(new FunctionItemBean("HttpRequest", R.mipmap.icon_internet, ""));
        functionList.add(new FunctionItemBean("RxJava", R.mipmap.icon_rxjava, RouteUtil.getDestination(RxJavaSampleActivity.class)));
        functionList.add(new FunctionItemBean("Animation", R.mipmap.icon_animation, RouteUtil.getDestination(AnimationActivity.class)));
        functionList.add(new FunctionItemBean("JNI", R.mipmap.icon_jni, ""));
        functionList.add(new FunctionItemBean("扫码", R.mipmap.icon_scan, ""));
        functionList.add(new FunctionItemBean("架构", R.mipmap.icon_architecture, ""));
        functionList.add(new FunctionItemBean("DataBinding", R.mipmap.icon_data_binding, ""));
        functionList.add(new FunctionItemBean("地图", R.mipmap.icon_map, ""));
        functionList.add(new FunctionItemBean("性能优化", R.mipmap.icon_performance_optimization,
                RouteUtil.getDestination(CodeViewActivity.class), CodeVariate.getInstance().getCode_3()));

        return functionList;
    }

    public LiveData<List<FunctionItemBean>> getAdapterList() {
        return getData();
    }

}