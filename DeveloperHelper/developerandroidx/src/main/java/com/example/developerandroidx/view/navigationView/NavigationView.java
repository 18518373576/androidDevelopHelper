package com.example.developerandroidx.view.navigationView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.developerandroidx.view.navigationView.utils.PixelTransformUtil;

/**
 * Date: 2020/5/10 22:49
 * 参考:
 * 描述: 首页导航，以底部按钮导航，以viewpager加载fragment，最多添加5个
 */
public class NavigationView extends LinearLayout {
    private Context context;
    private LayoutParams paramsPager;
    private ViewPager pager;
    private LinearLayout navigation;
    private LayoutParams paramsNavigation;

    public NavigationView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public NavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public NavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    /**
     * 初始化，添加viewpager和底部导航栏
     */
    private void initView() {

        setOrientation(VERTICAL);
        pager = new ViewPager(context);

        paramsPager = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        paramsPager.weight = 1000;

        pager.setLayoutParams(paramsPager);
        addView(pager);

        navigation = new LinearLayout(context);
        paramsNavigation = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, PixelTransformUtil.dip2px(context, 45));
        navigation.setLayoutParams(paramsNavigation);
        addView(navigation);
    }

    public void setNavigationBG(int colorId) {
        navigation.setBackgroundResource(colorId);
    }
}
