package com.example.developerandroidx.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.developerandroidx.MainActivity;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.bean.EventBusMessageBean;
import com.example.developerandroidx.ui.android.NotificationActivity;
import com.example.developerandroidx.ui.android.service.service.TestIntentService;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.v3.BottomMenu;
import com.kongzue.dialog.v3.FullScreenDialog;
import com.kongzue.dialog.v3.TipDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @作者： zjf 2020/5/15 9:17
 * @描述： 弹框工具类 使用单例模式减少过多的使用静态方法
 * <p>
 * 使用时犯了一个错误，Context上下文变量在构造函数进行了赋值，导致上下文不会改变，弹窗失败
 */
public class DialogUtils {

    private static DialogUtils dialogUtils;


    private DialogUtils() {
    }

    public static DialogUtils getInstance() {
        if (dialogUtils == null) {
            dialogUtils = new DialogUtils();
        }
        return dialogUtils;
    }

    public interface OnFullScreenDialogBindView {
        void onBind(FullScreenDialog dialog, View rootView);
    }

    //全屏对话框展示,需要处理回调
    public void shouFullScreenDialog(Context context, int layoutId, OnFullScreenDialogBindView onFullScreenDialogBindView) {
        FullScreenDialog.show((AppCompatActivity) context, layoutId, new FullScreenDialog.OnBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                if (onFullScreenDialogBindView != null)
                    onFullScreenDialogBindView.onBind(dialog, rootView);
            }
        });
    }

    //全屏对话框展示,不需要处理回调
    public void shouFullScreenDialog(Context context, int layoutId) {
        shouFullScreenDialog(context, layoutId, null);
    }

    /**
     * 显示提醒消息
     *
     * @param context
     */
    public void showTip(Context context, String msg) {
        TipDialog.show((AppCompatActivity) context, msg, TipDialog.TYPE.WARNING);
    }

    /**
     * 通知类型弹框
     */
    public void showNotificationBottomMenuDialog(Context context) {
        String[] notificationStyles = new String[]{"common", "with progress", "custom"};
        BottomMenu.show((AppCompatActivity) context, notificationStyles, new OnMenuItemClickListener() {
            @Override
            public void onClick(String text, int index) {

                switch (text) {
                    case "common":
                        showCommonNotification(context);
                        break;
                    case "with progress":
                        showProgressNotificaion(context);
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
        builder = new NotificationCompat.Builder(context, App.download_channel_id);
        builder.setContentTitle("发工资")
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

        //设置点击通知事件
        Intent intent = new Intent(context, NotificationActivity.class);
        //setFlags() 方法帮助保留用户在通过通知打开应用后的预期导航体验。但您是否要使用这一方法取决于您要启动的 Activity 类型
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        //添加通知按钮,发送一条广播执行操作
//        Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
//        snoozeIntent.setAction("ACTION_SNOOZE");
//        snoozeIntent.putExtra("EXTRA_NOTIFICATION_ID", 0);
//        PendingIntent snoozePendingIntent =
//                PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);
        /**
         * 小图标，通过 setSmallIcon() 设置。这是所必需的唯一一个用户可见内容。
         * 标题，通过 setContentTitle() 设置。
         * 正文文本，通过 setContentText() 设置。
         * 通知优先级，通过 setPriority() 设置。优先级确定通知在 Android 7.1 和更低版本上的干扰程度。（对于 Android 8.0 和更高版本，必须设置渠道重要性，如下一节中所示。）
         */
        builder = new NotificationCompat.Builder(context, App.channel_id)
                .setSmallIcon(R.mipmap.icon_notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_notification))
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)//setAutoCancel()，它会在用户点按通知后自动移除通知。不过对于前台服务通知无效
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.icon_map, "了然", pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {

            builder.setDefaults(App.defaults);

        }
//        要显示通知，请调用 NotificationManagerCompat.notify()，并将通知的唯一 ID 和 NotificationCompat.Builder.build() 的结果传递给它
        notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(100, builder.build());
    }
}
