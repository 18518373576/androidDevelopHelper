package com.example.developerandroidx.ui.android.fragment.testFragment;

import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseFragment;
import com.example.developerandroidx.ui.android.fragment.FragmentActivityViewModel;

import butterknife.OnClick;

/**
 * Date: 2020/6/1 9:34
 * 参考:
 * 描述:
 */
public class TestTwoFragment extends BaseFragment {

    private FragmentActivityViewModel viewModel;

    public interface OnBtnClickListener {
        void onBtnClick(int id);
    }

    OnBtnClickListener listener;

    public TestTwoFragment(OnBtnClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_test_two;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        viewModel = ViewModelProviders.of(getActivity()).get(FragmentActivityViewModel.class);

    }

    @OnClick({R.id.btn_with_interface, R.id.btn_with_viewmodel})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_with_interface:
                listener.onBtnClick(v.getId());
                break;
            case R.id.btn_with_viewmodel:
                viewModel.setData("执行动画");
                break;
        }
    }
}
