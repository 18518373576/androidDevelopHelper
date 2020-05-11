package com.example.developerandroidx.view.navigationView.bean;

import androidx.fragment.app.Fragment;

/**
 * @作者： zjf 2020/5/11 10:55
 * @参考：
 * @描述：
 */
public class NavigationBean {
    public Fragment fragment;
    public String navigationName;
    public int navigationMipmapId;

    public NavigationBean(Fragment fragment, String navigationName, int navigationMipmapId) {
        this.fragment = fragment;
        this.navigationName = navigationName;
        this.navigationMipmapId = navigationMipmapId;
    }
}
