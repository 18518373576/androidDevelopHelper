package com.example.developerandroidx.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
     * 弹出吐司
     *
     * @param showMsg
     */
    protected void showToast(String showMsg) {
        Toast.makeText(context, showMsg, Toast.LENGTH_LONG).show();
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
