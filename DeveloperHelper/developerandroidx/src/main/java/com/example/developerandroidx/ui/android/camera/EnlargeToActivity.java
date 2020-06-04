package com.example.developerandroidx.ui.android.camera;

import android.app.Instrumentation;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class EnlargeToActivity extends BaseActivity {

    @BindView(R.id.iv_enlarge)
    ImageView iv_enlarge;
    private Transition transition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //默认使用淡入淡出动画，配合sharedView使用
        transition = TransitionInflater.from(this).inflateTransition(R.transition.fade);
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
        return R.layout.activity_enlarge_to;
    }

    @Override
    protected void initView() {
        super.initView();
        Uri imageUri = getIntent().getParcelableExtra(Constant.IntentParams.INTENT_PARAM);
        Glide.with(context).load(imageUri).override(PixelTransformForAppUtil.getDiaplayWidth(), PixelTransformForAppUtil.getDiaplayWidth()).centerCrop().into(iv_enlarge);
    }

    @OnClick({R.id.iv_enlarge})
    public void click(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //模拟返回键点击效果
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
            }
        }).start();
    }
}