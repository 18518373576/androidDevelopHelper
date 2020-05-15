package com.example.developerandroidx.ui.android.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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
    public TestService() {
    }

    /**
     * 首次创建服务时，系统会（在调用 onStartCommand() 或 onBind() 之前）
     * 调用此方法来执行一次性设置程序。如果服务已在运行，则不会调用此方法。
     */
    @Override
    public void onCreate() {
        super.onCreate();
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
        return super.onStartCommand(intent, flags, startId);
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
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /**
     * 当不再使用服务且准备将其销毁时，系统会调用此方法。服务应通过实现此
     * 方法来清理任何资源，如线程、注册的侦听器、接收器等。这是服务接收的最后一个调用。
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
