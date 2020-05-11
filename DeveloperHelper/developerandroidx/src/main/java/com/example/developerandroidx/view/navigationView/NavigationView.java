package com.example.developerandroidx.view.navigationView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.developerandroidx.view.navigationView.adapter.NavigationFragmentAdapter;
import com.example.developerandroidx.view.navigationView.bean.NavigationBean;
import com.example.developerandroidx.view.navigationView.utils.PixelTransformUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2020/5/10 22:49
 * 参考:
 * 描述: 首页导航，以底部按钮导航，以viewpager加载fragment，最多添加5个
 */
public class NavigationView extends LinearLayout implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private Context context;
    private LayoutParams paramsPager;
    private ViewPager pager;
    private LinearLayout navigation;
    private LayoutParams paramsNavigation;
    private List<NavigationBean> navigationList;
    private int checkedColorId, unCheckedColorId;
    private List<NavigationItem> items;
    private FragmentManager fragmentManager;
    private NavigationFragmentAdapter adapter;
    private OnNavigationChangedListener changListener;

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
    @SuppressLint("ResourceType")
    private void initView() {

        //设置linearLayout为竖向排列
        setOrientation(VERTICAL);
        pager = new ViewPager(context);
        //必须添加一个ID，否则报错： ViewPager with adapter com.example.developerandroidx.view.navigationView.adapter.NavigationFragmentAdapter@8c3e8eb requires a view id
        pager.setId(1000);
        pager.setOverScrollMode(OVER_SCROLL_NEVER);
        pager.setOffscreenPageLimit(5);

        paramsPager = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        paramsPager.weight = 1000;

        pager.setLayoutParams(paramsPager);
        addView(pager);

        navigation = new LinearLayout(context);
        paramsNavigation = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, PixelTransformUtil.dip2px(context, 50));
        navigation.setLayoutParams(paramsNavigation);
        addView(navigation);
    }

    /**
     * 设置导航栏和页面数据，页面数量必须在2-5之间
     *
     * @param navigationList
     */
    public void setNavigationPager(FragmentManager fragmentManager, List<NavigationBean> navigationList) {
        if (navigationList == null || navigationList.size() > 5 || navigationList.size() < 2) {
            return;
        }
        this.navigationList = navigationList;
        this.fragmentManager = fragmentManager;
        setNavigation();
    }

    /**
     * 设置底部导航栏选中颜色
     *
     * @param checkedColorId
     * @param unCheckedColorId
     */
    public void setNavitionSelector(int checkedColorId, int unCheckedColorId) {
        this.checkedColorId = checkedColorId;
        this.unCheckedColorId = unCheckedColorId;
        setChecked(0);//默认选中左边第一个
    }

    /**
     * 填充navigation数据
     */
    private void setNavigation() {
        //设置viewPager
        adapter = new NavigationFragmentAdapter(fragmentManager, navigationList);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
        pager.addOnPageChangeListener(this);
        //设置底栏
        items = new ArrayList<>();
        for (int i = 0; i < navigationList.size(); i++) {
            NavigationItem item = new NavigationItem(context, navigationList.get(i));
            LayoutParams paramsNavigationItem = new LayoutParams(PixelTransformUtil.dip2px(context, 100), PixelTransformUtil.dip2px(context, 50));
            paramsNavigationItem.weight = 1;
            item.setLayoutParams(paramsNavigationItem);
            item.setId(i);
            item.setOnClickListener(this);
            items.add(item);
            navigation.addView(item, i);
        }
    }

    /**
     * 释放资源
     */
    public void release() {
        pager.removeOnPageChangeListener(this);
        context = null;
        paramsPager = null;
        pager = null;
        navigation = null;
        paramsNavigation = null;
        navigationList = null;
        items = null;
        fragmentManager = null;
        adapter = null;
        changListener = null;
    }

    public interface OnNavigationChangedListener {
        public void OnNavigationChanged(int position);
    }

    public void setOnNavigationChangListener(OnNavigationChangedListener changListener) {
        this.changListener = changListener;
    }

    /**
     * 设置navigation栏背景颜色
     *
     * @param colorId
     */
    public void setNavigationBG(int colorId) {
        navigation.setBackgroundResource(colorId);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {

        pager.setCurrentItem(view.getId());
        setChecked(view.getId());
    }

    private void setChecked(int indx) {

        for (int i = 0; i < items.size(); i++) {
            if (i == indx) {
                items.get(i).setCheckColor(checkedColorId);
            } else {
                items.get(i).setCheckColor(unCheckedColorId);
            }
        }
    }

    //viewpager滑动监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        changListener.OnNavigationChanged(position);
        setChecked(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
