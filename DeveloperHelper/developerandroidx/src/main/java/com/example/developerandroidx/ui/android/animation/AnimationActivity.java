package com.example.developerandroidx.ui.android.animation;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.widget.Button;

import androidx.dynamicanimation.animation.FlingAnimation;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.realize.MyAnimationListener;
import com.example.developerandroidx.ui.android.animation.dialog.AnimatedStateListDrawableDialog;
import com.example.developerandroidx.ui.android.animation.dialog.CircularRevealDialog;
import com.example.developerandroidx.ui.android.animation.dialog.FlingAnimationDialog;
import com.example.developerandroidx.ui.android.animation.dialog.FlipCardDialog;
import com.example.developerandroidx.ui.android.animation.dialog.LayoutTransitionDialog;
import com.example.developerandroidx.ui.android.animation.dialog.PathInterpolatorDialog;
import com.example.developerandroidx.ui.android.animation.dialog.ScaleViewToViewDialog;
import com.example.developerandroidx.ui.android.animation.dialog.SpringAnimationDialog;
import com.example.developerandroidx.ui.android.animation.dialog.SpringInterpolatorDialog;
import com.example.developerandroidx.ui.android.animation.dialog.ValueAnimatorDialog;
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.RouteUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 参考：https://www.jianshu.com/p/16e0d4e92bb2
 */
public class AnimationActivity extends BaseActivity {

    @BindView(R.id.btn_translate)
    Button btn_translate;
    @BindView(R.id.rl_content)
    View rl_content;

    @Override
    protected int bindLayout() {
        return R.layout.activity_animation;
    }

    @Override
    protected void initView() {
        setTitle("Animation");
        iv_right.setVisibility(View.VISIBLE);
        showViewTransitionAnimation();
    }

    /**
     * 在应用的使用过程中，需要在屏幕上显示新信息，同时移除旧信息。
     * 立即切换显示的内容看起来有些突兀，或者导致用户很容易错过屏幕上的新内容。
     * 利用动画可以减慢更改的速度，并通过概念吸引用户的注意，以使更新更加明显。
     */
    private void showViewTransitionAnimation() {

        //将 config_shortAnimTime 系统属性缓存到成员变量中。此属性用于定义动画的标准“短”持续时间。该持续时间非常适用于巧妙的动画效果或经常显示的动画。
        //实在是太短了
        int shortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        rl_content.setAlpha(0);//完全透明
        rl_content.setVisibility(View.VISIBLE);
        rl_content.animate().alpha(1).setDuration(500).setStartDelay(50);
    }

    @OnClick({R.id.btn_translate, R.id.btn_scale, R.id.btn_rotate, R.id.btn_alpha,
            R.id.btn_shake, R.id.iv_code, R.id.btn_anim_set, R.id.btn_translate_animator,
            R.id.iv_right})
    public void click(View v) {
        Animation animation;
        switch (v.getId()) {
            case R.id.btn_translate://平移动画
                animation = AnimationUtils.loadAnimation(context, R.anim.translate);
                animation.setInterpolator(new BounceInterpolator());
                animation.setFillAfter(true);
                animation.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
//                        Log.e("打印位置：", v.getX() + "#" + v.getY());
                        btn_translate.setText("X:" + v.getTranslationX() + "\nY:" + v.getY());
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Log.e("打印位置：", v.getX() + "#" + v.getY());
                        btn_translate.setText("X:" + v.getX() + "\nY:" + v.getY());
                    }
                });
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
            case R.id.btn_translate_animator:
                //ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "alpha", 1f, 0f, 1f);
                // 表示的是:
                // 动画作用对象是mButton
                // 动画作用的对象的属性是透明度alpha
                // 动画效果是:常规 - 全透明 - 常规
                //ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "rotation", 0f, 360f);
                // 表示的是:
                // 动画作用对象是mButton
                // 动画作用的对象的属性是旋转rotation
                // 动画效果是:0 - 360
                //ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "translationX", curTranslationX, 300,curTranslationX);
                // 表示的是:
                // 动画作用对象是mButton
                // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
                // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
                //ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "scaleX", 1f, 3f, 1f);
                // 表示的是:
                // 动画作用对象是mButton
                // 动画作用的对象的属性是X轴缩放
                // 动画效果是:放大到3倍,再缩小到初始大小
                float[] translationXs = new float[]{v.getTranslationX(), v.getWidth(), v.getTranslationX(), -v.getWidth(), v.getTranslationX()};
                ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationX", translationXs);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(3000);
                animator.start();
                break;
            case R.id.iv_right:
                showExtendDialog();
                break;
        }
    }

    /**
     * 动画扩展
     */
    private void showExtendDialog() {

        String[] items = new String[]{"ValueAnimator for object", "AnimatedStateListDrawable", "Flip card", "Circular reveal", "PathInterpolator", "Fling"
                , "SpringAnimation", "SpringAnimation with interpolator", "Scale view to view", "LayoutTransition", "物理原理动画", "布局更新动画", "布局过渡动画"};
        DialogUtils.getInstance().showBottomMenu(context, "动画扩展", items, new DialogUtils.OnItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                switch (text) {
                    case "ValueAnimator for object"://作用与对象的属性动画
                        new ValueAnimatorDialog().show(context);
                        break;
                    case "AnimatedStateListDrawable"://视图控件状态切换动画，原理是使用帧动画填充状态间的过渡
                        //文档： https://developer.android.google.cn/guide/topics/graphics/prop-animation
                        new AnimatedStateListDrawableDialog().show(context);
                        break;
                    case "Flip card"://卡片反转动画
                        new FlipCardDialog().show(context);
                        break;
                    case "Circular reveal"://圆形揭露动画
                        new CircularRevealDialog().show(context);
                        break;
                    case "PathInterpolator"://按照曲线运动的动画，原理是定义一个椭圆形，定义视图在椭圆形上开始的点，并移动多少角度
                        new PathInterpolatorDialog().show(context);
                        break;
                    case "Fling"://抛掷动画，模拟物理原理，可以设置摩擦力
                        new FlingAnimationDialog().show(context);
                        break;
                    case "Scale view to view"://视图之间的缩放转换，如，点击小图放大浏览
                        new ScaleViewToViewDialog().show(context);
                        break;
                    case "LayoutTransition":
                        //视图内容改变时的动画，如在LinearLayout添加或删除view会有动画效果，
                        // 父控件只需设置即可：android:animateLayoutChanges="true"
                        new LayoutTransitionDialog().show(context);
                        break;
                    case "SpringAnimation"://模拟物理弹簧效果，可设置setDampingRatio()弹簧阻尼，和设置setStiffness()弹簧刚度
                        new SpringAnimationDialog().show(context);
                        break;
                    case "SpringAnimation with interpolator"://使用插值器实现弹簧动画效果
                        new SpringInterpolatorDialog().show(context);
                        break;
                }
            }
        });
    }
}
