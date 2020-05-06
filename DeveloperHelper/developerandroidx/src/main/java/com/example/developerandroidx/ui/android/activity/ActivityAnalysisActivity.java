package com.example.developerandroidx.ui.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.RouteUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivityAnalysisActivity extends BaseActivity {

    @BindView(R.id.tv_print)
    TextView tv_print;

    private String TAG = "当前Activity";

    @Override
    protected int bindLayout() {
        return R.layout.activity_analysis_activity;
    }

    @Override
    protected void initView() {
        super.initView();
        actionBar.setTitle("Activity");
    }

    @OnClick({R.id.btn_lifecyle, R.id.btn_start_up_mode, R.id.btn_orientation_change})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_lifecyle:
                RouteUtil.goTo(context, RouteUtil.getDestination(DialogTestctivity.class));
                break;
            case R.id.btn_start_up_mode:

                break;
            case R.id.btn_orientation_change:

                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_print.append(TAG + "：onCreate()\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        tv_print.append(TAG + "：onStart()\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_print.append(TAG + "：onResume()\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        tv_print.append(TAG + "：onPause()\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        tv_print.append(TAG + "：onStop()\n");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        tv_print.append(TAG + "：onRestart()\n");
    }
}
