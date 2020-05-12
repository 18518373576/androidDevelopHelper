package com.example.developerandroidx.ui.android.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;
import com.example.developerandroidx.ui.widget.webView.TechnologyWebviewActivity;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.RouteUtil;

import butterknife.OnClick;

/**
 * 参考：https://www.jianshu.com/p/16e0d4e92bb2
 */
public class AnimationActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_animation;
    }

    @Override
    protected void initView() {
        setTitle("Animation");
    }

    @OnClick({R.id.btn_translate, R.id.btn_scale, R.id.btn_rotate, R.id.btn_alpha,
            R.id.btn_shake, R.id.iv_code, R.id.btn_anim_set})
    public void click(View v) {
        Animation animation;
        switch (v.getId()) {
            case R.id.btn_translate://平移动画
                animation = AnimationUtils.loadAnimation(context, R.anim.translate);
                animation.setInterpolator(new BounceInterpolator());
                v.startAnimation(animation);
                break;
            case R.id.btn_scale://缩放
                animation = AnimationUtils.loadAnimation(context, R.anim.scale);
                v.startAnimation(animation);
                break;
            case R.id.btn_rotate://旋转
                animation = AnimationUtils.loadAnimation(context, R.anim.rotate);
                animation.setInterpolator(new BounceInterpolator());
                v.startAnimation(animation);
                break;
            case R.id.btn_alpha://渐变
                animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
                v.startAnimation(animation);
                break;
            case R.id.btn_shake://抖动
                animation = AnimationUtils.loadAnimation(context, R.anim.shake);
//                animation = new TranslateAnimation(-10, 10, 0, 0);
                animation.setInterpolator(new CycleInterpolator(3));
                v.startAnimation(animation);
                break;
            case R.id.btn_anim_set:
                animation = AnimationUtils.loadAnimation(context, R.anim.anim_set);
                v.startAnimation(animation);
                break;
            case R.id.iv_code:
                //参考文章
                RouteUtil.goTo(context, RouteUtil.getDestination(CodeViewActivity.class), CodeVariate.getInstance().getCode_6());
                break;
        }
    }
}
