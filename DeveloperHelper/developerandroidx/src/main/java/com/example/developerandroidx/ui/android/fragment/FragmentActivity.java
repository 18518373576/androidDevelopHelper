package com.example.developerandroidx.ui.android.fragment;

import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.android.fragment.dialog.FragmentDescDialog;
import com.example.developerandroidx.ui.android.fragment.testFragment.TestOneFragment;
import com.example.developerandroidx.ui.android.fragment.testFragment.TestTwoFragment;

import butterknife.OnClick;

public class FragmentActivity extends BaseActivity implements TestTwoFragment.OnBtnClickListener {

    private TestOneFragment oneFragment;
    private TestTwoFragment twoFragment;

    @Override
    protected int bindLayout() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Fragment");
        iv_right.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        oneFragment = new TestOneFragment();
        //控制一个fragment的实例是否保留，在activity重建的时候(例如横竖屏切换)
        oneFragment.setRetainInstance(true);
        //通过接口通信，被依赖的activity实现接口
        twoFragment = new TestTwoFragment(this);
        transaction.add(R.id.fl_fragment_one, oneFragment);
        transaction.add(R.id.fl_fragment_two, twoFragment);

        transaction.commit();
    }

    @OnClick({R.id.iv_right})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.iv_right:
                new FragmentDescDialog().show(context);
                break;
        }
    }

    @Override
    public void onBtnClick(int id) {
        //接收消息的fragment实现public方法，用于通信
        oneFragment.startAnim();
    }
}
