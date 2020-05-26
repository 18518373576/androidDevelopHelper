package com.example.developerandroidx.ui.android.animation.dialog;

import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.kongzue.dialog.interfaces.OnDismissListener;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * @作者： zjf 2020/5/21 11:50
 * @参考：
 * @描述： 作用于对象的属性动画，Tree对象模拟一棵树发芽生长的过程，对象有三个参数
 * public boolean isSprouting;  是否正在发芽，动画事件在5%的时候设置为true，开始生长
 * public float age;            树龄，随着时间的推移匀速增长
 * public float height;         树高，随着时间的推移，开始生长缓慢，然后生长加速，最后生长放缓
 */
public class ValueAnimatorDialog implements FunctionDialogInterface {
    private View v_seed;
    private TextView tv_title;

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_value_animator, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                v_seed = rootView.findViewById(R.id.v_seed);
                tv_title = rootView.findViewById(R.id.tv_title);
                rootView.findViewById(R.id.btn_grow).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startAnimator();
                    }
                });

                dialog.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        v_seed.clearAnimation();
                    }
                });
            }
        });
    }

    private class Tree {
        //发芽
        public boolean isSprouting;
        public float age;
        public float height;

        public Tree(boolean isSprout, float age, float height) {
            this.isSprouting = isSprout;
            this.age = age;
            this.height = height;
        }
    }

    /**
     * 估值器
     */
    private class GrowEvaluator implements TypeEvaluator<Tree> {

        @Override
        public Tree evaluate(float fraction, Tree startValue, Tree endValue) {
            boolean isSprout = false;
            float age;
            float height;
            if (fraction > 0.05) {
                isSprout = true;
            }
            age = startValue.age + (endValue.age - startValue.age) * fraction;
            //开始和结束时缓慢但在中间会加快，借用了官方文档插值器的函数，为了符合树木生长规律，年龄匀速增长，高度开始慢，中间加快生长，长到一定程度生长放缓
            height = startValue.height + (endValue.height - startValue.height) * ((float) (Math.cos((fraction + 1) * Math.PI) / 2.0f) + 0.5f);
            return new Tree(isSprout, age, height);
        }
    }

    /**
     * 插值器，自定义以哪种曲线运行动画，比如正弦曲线
     */
    private class GrowInterpolator implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {
            //实现了文档提供 AccelerateDecelerateInterpolator插值器的实现方法	该插值器的变化率在开始和结束时缓慢但在中间会加快。
            LogUtils.e("插值器", input + "");
            return (float) (Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
        }
    }

    private void startAnimator() {

        ValueAnimator animator = ValueAnimator.ofObject(new GrowEvaluator(), new Tree(false, 0, 0), new Tree(true, 20, 200));
        //一秒发芽，10秒生长
        animator.setDuration(10000);
        //插值器作用于对象的所有属性，在估值器对属性进行了插值变化处理，不再设置插值器
        //animator.setInterpolator(new GrowInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Tree tree = (Tree) animation.getAnimatedValue();
                LogUtils.e("属性动画对象", tree.age + "*" + tree.height);
                if (tree.isSprouting) {
                    tv_title.setText("Grow up");
                } else {
                    tv_title.setText("Sprouting");
                }
                v_seed.getLayoutParams().width = PixelTransformForAppUtil.dip2px(20 + tree.age);
                v_seed.getLayoutParams().height = PixelTransformForAppUtil.dip2px(20 + tree.height);
                v_seed.requestLayout();
            }
        });
        animator.start();
    }
}
