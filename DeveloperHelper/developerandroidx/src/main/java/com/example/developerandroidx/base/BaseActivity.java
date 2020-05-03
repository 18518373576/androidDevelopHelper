package com.example.developerandroidx.base;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;
import com.example.developerandroidx.utils.RouteUtil;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context context;
    protected ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(bindLayout());

        ButterKnife.bind(this);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        initView();

        initData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    /**
     * 弹出吐司
     *
     * @param showMsg
     */
    protected void showToast(String showMsg) {
        Toast.makeText(context, showMsg, Toast.LENGTH_LONG).show();
    }

    /**
     * 跳转到代码展示界面
     *
     * @param code
     */
    protected void goToCodeViewActivity(String code) {
        try {
            RouteUtil.goTo(context, RouteUtil.getDestination(CodeViewActivity.class), null, code);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绑定layout
     *
     * @return layout文件id
     */
    protected abstract int bindLayout();

    /**
     * view的一些操作
     */
    protected abstract void initView();

    /**
     * 处理回调数据的操作
     */
    protected abstract void initData();
}
