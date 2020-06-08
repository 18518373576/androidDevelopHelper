package com.example.developerandroidx.adapter.quickAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.developerandroidx.R;

import org.jetbrains.annotations.NotNull;

/**
 * 作者： zjf 2020/6/8 4:25 PM
 * 参考：
 * 描述：
 */
public class RxJavaSampleRcvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RxJavaSampleRcvAdapter() {
        super(R.layout.item_rxjava_sample);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, String item) {
        holder.setText(R.id.tv_rxjava_sample, item);
    }
}
