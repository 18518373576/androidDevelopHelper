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
 * 使用方法:
 * nv_view.setNavigationBG(R.color.bg_interface);设置底栏背景色
 * List<NavigationBean> list = new ArrayList<>();
 * nv_view.setNavigationPager(getSupportFragmentManager(), list);添加导航页面
 * nv_view.setNavitionSelector(R.color.colorMain, R.color.textColor);设置底部按钮选中和未选中颜色
 * nv_view.showNotify(int index);显示通知红点
 * nv_view.showNotify(int index, int notifyNum);显示通知并显示通知数量
 * 退出时释放资源
 * nv_view.release();
 * 描述: 首页导航，以底部按钮导航，以viewpager加载fragment，最多添加5个 {@link NavigationItem}
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

    private int currentIndex = 0;

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
     * 显示通知，只显示一个圆点，不显示数量
     *
     * @param index 通知所在底栏图标的索引
     */
    public void showNotify(int index) {

        ((NavigationItem) navigation.getChildAt(index)).showNotify();
    }

    /**
     * 显示通知，和通知条数
     *
     * @param index
     * @param notifyNum
     */
    public void showNotify(int index, int notifyNum) {
        ((NavigationItem) navigation.getChildAt(index)).showNotify(notifyNum);
    }

    /**
     * 设置底部导航栏选中和未选中的颜色
     * 原理是使用了imageview的tint属性值，所以尽量使用纯色图标
     *
     * @param checkedColorId
     * @param unCheckedColorId
     */
    public void setNavitionSelector(int checkedColorId, int unCheckedColorId) {
        this.checkedColorId = checkedColorId;
        this.unCheckedColorId = unCheckedColorId;
        setCheckedDefult(currentIndex);//默认选中左边第一个
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
        void OnNavigationChanged(int position);
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
        //viewPager已经添加监听，这里不再调用setChecked方法
        //setChecked(view.getId());
    }

    /**
     * 默认选中的不加动画
     *
     * @param index
     */
    private void setCheckedDefult(int index) {
        for (int i = 0; i < items.size(); i++) {
            if (i == index) {
                items.get(i).setCheckedDefult(true, checkedColorId, unCheckedColorId);
            } else {
                items.get(i).setCheckedDefult(false, unCheckedColorId, unCheckedColorId);
            }
        }
    }

    /**
     * 点击选中的加动画
     *
     * @param index
     */
    private void setChecked(int index) {

        items.get(currentIndex).setChecked(false, checkedColorId, unCheckedColorId);
        items.get(index).setChecked(true, checkedColorId, unCheckedColorId);
        currentIndex = index;
    }

    //viewpager滑动监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        if (changListener != null) {
            changListener.OnNavigationChanged(position);
        }
        setChecked(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
