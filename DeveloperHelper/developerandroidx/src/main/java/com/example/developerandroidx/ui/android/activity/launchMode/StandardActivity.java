package com.example.developerandroidx.ui.android.activity.launchMode;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.RouteUtil;

import butterknife.OnClick;

public class StandardActivity extends BaseActivity {

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

    @OnClick({R.id.btn_jump})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_jump:
                switch (param) {
                    case "standard_activity_1":
                        RouteUtil.goTo(this, RouteUtil.getDestination(StandardActivity.class), "standard_activity_2");
                        break;
                    case "form_singleTask":
                        RouteUtil.goTo(this, RouteUtil.getDestination(SingleTaskActivity.class), "from_standard");
                        break;
                    case "form_singleInstance":
                        RouteUtil.goTo(this, RouteUtil.getDestination(SingleInstanceActivity.class), "from_standard");
                        break;
                }
                break;
        }
    }
}
