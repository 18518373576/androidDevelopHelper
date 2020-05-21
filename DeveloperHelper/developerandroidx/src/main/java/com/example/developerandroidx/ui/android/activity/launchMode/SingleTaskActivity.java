package com.example.developerandroidx.ui.android.activity.launchMode;

import android.content.Intent;
import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.RouteUtil;

import butterknife.OnClick;

public class SingleTaskActivity extends BaseActivity {

    private String param;


    @Override
    protected int bindLayout() {
        return R.layout.activity_launch_model;
    }

    @Override
    protected void initView() {
        super.initView();
        param = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);
        setTitle(param);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        App.showNotify("还是我", "onNewIntent()被调用：" +
                intent.getStringExtra(Constant.IntentParams.INTENT_PARAM));
    }

    @OnClick({R.id.btn_jump})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_jump:
                switch (param) {
                    case "singleTask_activity_1":
                        RouteUtil.goTo(this, RouteUtil.getDestination(StandardActivity.class), "form_singleTask");
                        break;
                }
                break;
        }
    }
}
