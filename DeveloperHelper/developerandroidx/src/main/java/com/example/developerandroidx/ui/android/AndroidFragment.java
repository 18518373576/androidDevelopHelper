package com.example.developerandroidx.ui.android;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseFragment;

import butterknife.BindView;

public class AndroidFragment extends BaseFragment {

    @BindView(R.id.rcv_android)
    RecyclerView rcv_android;

    private AndroidViewModel viewModel;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_android;
    }

    @Override
    protected void initView() {

        rcv_android.setLayoutManager(new GridLayoutManager(context,2));
    }

    @Override
    protected void initData() {
        //把viewModel绑定到fragment
        viewModel = ViewModelProviders.of(this).get(AndroidViewModel.class);

        //观察数据的变化
        //使用lambda表达式，java写法如下作为参考
        // viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //            @Override
        //            public void onChanged(@Nullable String s) {
        //                text_dashboard.setText(s);
        //            }
        //        });
        viewModel.getAdapterList().observe(getViewLifecycleOwner(), s -> rcv_android.getAdapter());
    }
}
