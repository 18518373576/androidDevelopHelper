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
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.android.activity.launchMode.SingleInstanceActivity;
import com.example.developerandroidx.ui.android.activity.launchMode.SingleTaskActivity;
import com.example.developerandroidx.ui.android.activity.launchMode.SingleTopActivity;
import com.example.developerandroidx.ui.android.activity.launchMode.StandardActivity;
import com.example.developerandroidx.ui.android.activity.lifeCycle.DialogTestctivity;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.RouteUtil;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.v3.BottomMenu;

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
        actionBar.setTitle("Activity");
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
            R.id.iv_codes, R.id.btn_action_start, R.id.btn_start_for_result, R.id.btn_cut_animation})
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

                break;
            case R.id.btn_cut_animation://切换动画

                break;

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
