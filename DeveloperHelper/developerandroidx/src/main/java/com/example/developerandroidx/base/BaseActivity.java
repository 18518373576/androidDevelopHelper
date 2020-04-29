package com.example.developerandroidx.base;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(bindLayout());

        ButterKnife.bind(this);

        initView();
    }

    /**
     * 弹出吐司
     * @param showMsg
     */
    protected void showToast(String showMsg)
    {
        Toast.makeText(context,showMsg,Toast.LENGTH_LONG).show();
    }

    /**
     * 绑定layout
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
