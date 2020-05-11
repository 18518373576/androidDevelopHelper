package com.example.developerandroidx.view.navigationView.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.developerandroidx.view.navigationView.bean.NavigationBean;

import java.util.List;

/**
 * @作者： zjf 2020/5/11 16:13
 * @参考：
 * @描述： viewPager的加载fragment的adapter
 */
public class NavigationFragmentAdapter extends FragmentPagerAdapter {

    private List<NavigationBean> navigationBeans;

    public NavigationFragmentAdapter(@NonNull FragmentManager fm, List<NavigationBean> navigationBeans) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.navigationBeans = navigationBeans;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return navigationBeans.get(position).fragment;
    }

    @Override
    public int getCount() {
        return navigationBeans.size();
    }
}
