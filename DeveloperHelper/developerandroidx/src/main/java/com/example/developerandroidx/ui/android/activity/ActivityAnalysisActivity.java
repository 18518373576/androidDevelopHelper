package com.example.developerandroidx.ui.android.activity;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

public class ActivityAnalysisActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_analysis_activity;
    }

    @Override
    protected void initView() {
        super.initView();
        actionBar.setTitle("Activity");
    }
}
