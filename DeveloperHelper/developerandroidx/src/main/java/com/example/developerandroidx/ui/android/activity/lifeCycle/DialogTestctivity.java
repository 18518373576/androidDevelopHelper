package com.example.developerandroidx.ui.android.activity.lifeCycle;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

public class DialogTestctivity extends BaseActivity {
    @Override
    protected int bindLayout() {
        return R.layout.activity_dialog_testctivity;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("DialogActivity");
    }
}
