package com.example.developerandroidx.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvHolder;
import com.example.developerandroidx.model.TransitionAnimationItemBean;

import butterknife.BindView;

/**
 * Date: 2020/5/10 14:05
 * 参考:
 * 描述:
 */
public class TransitionAnimationRcvHolder extends BaseRcvHolder<TransitionAnimationItemBean> {

    @BindView(R.id.iv_landscape)
    ImageView iv_landscape;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;

    /**
     * 构造方法，执行初始化操作
     *
     * @param itemView
     */
    public TransitionAnimationRcvHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void setData(@NonNull TransitionAnimationItemBean data, int position) {

        iv_landscape.setImageResource(data.mipmapId);
        tv_title.setText(data.title);
        tv_content.setText(data.content);
    }
}
