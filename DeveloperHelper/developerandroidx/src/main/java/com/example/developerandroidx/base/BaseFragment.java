package com.example.developerandroidx.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(bindLayout(), container, false);
        context = getActivity();
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    /**
     * 获取ViewModel
     *
     * @param owner      ViewModel库拥有者，可是是fragment或者activity
     * @param modelClass 自己定义的viewModel类
     * @return
     */
    public ViewModel getViewModel(ViewModelStoreOwner owner, Class modelClass) {
        return new ViewModelProvider(owner).get(modelClass);
    }

    /**
     * 绑定layout
     *
     * @return layout文件id
     */
    protected abstract int bindLayout();

    /**
     * view的一些操作
     */
    protected abstract void initView();

    /**
     * 数据回调的一些操作
     */
    protected abstract void initData();

    /**
     * 释放资源
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        unbinder = null;
    }
}
