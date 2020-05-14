package com.example.developerandroidx.ui.widget.navigationView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.android.AndroidFragment;
import com.example.developerandroidx.ui.java.JavaFragment;
import com.example.developerandroidx.ui.widget.WidgetFragment;
import com.example.developerandroidx.view.navigationView.NavigationView;
import com.example.developerandroidx.view.navigationView.bean.NavigationBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NavigationViewActivity extends BaseActivity {

    @BindView(R.id.nv_view)
    NavigationView nv_view;

    @Override
    protected int bindLayout() {
        return R.layout.activity_navigation_view;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("自定义NavigationView");

        nv_view.setNavigationBG(R.color.bg_interface);
        List<NavigationBean> list = new ArrayList<>();
        list.add(new NavigationBean(new AndroidFragment(), "Android", R.mipmap.navigation_android));
        list.add(new NavigationBean(new JavaFragment(), "Java", R.mipmap.navigation_java));
        list.add(new NavigationBean(new WidgetFragment(), "Widget", R.mipmap.navigation_widget));
        nv_view.setNavigationPager(getSupportFragmentManager(), list);
        nv_view.setNavitionSelector(R.color.colorMain, R.color.textColor);
        nv_view.showNotify(0);
        nv_view.showNotify(1, 101);
    }

    @Override
    public void onDestroy() {
        nv_view.release();
        super.onDestroy();
    }
}
