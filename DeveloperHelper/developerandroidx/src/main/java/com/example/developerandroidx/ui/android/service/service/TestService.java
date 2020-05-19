package com.example.developerandroidx.ui.android.service.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.bean.EventBusMessageBean;
import com.example.developerandroidx.ui.android.broadcastReceiver.AppBroadcastReceiver;
import com.example.developerandroidx.ui.android.notification.NotificationActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * 如果组件通过调用 startService() 启动服务（这会引起对 onStartCommand() 的调用），
 * 则服务会一直运行，直到其使用 stopSelf() 自行停止运行，或由其他组件通过调用 stopService() 将其停止为止。
 * <p>
 * 如果组件通过调用 bindService() 来创建服务，且未调用 onStartCommand()，
 * 则服务只会在该组件与其绑定时运行。当该服务与其所有组件取消绑定后，系统便会将其销毁。
 * <p>
 * 只有在内存过低且必须回收系统资源以供拥有用户焦点的 Activity 使用时，Android 系统才会停止服务。
 * 如果将服务绑定到拥有用户焦点的 Activity，则它其不太可能会终止；如果将服务声明为在前台运行，
 * 则其几乎永远不会终止。如果服务已启动并长时间运行，则系统逐渐降低其在后台任务列表中的位置，
 * 而服务被终止的概率也会大幅提升—如果服务是启动服务，则您必须将其设计为能够妥善处理系统执行的重启。
 * 如果系统终止服务，则其会在资源可用时立即重启服务，但这还取决于您从 onStartCommand() 返回的值。
 */
public class TestService extends Service {

    private final IBinder binder = new MyIBinder();
    private AppBroadcastReceiver receiver;
    private Context context;

    /**
     * 定义binder
     */
    public class MyIBinder extends Binder {

        public TestService getService() {
            //返回服务实例
            return TestService.this;
        }
    }

    /**
     * 首次创建服务时，系统会（在调用 onStartCommand() 或 onBind() 之前）
     * 调用此方法来执行一次性设置程序。如果服务已在运行，则不会调用此方法。
     */
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //开启服务时，注册监听，用于响应前台服务通知栏事件
        receiver = new AppBroadcastReceiver();
        //广播监听回调
        receiver.setOnReceivedListener(new AppBroadcastReceiver.OnReceivedListener() {
            @Override
            public void onReceived(Intent intent) {
                switch (intent.getAction()) {
                    case "com.example.developerandroidx.STOP_FOREGROUND":
                        //销毁当前服务，服务销毁会自动关闭启动的前台服务
                        //除非有绑定服务的组件没有解绑
                        stopForeground();
                        stopSelf();
                        break;
                }
            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.developerandroidx.STOP_FOREGROUND");
        registerReceiver(receiver, intentFilter);


        EventBus.getDefault().post(new EventBusMessageBean(Constant.EventBusMsgId.MSG_ID_03, this.getClass().getName(),
                StringUtils.getInstance().getCurrentTime() + "\nonCreate()"));
    }

    /**
     * 当另一个组件（如 Activity）请求启动服务时，系统会通过调用 startService() 来调用此方法。
     * 执行此方法时，服务即会启动并可在后台无限期运行。如果您实现此方法，则在服务工作完成后，
     * 您需负责通过调用 stopSelf() 或 stopService() 来停止服务。（如果您只想提供绑定，则无需实现此方法。）
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        EventBus.getDefault().post(new EventBusMessageBean(Constant.EventBusMsgId.MSG_ID_03, this.getClass().getName(), StringUtils.getInstance().getCurrentTime() +
                "\nonStartCommand(Intent intent, int flags, int startId)"));
        if (intent.getBooleanExtra("isForeground", false)) {
            startForeground();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 开启前台服务
     */
    private void startForeground() {

        String title = "到账提醒";
        String content = "账户到账￥0.01元";

        //设置点击通知事件
        Intent intent = new Intent(this, NotificationActivity.class);
        //setFlags() 方法帮助保留用户在通过通知打开应用后的预期导航体验。但您是否要使用这一方法取决于您要启动的 Activity 类型
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //添加通知按钮,发送一条广播执行操作
        Intent snoozeIntent = new Intent(context, AppBroadcastReceiver.class);
        snoozeIntent.setAction("com.example.developerandroidx.STOP_FOREGROUND");
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);
        /**
         * 小图标，通过 setSmallIcon() 设置。这是所必需的唯一一个用户可见内容。
         * 标题，通过 setContentTitle() 设置。
         * 正文文本，通过 setContentText() 设置。
         * 通知优先级，通过 setPriority() 设置。优先级确定通知在 Android 7.1 和更低版本上的干扰程度。（对于 Android 8.0 和更高版本，必须设置渠道重要性，如下一节中所示。）
         */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, App.channel_id)
                .setSmallIcon(R.mipmap.icon_service)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_service))
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)//setAutoCancel()，它会在用户点按通知后自动移除通知。不过对于前台服务通知无效
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.icon_map, "了然", snoozePendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {

            builder.setDefaults(App.defaults);

        }
//        要显示通知，请调用 NotificationManagerCompat.notify()，并将通知的唯一 ID 和 NotificationCompat.Builder.build() 的结果传递给它
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(100, builder.build());

        startForeground(102, builder.build());
    }

    /**
     * 停止前台服务
     */
    public void stopForeground() {
        stopForeground(true);
    }

    /**
     * 当另一个组件想要与服务绑定（例如执行 RPC）时，系统会通过调用 bindService() 来调用此方法。
     * 在此方法的实现中，您必须通过返回 IBinder 提供一个接口，以供客户端用来与服务进行通信。
     * 请务必实现此方法；但是，如果您并不希望允许绑定，则应返回 null。
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        EventBus.getDefault().post(new EventBusMessageBean(Constant.EventBusMsgId.MSG_ID_03, this.getClass().getName(), StringUtils.getInstance().getCurrentTime() +
                "\nonBind(Intent intent)"));

        return binder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        EventBus.getDefault().post(new EventBusMessageBean(Constant.EventBusMsgId.MSG_ID_03, this.getClass().getName(), StringUtils.getInstance().getCurrentTime() + "\nonRebind(Intent intent)"));
    }

    /**
     * 调用 unbindService(connection)时执行此方法，如果组件调用bindService开启服务，所有组件与服务解绑后，此方法后会执行
     * onDestroy()
     * activity退出时默认会调用unbindService方法，如果组件同时调用startService和bindService开启服务，
     * 则所有组件与服务解绑后，调用stopService才能结束服务。
     *
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        EventBus.getDefault().post(new EventBusMessageBean(Constant.EventBusMsgId.MSG_ID_03, this.getClass().getName(), StringUtils.getInstance().getCurrentTime() +
                "\nonUnbind(Intent intent)"));
        App.showNotify("TestService", "onUnbind()");
        return true;
    }

    /**
     * 当不再使用服务且准备将其销毁时，系统会调用此方法。服务应通过实现此
     * 方法来清理任何资源，如线程、注册的侦听器、接收器等。这是服务接收的最后一个调用。
     */
    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        EventBus.getDefault().post(new EventBusMessageBean(Constant.EventBusMsgId.MSG_ID_03, this.getClass().getName(), StringUtils.getInstance().getCurrentTime() +
                "\nonDestroy()"));
        App.showNotify("TestService", "onDestroy()");
        super.onDestroy();
    }

    /**
     * 组件与服务通信方法
     *
     * @return
     */
    public String showMessage() {
        return "TestService通信消息";
    }
}
