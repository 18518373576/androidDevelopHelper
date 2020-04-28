package com.example.developerandroidx.ui.android;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseFragment;

import butterknife.BindView;

public class AndroidFragment extends BaseFragment {

    @BindView(R.id.text_home)
    TextView text_home;

    private AndroidViewModel viewModel;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_android;
    }

    @Override
    protected void initView() {
        //把viewModel绑定到fragment
        viewModel = ViewModelProviders.of(this).get(AndroidViewModel.class);

        //观察数据的变化
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                text_home.setText(s);
            }
        });
    }
}
