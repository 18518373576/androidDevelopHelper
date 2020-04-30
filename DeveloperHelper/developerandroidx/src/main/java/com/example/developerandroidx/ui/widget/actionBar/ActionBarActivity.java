package com.example.developerandroidx.ui.widget.actionBar;

import android.view.View;

import androidx.appcompat.app.ActionBar;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

import butterknife.OnClick;

/**
 * @参考： https://www.jianshu.com/p/81d0bcb282cb
 */
public class ActionBarActivity extends BaseActivity {

    private ActionBar actionBar;

    @Override
    protected int bindLayout() {
        return R.layout.activity_action_bar;
    }

    @OnClick({R.id.btn_change_title, R.id.btn_set_subhead_title, R.id.btn_navigation})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_change_title:
                actionBar.setTitle("标题");
                break;
            case R.id.btn_set_subhead_title:
                actionBar.setSubtitle("副标题");
                break;
            case R.id.btn_navigation:
                showToast("左侧按钮：可见+可用");
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
//                actionBar.setHomeAsUpIndicator();
                break;
        }
    }


    @Override
    protected void initView() {

        actionBar = getSupportActionBar();
    }

    @Override
    protected void initData() {

    }
}
