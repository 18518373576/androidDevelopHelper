package com.example.developerandroidx.ui.widget.navigationView;

import android.os.Bundle;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.view.navigationView.NavigationView;

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
        nv_view.setNavigationBG(R.color.colorMain);
    }
}
