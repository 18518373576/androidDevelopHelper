package com.example.developerandroidx.adapter.quickAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.developerandroidx.R;
import com.example.developerandroidx.model.HistoryBlogBean;

import org.jetbrains.annotations.NotNull;

/**
 * 作者： zjf 2020/6/6 8:47 PM
 * 参考：
 * 描述：
 */
public class BlogHistoryRcvAdapter extends BaseQuickAdapter<HistoryBlogBean.Model, BaseViewHolder> implements LoadMoreModule {


    public BlogHistoryRcvAdapter() {
        super(R.layout.item_blog_history_list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, HistoryBlogBean.Model item) {

        holder.setText(R.id.tv_shared_time, item.niceDate.split(" ")[0]);
        holder.setText(R.id.tv_blog_title, item.title);
    }
}
