package com.example.developerandroidx.ui.android.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.utils.Constant;

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
            case Constant.BroadcastAction.STOP_FOREGROUND:
                /**
                 * 接收到通知栏事件的广播，继续传递给服务，执行事件,这里是关闭前台服务
                 * {@link com.example.developerandroidx.ui.android.service.service.TestService}
                 * {@link com.example.developerandroidx.ui.android.service.ServiceActivity}
                 */
                context.sendBroadcast(new Intent(Constant.BroadcastAction.STOP_FOREGROUND_ACTION));
                break;
            case Intent.ACTION_SCREEN_ON:
                /**
                 * 安卓高版本，限制了一些广播在清单文件中的申请
                 * {@link BroadcastReceiverActivity}
                 */
                App.showNotify("广播通知", intent.getAction(), R.mipmap.icon_broadcast_receiver);
                break;
            case Constant.BroadcastAction.HANDLE_NOTIFICATION:
                /**
                 * 处理通知栏按钮事件，此处是删除当前通知
                 * {@link com.example.developerandroidx.ui.android.notification.NotificationDialog}
                 * {@link com.example.developerandroidx.ui.android.notification.NotificationActivity}
                 */
                NotificationManagerCompat.from(context).cancel(intent.getIntExtra("NOTIFICATION_ID", 0));
                break;
            case Constant.BroadcastAction.INPUT_NOTIFICATION:
                /**
                 * 处理通知栏输入内容
                 * {@link com.example.developerandroidx.ui.android.notification.NotificationDialog}
                 * {@link com.example.developerandroidx.ui.android.notification.NotificationActivity}
                 */
                Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
                if (remoteInput != null) {
                    //获取到输入内容，并执行操作
                    String inputStr = (String) remoteInput.getCharSequence(Constant.KEY_TEXT_REPLY);
                    App.showNotify("广播通知", inputStr, R.mipmap.icon_broadcast_receiver);
                    //确认操作执行完成后，更新通知
//                    Notification repliedNotification = new NotificationCompat.Builder(context, App.channel_id)
//                            .setSmallIcon(R.mipmap.icon_notification)
//                            .setContentText("已发送")
//                            .build();
//                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
//                    notificationManager.notify(intent.getIntExtra("NOTIFICATION_ID", 0), repliedNotification);
                    //收到回复后,确认操作执行完成后,清除通知
                    NotificationManagerCompat.from(context).cancel(intent.getIntExtra("NOTIFICATION_ID", 0));
                }
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
