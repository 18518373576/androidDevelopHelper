package com.example.developerandroidx.ui.android.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.ui.widget.webView.TechnologyWebviewActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/19 20:24
 * 参考:
 * 描述: activity的描述弹框，增加一个类，为了使activity类界面更加简洁
 */
public class ActivityAnalysisDescDialog implements FunctionDialogInterface {

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, new DialogUtils.OnEsvDialogBindView() {

            @Override
            public void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content) {
                title.setText("Activity概述");

                esv_content.addText("Activity的概念", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("Activity 类是 Android 应用的关键组件，而 Activity 的启动和组合方式则是该平台应用模型的基本组成部分。在编程范式中，应用是通过 main() 方法启动的，而 Android 系统与此不同，它会调用与其生命周期特定阶段相对应的特定回调方法来启动 Activity 实例中的代码。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("Activity 提供窗口供应用在其中绘制界面。此窗口通常会填满屏幕，但也可能比屏幕小，并浮动在其他窗口上面。通常，一个 Activity 实现应用中的一个屏幕。例如，应用中的一个 Activity 实现“偏好设置”屏幕，而另一个 Activity 实现“选择照片”屏幕。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("Activity的生命周期", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addImage(R.mipmap.activity_lifecycle, 400);
                esv_content.addText("", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);

                esv_content.addText("onCreate()", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("您必须实现此回调，它会在系统创建您的 Activity 时触发。您的实现应该初始化 Activity 的基本组件：例如，您的应用应该在此处创建视图并将数据绑定到列表。最重要的是，您必须在此处调用 setContentView() 来定义 Activity 界面的布局。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);

                esv_content.addText("onStart()", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("onCreate() 退出后，Activity 将进入“已启动”状态，并对用户可见。此回调包含 Activity 进入前台与用户进行互动之前的最后准备工作。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);

                esv_content.addText("onResume()", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("系统会在 Activity 开始与用户互动之前调用此回调。此时，该 Activity 位于 Activity 堆栈的顶部，并会捕获所有用户输入。应用的大部分核心功能都是在 onResume() 方法中实现的。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);

                esv_content.addText("onPause()", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("当 Activity 失去焦点并进入“已暂停”状态时，系统就会调用 onPause()。例如，当用户点按“返回”或“最近使用的应用”按钮时，就会出现此状态。当系统为您的 Activity 调用 onPause() 时，从技术上来说，这意味着您的 Activity 仍然部分可见，但大多数情况下，这表明用户正在离开该 Activity，该 Activity 很快将进入“已停止”或“已恢复”状态。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("如果用户希望界面继续更新，则处于“已暂停”状态的 Activity 也可以继续更新界面。例如，显示导航地图屏幕或播放媒体播放器的 Activity 就属于此类 Activity。即使此类 Activity 失去了焦点，用户仍希望其界面继续更新。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("您不应使用 onPause() 来保存应用或用户数据、进行网络呼叫或执行数据库事务。有关保存数据的信息，请参阅保存和恢复 Activity 状态。onPause() 执行完毕后，下一个回调为 onStop()或 onResume()，具体取决于 Activity 进入“已暂停”状态后发生的情况", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);

                esv_content.addText("onStop()", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("当 Activity 对用户不再可见时，系统会调用 onStop()。出现这种情况的原因可能是 Activity 被销毁，新的 Activity 启动，或者现有的 Activity 正在进入“已恢复”状态并覆盖了已停止的 Activity。在所有这些情况下，停止的 Activity 都将完全不再可见。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("系统调用的下一个回调将是 onRestart()（如果 Activity 重新与用户互动）或者 onDestroy()（如果 Activity 彻底终止）。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);

                esv_content.addText("onRestart()", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("当处于“已停止”状态的 Activity 即将重启时，系统就会调用此回调。onRestart() 会从 Activity 停止时的状态恢复 Activity。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("此回调后面总是跟着 onStart()。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);

                esv_content.addText("onDestroy()", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addText("系统会在销毁 Activity 之前调用此回调。此回调是 Activity 接收的最后一个回调。通常，实现 onDestroy() 是为了确保在销毁 Activity 或包含该 Activity 的进程时释放该 Activity 的所有资源。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
                esv_content.addText("任务和返回栈", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addBodyWithIntent("了解任务和返回堆栈", R.color.colorMain,
                        new Intent(context, TechnologyWebviewActivity.class).
                                putExtra(Constant.IntentParams.INTENT_PARAM, "https://developer.android.google.cn/guide/components/activities/tasks-and-back-stack"));
                esv_content.addText("进程和应用生命周期", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addBodyWithIntent("了解进程和应用生命周期", R.color.colorMain,
                        new Intent(context, TechnologyWebviewActivity.class).
                                putExtra(Constant.IntentParams.INTENT_PARAM, "https://developer.android.google.cn/guide/components/activities/process-lifecycle"));
                esv_content.addTitle_2("将用户转到其他应用");
                esv_content.addBodyWithIntent("使用隐式Intent启动activity", R.color.colorMain,
                        new Intent(context, TechnologyWebviewActivity.class).
                                putExtra(Constant.IntentParams.INTENT_PARAM, "https://developer.android.google.cn/training/basics/intents/sending"));
                esv_content.addTitle_2("多窗口支持");
                esv_content.addBodyWithIntent("针对多窗口模式配置应用", R.color.colorMain,
                        new Intent(context, TechnologyWebviewActivity.class).
                                putExtra(Constant.IntentParams.INTENT_PARAM, "https://developer.android.google.cn/guide/topics/ui/multi-window"));


            }
        });
    }
}
