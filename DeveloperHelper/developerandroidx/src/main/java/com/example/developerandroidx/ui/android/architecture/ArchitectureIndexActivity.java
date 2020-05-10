package com.example.developerandroidx.ui.android.architecture;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

public class ArchitectureIndexActivity extends BaseActivity {


    @Override
    protected int bindLayout() {
        return R.layout.activity_architecture_index;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("架构");
    }
}
