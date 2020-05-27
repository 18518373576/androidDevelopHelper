package com.example.developerandroidx.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Date: 2020/5/5 11:35
 * 参考:
 * 描述: recyclerView基础apapter
 */
public abstract class BaseRcvAdapter<T> extends RecyclerView.Adapter<BaseRcvHolder<T>> {

    protected List<T> mList;

    protected OnRecyclerViewItemClickListner itemClickListner;

    /**
     * 通知数据变化
     *
     * @param mList
     */
    public void notifyDataChanged(List<T> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    /**
     * 构造方法，初始化数据
     *
     * @param mList
     */
    public BaseRcvAdapter(List<T> mList) {
        super();
        this.mList = mList;
    }

    /**
     * 遍历所有 {@link BaseRcvHolder} ,释放资源
     *
     * @param recyclerView
     */
    public static void releaseAllHolder(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return;
        }
        for (int i = recyclerView.getChildCount() - 1; i > 0; i--) {
            View view = recyclerView.getChildAt(i);
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            if (viewHolder instanceof BaseRcvHolder) {
                ((BaseRcvHolder) viewHolder).onRelease();
            }
        }
    }

    /**
     * 创建{@link BaseRcvHolder}
     *
     * @param parent   父容器
     * @param viewType 布局类型
     * @return {@link BaseRcvHolder}
     */
    @NonNull
    @Override
    public BaseRcvHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(bindItemLayout(viewType), parent, false);
        BaseRcvHolder<T> holder = bindHolder(view, viewType);

        holder.setOnItemClickListener((v, position) -> {
            if (itemClickListner != null && mList.size() > 0) {
                itemClickListner.onItemClick(v, viewType, mList.get(position), position);
            }
        });
        return holder;
    }

    /**
     * 绑定数据
     *
     * @param holder   {@link BaseRcvHolder}
     * @param position 在RecyclerView中的位置
     */
    @Override
    public void onBindViewHolder(@NonNull BaseRcvHolder<T> holder, int position) {
        holder.setData(mList.get(position), position);
    }

    /**
     * 返回数据总个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 获取数据集合
     *
     * @return
     */
    public List<T> getList() {
        return mList;
    }

    /**
     * 获取RecyclerView某个位置上的item数据
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    /**
     * 绑定布局文件，子类实现
     *
     * @param viewType
     * @return item的布局文件id
     */
    protected abstract int bindItemLayout(int viewType);

    /**
     * 绑定adapter的viewHolder，让子类实现用以提供 {@link BaseRcvHolder}
     *
     * @param v        用于展示的 {@link View}
     * @param viewType 布局类型
     * @return {@link BaseRcvHolder}
     */
    @NonNull
    protected abstract BaseRcvHolder<T> bindHolder(@NonNull View v, int viewType);

    /**
     * 设置点item击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListner listener) {
        this.itemClickListner = listener;
    }

    /**
     * item点击事件
     *
     * @param <T>
     */
    public interface OnRecyclerViewItemClickListner<T> {

        /**
         * item被点击
         *
         * @param v        被点击的view
         * @param viewType 布局类型
         * @param data     数据
         * @param position 在RecyclerView中的位置
         */
        void onItemClick(@NonNull View v, int viewType, @NonNull T data, int position);
    }
}
