package com.example.developerandroidx.ui.android.broadcastReceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.widget.webView.TechnologyWebviewActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;

import butterknife.BindView;
import butterknife.OnClick;

public class BroadcastReceiverActivity extends BaseActivity {

    @BindView(R.id.esv_content)
    ExtensibleScrollView esv_content;

    private AppBroadcastReceiver receiver;

    @Override
    protected int bindLayout() {
        return R.layout.activity_broadcast_receiver;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Broadcast Receiver");
        iv_right.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        registerBroadcastReceiver();
        adddesc();
    }

    /**
     * 注册广播接收者
     */
    private void registerBroadcastReceiver() {
        receiver = new AppBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Constant.BroadcastAction.TEST);
        registerReceiver(receiver, intentFilter);

        receiver.setOnReceivedListener(new AppBroadcastReceiver.OnReceivedListener() {
            @Override
            public void onReceived(Intent intent) {
                switch (intent.getAction()) {
                    case Constant.BroadcastAction.TEST:
                        DialogUtils.getInstance().showMessageDialog(context, "提示", intent.getStringExtra("sendBroadcast"));
                        break;
                }
            }
        });
    }

    /**
     * 添加描述信息
     */
    private void adddesc() {
        esv_content.addText("广播概览", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
        esv_content.addText("Android 应用与 Android 系统和其他 Android 应用之间可以相互收发广播消息，这与发布-订阅设计模式相似。这些广播会在所关注的事件发生时发送。举例来说，Android 系统会在发生各种系统事件时发送广播，例如系统启动或设备开始充电时。再比如，应用可以发送自定义广播来通知其他应用它们可能感兴趣的事件（例如，一些新数据已下载）。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("应用可以注册接收特定的广播。广播发出后，系统会自动将广播传送给同意接收这种广播的应用。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("一般来说，广播可作为跨应用和普通用户流之外的消息传递系统。但是，您必须小心，不要滥用在后台响应广播和运行作业的机会，因为这会导致系统变慢。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("系统广播", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
        esv_content.addText("系统会在发生各种系统事件时自动发送广播，例如当系统进入和退出飞行模式时。系统广播会被发送给所有同意接收相关事件的应用。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("广播消息本身会被封装在一个 Intent 对象中，该对象的操作字符串会标识所发生的事件（例如 android.intent.action.AIRPLANE_MODE）。该 Intent 可能还包含绑定到其 extra 字段中的附加信息。例如，飞行模式 intent 包含布尔值 extra 来指示是否已开启飞行模式。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("有关系统广播操作的完整列表，请参阅 Android SDK 中的 BROADCAST_ACTIONS.TXT 文件。每个广播操作都有一个与之关联的常量字段。例如，常量 ACTION_AIRPLANE_MODE_CHANGED 的值为 android.intent.action.AIRPLANE_MODE。每个广播操作的文档都可以在关联的常量字段中找到。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("系统广播所发生的更改", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
        esv_content.addText("随着 Android 平台的发展，它会不定期地更改系统广播的行为方式。如果您的应用以 Android 7.0（API 级别 24）或更高版本为目标平台，或者安装在搭载 Android 7.0 或更高版本的设备上，请注意以下更改。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("从 Android 9（API 级别 28）开始，NETWORK_STATE_CHANGED_ACTION 广播不再接收有关用户位置或个人身份数据的信息。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("此外，如果您的应用安装在搭载 Android 9 或更高版本的设备上，则通过 WLAN 接收的系统广播不包含 SSID、BSSID、连接信息或扫描结果。要获取这些信息，请调用 getConnectionInfo()。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("从 Android 8.0（API 级别 26）开始，系统对清单声明的接收器施加了额外的限制。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("如果您的应用以 Android 8.0 或更高版本为目标平台，那么对于大多数隐式广播（没有明确针对您的应用的广播），您不能使用清单来声明接收器。当用户正在活跃地使用您的应用时，您仍可使用上下文注册的接收器。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("Android 7.0（API 级别 24）及更高版本不发送以下系统广播：", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("1.ACTION_NEW_PICTURE", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("2.ACTION_NEW_VIDEO", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("此外，以 Android 7.0 及更高版本为目标平台的应用必须使用 registerReceiver(BroadcastReceiver, IntentFilter) 注册 CONNECTIVITY_ACTION 广播。无法在清单中声明接收器。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("接收广播", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
        esv_content.addText("1.清单声明的接收器", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("系统软件包管理器会在应用安装时注册接收器。然后，该接收器会成为应用的一个独立入口点，这意味着如果应用当前未运行，系统可以启动应用并发送广播。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("系统会创建新的 BroadcastReceiver 组件对象来处理它接收到的每个广播。此对象仅在调用 onReceive(Context, Intent) 期间有效。一旦从此方法返回代码，系统便会认为该组件不再活跃。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("2.上下文注册的接收器", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("只要注册上下文有效，上下文注册的接收器就会接收广播。例如，如果您在 Activity 上下文中注册，只要 Activity 没有被销毁，您就会收到广播。如果您在应用上下文中注册，只要应用在运行，您就会收到广播。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("请注意注册和注销接收器的位置，比方说，如果您使用 Activity 上下文在 onCreate(Bundle) 中注册接收器，则应在 onDestroy() 中注销，以防接收器从 Activity 上下文中泄露出去。如果您在 onResume() 中注册接收器，则应在 onPause() 中注销，以防多次注册接收器（如果您不想在暂停时接收广播，这样可以减少不必要的系统开销）。请勿在 onSaveInstanceState(Bundle) 中注销，因为如果用户在历史记录堆栈中后退，则不会调用此方法。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("对进程状态的影响", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
        esv_content.addText("BroadcastReceiver 的状态（无论它是否在运行）会影响其所在进程的状态，而其所在进程的状态又会影响它被系统终结的可能性。例如，当进程执行接收器（即当前在运行其 onReceive() 方法中的代码）时，它被认为是前台进程。除非遇到极大的内存压力，否则系统会保持该进程运行。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("但是，一旦从 onReceive() 返回代码，BroadcastReceiver 就不再活跃。接收器的宿主进程变得与在其中运行的其他应用组件一样重要。如果该进程仅托管清单声明的接收器（这对于用户从未与之互动或最近没有与之互动的应用很常见），则从 onReceive() 返回时，系统会将其进程视为低优先级进程，并可能会将其终止，以便将资源提供给其他更重要的进程使用。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("因此，您不应从广播接收器启动长时间运行的后台线程。onReceive() 完成后，系统可以随时终止进程来回收内存，在此过程中，也会终止进程中运行的派生线程。要避免这种情况，您应该调用 goAsync()（如果您希望在后台线程中多花一点时间来处理广播）或者使用 JobScheduler 从接收器调度 JobService，这样系统就会知道该进程将继续活跃地工作。如需了解详情，请参阅进程和应用生命周期", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("发送广播", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
        esv_content.addText("Android 为应用提供三种方式来发送广播：", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("1.sendOrderedBroadcast(Intent, String) 方法一次向一个接收器发送广播。当接收器逐个顺序执行时，接收器可以向下传递结果，也可以完全中止广播，使其不再传递给其他接收器。接收器的运行顺序可以通过匹配的 intent-filter 的 android:priority 属性来控制；具有相同优先级的接收器将按随机顺序运行。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("2.sendBroadcast(Intent) 方法会按随机的顺序向所有接收器发送广播。这称为常规广播。这种方法效率更高，但也意味着接收器无法从其他接收器读取结果，无法传递从广播中收到的数据，也无法中止广播。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("3.LocalBroadcastManager.sendBroadcast 方法会将广播发送给与发送器位于同一应用中的接收器。如果您不需要跨应用发送广播，请使用本地广播。这种实现方法的效率更高（无需进行进程间通信），而且您无需担心其他应用在收发您的广播时带来的任何安全问题。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("隐式广播例外情况", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
        Intent intent = new Intent(context, TechnologyWebviewActivity.class);
        intent.putExtra(Constant.IntentParams.INTENT_PARAM, "https://developer.android.google.cn/guide/components/broadcast-exceptions");
        esv_content.addBodyWithIntent("了解更多", R.color.colorMain, intent);

    }

    @OnClick({R.id.iv_right})
    public void click(View v) {

        switch (v.getId()) {
            case R.id.iv_right:
                DialogUtils.getInstance().showBottomMenu(context, new String[]{"sendBroadcast"}, new DialogUtils.OnItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
//                        Intent intent = new Intent(context, AppBroadcastReceiver.class);
//                        intent.setAction(Constant.BroadcastAction.TEST);
//                        intent.putExtra("sendBroadcast", "收到一条广播，还附加了一条消息");
//                        sendBroadcast(intent);


                        sendBroadcast(new Intent(Constant.BroadcastAction.TEST).putExtra("sendBroadcast", "收到一条广播，还附加了一条消息"));
                    }
                });
                break;
        }
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
