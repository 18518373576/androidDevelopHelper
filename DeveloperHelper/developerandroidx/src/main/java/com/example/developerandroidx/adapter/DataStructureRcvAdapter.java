package com.example.developerandroidx.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.base.BaseRcvHolder;

import java.util.List;

/**
 * Date: 2020/5/28 14:44
 * 参考:
 * 描述:
 */
public class DataStructureRcvAdapter extends BaseRcvAdapter<String> {
    /**
     * 构造方法，初始化数据
     *
     * @param mList
     */
    public DataStructureRcvAdapter(List<String> mList) {
        super(mList);
    }

    @Override
    protected int bindItemLayout(int viewType) {
        return R.layout.item_data_structure;
    }

    @NonNull
    @Override
    protected BaseRcvHolder<String> bindHolder(@NonNull View v, int viewType) {
        return new DataStructureRcvHolder(v);
    }
}
