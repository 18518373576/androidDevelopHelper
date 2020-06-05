package com.example.developerandroidx.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.base.BaseRcvHolder;
import com.example.developerandroidx.model.BlogListBean;

import java.util.List;

/**
 * @作者： zjf 2020/6/5 4:19 PM
 * @参考：
 * @描述： {@link com.example.developerandroidx.ui.android.httpRequest.HttpRequestActivity}
 */
public class BlogListRcvAdapter extends BaseRcvAdapter<BlogListBean.Model> {
    /**
     * 构造方法，初始化数据
     *
     * @param mList
     */
    public BlogListRcvAdapter(List<BlogListBean.Model> mList) {
        super(mList);
    }

    @Override
    protected int bindItemLayout(int viewType) {
        return R.layout.item_blog_list;
    }

    @NonNull
    @Override
    protected BaseRcvHolder<BlogListBean.Model> bindHolder(@NonNull View v, int viewType) {
        return new BlogListRcvHolder(v);
    }
}
