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

    /**
     * actionBar左侧按钮点击
     *
     * @return
     */
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
     * 绑定layout
     *
     * @return layout文件id
     */
    protected abstract int bindLayout();

    /**
     * view的一些操作,有些activity可能用不到,不作抽象处理,根据需要实现
     */
    protected void initView() {
    }

    /**
     * 处理回调数据的操作,有些activity可能用不到,不作抽象处理,根据需要实现
     */
    protected void initData() {
    }
}
