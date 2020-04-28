package com.hello.world.developer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hello.world.developer.android.AndroidIndexFragment;
import com.hello.world.developer.java.JavaIndexFragment;
import com.hello.world.developer.widget.WidgetIndexFragment;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private AndroidIndexFragment androidIndexFragment;
    private JavaIndexFragment javaIndexFragment;
    private WidgetIndexFragment widgetIndexFragment;
    private Fragment[] fragments;

    private int currentIndex;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        androidIndexFragment = new AndroidIndexFragment();
        javaIndexFragment = new JavaIndexFragment();
        widgetIndexFragment = new WidgetIndexFragment();

        actionBar = getSupportActionBar();

        fragments = new Fragment[]{androidIndexFragment, javaIndexFragment, widgetIndexFragment};
        //设置默认展示的界面
        currentIndex = 0;
        switchFragment(0, 0);
        actionBar.setTitle("安卓");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);


        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    /**
     * 底部按钮点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_android:
                if (currentIndex != 0) {
                    switchFragment(currentIndex, 0);
                    currentIndex = 0;
                    actionBar.setTitle("安卓");
                }
                return true;
            case R.id.navigation_java:
                if (currentIndex != 1) {
                    switchFragment(currentIndex, 1);
                    currentIndex = 1;
                    actionBar.setTitle("java");
                }
                return true;
            case R.id.navigation_widget:
                if (currentIndex != 2) {
                    switchFragment(currentIndex, 2);
                    currentIndex = 2;
                    actionBar.setTitle("控件");
                }
                return true;
        }
        return false;
    }

    /**
     * 选择fragment
     *
     * @param lastIndex
     * @param currentClick
     */
    private void switchFragment(int lastIndex, int currentClick) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.hide(fragments[lastIndex]);
        if (!fragments[currentClick].isAdded()) {
            transaction.add(R.id.fl_content, fragments[currentClick]);
        }

        transaction.show(fragments[currentClick]).commitAllowingStateLoss();
    }
}
