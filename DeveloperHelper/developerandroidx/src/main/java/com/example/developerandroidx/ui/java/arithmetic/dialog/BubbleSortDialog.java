package com.example.developerandroidx.ui.java.arithmetic.dialog;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.realize.MyAnimationListener;
import com.example.developerandroidx.view.navigationView.utils.PixelTransformUtil;
import com.kongzue.dialog.interfaces.OnDismissListener;
import com.kongzue.dialog.v3.FullScreenDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者： zjf 2020/5/14 11:25
 * @参考：
 * @描述： 冒泡排序
 */
public class BubbleSortDialog {

    private Context context;

    private boolean isDis = false;

    private Thread thread;


    public void show(Context context) {
        isDis = false;
        this.context = context;
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.view_arithmetic_dialog, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                bubbleSort(rootView);
                dialog.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        if (thread != null) {
                            isDis = true;
                            thread.interrupt();
                            thread = null;
                            points = null;
                            pointsNum = null;
                        }
                    }
                });
            }
        });
    }

    private List<TextView> points;
    private List<Integer> pointsNum;
    private LinearLayout ll_content;
    private boolean isSorting = false;

    /**
     * 冒泡排序
     *
     * @param rootView
     */
    private void bubbleSort(View rootView) {
        LinearLayout.LayoutParams params;
        ll_content = rootView.findViewById(R.id.ll_content);


        points = new ArrayList<>();
        pointsNum = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            //随机生成一组100以内的数，用于排序
            TextView point;
            point = new TextView(context);
            params = new LinearLayout.LayoutParams(PixelTransformUtil.dip2px(context, 35), PixelTransformUtil.dip2px(context, 35));
            int margin = PixelTransformUtil.dip2px(context, 5);
            params.setMargins(margin, margin, margin, margin);
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.bg_circle_main_color);
            point.setTextColor(context.getResources().getColor(R.color.white));
            point.setTextSize(14);
            //Math.random()返回一个0-1之间的随机数
            int random = (int) (Math.random() * 100);
            point.setText(String.valueOf(random));
            point.setGravity(Gravity.CENTER);
            Animation animScal = AnimUtil.getInstance().getScaleAnim(400, 400 + i * 100);
            animScal.setInterpolator(new OvershootInterpolator());
            animScal.setAnimationListener(new MyAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    point.setText(String.valueOf(point.getText().toString()));
                }
            });
            point.startAnimation(animScal);
            ll_content.addView(point);
            //记录每次数值的改变，和view位置的改变
            points.add(point);
            pointsNum.add(random);
        }

        Button btn_sort = rootView.findViewById(R.id.btn_sort);
        btn_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSorting) {
                    v.startAnimation(AnimUtil.getInstance().getShakeAnim());
                    return;
                }
                //排序
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            isSorting = true;
                            btn_sort.setBackgroundResource(R.drawable.selector_gray_btn);
                            btn_sort.setTextColor(context.getResources().getColor(R.color.textColor));
                            //尝试使用补间动画位移，失败,会闪动,因为补间动画执行完会回到原始位置。所以在动画开始之前只显示背景，视觉上看不见闪动了
                            for (int i = 0; i < points.size() - 1; i++) {
                                if (isDis) {
                                    break;
                                }
                                for (int j = i + 1; j < points.size(); j++) {
                                    if (isDis) {
                                        break;
                                    }
                                    int pointI = pointsNum.get(i);
                                    int pointJ = pointsNum.get(j);

                                    TextView tvPointI = points.get(i);
                                    TextView tvPointJ = points.get(j);

                                    if (pointI > pointJ) {

                                        float pIX = points.get(i).getX();
                                        float pJX = points.get(j).getX();

                                        pointsNum.set(i, pointJ);
                                        pointsNum.set(j, pointI);


                                        AnimationSet setI = new AnimationSet(true);
                                        setI.addAnimation(AnimUtil.getInstance().getTranslateAnim(0f, pJX - pIX,
                                                0f, 0f, 800, 0));

                                        points.get(i).startAnimation(setI);


                                        AnimationSet setJ = new AnimationSet(true);
                                        setJ.addAnimation(AnimUtil.getInstance().getTranslateAnim(0f, pIX - pJX,
                                                0f, 0f, 800, 0));

                                        points.get(j).startAnimation(setJ);

                                        try {
//                                            points.get(i).setTextColor(context.getResources().getColor(R.color.colorMain));
//                                            points.get(j).setTextColor(context.getResources().getColor(R.color.colorMain));

                                            Thread.sleep(850);

                                            points.get(i).setX(pJX);
                                            points.get(j).setX(pIX);

//                                            points.get(i).setTextColor(context.getResources().getColor(R.color.white));
//                                            points.get(j).setTextColor(context.getResources().getColor(R.color.white));

                                            Thread.sleep(300);
                                            points.set(i, tvPointJ);
                                            points.set(j, tvPointI);
                                            Thread.sleep(300);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            btn_sort.setBackgroundResource(R.drawable.selector_btn);
                            btn_sort.setTextColor(context.getResources().getColor(R.color.white));
                            isSorting = false;
                            //使用属性动画修改,失败,总是漂移
//                            for (int i = 0; i < pointsNum.size() - 1; i++) {
//                                for (int j = i + 1; j < pointsNum.size(); j++) {
//                                    int pointI = pointsNum.get(i);
//                                    int pointJ = pointsNum.get(j);
//
//                                    TextView tvPointI = points.get(i);
//                                    TextView tvPointJ = points.get(j);
//
//                                    if (pointI > pointJ) {
//
//                                        Message msg = new Message();
//                                        msg.what = 100;
//                                        msg.obj = new int[]{i, j};
//                                        handler.sendMessage(msg);
//
//                                        try {
//                                            Thread.sleep(2000);
//
//                                            points.set(i, tvPointJ);
//                                            points.set(j, tvPointI);
//
//                                            handler.sendEmptyMessage(200);
//
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });

    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 200:
                    ll_content.requestLayout();
                    break;
                case 100:

                    int i = ((int[]) (msg.obj))[0];
                    int j = ((int[]) (msg.obj))[1];

                    int pointI = pointsNum.get(i);
                    int pointJ = pointsNum.get(j);

                    View tvPointI = ll_content.getChildAt(i);
                    View tvPointJ = ll_content.getChildAt(j);

//                    float iX = pointsX.get(i);
//                    float jX = pointsX.get(j);
                    float iX = tvPointI.getX();
                    float jX = tvPointJ.getX();

                    Log.e("打印排序：", i + "*" + j + pointsNum.toString());
                    Log.e("打印排序tvPointIX：", "获取：" + tvPointI.getX() + "保存:" + iX);
                    Log.e("打印排序tvPointJX：", "获取：" + tvPointJ.getX() + "保存:" + jX);


                    float[] iTranslationXs = new float[]{tvPointI.getTranslationX(), jX - iX};
                    float[] jTranslationXs = new float[]{tvPointJ.getTranslationX(), iX - jX};

                    ObjectAnimator iAnimator = ObjectAnimator.ofFloat(iX, "translationX", jX);

                    iAnimator.setDuration(500);

                    ObjectAnimator jAnimator = ObjectAnimator.ofFloat(jX, "translationX", iX);
                    jAnimator.setDuration(500);

                    iAnimator.start();
                    jAnimator.start();

                    pointsNum.set(i, pointJ);
                    pointsNum.set(j, pointI);

//                    pointsX.set(i, jX);
//                    pointsX.set(j, iX);

//                    points.set(i, tvPointJ);
//                    points.set(j, tvPointI);
                    break;
            }
        }
    };
}
