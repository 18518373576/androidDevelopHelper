package com.example.developerandroidx.ui.android.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.model.EventBusMessageBean;
import com.example.developerandroidx.ui.android.broadcastReceiver.AppBroadcastReceiver;
import com.example.developerandroidx.ui.android.service.service.TestIntentService;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.PreferenceUtils;
import com.kongzue.dialog.v3.BottomMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @作者： zjf 2020/5/19 11:18
 * @参考：
 * @描述：
 */
public class NotificationDialog {

    public void show(Context context) {
        showNotificationBottomMenuDialog(context);
    }

    /**
     * 通知类型弹框
     */
    public void showNotificationBottomMenuDialog(Context context) {
        String[] notificationStyles = new String[]{"common", "progress", "custom", "setting"};
        BottomMenu bottomMenu = DialogUtils.getInstance().showBottomMenu(context, notificationStyles, new DialogUtils.OnItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                switch (text) {
                    case "common":
                        showCommonNotification(context);
                        break;
                    case "progress":
                        showProgressNotificaion(context);
                        break;
                    case "custom":
                        showCustomNotification(context);
                        break;
                    case "setting":
                        //设置渠道通知
                        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                        intent.putExtra(Settings.EXTRA_CHANNEL_ID, App.IMPORTANCE_HIGH_CHANNEL_ID);
                        context.startActivity(intent);
                        break;
                }
            }
        });
    }

    /**
     * 展示带进度条的通知
     *
     * @param context
     */
    private NotificationCompat.Builder builder;
    private NotificationManagerCompat notificationManager;
    private Context ProgressNotificaionContext;

    private void showProgressNotificaion(Context context) {
        EventBus.getDefault().register(this);
        ProgressNotificaionContext = context;
        notificationManager = NotificationManagerCompat.from(context);
        builder = new NotificationCompat.Builder(context, App.IMPORTANCE_LOW_CHANNEL_ID);
        builder.setContentTitle("工资到账")
                .setContentText("到账中0%")
                .setSmallIcon(R.mipmap.icon_notification)
                .setPriority(NotificationCompat.PRIORITY_LOW);

        //如果您可以估算操作在任何时间点的完成进度，应通过调用 setProgress(max, progress, false)
        // 使用指示器（如图 4 所示）的“确定性”形式。
        // 第一个参数是“完成”值（如 100）；
        // 第二个参数是当前完成的进度；
        // 最后一个参数表明这是一个确定性进度条。
        int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 0;
        builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        notificationManager.notify(101, builder.build());
        //开启intentService执行任务
        TestIntentService.startActionFoo(context, "", "");
    }

    /**
     * 从intentService{@link TestIntentService}接收eventbus消息更新通知
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsg(EventBusMessageBean message) {
        switch (message.msgId) {
            case Constant.EventBusMsgId.MSG_ID_01: {
                int progress = Integer.parseInt(message.msg) * 10;
                if (progress < 100) {
                    builder.setContentText("到账中" + progress + "%").setProgress(100, progress, false);
                    notificationManager.notify(101, builder.build());
                } else {
                    builder.setProgress(0, 0, false);
                    EventBus.getDefault().unregister(this);
                    notificationManager.cancel(101);
                    showCommonNotification(ProgressNotificaionContext);
                }
            }
            break;
        }
    }

    /**
     * 展示普通提示
     *
     * @param context
     */
    private void showCommonNotification(Context context) {
        String title = "到账提醒";
        String content = "账户到账￥0.01元";

        //设置点击通知事件，点击item的事件
        Intent intent = new Intent(context, NotificationActivity.class);
        //setFlags() 方法帮助保留用户在通过通知打开应用后的预期导航体验。但您是否要使用这一方法取决于您要启动的 Activity 类型
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        //添加通知按钮,发送一条广播执行操作
        Intent iKonwIntent = new Intent(context, AppBroadcastReceiver.class);
        iKonwIntent.setAction(Constant.BroadcastAction.HANDLE_NOTIFICATION);
        iKonwIntent.putExtra("NOTIFICATION_ID", 100);
        PendingIntent iKonwPendingIntent =
                PendingIntent.getBroadcast(context, 0, iKonwIntent, 0);
        //创建支持直接回复的通知操作
        RemoteInput remoteInput = new RemoteInput.Builder(Constant.KEY_TEXT_REPLY)
                .setLabel("我要吐槽")
                .build();
        Intent inputIntent = new Intent(context, AppBroadcastReceiver.class);
        inputIntent.setAction(Constant.BroadcastAction.INPUT_NOTIFICATION);
        inputIntent.putExtra("NOTIFICATION_ID", 100);
        PendingIntent replyPendingIntent =
                PendingIntent.getBroadcast(context,
                        0,
                        inputIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.mipmap.icon_notification,
                        "我要吐槽", replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();


        /**
         * 小图标，通过 setSmallIcon() 设置。这是所必需的唯一一个用户可见内容。
         * 标题，通过 setContentTitle() 设置。
         * 正文文本，通过 setContentText() 设置。
         * 通知优先级，通过 setPriority() 设置。优先级确定通知在 Android 7.1 和更低版本上的干扰程度。（对于 Android 8.0 和更高版本，必须设置渠道重要性，如下一节中所示。）
         */
        builder = new NotificationCompat.Builder(context, App.IMPORTANCE_HIGH_CHANNEL_ID)
                .setSmallIcon(R.mipmap.icon_notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_notification))
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)//setAutoCancel()，它会在用户点按通知后自动移除通知。不过对于前台服务通知无效
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.icon_map, "了然", iKonwPendingIntent)
                .addAction(action)
                .setNumber(10)//测试手机华为mate20x不起作用
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)//锁屏界面展示消息，测试手机华为mate20x不起作用，只有前台服务通知可以锁屏展示
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {

            builder.setDefaults(App.defaults);

        }
