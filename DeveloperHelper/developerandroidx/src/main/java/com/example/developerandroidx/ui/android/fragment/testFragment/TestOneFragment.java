package com.example.developerandroidx.ui.android.fragment.testFragment;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseFragment;
import com.example.developerandroidx.ui.android.fragment.FragmentActivityViewModel;
import com.example.developerandroidx.utils.AnimUtil;

import butterknife.BindView;

/**
 * Date: 2020/6/1 9:34
 * 参考:
 * 描述:
 */
public class TestOneFragment extends BaseFragment {

    @BindView(R.id.iv_basketball)
    ImageView iv_basketball;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_test_one;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //获取ViewModel，使用of(getActivity())获取共享的viewModel
        FragmentActivityViewModel viewModel = ViewModelProviders.of(getActivity()).get(FragmentActivityViewModel.class);
        //观察liveData如果设置内容为“执行动画”，则执行相应的操作
        viewModel.getData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                switch (s) {
                    case "执行动画":
                        AnimUtil.getInstance().startSpringScaleAnimator(iv_basketball, 3000, 1f, 2f, 1f);
                        break;
                }
            }
        });
    }

    public void startAnim() {
        //接收activity接口回调方法，执行操作
        AnimUtil.getInstance().startSpringScaleAnimator(iv_basketball, 3000, 1f, 2f, 1f);
    }
}
