package com.example.developerandroidx.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvHolder;

import butterknife.BindView;

/**
 * Date: 2020/5/28 14:43
 * 参考:
 * 描述:
 */
public class DataStructureRcvHolder extends BaseRcvHolder<String> {

    @BindView(R.id.tv_data_structure)
    TextView tv_data_structure;

    /**
     * 构造方法，执行初始化操作
     *
     * @param itemView
     */
    public DataStructureRcvHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void setData(@NonNull String data, int position) {
        tv_data_structure.setText(data);
    }
}
