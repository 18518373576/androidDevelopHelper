package com.example.developerandroidx.ui;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.android.AndroidFragment;
import com.example.developerandroidx.ui.java.JavaFragment;
import com.example.developerandroidx.ui.widget.WidgetFragment;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.RouteUtil;
import com.example.developerandroidx.view.navigationView.NavigationView;
import com.example.developerandroidx.view.navigationView.bean.NavigationBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

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
        nv_view.showNotify(2, 10);

        //设置title
        setTitle(list.get(0).navigationName);
        iv_back.setVisibility(View.GONE);
        iv_right.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.iv_right})
    public void click(View v) {
        String[] mainMenus = new String[]{"About", "Issues"};
        DialogUtils.getInstance().showBottomMenu(context, mainMenus, new DialogUtils.OnItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                switch (text) {
                    case "About":
                        RouteUtil.goTo(context, RouteUtil.getDestination(AboutActivity.class));
                        break;
                    case "Issues":
                        RouteUtil.goToCodeViewActivity(context, CodeVariate.getInstance().getIssueNotes());
                        break;
                }
            }
        });
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
