package com.example.developerandroidx.ui.java.arithmetic;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.view.navigationView.utils.PixelTransformUtil;
import com.kongzue.dialog.v3.FullScreenDialog;

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
            point.setText("1");
            point.setGravity(Gravity.CENTER);
            ll_content.addView(point);

            point.startAnimation(AnimUtil.getInstance().getScaleAnim(400, 400 + i * 100));
        }
    }
}
