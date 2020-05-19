package com.example.developerandroidx.ui.java.arithmetic;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

import butterknife.OnClick;

public class ArithmeticActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_arithmetic;
    }

    @Override
    protected void initView() {
        setTitle("算法");
    }

    @OnClick({R.id.btn_bubble_sort})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_bubble_sort://冒泡排序
                //测试没有执行setTag调用getTag是否为空
//                if (v.getTag() == null) {
//                    showNotify("kong");
//                    return;
//                }
                new BubbleSortDialog().show(context);
                break;
        }
    }
}
