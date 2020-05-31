package com.example.developerandroidx.ui.android.fragment;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

import butterknife.OnClick;

public class FragmentActivity extends BaseActivity {

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

    @OnClick({R.id.iv_right})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.iv_right:
                new FragmentDescDialog().show(context);
                break;
        }
    }
}
