package com.example.developerandroidx.ui.android.dialog;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.RouteUtil;

import butterknife.OnClick;

public class DialogIndexActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_dialog_index;
    }

    @Override
    protected void initView() {
        super.initView();
        actionBar.setTitle("Dialog");
    }

    @OnClick({R.id.btn_kongzue})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_kongzue:
                RouteUtil.goTo(context, RouteUtil.getDestination(KongZueDialogActivity.class));
                break;
        }
    }
}
