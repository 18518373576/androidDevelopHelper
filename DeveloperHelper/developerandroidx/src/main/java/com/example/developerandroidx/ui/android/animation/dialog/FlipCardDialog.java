package com.example.developerandroidx.ui.android.animation.dialog;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/23 11:23
 * 参考:
 * 描述: 卡片反转动画
 */
public class FlipCardDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_flip_card, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                View iv_image = rootView.findViewById(R.id.iv_image);
                View tv_text = rootView.findViewById(R.id.tv_text);
                View rl_content = rootView.findViewById(R.id.rl_content);

                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rl_content.setClickable(false);

                        if (iv_image.getVisibility() == View.VISIBLE) {
                            AnimatorSet flipOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_out);
                            flipOut.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    iv_image.setVisibility(View.GONE);
                                    rl_content.setClickable(true);
                                }
                            });
                            flipOut.setTarget(iv_image);
                            flipOut.start();
                            tv_text.setVisibility(View.VISIBLE);

                            AnimatorSet flipIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_in);
                            flipIn.setTarget(tv_text);
                            flipIn.start();
                        } else {
                            AnimatorSet flipOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_left_out);
                            flipOut.setTarget(tv_text);
                            flipOut.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    tv_text.setVisibility(View.GONE);
                                    rl_content.setClickable(true);
                                }
                            });
                            flipOut.start();

                            iv_image.setVisibility(View.VISIBLE);
                            AnimatorSet flipIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_left_in);
                            flipIn.setTarget(iv_image);
                            flipIn.start();
                        }
                    }
                });

            }
        });
    }
}
