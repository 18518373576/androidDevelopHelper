package com.example.developerandroidx.ui.widget.mathematicalCurve;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.curve.CurveView;

import butterknife.BindView;

public class MathematicalCurveActivity extends BaseActivity {

    @BindView(R.id.cv_curve)
    CurveView cv_curve;
    private PointsGetter getter;
    private String[] mathematicals;

    @Override
    protected int bindLayout() {
        return R.layout.activity_mathematical_curve;
    }

    @Override
    protected void initView() {
        super.initView();
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setOnClickListener(v -> showItem());
    }

    @Override
    protected void initData() {
        super.initData();
        getter = new PointsGetter();
        //Y = pow(2, -10 * x) * sin((x - factor / 4) * (2 * PI) / factor) + 1
        mathematicals = new String[]{"Y=X", "Y=X²", "Y=sin(X)", "Y=cos(X)", "Y=tan(X)", "Y=cot(X)",
                "波动曲线", "心形方程"};
        //默认展示Y=X
        setTitle(mathematicals[0]);
        cv_curve.setPoints(getter.getPoints(mathematicals[0]), 80f, 80f, "X:-20~20  Y:-20~20");

    }

    private void showItem() {
        DialogUtils.getInstance().showBottomMenu(context, mathematicals, new DialogUtils.OnItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                setTitle(text);
                switch (index) {
                    case 0:
                        cv_curve.setPoints(getter.getPoints(text), 80f, 80f, "X:-40~40  Y:-40~40");
                        break;
                    case 1:
                        cv_curve.setPoints(getter.getPoints(text), 80f, 3200f, "X:-40~40  Y:-1600~1600");
                        break;
                    case 2:
                    case 3:
                        cv_curve.setPoints(getter.getPoints(text), 720f, 10f, "X:-360°~360°  Y:-5~5");
                        break;
                    case 4:
                    case 5:
                        cv_curve.setPoints(getter.getPoints(text), 360, 20f, "X:-180°~180°  Y:-10~10");
                        break;
                    case 6:
                        cv_curve.setPoints(getter.getPoints(text), 2f, 4f, "X:-1~1  Y:-2~2");
                        break;
                    case 7:
                        cv_curve.setPoints(getter.getPoints(text), 4f, 4f, "X:-2~2  Y:-2~2", true);
                        break;
                }
            }
        });
    }
}