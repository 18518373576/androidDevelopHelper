package com.example.developerandroidx.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvHolder;
import com.example.developerandroidx.model.FunctionItemBean;

import butterknife.BindView;

/**
 * Date: 2020/5/5 14:34
 * 参考:
 * 描述: 首页功能选项列表的ViewHolder
 */
public class FunctionRcvHolder extends BaseRcvHolder<FunctionItemBean> {

    @BindView(R.id.tv_item_name)
    TextView tv_item_name;
    @BindView(R.id.iv_item_icon)
    ImageView iv_item_icon;

    public FunctionRcvHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void setData(@NonNull FunctionItemBean data, int position) {
        tv_item_name.setText(data.itemName);
        iv_item_icon.setImageResource(data.itemIconId);
    }
}
