package com.example.developerandroidx.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvHolder;
import com.example.developerandroidx.model.OperatorItemBean;

import butterknife.BindView;

/**
 * Date: 2020/5/5 17:25
 * 参考:
 * 描述:
 */
public class OperatorRcvHolder extends BaseRcvHolder<OperatorItemBean> {

    @BindView(R.id.tv_operator_type)
    TextView tv_operator_type;
    @BindView(R.id.tv_operator)
    TextView tv_operator;
    @BindView(R.id.tv_operator_desc)
    TextView tv_operator_desc;

    public OperatorRcvHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void setData(@NonNull OperatorItemBean data, int position) {

        if (data.isShowOperatorType) {
            tv_operator_type.setVisibility(View.VISIBLE);
        } else {
            tv_operator_type.setVisibility(View.GONE);
        }

        if (data.isEnd) {
            tv_operator.setBackgroundResource(R.drawable.shape_operator_border_corner_left);
            tv_operator_desc.setBackgroundResource(R.drawable.shape_operator_border_corner_right);
        } else {
            tv_operator.setBackgroundResource(R.drawable.shape_operator_border);
            tv_operator_desc.setBackgroundResource(R.drawable.shape_operator_border);
        }
        tv_operator_type.setText(data.operatorType);
        tv_operator.setText(data.operator);
        tv_operator_desc.setText(data.description);
    }
}
