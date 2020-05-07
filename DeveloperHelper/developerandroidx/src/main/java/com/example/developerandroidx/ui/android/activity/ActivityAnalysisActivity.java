package com.example.developerandroidx.ui.android.activity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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
import com.example.developerandroidx.utils.RouteUtil;
import com.kongzue.dialog.v3.MessageDialog;

import butterknife.BindView;
import butterknife.OnClick;

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
    @OnClick({R.id.btn_lifecyle, R.id.btn_start_up_mode, R.id.btn_orientation_change})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_lifecyle://生命周期
                RouteUtil.goTo(context, RouteUtil.getDestination(DialogTestctivity.class));
                break;
            case R.id.btn_start_up_mode://启动模式
                MessageDialog.show((AppCompatActivity) context, "提示", "这是一条消息", "确定");
                break;
            case R.id.btn_orientation_change://横竖屏切换

                viewModel.onLifecyleChanged("-----屏幕切换方向了-----\n");
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
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
}
