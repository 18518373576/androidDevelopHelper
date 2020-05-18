package com.example.developerandroidx.ui.android.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.android.activity.activityforResult.ForResultActivity;
import com.example.developerandroidx.ui.android.activity.launchMode.SingleInstanceActivity;
import com.example.developerandroidx.ui.android.activity.launchMode.SingleTaskActivity;
import com.example.developerandroidx.ui.android.activity.launchMode.SingleTopActivity;
import com.example.developerandroidx.ui.android.activity.launchMode.StandardActivity;
import com.example.developerandroidx.ui.android.activity.lifeCycle.DialogTestctivity;
import com.example.developerandroidx.ui.android.activity.transitionAnimation.TransitionAnimationActivity;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.RouteUtil;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.v3.BottomMenu;
import com.kongzue.dialog.v3.FullScreenDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * activity相关
 */
public class ActivityAnalysisActivity extends BaseActivity {

    @BindView(R.id.tv_print)
    TextView tv_print;
    @BindView(R.id.sv_show_data)
    ScrollView sv_show_data;
    @BindView(R.id.iv_codes)
    ImageView iv_codes;

    private String TAG = "当前Activity";
    private ActivityAnalysisViewModel viewModel;

    @Override
    protected int bindLayout() {
        //当屏幕切换的时候，布局文件也进行切换，适应屏幕方向
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            return R.layout.activity_analysis_activity_landscape;
        } else {
            return R.layout.activity_analysis_activity_portrait;
        }
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Activity");
        
        iv_right.setVisibility(View.VISIBLE);
        //获取屏幕方向
        Log.e(TAG, ":" + getRequestedOrientation());
    }

    @Override
    protected void initData() {
        super.initData();
        //因为切换横竖屏，activity会销毁重建，为了保存记录生命周期数据，使用不会随着activity生命周期销毁的LiveData来保存数据
        viewModel = ViewModelProviders.of(this).get(ActivityAnalysisViewModel.class);
        //观察LiveData数据变化，并把变化打印
        viewModel.getLifecycleBuffer().observe(this, new Observer<StringBuffer>() {
            @Override
            public void onChanged(StringBuffer stringBuffer) {
                if (tv_print != null) {
                    tv_print.setText(stringBuffer.toString());
                }
            }
        });
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @OnClick({R.id.btn_lifecyle, R.id.btn_start_up_mode, R.id.btn_orientation_change,
            R.id.iv_codes, R.id.btn_action_start, R.id.btn_start_for_result, R.id.btn_cut_animation,
            R.id.iv_right})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_lifecyle://生命周期
                RouteUtil.goTo(context, RouteUtil.getDestination(DialogTestctivity.class));
                break;
            case R.id.btn_start_up_mode://启动模式
                String[] startUpModels = new String[]{"standard", "single Top", "single Task", "single Instance"};
                BottomMenu.show((AppCompatActivity) context, startUpModels, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        switch (text) {
                            case "standard":
                                RouteUtil.goTo(context, RouteUtil.getDestination(StandardActivity.class), "standard_activity_1");
                                break;
                            case "single Top":
                                RouteUtil.goTo(context, RouteUtil.getDestination(SingleTopActivity.class), "singleTop_activity_1");
                                break;
                            case "single Task":
                                RouteUtil.goTo(context, RouteUtil.getDestination(SingleTaskActivity.class), "singleTask_activity_1");
                                break;
                            case "single Instance":
                                RouteUtil.goTo(context, RouteUtil.getDestination(SingleInstanceActivity.class), "singleInstance_activity_1");
                                break;

                        }
                    }
                });
                break;
            case R.id.btn_orientation_change://横竖屏切换

                viewModel.onLifecyleChanged("-----屏幕切换方向了-----\n");
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                break;
            case R.id.iv_codes:
                RouteUtil.goToCodeViewActivity(context, CodeVariate.getInstance().getCode_5());
                break;
            case R.id.btn_action_start://activity隐式启动
                Intent intent = new Intent("com.example.developerandroidx.ActionIntentActivity");
                intent.setData(Uri.parse("example://example.com:1080/from?message=ActivityAnalysisActivity"));
                startActivity(intent);
                break;
            case R.id.btn_start_for_result://启动activity带返回结果
                startActivityForResult(new Intent(this, ForResultActivity.class), 101);
                break;
            case R.id.btn_cut_animation://切换动画
                String[] transitions = new String[]{"Explode", "Slide", "Fade", "Shared Element"};
                BottomMenu.show((AppCompatActivity) context, transitions, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        RouteUtil.goTo(context, RouteUtil.getDestination(TransitionAnimationActivity.class), text);
                    }
                });
                break;
            case R.id.iv_right:
                DialogUtils.getInstance().shouFullScreenDialog(context, R.layout.dialog_activity_lifecycle, new DialogUtils.OnFullScreenDialogBindView() {
                    @Override
                    public void onBind(FullScreenDialog dialog, View rootView) {
                        ExtensibleScrollView esv_content = rootView.findViewById(R.id.esv_content);
//                        esv_content.addText("",ExtensibleScrollView.InsertTextType.TITLE_2,R.color.textColorBlack);
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
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            App.showNotify("消息回调", "resultCode:" + resultCode + " msg:" + data.getStringExtra("res"));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.onLifecyleChanged(TAG + "：onCreate()\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.onLifecyleChanged(TAG + "：onStart()\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onLifecyleChanged(TAG + "：onResume()\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onLifecyleChanged(TAG + "：onPause()\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.onLifecyleChanged(TAG + "：onStop()\n");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        viewModel.onLifecyleChanged(TAG + "：onRestart()\n");
    }

    @Override
    public void onDestroy() {
        //在onDestroy之前存入数据
        viewModel.onLifecyleChanged(TAG + "：onDestroy()\n");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        viewModel.onLifecyleChanged(TAG + "：onSaveInstanceState()\n" +
                "saved：saveData\n");
        outState.putString("save", "saveData");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        viewModel.onLifecyleChanged(TAG + "：onRestoreInstanceState()\n" +
                "getData：" + savedInstanceState.getString("save") + "\n");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
