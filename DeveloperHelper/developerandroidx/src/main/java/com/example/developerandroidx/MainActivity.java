package com.example.developerandroidx;

import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.android.AndroidFragment;
import com.example.developerandroidx.ui.java.JavaFragment;
import com.example.developerandroidx.ui.widget.WidgetFragment;
import com.example.developerandroidx.view.navigationView.NavigationView;
import com.example.developerandroidx.view.navigationView.bean.NavigationBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 关于对lifecircle的理解
 * 参考：https://www.jianshu.com/p/b1208012b268
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationChangedListener {

    @BindView(R.id.nv_view)
    NavigationView nv_view;

    private ArrayList<NavigationBean> list;

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
        list = new ArrayList<>();
        list.add(new NavigationBean(new AndroidFragment(), "Android", R.mipmap.navigation_android));
        list.add(new NavigationBean(new JavaFragment(), "Java", R.mipmap.navigation_java));
        list.add(new NavigationBean(new WidgetFragment(), "Widget", R.mipmap.navigation_widget));

        nv_view.setNavigationBG(R.color.bg_interface);
        nv_view.setNavigationPager(getSupportFragmentManager(), list);
        nv_view.setNavitionSelector(R.color.colorMain, R.color.textColor);
        nv_view.setOnNavigationChangListener(this);

        setTitle(list.get(0).navigationName);
        iv_back.setClickable(false);
        iv_back.setImageResource(list.get(0).navigationMipmapId);
    }

    @Override
    public void onDestroy() {
        nv_view.release();
        super.onDestroy();
    }

    @Override
    public void OnNavigationChanged(int position) {
        setTitle(list.get(position).navigationName);
        iv_back.setImageResource(list.get(position).navigationMipmapId);
    }
}
