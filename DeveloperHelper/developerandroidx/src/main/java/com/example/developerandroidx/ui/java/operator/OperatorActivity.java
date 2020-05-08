package com.example.developerandroidx.ui.java.operator;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.OperatorRcvAdapter;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.bean.OperatorItemBean;
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.RouteUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OperatorActivity extends BaseActivity {

    @BindView(R.id.rcv_operator)
    RecyclerView rcv_operator;

    @Override
    protected int bindLayout() {
        return R.layout.activity_operator;
    }

    @OnClick({R.id.iv_codes})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.iv_codes:
                RouteUtil.goToCodeViewActivity(context, CodeVariate.getInstance().getCode_4());
                break;
        }
    }

    @Override
    protected void initView() {

        actionBar.setTitle(R.string.operator);
        rcv_operator.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initData() {

        OperatorViewModel viewModel = ViewModelProviders.of(this).get(OperatorViewModel.class);
//        viewModel.bindLifeCircle(this);
        viewModel.getAdapterList().observe(this, new Observer<List<OperatorItemBean>>() {
            @Override
            public void onChanged(List<OperatorItemBean> operatorItemBeans) {
                rcv_operator.setAdapter(new OperatorRcvAdapter(operatorItemBeans));
            }
        });
    }
}
