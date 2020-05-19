package com.example.developerandroidx.base;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

import com.example.developerandroidx.R;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.v3.Notification;

public class App extends Application {
    public static Context context;
    public static String channel_id;
    public static String download_channel_id;
    public static int defaults;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        //初始化弹框
        initDialog();
        //初始化通知
        initNotification();
    }

    /**
     * 初始化通知
     */
    private void initNotification() {
        channel_id = "channel_id";  // 通知渠道的id
        download_channel_id = "channel_id_01";  // 通知渠道的id
        CharSequence name = "到账提醒";   // 用户可以看到的通知渠道的名字.
        String description = "用户账户变动通知";// 用户可以看到的通知渠道的描述

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(channel_id, name, NotificationManager.IMPORTANCE_HIGH);
            NotificationChannel downloadChannel = new NotificationChannel(download_channel_id, "到账进度", NotificationManager.IMPORTANCE_LOW);
            channel.setDescription(description);
            downloadChannel.setDescription(description);
            downloadChannel.enableLights(false);
            downloadChannel.enableVibration(false);

            //高版本可以根据渠道id分别设置通知震动和铃声
            //            mChannel.enableLights(true); // 设置通知出现时的闪灯（如果 android 设备支持的话）
            if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE
                    || audioManager.getRingerMode() == AudioManager.MODE_NORMAL) {
                channel.enableVibration(true);  // 设置通知出现时的震动（如果 android 设备支持的话）
            }
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            notificationManager.createNotificationChannel(downloadChannel);
        } else {
            //低版本设置通知震动和铃声
            int defaults = android.app.Notification.DEFAULT_LIGHTS | android.app.Notification.DEFAULT_SOUND;
//            defaults = android.app.Notification.DEFAULT_SOUND;
            if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE
                    || audioManager.getRingerMode() == AudioManager.MODE_NORMAL) {
                defaults |= android.app.Notification.DEFAULT_VIBRATE;//设置Notification.DEFAULT_VIBRATE的flag后可能会在任何情况下都震动，部分系统的bug，所以要判断是否开启振动
            }
        }

    }

    /**
     * 弹出通知
     *
     * @param showMsg
     */
    public static void showNotify(String showMsg) {
        showNotify("提示", showMsg, R.mipmap.icon_message);
    }

    /**
     * 弹出通知
     *
     * @param title
     * @param showMsg
     */
    public static void showNotify(String title, String showMsg) {
        showNotify(title, showMsg, R.mipmap.icon_message);
    }

    /**
     * 弹出通知
     *
     * @param title
     * @param showMsg
     */
    public static void showNotify(String title, String showMsg, int mipmapId) {
//        Notification.show(context, title, showMsg, mipmapId);
        Notification.build(context, showMsg)
                .setIconResId(mipmapId)
                .setTitle(title)
                .setStyle(DialogSettings.STYLE.STYLE_IOS)
                .showNotification();
    }

    /**
     * 初始化dialog{@link DialogSettings}
     */
    private void initDialog() {
        DialogSettings.init();                           //初始化清空 BaseDialog 队列
        DialogSettings.isUseBlur = true;                   //是否开启模糊效果，默认关闭
//        DialogSettings.modalDialog = (boolean);                 //是否开启模态窗口模式，一次显示多个对话框将以队列形式一个一个显示，默认关闭
        DialogSettings.style = DialogSettings.STYLE.STYLE_MATERIAL;          //全局主题风格，提供三种可选风格，STYLE_MATERIAL, STYLE_KONGZUE, STYLE_IOS
//        DialogSettings.theme = (DialogSettings.THEME);          //全局对话框明暗风格，提供两种可选主题，LIGHT, DARK
//        DialogSettings.tipTheme = (DialogSettings.THEME);       //全局提示框明暗风格，提供两种可选主题，LIGHT, DARK
//        DialogSettings.titleTextInfo = (TextInfo);              //全局对话框标题文字样式
//        DialogSettings.menuTitleInfo = (TextInfo);              //全局菜单标题文字样式
//        DialogSettings.menuTextInfo = (TextInfo);               //全局菜单列表文字样式
//        DialogSettings.contentTextInfo = (TextInfo);            //全局正文文字样式
//        DialogSettings.buttonTextInfo = (TextInfo);             //全局默认按钮文字样式
//        DialogSettings.buttonPositiveTextInfo = (TextInfo);     //全局焦点按钮文字样式（一般指确定按钮）
//        DialogSettings.inputInfo = (InputInfo);                 //全局输入框文本样式
//        DialogSettings.backgroundColor = (ColorInt);            //全局对话框背景颜色，值0时不生效
//        DialogSettings.cancelable = (boolean);                  //全局对话框默认是否可以点击外围遮罩区域或返回键关闭，此开关不影响提示框（TipDialog）以及等待框（TipDialog）
//        DialogSettings.cancelableTipDialog = true;         //全局提示框及等待框（WaitDialog、TipDialog）默认是否可以关闭
//        DialogSettings.DEBUGMODE = (boolean);                   //是否允许打印日志
//        DialogSettings.blurAlpha = (int);                       //开启模糊后的透明度（0~255）
//        DialogSettings.systemDialogStyle = (styleResId);        //自定义系统对话框style，注意设置此功能会导致原对话框风格和动画失效
//        DialogSettings.dialogLifeCycleListener = (DialogLifeCycleListener);  //全局Dialog生命周期监听器
//        DialogSettings.defaultCancelButtonText = (String);      //设置 BottomMenu 和 ShareDialog 默认“取消”按钮的文字
//        DialogSettings.tipBackgroundResId = (drawableResId);    //设置 TipDialog 和 WaitDialog 的背景资源
//        DialogSettings.tipTextInfo = (InputInfo);               //设置 TipDialog 和 WaitDialog 文字样式
//        DialogSettings.autoShowInputKeyboard = (boolean);       //设置 InputDialog 是否自动弹出输入法
//        DialogSettings.okButtonDrawable = (drawable);           //设置确定按钮背景资源
//        DialogSettings.cancelButtonDrawable = (drawable);       //设置取消按钮背景资源
//        DialogSettings.otherButtonDrawable = (drawable);        //设置其他按钮背景资源
//
//        //检查 Renderscript 兼容性，若设备不支持，DialogSettings.isUseBlur 会自动关闭；
//        boolean renderscriptSupport = DialogSettings.checkRenderscriptSupport(context)
    }
}
