package com.example.developerandroidx.ui.widget.mathematicalCurve;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

public class MathematicalCurveActivity extends BaseActivity {


    @Override
    protected int bindLayout() {
        return R.layout.activity_mathematical_curve;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Curve");
    }
}