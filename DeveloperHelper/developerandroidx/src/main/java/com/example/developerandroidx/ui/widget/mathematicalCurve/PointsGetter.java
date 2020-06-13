package com.example.developerandroidx.ui.widget.mathematicalCurve;

import android.annotation.SuppressLint;

import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.view.curve.Point;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * 作者： zjf 2020/6/13 2:51 PM
 * 参考：
 * 描述：
 */
public class PointsGetter {
    private List<Point> points;

    public List<Point> getPoints(String equation) {
        points = new ArrayList<>();
        switch (equation) {
            case "Y=X":
                getPoints_01();
                break;
            case "Y=X²":
                getPoints_02();
                break;
            case "Y=sin(X)":
                getPoints_03();
                break;
            case "Y=cos(X)":
                getPoints_04();
                break;
            case "Y=tan(X)":
                getPoints_05();
                break;
            case "Y=cot(X)":
                getPoints_06();
                break;
        }
        //移除第一个和最后一个点,为了在屏幕上显示效果更好
        points.remove(0);
        points.remove(points.size() - 1);
        return points;
    }

    @SuppressLint("CheckResult")
    private void getPoints_06() {
        Observable.range(-20, 41)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //含义即为,在坐标轴上包括0点画41个点,计算这41个点的x和y的坐标(根据x和y事前定义的取值范围计算)
                        //java没有定义cot算法,可以使用1/tan求得
                        float x = integer / 40f * 360f;
                        float y;
                        if (Math.abs(x) % 180 == 0) {
                            y = 0f;
                        } else {
                            y = (float) (1 / (float) Math.tan(Math.toRadians(x)));
                        }
                        points.add(new Point(x, y));
//                        LogUtils.d("求tan值", x + "#" + y + "\n");
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_05() {
        Observable.range(-20, 41)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //含义即为,在坐标轴上包括0点画41个点,计算这41个点的x和y的坐标(根据x和y事前定义的取值范围计算)
                        float x = integer / 40f * 180f;
                        float y;
                        if (Math.abs(x) == 90) {
                            y = 0f;
                        } else {
                            y = (float) Math.tan(Math.toRadians(x));
                        }
                        points.add(new Point(x, y));
//                        LogUtils.d("求tan值", x + "#" + y + "\n");
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_04() {
        Observable.range(-20, 41)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //算法与getPoints_03类似
                        //X = integer/40f*720 即 integer*18 720即坐标轴X取值范围
                        //含义即为,在坐标轴上包括0点画41个点,计算这41个点的x和y的坐标(根据x和y事前定义的取值范围计算)
                        points.add(new Point(integer * 18, (float) Math.cos(Math.toRadians(integer * 18))));
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_03() {
        Observable.range(-20, 41)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //X = integer/40f*720 即 integer*18
                        //Math.toRadians(integer)把数值转换为角度进行计算
                        points.add(new Point(integer * 18, (float) Math.sin(Math.toRadians(integer * 18))));

//                        LogUtils.d("求sin值", integer * 18 + "#" + (float) Math.toRadians(integer * 18) + "\n");
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_02() {
        Observable.range(-20, 41)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        points.add(new Point(integer, (float) Math.pow(integer, 2)));
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_01() {
        Observable.range(-20, 41)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        points.add(new Point(integer, integer));
                    }
                });
    }
}