//        要显示通知，请调用 NotificationManagerCompat.notify()，并将通知的唯一 ID 和 NotificationCompat.Builder.build() 的结果传递给它
        notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(100, builder.build());
    }

    /**
     * 展示自定义通知栏
     * 注意：使用自定义通知布局时，请特别注意确保您的自定义布局适用于不同的设备屏幕方向和分辨率。
     * 虽然对于所有界面布局，此建议都适用，但它对通知布局而言尤为重要，因为抽屉式通知栏中的空间非常有限。
     * 自定义通知布局的可用高度取决于通知视图。通常情况下，收起后的视图布局的高度上限为 64 dp，展开后的视图布局的高度上限为 256 dp。
     * <p>
     * FLAG_CANCEL_CURRENT:如果要创建的PendingIntent已经存在了，那么在创建新的PendingIntent之前，原先已经存在的PendingIntent中的intent将不能使用
     * <p>
     * FLAG_NO_CREATE:如果要创建的PendingIntent尚未存在，则不创建新的PendingIntent，直接返回null
     * <p>
     * FLAG_ONE_SHOT:相同的PendingIntent只能使用一次，且遇到相同的PendingIntent时不会去更新PendingIntent中封装的Intent的extra部分的内容
     * <p>
     * FLAG_UPDATE_CURRENT:如果要创建的PendingIntent已经存在了，那么在保留原先PendingIntent的同时，将原先PendingIntent封装的Intent中的extra部分替换为现在新创建的PendingIntent的intent中extra的内容
     *
     * @param context
     */
    private final int PLAY_LAST = 0;
    private final int PLAY_STOP = 1;
    private final int PLAY_NEXT = 2;
    private AppBroadcastReceiver receiver;
    private RemoteViews notificationLayout;
    private RemoteViews notificationLayoutExpanded;
    private Notification customNotification;
    private Intent playMusicIntent;
    private PendingIntent pendingIntent;

    private void showCustomNotification(Context context) {
        PreferenceUtils.getInstance().putBooleanValue(Constant.PreferenceKeys.IS_PALYING, false);
        //使用上下文注册一个广播,用于接收自定义view的事件
        registerBroadcastReceiver(context);
        //自定义通知栏的小图
        notificationLayout = new RemoteViews(context.getPackageName(), R.layout.notification_small_custom);
        //自定义通知栏的大图
        notificationLayoutExpanded = new RemoteViews(context.getPackageName(), R.layout.notification_large_custom);
        //此种方式发送的广播必须在清单文件中声明广播接收器
        //        Intent playMusicIntent = new Intent(context, AppBroadcastReceiver.class);
        //        playMusicIntent.setAction(Constant.BroadcastAction.CONTROL_PLAY_MUSIC);
        //上一曲
        playMusicIntent = new Intent(Constant.BroadcastAction.CONTROL_PLAY_MUSIC_ACTION);
        playMusicIntent.putExtra("controlType", PLAY_LAST);
        pendingIntent = PendingIntent.getBroadcast(context, 0, playMusicIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationLayout.setOnClickPendingIntent(R.id.iv_last_song, pendingIntent);

        //下一曲
        playMusicIntent = new Intent(Constant.BroadcastAction.CONTROL_PLAY_MUSIC_ACTION);
        playMusicIntent.putExtra("controlType", PLAY_NEXT);
        pendingIntent = PendingIntent.getBroadcast(context, 1, playMusicIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationLayout.setOnClickPendingIntent(R.id.iv_play_next, pendingIntent);

        //播放和暂停
        playMusicIntent = new Intent(Constant.BroadcastAction.CONTROL_PLAY_MUSIC_ACTION);
        playMusicIntent.putExtra("controlType", PLAY_STOP);
        pendingIntent = PendingIntent.getBroadcast(context, 2, playMusicIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationLayout.setOnClickPendingIntent(R.id.iv_play_stop, pendingIntent);

        customNotification = new NotificationCompat.Builder(context, App.IMPORTANCE_LOW_CHANNEL_ID)
                .setSmallIcon(R.mipmap.icon_notification)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)//完全自定义，只实现此方法即可
                .setCustomBigContentView(notificationLayoutExpanded)
//                .setContentIntent(pendingIntent) //此处设置的是整个通知栏的点击事件
                .build();
        notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(104, customNotification);
    }

    /**
     * 注册广播接收者
     */
    private void registerBroadcastReceiver(Context context) {
        receiver = new AppBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.BroadcastAction.CONTROL_PLAY_MUSIC_ACTION);
        context.registerReceiver(receiver, intentFilter);

        receiver.setOnReceivedListener(new AppBroadcastReceiver.OnReceivedListener() {
            @Override
            public void onReceived(Intent intent) {
                switch (intent.getAction()) {
                    case Constant.BroadcastAction.CONTROL_PLAY_MUSIC_ACTION:
                        switch (intent.getIntExtra("controlType", 0)) {
                            case PLAY_LAST:
                                notificationLayout.setTextViewText(R.id.tv_song_name, "上一曲");
                                notificationManager.notify(104, customNotification);
                                break;
                            case PLAY_NEXT:
                                notificationLayout.setTextViewText(R.id.tv_song_name, "下一曲");
                                notificationManager.notify(104, customNotification);
                                break;
                            case PLAY_STOP:
                                //默认没播放，点击播放按钮切换播放状态
                                boolean isPlaying = !PreferenceUtils.getInstance().getBooleanValue(Constant.PreferenceKeys.IS_PALYING);
                                if (isPlaying) {
                                    notificationLayout.setImageViewResource(R.id.iv_play_stop, R.mipmap.icon_stop);
                                } else {
                                    notificationLayout.setImageViewResource(R.id.iv_play_stop, R.mipmap.icon_play);
                                }
                                PreferenceUtils.getInstance().putBooleanValue(Constant.PreferenceKeys.IS_PALYING, isPlaying);
                                notificationManager.notify(104, customNotification);
                                break;
                        }
                        break;
                }
            }
        });
    }
}
