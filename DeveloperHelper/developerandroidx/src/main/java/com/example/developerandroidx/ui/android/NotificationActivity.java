package com.example.developerandroidx.ui.android;

import android.os.Bundle;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

public class NotificationActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("通知中心");
    }
}
