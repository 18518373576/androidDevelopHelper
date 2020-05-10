package com.example.developerandroidx.ui.android.activity.transitionAnimation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.TransitionAnimationRcvAdapter;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.bean.TransitionAnimationItemBean;
import com.example.developerandroidx.utils.Constant;

import java.util.List;

import butterknife.BindView;

public class TransitionAnimationActivity extends BaseActivity implements BaseRcvAdapter.OnRecyclerViewItemClickListner {

    @BindView(R.id.rcv_transition)
    RecyclerView rcv_transition;
    private TransitionAnimationRcvAdapter adapter;
    private String transitionType;
    private Transition transition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        transitionType = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);

        switch (transitionType) {
            case "Explode":
                transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
                break;
            case "Slide":
                transition = TransitionInflater.from(this).inflateTransition(R.transition.slide);
                break;
            case "Fade":
                transition = TransitionInflater.from(this).inflateTransition(R.transition.fade);
                break;
        }
        //退出时使用
        getWindow().setExitTransition(transition);
        //第一次进入时使用
        getWindow().setEnterTransition(transition);
        //再次进入时使用
        getWindow().setReenterTransition(transition);

        getWindow().setReturnTransition(transition);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_transition_animation;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("切换动画");
        rcv_transition.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    protected void initData() {
        super.initData();
        TransitionAnimationViewModel viewModel = ViewModelProviders.of(this).get(TransitionAnimationViewModel.class);

        viewModel.getAdapterList().observe(this, new Observer<List<TransitionAnimationItemBean>>() {
            @Override
            public void onChanged(List<TransitionAnimationItemBean> transitionAnimationItemBeans) {
                if (adapter == null) {
                    adapter = new TransitionAnimationRcvAdapter(transitionAnimationItemBeans);
                    rcv_transition.setAdapter(adapter);
                    adapter.setOnItemClickListener(TransitionAnimationActivity.this);
                } else {
                    adapter.notifyDataSetChanged(transitionAnimationItemBeans);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        BaseRcvAdapter.releaseAllHolder(rcv_transition);
        super.onDestroy();
    }

    @Override
    public void onItemClick(@NonNull View v, int viewType, @NonNull Object data, int position) {
        Intent intent = new Intent(this, TransitionToActivity.class);
        intent.putExtra(Constant.IntentParams.INTENT_PARAM, transitionType);
        switch (transitionType) {
            case "Shared Element":
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, v.findViewById(R.id.iv_landscape), "sharedView").toBundle());
                break;
            default:
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }

    }
}
