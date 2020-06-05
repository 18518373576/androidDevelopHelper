package com.example.developerandroidx.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvHolder;
import com.example.developerandroidx.model.BlogListBean;

import butterknife.BindView;

/**
 * @作者： zjf 2020/6/5 4:31 PM
 * @参考：
 * @描述： {@link BlogListRcvAdapter}
 */
public class BlogListRcvHolder extends BaseRcvHolder<BlogListBean.Model> {
    @BindView(R.id.tv_blog)
    TextView tv_blog;

    /**
     * 构造方法，执行初始化操作
     *
     * @param itemView
     */
    public BlogListRcvHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void setData(@NonNull BlogListBean.Model data, int position) {
        tv_blog.setText(data.name);
    }
}
