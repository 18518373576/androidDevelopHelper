package com.example.developerandroidx.ui.android.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationManagerCompat;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.kongzue.dialog.v3.Notification;

/**
 * 测试BroadcastReceiver，同时作为整个APP的广播接收器使用
 * 此APP的所有广播在这里处理
 */
public class AppBroadcastReceiver extends BroadcastReceiver {

    private OnReceivedListener listener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener != null) {
            listener.onReceived(intent);
        }
        switch (intent.getAction()) {
            case Intent.ACTION_SCREEN_ON:
                App.showNotify("广播通知", intent.getAction(), R.mipmap.icon_broadcast_receiver);
                break;
            case "com.example.developerandroidx.HANDLE_NOTIFICATION"://处理通知事件
                NotificationManagerCompat.from(context).cancel(intent.getIntExtra("NOTIFICATION_ID", 0));
                break;
        }
    }

    public interface OnReceivedListener {
        void onReceived(Intent intent);
    }

    /**
     * 广播接收者的回调
     *
     * @param listener
     */
    public void setOnReceivedListener(OnReceivedListener listener) {
        this.listener = listener;
    }
}
