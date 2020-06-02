package com.example.developerandroidx.utils;

/**
 * Date: 2020/5/1 10:16
 * 参考:
 * 描述:
 */
public class Constant {

    //创建带回复能力的通知使用到的字段
    public static final String KEY_TEXT_REPLY = "key_text_reply";

    public static boolean isDebug = true;

    public static class PreferenceKeys {
        public static final String MY_PREFERENCE_NAME = "MY_PREFERENCE_NAME";
        public static final String IS_PALYING = "PreferenceKey_01";
    }

    /**
     * intent参数
     */
    public static class IntentParams {
        public static final String INTENT_PARAM = "INTENT_PARAM";
    }

    /**
     * eventbus消息id
     */
    public static class EventBusMsgId {
        public static final int MSG_ID_01 = 1;
        public static final int MSG_ID_02 = 2;
        public static final int MSG_ID_03 = 3;
        public static final int START_SCAN = 4;
    }

    public static class BroadcastAction {
        public static final String TEST = "com.example.developerandroidx.TEST";
        /**
         * 关闭前台服务的广播
         * {@link com.example.developerandroidx.ui.android.service.ServiceActivity}
         * {@link com.example.developerandroidx.ui.android.service.service.TestService}
         * {@link com.example.developerandroidx.ui.android.broadcastReceiver.AppBroadcastReceiver}
         */
        public static final String STOP_FOREGROUND_ACTION = "com.example.developerandroidx.STOP_FOREGROUND_ACTION";
        /**
         * 前台服务通知栏按钮点击事件广播，这里是关闭前台服务
         * {@link com.example.developerandroidx.ui.android.service.service.TestService}
         * {@link com.example.developerandroidx.ui.android.broadcastReceiver.AppBroadcastReceiver}
         */
        public static final String STOP_FOREGROUND = "com.example.developerandroidx.STOP_FOREGROUND";
        /**
         * 处理通知栏按钮事件，这里是关闭通知
         * {@link com.example.developerandroidx.ui.android.notification.NotificationDialog}
         * {@link com.example.developerandroidx.ui.android.broadcastReceiver.AppBroadcastReceiver}
         */
        public static final String HANDLE_NOTIFICATION = "com.example.developerandroidx.HANDLE_NOTIFICATION";
        /**
         * 处理通知栏按输入内容事件
         * {@link com.example.developerandroidx.ui.android.notification.NotificationDialog}
         * {@link com.example.developerandroidx.ui.android.broadcastReceiver.AppBroadcastReceiver}
         */
        public static final String INPUT_NOTIFICATION = "com.example.developerandroidx.INPUT_NOTIFICATION";
        /**
         * 自定义通知栏,按钮点击广播
         * {@link com.example.developerandroidx.ui.android.notification.NotificationDialog}
         * {@link com.example.developerandroidx.ui.android.broadcastReceiver.AppBroadcastReceiver}
         */
        public static final String CONTROL_PLAY_MUSIC_ACTION = "com.example.developerandroidx.CONTROL_PLAY_MUSIC_ACTION";
    }
}
