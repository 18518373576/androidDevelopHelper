package com.example.developerandroidx.ui.widget;

import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseFragment;

import butterknife.BindView;

public class WidgetFragment extends BaseFragment {

    @BindView(R.id.text_notifications)
    TextView text_notifications;

    private WidgetViewModel viewModel;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_widget;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        viewModel = ViewModelProviders.of(this).get(WidgetViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), s -> text_notifications.setText(s));
    }
}
