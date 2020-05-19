package com.example.developerandroidx.ui.android.broadcastReceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.DialogUtils;

import butterknife.OnClick;

public class BroadcastReceiverActivity extends BaseActivity {


    private AppBroadcastReceiver receiver;

    @Override
    protected int bindLayout() {
        return R.layout.activity_broadcast_receiver;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Broadcast Receiver");
        iv_right.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        receiver = new AppBroadcastReceiver();
        IntentFilter screenStatusIF = new IntentFilter();
        screenStatusIF.addAction(Intent.ACTION_SCREEN_ON);
        screenStatusIF.addAction("com.example.developerandroidx.TEST");
        registerReceiver(receiver, screenStatusIF);

        receiver.setOnReceivedListener(new AppBroadcastReceiver.OnReceivedListener() {
            @Override
            public void onReceived(Intent intent) {
                switch (intent.getAction()) {
                    case "com.example.developerandroidx.TEST":
                        DialogUtils.getInstance().showTip(context, intent.getAction());
                        break;
                }
            }
        });
    }

    @OnClick({R.id.iv_right})
    public void click(View v) {

        switch (v.getId()) {
            case R.id.iv_right:
                sendBroadcast(new Intent("com.example.developerandroidx.TEST"));
//                DialogUtils.getInstance().
                break;
        }
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
