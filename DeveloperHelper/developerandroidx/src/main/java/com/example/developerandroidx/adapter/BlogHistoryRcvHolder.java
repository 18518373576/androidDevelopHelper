package com.example.developerandroidx.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvHolder;
import com.example.developerandroidx.model.HistoryBlogBean;

import butterknife.BindView;

/**
 * 作者： zjf 2020/6/6 11:01 AM
 * 参考：
 * 描述：
 */
public class BlogHistoryRcvHolder extends BaseRcvHolder<HistoryBlogBean.Model> {

    @BindView(R.id.tv_shared_time)
    TextView tv_shared_time;
    @BindView(R.id.tv_blog_title)
    TextView tv_blog_title;

    /**
     * 构造方法，执行初始化操作
     *
     * @param itemView
     */
    public BlogHistoryRcvHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void setData(@NonNull HistoryBlogBean.Model data, int position) {
        tv_shared_time.setText(data.niceDate.split(" ")[0]);
        tv_blog_title.setText(data.title);
    }
}
