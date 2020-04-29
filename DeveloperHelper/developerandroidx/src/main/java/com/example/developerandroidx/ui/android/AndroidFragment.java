package com.example.developerandroidx.ui.android;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.FunctionRcvAdapter;
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

        //设置布局管理器
        rcv_android.setLayoutManager(new GridLayoutManager(context,2));
    }

    @Override
    protected void initData() {
        //把viewModel绑定到fragment
        viewModel = ViewModelProviders.of(this).get(AndroidViewModel.class);

        //观察数据的变化
        //使用lambda表达式，java写法如下作为参考
//         viewModel.getAdapterList().observe(getViewLifecycleOwner(), new Observer<List<FunctionItemBean>>() {
//                    @Override
//                    public void onChanged(@Nullable List<FunctionItemBean> functionList) {
//                        showToast(functionList.size()+"");
//                    }
//                });
         viewModel.getAdapterList().observe(getViewLifecycleOwner(), functionList ->
                 rcv_android.setAdapter(new FunctionRcvAdapter(context,functionList)));
    }
}
