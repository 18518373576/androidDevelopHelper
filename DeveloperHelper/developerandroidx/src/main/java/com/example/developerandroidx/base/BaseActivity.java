package com.example.developerandroidx.base;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.developerandroidx.R;
import com.kongzue.dialog.v3.Notification;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 所有activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context context;
    private Unbinder unbinder;

    @BindView(R.id.tv_title)
    protected TextView tv_title;
    @BindView(R.id.iv_back)
    protected ImageView iv_back;
    @BindView(R.id.iv_right)
    protected ImageView iv_right;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setAndroidNativeLightStatusBar(true);
        setContentView(bindLayout());

        unbinder = ButterKnife.bind(this);


        initView();

        initData();
    }

    /**
     * 设置顶栏文字为浅色
     */
    protected void setTopBarTextLight() {
        //设置返回按钮的颜色
        iv_back.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white)));
        //设置顶部信号栏字体颜色
        setAndroidNativeLightStatusBar(false);
        //设置title文字颜色
        tv_title.setTextColor(getResources().getColor(R.color.white));
    }

    /**
     * 设置标题
     *
     * @param title
     */
    protected void setTitle(String title) {
        tv_title.setText(title);
    }

    @OnClick({R.id.iv_back})
    public void onTitleButtonClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 设置顶栏文字颜色
     *
     * @param dark
     */
    protected void setAndroidNativeLightStatusBar(boolean dark) {
        View decor = this.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN设置全屏，顶栏展示图片的时候会用到
            //decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
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

    /**
     * 释放资源
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        unbinder = null;
    }
}
