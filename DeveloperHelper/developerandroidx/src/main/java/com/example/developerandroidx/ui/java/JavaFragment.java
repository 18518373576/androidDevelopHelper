package com.example.developerandroidx.ui.java;

import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseFragment;

import butterknife.BindView;

public class JavaFragment extends BaseFragment {

    @BindView(R.id.text_dashboard)
    TextView text_dashboard;

    private JavaViewModel viewModel;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_java;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        viewModel = ViewModelProviders.of(this).get(JavaViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), s -> text_dashboard.setText(s));
    }
}
