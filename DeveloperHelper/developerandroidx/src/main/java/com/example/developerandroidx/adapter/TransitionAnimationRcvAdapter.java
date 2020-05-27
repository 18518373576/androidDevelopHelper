package com.example.developerandroidx.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.base.BaseRcvHolder;
import com.example.developerandroidx.model.TransitionAnimationItemBean;

import java.util.List;

/**
 * Date: 2020/5/10 13:43
 * 参考:
 * 描述: activity过度动画界面adapter
 */
public class TransitionAnimationRcvAdapter extends BaseRcvAdapter<TransitionAnimationItemBean> {

    /**
     * 构造方法，初始化数据
     *
     * @param mList
     */
    public TransitionAnimationRcvAdapter(List<TransitionAnimationItemBean> mList) {
        super(mList);
    }

    @Override
    protected int bindItemLayout(int viewType) {
        return R.layout.item_transition_animation;
    }

    @NonNull
    @Override
    protected BaseRcvHolder<TransitionAnimationItemBean> bindHolder(@NonNull View v, int viewType) {
        return new TransitionAnimationRcvHolder(v);
    }
}
