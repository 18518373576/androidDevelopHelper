package com.example.developerandroidx.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.base.BaseRcvHolder;
import com.example.developerandroidx.model.HistoryBlogBean;

import java.util.List;

/**
 * 作者： zjf 2020/6/6 11:00 AM
 * 参考：
 * 描述：
 */
public class BlogHistoryRcvAdapter extends BaseRcvAdapter<HistoryBlogBean.Model> {
    /**
     * 构造方法，初始化数据
     *
     * @param mList
     */
    public BlogHistoryRcvAdapter(List<HistoryBlogBean.Model> mList) {
        super(mList);
    }

    @Override
    protected int bindItemLayout(int viewType) {
        return R.layout.item_blog_history_list;
    }

    @NonNull
    @Override
    protected BaseRcvHolder<HistoryBlogBean.Model> bindHolder(@NonNull View v, int viewType) {
        return new BlogHistoryRcvHolder(v);
    }
}
