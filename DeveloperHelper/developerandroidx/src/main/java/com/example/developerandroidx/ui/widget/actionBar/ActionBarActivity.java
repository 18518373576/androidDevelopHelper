package com.example.developerandroidx.ui.widget.actionBar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

import butterknife.OnClick;

/**
 * 参考：https://www.jianshu.com/p/81d0bcb282cb
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
                //设置标题
                actionBar.setTitle("标题");
                break;
            case R.id.btn_set_subhead_title:
                //设置副标题
                actionBar.setSubtitle("副标题");
                break;
            case R.id.btn_navigation:
                //设置导航按钮 可见、可点击
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
//                actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.textColor));//设置actionBar背景
//                actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);//设置左侧按钮图标
                break;
        }
    }

    /**
     * 复写：左侧导航按钮点击事件
     * 注意：如果复写了onOptionsItemSelected方法，则onSupportNavigateUp无用
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    /**
     * 添加菜单监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //左侧导航按钮点击事件
            case android.R.id.home:
                finish();
                break;
            case R.id.navigation_android:
                showToast(getResources().getString(R.string.android));
                break;
            case R.id.navigation_java:
                showToast(getResources().getString(R.string.java));
                break;
            case R.id.navigation_widget:
                showToast(getResources().getString(R.string.widget));
                break;
        }
        return true;
    }

    @Override
    protected void initView() {

        actionBar = getSupportActionBar();
    }

    @Override
    protected void initData() {

    }
}
