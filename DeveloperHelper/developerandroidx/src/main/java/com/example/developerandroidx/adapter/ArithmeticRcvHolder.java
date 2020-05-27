package com.example.developerandroidx.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvHolder;

import butterknife.BindView;

/**
 * Date: 2020/5/27 12:23
 * 参考:
 * 描述:
 */
public class ArithmeticRcvHolder extends BaseRcvHolder<String> {

    @BindView(R.id.tv_arithmetic)
    TextView tv_arithmetic;

    /**
     * 构造方法，执行初始化操作
     *
     * @param itemView
     */
    public ArithmeticRcvHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void setData(@NonNull String data, int position) {
        tv_arithmetic.setText(data);
    }
}
