package com.example.developerandroidx.ui.android.service;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/19 20:28
 * 参考:
 * 描述: service描述弹框
 */
public class ServiceDescDialog implements FunctionDialogInterface {

    @Override
    public void show(Context context) {

        DialogUtils.getInstance().showEsvDialog(context, new DialogUtils.OnEsvDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content) {
                title.setText("Service概述");
                esv_content.addText("Service 是一种可在后台执行长时间运行操作而不提供界面的应用组件。服务可由其他应用组件启动，而且即使用户切换到其他应用，服务仍将在后台继续运行。此外，组件可通过绑定到服务与之进行交互，甚至是执行进程间通信 (IPC)。例如，服务可在后台处理网络事务、播放音乐，执行文件 I/O 或与内容提供程序进行交互。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addImage(R.mipmap.service_lifecycle, 350);
                esv_content.addText("前台服务", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("前台服务执行一些用户能注意到的操作。例如，音频应用会使用前台服务来播放音频曲目。前台服务必须显示通知。即使用户停止与应用的交互，前台服务仍会继续运行。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("后台服务", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("后台服务执行用户不会直接注意到的操作。例如，如果应用使用某个服务来压缩其存储空间，则此服务通常是后台服务。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("绑定服务", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("当应用组件通过调用 bindService() 绑定到服务时，服务即处于绑定状态。绑定服务会提供客户端-服务器接口，以便组件与服务进行交互、发送请求、接收结果，甚至是利用进程间通信 (IPC) 跨进程执行这些操作。仅当与另一个应用组件绑定时，绑定服务才会运行。多个组件可同时绑定到该服务，但全部取消绑定后，该服务即会被销毁。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("注意事项", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.colorRed);
                esv_content.addText("虽然本文档分开概括讨论启动服务和绑定服务，但您的服务可同时以这两种方式运行，换言之，它既可以是启动服务（以无限期运行），亦支持绑定。唯一的问题在于您是否实现一组回调方法：onStartCommand()（让组件启动服务）和 onBind()（实现服务绑定）。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("无论服务是处于启动状态还是绑定状态（或同时处于这两种状态），任何应用组件均可像使用 Activity 那样，通过调用 Intent 来使用服务（即使此服务来自另一应用）。不过，您可以通过清单文件将服务声明为私有服务，并阻止其他应用访问该服务。使用清单文件声明服务部分将对此做更详尽的阐述。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("服务在其托管进程的主线程中运行，它既不创建自己的线程，也不在单独的进程中运行（除非另行指定）。如果服务将执行任何 CPU 密集型工作或阻止性操作（例如 MP3 播放或联网），则应通过在服务内创建新线程来完成这项工作。通过使用单独的线程，您可以降低发生“应用无响应”(ANR) 错误的风险，而应用的主线程仍可继续专注于运行用户与 Activity 之间的交互。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("在服务和线程之间进行选择", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("简单地说，服务是一种即使用户未与应用交互也可在后台运行的组件，因此，只有在需要服务时才应创建服务。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("如果您必须在主线程之外执行操作，但只在用户与您的应用交互时执行此操作，则应创建新线程。例如，如果您只是想在 Activity 运行的同时播放一些音乐，则可在 onCreate() 中创建线程，在 onStart() 中启动线程运行，然后在 onStop() 中停止线程。您还可考虑使用 AsyncTask 或 HandlerThread，而非传统的 Thread 类。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("请记住，如果您确实要使用服务，则默认情况下，它仍会在应用的主线程中运行，因此，如果服务执行的是密集型或阻止性操作，则您仍应在服务内创建新线程。", ExtensibleScrollView.InsertTextType.BODY, R.color.colorRed);
                esv_content.addText("绑定到已启动服务", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("您可以创建同时具有已启动和已绑定两种状态的服务。换言之，可通过调用 startService() 启动服务，让服务无限期运行；此外，还可通过调用 bindService() 让客户端绑定到该服务。如果您确实允许服务同时具有已启动和已绑定状态，则在启动服务后，如果所有客户端均解绑服务，则系统不会销毁该服务。为此，您必须通过调用 stopSelf() 或 stopService() 显式停止服务。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("管理绑定服务的生命周期", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("当取消服务与所有客户端之间的绑定时，Android 系统会销毁该服务（除非您还使用 onStartCommand() 启动了该服务）。因此，如果您的服务完全是绑定服务，则您无需管理其生命周期，Android 系统会根据它是否绑定到任何客户端代您管理。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("不过，如果您选择实现 onStartCommand() 回调方法，则您必须显式停止服务，因为系统现已将其视为已启动状态。在此情况下，服务将一直运行，直到其通过 stopSelf() 自行停止，或其他组件调用 stopService()（与该服务是否绑定到任何客户端无关）。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("此外，如果您的服务已启动并接受绑定，则当系统调用您的 onUnbind() 方法时，如果您想在客户端下一次绑定到服务时接收 onRebind() 调用，则可选择返回 true。onRebind() 返回空值，但客户端仍在其 onServiceConnected() 回调中接收 IBinder。下图说明这种生命周期的逻辑。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addImage(R.mipmap.service_binding_tree_lifecycle, 350);

                esv_content.addText("IntentService", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("IntentService 类为在单个后台线程上运行操作提供了一个简单明了的结构。这使它能够在不影响界面响应速度的情况下处理长时间运行的操作。此外，IntentService 不受大多数界面生命周期事件的影响，因此它能够在会关闭 AsyncTask 的情况下继续运行", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("IntentService 有一些限制：", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("1.它无法直接与您的界面互动。要在界面中显示其结果，您必须将结果发送到 Activity。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("2.工作请求依序运行。如果某个操作在 IntentService 中运行，并且您向其发送了另一个请求，则该请求会等待第一个操作完成。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("3.在 IntentService 上运行的操作无法中断。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("但在大多数情况下，执行简单后台操作的首选方式是 IntentService。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
            }
        });
    }
}
