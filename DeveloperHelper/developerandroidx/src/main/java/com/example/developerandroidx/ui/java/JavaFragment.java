package com.example.developerandroidx.ui.java;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.FunctionRcvAdapter;
import com.example.developerandroidx.base.BaseFragment;

import butterknife.BindView;

public class JavaFragment extends BaseFragment {

    @BindView(R.id.rcv_java)
    RecyclerView rcv_java;

    private JavaViewModel viewModel;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_java;
    }

    @Override
    protected void initView() {
        rcv_java.setLayoutManager(new GridLayoutManager(context, 2));
    }

    @Override
    protected void initData() {
        viewModel = ViewModelProviders.of(this).get(JavaViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), functionList ->
                rcv_java.setAdapter(new FunctionRcvAdapter(context, functionList)));
    }
}
