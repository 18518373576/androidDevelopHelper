package com.example.developerandroidx.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

    //权限请求code
    private static final int REQUEST_CODE = 100;
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

    @Override
    protected void initData() {
        super.initData();
        getPermission();
    }

    /**
     * 获取用户权限
     */
    private void getPermission() {
        String permissionStr = "";
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionStr += Manifest.permission.CAMERA + "##";
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionStr += Manifest.permission.WRITE_EXTERNAL_STORAGE + "##";
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionStr += Manifest.permission.READ_EXTERNAL_STORAGE;
        }
        String[] permissions = permissionStr.split("##");
        if (permissions.length != 0) {
            //如果用户拒绝了权限,shouldShowRequestPermissionRationale返回true
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission_group.CAMERA)) {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
            } else {//如果用户拒绝了权限，并勾选了不再询问
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
            }
        }
    }

    /**
     * 请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //如果用户拒绝了一些必须的权限可以在这里给予提示
        switch (requestCode) {
            case REQUEST_CODE:

                break;
        }

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
