package com.example.developerandroidx.ui.android.activity.transitionAnimation;

import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import androidx.annotation.Nullable;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.Constant;

public class TransitionToActivity extends BaseActivity {

    private String transitionType;
    private Transition transition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        transitionType = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);
        //默认使用淡入淡出动画，配合sharedView使用
        transition = TransitionInflater.from(this).inflateTransition(R.transition.fade);

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
        return R.layout.activity_transition_to;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("过渡动画");
    }
}
