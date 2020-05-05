package com.example.developerandroidx.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.base.BaseRcvHolder;
import com.example.developerandroidx.bean.OperatorItemBean;

import java.util.List;

/**
 * Date: 2020/5/5 17:24
 * 参考:
 * 描述: 操作符列表
 */
public class OperatorRcvAdapter extends BaseRcvAdapter<OperatorItemBean> {
    /**
     * 构造方法，初始化数据
     *
     * @param mList
     */
    public OperatorRcvAdapter(List<OperatorItemBean> mList) {
        super(mList);
    }

    @Override
    protected int bindItemLayout(int viewType) {
        return R.layout.item_operator;
    }

    @NonNull
    @Override
    protected BaseRcvHolder<OperatorItemBean> bindHolder(@NonNull View v, int viewType) {
        return new OperatorRcvHolder(v);
    }
}
