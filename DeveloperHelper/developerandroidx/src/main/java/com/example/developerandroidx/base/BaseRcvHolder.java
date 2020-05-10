package com.example.developerandroidx.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Date: 2020/5/5 11:39
 * 参考:
 * 描述: recyclerView的apater的基础holder
 */
public abstract class BaseRcvHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Unbinder unbinder;
    private OnItemClickListener onItemClickListener;

    /**
     * 构造方法，执行初始化操作
     *
     * @param itemView
     */
    public BaseRcvHolder(@NonNull View itemView) {
        super(itemView);
        //设置item点击事件
        itemView.setOnClickListener(this);
        //绑定ButterKnife
        unbinder = ButterKnife.bind(this, itemView);
    }

    /**
     * 设置数据
     *
     * @param data     数据
     * @param position 在RecyclerView中的位置
     */
    protected abstract void setData(@NonNull T data, int position);

    /**
     * 在 Activity 的 onDestroy 中使用 {@link BaseRcvAdapter#releaseAllHolder(RecyclerView)} 方法 (super.onDestroy() 之前)
     * {@link BaseRcvHolder#onRelease()} 才会被调用, 可以在此方法中释放一些资源
     */
    protected void onRelease() {
        //释放butterKnife
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    public void onClick(View v) {

        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    /**
     * item点击事件回调接口
     */
    interface OnItemClickListener {
        /**
         * item被点击
         *
         * @param v        被点击的view
         * @param position 在RecyclerView中的位置
         */
        void onItemClick(View v, int position);
    }
}
