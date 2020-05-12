package com.example.developerandroidx.ui.java.arithmetic;

import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.view.navigationView.utils.PixelTransformUtil;
import com.kongzue.dialog.v3.FullScreenDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class ArithmeticActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_arithmetic;
    }

    @Override
    protected void initView() {
        setTitle("算法");
    }

    @OnClick({R.id.btn_bubble_sort})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_bubble_sort:
                FullScreenDialog.show((AppCompatActivity) context, R.layout.view_arithmetic_dialog, new FullScreenDialog.OnBindView() {
                    @Override
                    public void onBind(FullScreenDialog dialog, View rootView) {
                        bubbleSort(rootView);
                    }
                });
                break;
        }
    }

    /**
     * 冒泡排序
     *
     * @param rootView
     */
    private void bubbleSort(View rootView) {
        LinearLayout.LayoutParams params;
        LinearLayout ll_content = rootView.findViewById(R.id.ll_content);
        List<TextView> points = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            TextView point;
            point = new TextView(context);
            params = new LinearLayout.LayoutParams(PixelTransformUtil.dip2px(context, 35), PixelTransformUtil.dip2px(context, 35));
            int margin = PixelTransformUtil.dip2px(context, 5);
            params.setMargins(margin, margin, margin, margin);
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.bg_circle_main_color);
            point.setTextColor(getResources().getColor(R.color.white));
            point.setTextSize(14);
            //Math.random()返回一个0-1之间的随机数
            point.setText(String.valueOf((int) (Math.random() * 100)));
            point.setGravity(Gravity.CENTER);
            point.startAnimation(AnimUtil.getInstance().getScaleAnim(400, 400 + i * 100));
            ll_content.addView(point);
            points.add(point);
        }
        rootView.findViewById(R.id.btn_sort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //排序
                for (int i = 0; i < points.size() - 1; i++) {
                    for (int j = i + 1; j < points.size(); j++) {
                        int pointI = Integer.parseInt(points.get(i).getText().toString());
                        int pointJ = Integer.parseInt(points.get(j).getText().toString());
                        if (pointI > pointJ) {

//                            float pIX = points.get(i).getX();
//                            float pJX = points.get(j).getX();
//
                            points.get(i).setText(String.valueOf(pointJ));
                            points.get(j).setText(String.valueOf(pointI));

//                            Animation animI = AnimUtil.getInstance().getTranslateAnim(0f, pJX - pIX,
//                                    0f, 0f, 500, 0);
//                            int finalI = i;
//                            animI.setAnimationListener(new Animation.AnimationListener() {
//                                @Override
//                                public void onAnimationStart(Animation animation) {
//
//                                }
//
//                                @Override
//                                public void onAnimationEnd(Animation animation) {
//                                    points.get(finalI).setX(pJX);
//                                }
//
//                                @Override
//                                public void onAnimationRepeat(Animation animation) {
//
//                                }
//                            });
//                            points.get(i).startAnimation(animI);
//
//                            Animation animJ = AnimUtil.getInstance().getTranslateAnim(0f, pIX - pJX,
//                                    0f, 0f, 500, 0);
//                            int finalJ = j;
//                            animJ.setAnimationListener(new Animation.AnimationListener() {
//                                @Override
//                                public void onAnimationStart(Animation animation) {
//
//                                }
//
//                                @Override
//                                public void onAnimationEnd(Animation animation) {
//                                    points.get(finalJ).setX(pIX);
//                                }
//
//                                @Override
//                                public void onAnimationRepeat(Animation animation) {
//
//                                }
//                            });
//                            points.get(j).startAnimation(animJ);
                        }
                    }
                }
            }
        });

    }
}
