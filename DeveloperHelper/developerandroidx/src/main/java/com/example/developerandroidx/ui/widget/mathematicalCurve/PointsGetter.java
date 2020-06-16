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

    /**
     * 默认从-40开始,取81个点,因为包含0点所以是80个
     */
    private int rangStart = -40;//取值范围开始点
    private float rang = 80;//取值范围(end = start)
    private int rangCount = 81;//总共需要几个点

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
            case "波动曲线":
                //实现弹簧动画的插值器方程
                getPoints_07();
                break;
            case "心形方程":
                getPoints_08();
                break;
        }
        //移除第一个和最后一个点,为了在屏幕上显示效果更好
        points.remove(0);
        points.remove(points.size() - 1);
        return points;
    }

    //注意这里的方程与其他方程有区别
    //x=cos(t)，则y-x^(2/3)=sin(t)
    //所以：y=sin(t)+(cos(t))^(2/3)
    @SuppressLint("CheckResult")
    private void getPoints_08() {

        Observable.range(0, 363)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        if (integer % 2 == 0) {

                            float x = (float) Math.cos(Math.toRadians(integer));
                            float y = (float) -(Math.sin(Math.toRadians(integer)) + Math.cbrt(Math.pow(Math.cos(Math.toRadians(integer)), 2.0)));

                            points.add(new Point(x, y));
//                            LogUtils.e("爱心曲线", integer + "#" + x + "#" + y);
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_07() {
        //因为包含0点,所以计数的时候+1
        Observable.range(0, 41)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //根据取值范围计算坐标轴上的x的位置
                        float x = integer / 40f;
                        //(float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1)
                        //factor数值为0-1；数值越小回弹次数越多
                        float factor = 0.3f;
                        float y = (float) (Math.pow(2, -10 * x) * Math.sin((x - factor / 4) * (2 * Math.PI) / factor) + 1);

                        points.add(new Point(x, y));
//                        LogUtils.e("弹簧曲线", x + "#" + y);
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_06() {
        Observable.range(rangStart, rangCount)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //含义即为,在坐标轴上包括0点画41个点,计算这41个点的x和y的坐标(根据x和y事前定义的取值范围计算)
                        //java没有定义cot算法,可以使用1/tan求得
                        float x = integer / rang * 360f;
                        float y;
                        if (Math.abs(x) % 180 == 0) {
                            y = 0f;
                        } else {
                            y = (float) (1 / (float) Math.tan(Math.toRadians(x)));
                        }
                        points.add(new Point(x, y));
//                        LogUtils.e("求tan值", x + "#" + y);
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_05() {
        Observable.range(rangStart, rangCount)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //含义即为,在坐标轴上包括0点画41个点,计算这41个点的x和y的坐标(根据x和y事前定义的取值范围计算)
                        float x = integer / rang * 360f;
                        float y;
                        if (Math.abs(x) == 90) {
                            y = 0f;
                        } else {
                            y = (float) Math.tan(Math.toRadians(x));
                        }
                        points.add(new Point(x, y));
//                        LogUtils.e("求tan值", x + "#" + y);
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_04() {
        Observable.range(rangStart, rangCount)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //算法与getPoints_03类似
                        //X = integer/80f*720  720即坐标轴X取值范围
                        //含义即为,在坐标轴上包括0点画41个点,计算这41个点的x和y的坐标(根据x和y事前定义的取值范围计算)
                        float x = integer / rang * 720f;
                        float y = (float) Math.cos(Math.toRadians(x));
                        points.add(new Point(x, y));
                        LogUtils.e("求余弦", x + "#" + y);
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_03() {
        Observable.range(rangStart, rangCount)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        //Math.toRadians(integer)把数值转换为角度进行计算
                        float x = integer / rang * 720;
                        float y = (float) Math.sin(Math.toRadians(x));
                        points.add(new Point(x, y));

//                        LogUtils.e("求sin值", integer * 18 + "#" + (float) Math.toRadians(integer * 18));
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_02() {
        Observable.range(rangStart, rangCount)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        points.add(new Point(integer, (float) Math.pow(integer, 2)));
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getPoints_01() {
        Observable.range(rangStart, rangCount)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        points.add(new Point(integer, integer));
                    }
                });
    }
}
