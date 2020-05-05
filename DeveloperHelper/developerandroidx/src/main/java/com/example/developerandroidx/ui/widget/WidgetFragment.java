package com.example.developerandroidx.ui.widget;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.FunctionRcvAdapter;
import com.example.developerandroidx.base.BaseFragment;
import com.example.developerandroidx.base.BaseRcvAdapter;

import butterknife.BindView;

public class WidgetFragment extends BaseFragment {

    @BindView(R.id.rcv_widget)
    RecyclerView rcv_widget;

    private WidgetViewModel viewModel;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_widget;
    }

    @Override
    protected void initView() {
        rcv_widget.setLayoutManager(new GridLayoutManager(context, 2));
    }

    @Override
    protected void initData() {
        viewModel = ViewModelProviders.of(this).get(WidgetViewModel.class);
        viewModel.getAdapterList().observe(getViewLifecycleOwner(), functionList ->
                rcv_widget.setAdapter(new FunctionRcvAdapter(functionList)));
    }

    @Override
    public void onDestroy() {
        BaseRcvAdapter.releaseAllHolder(rcv_widget);
        super.onDestroy();
    }
}
