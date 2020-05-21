package com.example.developerandroidx.ui.android.activity.activityforResult;

import android.content.Intent;
import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

import butterknife.OnClick;

public class ForResultActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_start_for_result;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("ForResultActivity");
    }

    @OnClick({R.id.btn_finish})
    public void click(View v) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("res", "这是返回的消息");
        switch (v.getId()) {
            case R.id.btn_finish:
                setResult(100, resultIntent);
                finish();
                break;
        }
    }
}
