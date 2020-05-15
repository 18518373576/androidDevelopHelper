package com.example.developerandroidx.ui.android.service.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * 这是 Service 的子类，其使用工作线程逐一处理所有启动请求。如果您不要求服务同时处理多个请求，
 * 此类为最佳选择。实现 onHandleIntent()，该方法会接收每个启动请求的 Intent，以便您执行后台工作。
 * <p>
 * 由于大多数启动服务无需同时处理多个请求（实际上，这种多线程情况可能很危险），因此最佳选择是利用 IntentService 类实现服务。
 * IntentService 类会执行以下操作：
 * 创建默认的工作线程，用于在应用的主线程外执行传递给 onStartCommand() 的所有 Intent。
 * 创建工作队列，用于将 Intent 逐一传递给 onHandleIntent() 实现，这样您就永远不必担心多线程问题。
 * 在处理完所有启动请求后停止服务，因此您永远不必调用 stopSelf()。
 * 提供 onBind() 的默认实现（返回 null）。
 * 提供 onStartCommand() 的默认实现，可将 Intent 依次发送到工作队列和 onHandleIntent() 实现。
 * 如要完成客户端提供的工作，请实现 onHandleIntent()。不过，您还需为服务提供小型构造函数。
 */
public class TestIntentService extends IntentService {

    private static final String ACTION_FOO = "com.example.developerandroidx.ui.android.service.service.action.FOO";
    private static final String ACTION_BAZ = "com.example.developerandroidx.ui.android.service.service.action.BAZ";


    public TestIntentService() {
        super("TestIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, TestIntentService.class);
        intent.setAction(ACTION_FOO);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, TestIntentService.class);
        intent.setAction(ACTION_BAZ);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                handleActionFoo();
            } else if (ACTION_BAZ.equals(action)) {
                handleActionBaz();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                Log.e("TestIntentService:", "Foo" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                Log.e("TestIntentService:", "Baz" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
