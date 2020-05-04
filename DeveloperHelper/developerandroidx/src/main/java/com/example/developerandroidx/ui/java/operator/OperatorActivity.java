package com.example.developerandroidx.ui.java.operator;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.bean.OperatorItemBean;
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;
import com.example.developerandroidx.utils.RouteUtil;

import java.util.List;

import butterknife.OnClick;

public class OperatorActivity extends BaseActivity {


    @Override
    protected int bindLayout() {
        return R.layout.activity_operator;
    }

    @OnClick({R.id.iv_codes})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.iv_codes:
                RouteUtil.goTo(context, RouteUtil.getDestination(CodeViewActivity.class), "操作符");
                break;
        }
    }

    @Override
    protected void initView() {

        actionBar.setTitle(R.string.operator);
    }

    @Override
    protected void initData() {

        OperatorViewModel viewModel = ViewModelProviders.of(this).get(OperatorViewModel.class);
        viewModel.getAdapterList().observe(this, new Observer<List<OperatorItemBean>>() {
            @Override
            public void onChanged(List<OperatorItemBean> operatorItemBeans) {
                showToast(operatorItemBeans.size() + "");
            }
        });
    }
}
