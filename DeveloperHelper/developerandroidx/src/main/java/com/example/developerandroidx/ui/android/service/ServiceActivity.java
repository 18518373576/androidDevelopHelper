package com.example.developerandroidx.ui.android.service;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.android.service.service.TestIntentService;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * service(服务)知识点整理
 */
public class ServiceActivity extends BaseActivity {

    @BindView(R.id.tv_task)
    TextView tv_task;
    @BindView(R.id.pb_task)
    ProgressBar pb_task;

    @Override
    protected int bindLayout() {
        return R.layout.activity_service;
    }

    @Override
    protected void initView() {
        setTitle("Service");
        iv_right.setVisibility(View.VISIBLE);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @OnClick({R.id.iv_right, R.id.btn_intent_service})
    public void click(View v) {

        switch (v.getId()) {
            case R.id.iv_right:
                DialogUtils.getInstance().shouFullScreenDialog(context, R.layout.dialog_service_desc, new DialogUtils.OnFullScreenDialogBindView() {
                    @Override
                    public void onBind(FullScreenDialog dialog, View rootView) {
                        ExtensibleScrollView esv_content = rootView.findViewById(R.id.esv_content);
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

                    }
                });
                break;
            case R.id.btn_intent_service:

                TestIntentService.startActionFoo(context, "", "");
                TestIntentService.startActionBaz(context, "", "");
//                startService(new Intent(this, TestIntentService.class));
                break;
        }

    }
}
