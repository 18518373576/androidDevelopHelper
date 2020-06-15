package com.example.developerandroidx.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.base.BaseRcvHolder;
import com.example.developerandroidx.model.ArithMeticModel;

import java.util.List;

/**
 * Date: 2020/5/27 12:21
 * 参考:
 * 描述:
 */
public class ArithmeticRcvAdapter extends BaseRcvAdapter<ArithMeticModel> {
    /**
     * 构造方法，初始化数据
     *
     * @param mList
     */
    public ArithmeticRcvAdapter(List<ArithMeticModel> mList) {
        super(mList);
    }

    @Override
    protected int bindItemLayout(int viewType) {
        return R.layout.item_arithmetic;
    }

    @NonNull
    @Override
    protected BaseRcvHolder<ArithMeticModel> bindHolder(@NonNull View v, int viewType) {
        return new ArithmeticRcvHolder(v);
    }
}
