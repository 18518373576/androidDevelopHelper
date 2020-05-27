package com.example.developerandroidx.ui.java.arithmetic;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.java.arithmetic.dialog.BubbleSortDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ArithmeticActivity extends BaseActivity {

    @BindView(R.id.rcv_arithmetic)
    RecyclerView rcv_arithmetic;

    @Override
    protected int bindLayout() {
        return R.layout.activity_arithmetic;
    }

    @Override
    protected void initView() {
        setTitle("算法");
        rcv_arithmetic.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void initData() {
        super.initData();
        ArithmeticViewModel viewModel = ViewModelProviders.of(this).get(ArithmeticViewModel.class);
        viewModel.getData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

            }
        });
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
