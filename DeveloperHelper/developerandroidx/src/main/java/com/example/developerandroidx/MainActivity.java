package com.example.developerandroidx;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.developerandroidx.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    /**
     * 绑定layout
     *
     * @return
     */
    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    /**
     * view的一些操作
     */
    @Override
    protected void initView() {

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_android, R.id.navigation_java, R.id.navigation_widget)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}
