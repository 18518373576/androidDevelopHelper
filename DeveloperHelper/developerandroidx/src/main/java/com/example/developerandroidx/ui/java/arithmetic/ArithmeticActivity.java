package com.example.developerandroidx.ui.java.arithmetic;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.utils.MyAnimationListener;
import com.example.developerandroidx.view.navigationView.utils.PixelTransformUtil;
import com.kongzue.dialog.interfaces.OnDismissListener;
import com.kongzue.dialog.v3.FullScreenDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class ArithmeticActivity extends BaseActivity implements OnDismissListener {

    private FullScreenDialog dialog;
    private Thread thread;

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
                //测试没有执行setTag调用getTag是否为空
//                if (v.getTag() == null) {
//                    showNotify("kong");
//                    return;
//                }
                dialog = FullScreenDialog.show((AppCompatActivity) context, R.layout.view_arithmetic_dialog, new FullScreenDialog.OnBindView() {
                    @Override
                    public void onBind(FullScreenDialog dialog, View rootView) {

                        bubbleSort(rootView);
                    }
                });
                dialog.setOnDismissListener(this);
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 100:

                    int i = ((int[]) (msg.obj))[0];
                    int j = ((int[]) (msg.obj))[1];

                    int pointI = pointsNum.get(i);
                    int pointJ = pointsNum.get(j);

                    TextView tvPointI = points.get(i);
                    TextView tvPointJ = points.get(j);

                    float iX = pointsX.get(i);
                    float jX = pointsX.get(j);

                    Log.e("打印排序：", i + "*" + j + pointsNum.toString() + "*" + pointsX.toString());
                    Log.e("打印排序tvPointIX：", "获取：" + tvPointI.getX() + "保存:" + iX);
                    Log.e("打印排序tvPointJX：", "获取：" + tvPointJ.getX() + "保存:" + jX);


                    float[] iTranslationXs = new float[]{tvPointI.getTranslationX(), jX - iX};
                    float[] jTranslationXs = new float[]{tvPointJ.getTranslationX(), iX - jX};

                    ObjectAnimator iAnimator = ObjectAnimator.ofFloat(tvPointI, "translationX", iTranslationXs);
                    iAnimator.setDuration(500);

                    ObjectAnimator jAnimator = ObjectAnimator.ofFloat(tvPointJ, "translationX", jTranslationXs);
                    jAnimator.setDuration(500);

                    iAnimator.start();
                    jAnimator.start();

                    pointsNum.set(i, pointJ);
                    pointsNum.set(j, pointI);

                    pointsX.set(i, jX);
                    pointsX.set(j, iX);

                    points.set(i, tvPointJ);
                    points.set(j, tvPointI);
                    break;
            }
        }
    };

    private List<TextView> points;
    private List<Integer> pointsNum;
    private List<Float> pointsX;

    /**
     * 冒泡排序
     *
     * @param rootView
     */
    private void bubbleSort(View rootView) {
        LinearLayout.LayoutParams params;
        LinearLayout ll_content = rootView.findViewById(R.id.ll_content);
        points = new ArrayList<>();
        pointsNum = new ArrayList<>();
        pointsX = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            //随机生成一组100以内的数，用于排序
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
            int random = (int) (Math.random() * 100);
            point.setText(String.valueOf(random));
            point.setGravity(Gravity.CENTER);
            Animation animScal = AnimUtil.getInstance().getScaleAnim(400, 400 + i * 100);
            animScal.setAnimationListener(new MyAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    point.setText(String.valueOf(random));
                }
            });
            point.startAnimation(animScal);
            ll_content.addView(point);
            //记录每次数值的改变，和view位置的改变
            points.add(point);
            pointsNum.add(random);
        }
        rootView.findViewById(R.id.btn_sort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //排序
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //尝试使用补间动画位移，总是位移，失败
//                            for (int i = 0; i < points.size() - 1; i++) {
//                                for (int j = i + 1; j < points.size(); j++) {
//                                    int pointI = pointsNum.get(i);
//                                    int pointJ = pointsNum.get(j);
//
//                                    TextView tvPointI = points.get(i);
//                                    TextView tvPointJ = points.get(j);
//
//                                    if (pointI > pointJ) {
//
//                                        float pIX = points.get(i).getX();
//                                        float pJX = points.get(j).getX();
//
//                                        pointsNum.set(i, pointJ);
//                                        pointsNum.set(j, pointI);
//
//                                        Animation animI = AnimUtil.getInstance().getTranslateAnim(0f, pJX - pIX,
//                                                0f, 0f, 800, 0);
//                                        points.get(i).startAnimation(animI);
//
//                                        Animation animJ = AnimUtil.getInstance().getTranslateAnim(0f, pIX - pJX,
//                                                0f, 0f, 800, 0);
//                                        points.get(j).startAnimation(animJ);
//
//                                        try {
//                                            Thread.sleep(850);
//                                            points.get(i).setX(pJX);
//                                            points.get(j).setX(pIX);
//                                            Thread.sleep(300);
//                                            points.set(i, tvPointJ);
//                                            points.set(j, tvPointI);
//                                            Thread.sleep(300);
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }
//                            }
                            for (TextView tv : points) {
                                pointsX.add(tv.getX());
                            }
                            //使用属性动画修改
                            for (int i = 0; i < pointsNum.size() - 1; i++) {
                                for (int j = i + 1; j < pointsNum.size(); j++) {
                                    int pointI = pointsNum.get(i);
                                    int pointJ = pointsNum.get(j);

                                    TextView tvPointI = points.get(i);
                                    TextView tvPointJ = points.get(j);

                                    if (pointI > pointJ) {

                                        Message msg = new Message();
                                        msg.what = 100;
                                        msg.obj = new int[]{i, j};
                                        handler.sendMessage(msg);

                                        try {
                                            Thread.sleep(2000);

                                            points.set(i, tvPointJ);
                                            points.set(j, tvPointI);

                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });

    }


    /**
     * dialog退出的时候释放资源
     */
    @Override
    public void onDismiss() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }
}
