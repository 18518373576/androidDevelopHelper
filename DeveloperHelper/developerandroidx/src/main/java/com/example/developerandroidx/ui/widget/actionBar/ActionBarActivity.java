package com.example.developerandroidx.ui.widget.actionBar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.RouteUtil;

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

    @OnClick({R.id.btn_change_title, R.id.btn_set_subhead_title, R.id.btn_navigation,
            R.id.btn_set_tab, R.id.btn_show_custom_view, R.id.btn_hide_show, R.id.iv_codes})
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
                //设置actionBar背景
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.textColor));
                //设置左侧按钮图标
                actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
                break;
            case R.id.btn_set_tab:
                actionBar.removeAllTabs();
                //@Deprecated 添加tab选项
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
                actionBar.addTab(actionBar.newTab().setText("TAB_1").setTabListener(new OnTabListeer()));
                actionBar.addTab(actionBar.newTab().setText("TAB_2").setTabListener(new OnTabListeer()));
                actionBar.addTab(actionBar.newTab().setText("TAB_3").setTabListener(new OnTabListeer()));
                actionBar.addTab(actionBar.newTab().setText("TAB_4").setTabListener(new OnTabListeer()));
                actionBar.addTab(actionBar.newTab().setText("TAB_5").setTabListener(new OnTabListeer()));
                actionBar.addTab(actionBar.newTab().setText("TAB_6").setTabListener(new OnTabListeer()));
                break;
            case R.id.btn_show_custom_view:
                //设置自定义ActionBar
                //只有这样而且没有设置菜单选项设置才能让width填充屏幕
                actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                View view_custom_action_bar = LayoutInflater.from(this).inflate(R.layout.view_custom_action_bar, null);
                ActionBar.LayoutParams layoutParams =
                        new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                actionBar.setCustomView(view_custom_action_bar, layoutParams);
                Toolbar parent = (Toolbar) view_custom_action_bar.getParent();
                parent.setContentInsetsAbsolute(0, 0);
                break;
            case R.id.btn_hide_show:
                if (actionBar.isShowing()) {
                    actionBar.hide();
                } else {
                    actionBar.show();
                }
                break;
            case R.id.iv_codes:
                RouteUtil.goToCodeViewActivity(context, CodeVariate.getInstance().getCode_1());
                break;
        }
    }

    /**
     * tab监听
     */
    private class OnTabListeer implements ActionBar.TabListener {

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

            App.showNotify(tab.getText().toString());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

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
                App.showNotify(getResources().getString(R.string.android));
                break;
            case R.id.navigation_java:
                App.showNotify(getResources().getString(R.string.java));
                break;
            case R.id.navigation_widget:
                App.showNotify(getResources().getString(R.string.widget));
                break;
        }
        return true;
    }

    @Override
    protected void initView() {
        super.initView();
        actionBar = getSupportActionBar();
        actionBar.setTitle("actionBar");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }
}
