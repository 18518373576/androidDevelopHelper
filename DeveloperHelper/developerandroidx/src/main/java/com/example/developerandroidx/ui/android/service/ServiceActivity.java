package com.example.developerandroidx.ui.android.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.bean.EventBusMessageBean;
import com.example.developerandroidx.ui.android.service.service.TestIntentService;
import com.example.developerandroidx.ui.android.service.service.TestService;
import com.example.developerandroidx.utils.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * service(服务)知识点整理
 */
public class ServiceActivity extends BaseActivity {

    @BindView(R.id.tv_task)
    TextView tv_task;
    @BindView(R.id.tv_print)
    TextView tv_print;
    @BindView(R.id.pb_task)
    ProgressBar pb_task;

    //服务是否是绑定状态
    private boolean mBound = false;

    private TestService myService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TestService.MyIBinder binder = (TestService.MyIBinder) service;//获取IBinder
            myService = binder.getService();//获取服务实例
            App.showNotify(myService.showMessage());//与服务通信，并提示消息
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected int bindLayout() {
        return R.layout.activity_service;
    }

    @Override
    protected void initView() {

        EventBus.getDefault().register(this);
        setTitle("Service");
        iv_right.setVisibility(View.VISIBLE);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @OnClick({R.id.iv_right, R.id.btn_intent_service, R.id.btn_start_service, R.id.btn_bind_service,
            R.id.btn_stop_service, R.id.btn_unbind_service, R.id.btn_start_foreground, R.id.btn_stop_foreground,
            R.id.btn_aidl})
    public void click(View v) {

        switch (v.getId()) {
            case R.id.iv_right:
                new ServiceDescDialog().show(context);
                break;
            case R.id.btn_intent_service:
                TestIntentService.startActionFoo(context, "", "");
                TestIntentService.startActionBaz(context, "", "");
                break;
            case R.id.btn_start_service:
                startService(new Intent(this, TestService.class));
                break;
            case R.id.btn_stop_service:
                stopService(new Intent(this, TestService.class));
                break;
            case R.id.btn_bind_service:
                bindService(new Intent(this, TestService.class), connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                if (mBound) {
                    unbindService(connection);
                    mBound = false;
                }
                break;
            case R.id.btn_start_foreground:
                Intent intent = new Intent(this, TestService.class).putExtra("isForeground", true);
                startService(intent);
                break;
            case R.id.btn_stop_foreground:
                //发送关闭前台服务的广播
                sendBroadcast(new Intent(Constant.BroadcastAction.STOP_FOREGROUND_ACTION));
                break;
            case R.id.btn_aidl:

                break;
        }

    }


    /**
     * threadMode = ThreadMode.MAIN
     * POSTING：默认，表示事件处理函数的线程跟发布事件的线程在同一个线程。
     * MAIN：表示事件处理函数的线程在主线程(UI)线程，因此在这里不能进行耗时操作。
     * BACKGROUND：表示事件处理函数的线程在后台线程，因此不能进行UI操作。如果发布事件的线程是主线程(UI线程)，那么事件处理函数将会开启一个后台线程，如果果发布事件的线程是在后台线程，那么事件处理函数就使用该线程。
     * ASYNC：表示无论事件发布的线程是哪一个，事件处理函数始终会新建一个子线程运行，同样不能进行UI操作。
     * {@link TestService}{@link TestIntentService}
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsg(EventBusMessageBean message) {
        switch (message.msgId) {
            case Constant.EventBusMsgId.MSG_ID_01: {
                int progress = Integer.parseInt(message.msg) * 10;
                tv_task.setText("A计划：" + progress + "%");
                pb_task.setProgress(progress);
            }
            break;
            case Constant.EventBusMsgId.MSG_ID_02: {
                int progress = Integer.parseInt(message.msg) * 10;
                tv_task.setText("B计划：" + progress + "%");
                pb_task.setProgress(progress);
            }
            break;
            case Constant.EventBusMsgId.MSG_ID_03: {
                tv_print.append(message.msg + "\n");
            }
            break;
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
