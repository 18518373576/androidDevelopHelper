package com.example.developerandroidx.ui.android.animation.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/23 17:54
 * 参考:
 * 描述: 圆形揭露动画
 * 当您显示或隐藏一组界面元素时，揭露动画可为用户提供视觉连续性。
 * ViewAnimationUtils.createCircularReveal() 方法让您能够为裁剪圆形添加动画以揭露或隐藏视图。
 * 此动画在 ViewAnimationUtils 类中提供，适用于 Android 5.0（API 级别 21）及更高版本。
 * <p>
 * ViewAnimationUtils.createCircularReveal() 动画采用五个参数。
 * 第一个参数是要在屏幕上隐藏或显示的视图。
 * 接下来的两个参数是裁剪圆形圆心的 x 和 y 坐标。
 * 通常，这将成为视图的中心，但您也可以使用用户轻触的点作为中心，以便动画从用户选择的位置开始。
 * 第四个参数是剪裁圆形的起始半径
 * 最后一个参数是圆形的最终半径。在显示视图时，请确保最终半径大于视图本身，以便在动画播放完毕之前完全显示视图。
 */
public class CircularRevealDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_circular_reveal, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                ImageView iv_landscape = rootView.findViewById(R.id.iv_landscape);
                Button btn_control_view = rootView.findViewById(R.id.btn_control_view);

                btn_control_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //获取视图的中心
                        int cx = iv_landscape.getWidth();
                        int cy = iv_landscape.getHeight();
                        //计算最终的圆的半径
                        float radius = (float) Math.hypot(cx, cy);

                        if (iv_landscape.getVisibility() == View.VISIBLE) {


                            Animator animator = ViewAnimationUtils.createCircularReveal(iv_landscape, 0, cy, radius, 0f);
                            animator.setDuration(500);
                            animator.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    iv_landscape.setVisibility(View.INVISIBLE);
                                }
                            });
                            animator.start();
                            btn_control_view.setText("VISIBLE");
                        } else {
                            Animator animator = ViewAnimationUtils.createCircularReveal(iv_landscape, 0, cy, 0f, radius);
                            animator.setDuration(500);
                            iv_landscape.setVisibility(View.VISIBLE);
                            animator.start();
                            btn_control_view.setText("GONE");
                        }
                    }
                });
            }
        });
    }
}
