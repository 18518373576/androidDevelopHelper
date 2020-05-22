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
 * <p>
 * 仔细考虑,这样使用广播接收器不合理,这样建立一个广播类,是为了注册到清单文件,接收系统或其他进程的广播的,
 * 如果动态注册此广播的话,onReceive接收到的数据没办法传递,对于通知栏的广播事件,这里又发送了一次广播作为中转,不合理.
 * -------------
 * 随着认知的深入,发现了这样使用的合理性,此接收者在清单文件中声明为静态广播接收者,使用<方式1>来发送广播,处理一一些在onReceive
 * 方法中就能解决的响应事件.
 * 如果需要与组件交互的时候就创建一个AppBroadcastReceiver实例在组件中动态注册,使用<方式2>发送广播
 * <p>
 * 问题描述:先看广播的两种发送方式:
 * 方式1:
 * 现象描述:此种广播发送方式,广播接收者必须在清单文件中声明,不在清单文件中声明的动态注册的广播接收者无法接收到,
 * 因为我在通知栏发送的广播是以这种方式发送的,所以一直很困惑二次传递的问题,后来改用<方式2>发送广播,所有的问题解决了.
 * 因为是静态注册所以,这里实现的 OnReceivedListener listener没有被赋值,消息需要再经过<方式2>的发送方式才能被动态注册的AppBroadcastReceiver接收到.
 * Intent intent = new Intent(context, AppBroadcastReceiver.class);
 * intent.setAction(Constant.BroadcastAction.TEST);
 * intent.putExtra("sendBroadcast", "收到一条广播，还附加了一条消息");
 * sendBroadcast(intent);
 * 方式2:
 * 这种方式发送的广播,广播接收者不需要在清单文件中声明
 * sendBroadcast(new Intent(Constant.BroadcastAction.TEST).putExtra("sendBroadcast", "收到一条广播，还附加了一条消息"));
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
