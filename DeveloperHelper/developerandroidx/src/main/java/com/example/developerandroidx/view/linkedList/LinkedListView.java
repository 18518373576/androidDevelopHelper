package com.example.developerandroidx.view.linkedList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.developerandroidx.R;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.view.linkedList.nodeView.NodeView;

/**
 * Date: 2020/5/29 9:24
 * 参考:
 * 描述: 模拟链表数据结构
 */
public class LinkedListView extends HorizontalScrollView {
    private Context context;
    private LinearLayout ll_content;

    public LinkedListView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public LinkedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        //去掉滑块
        setHorizontalScrollBarEnabled(false);
        //去掉滑动到头部和底部的阴影效果
        setOverScrollMode(OVER_SCROLL_NEVER);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll_content = (LinearLayout) View.inflate(context, R.layout.content_linked_list, null);
        this.addView(ll_content, params);
    }

    /**
     * 添加节点
     *
     * @param index
     * @param data
     */
    public void addNode(int index, int data) {
        if (index > ll_content.getChildCount() || index < 0) {
            throw new RuntimeException("index不能viewGroup的Child数量");
        }
        NodeView nodeView = new NodeView(context);
        nodeView.setText(String.valueOf(data));
        ll_content.addView(nodeView, index);
    }

    /**
     * 删除节点
     *
     * @param index
     */
    public void removeNode(int index) {
        if (index > ll_content.getChildCount() || index < 0) {
            throw new RuntimeException("index不能viewGroup的Child数量");
        }

        ll_content.removeView(ll_content.getChildAt(index));
    }

    /**
     * 修改节点值
     *
     * @param index
     * @param data
     */
    public void editNode(int index, int data) {
        if (index > ll_content.getChildCount() || index < 0) {
            throw new RuntimeException("index不能viewGroup的Child数量");
        }
        if (ll_content.getChildCount() > 0) {
            NodeView currentNode = ((NodeView) ll_content.getChildAt(index));
            currentNode.setBackgroundResource(R.drawable.bg_circle_main_color);
            AnimUtil.getInstance().startScaleAnimator(currentNode, 300, 1f, 1.5f, 1f);
            currentNode.setText(String.valueOf(data));
        }
    }

    /**
     * 获取某个节点的值
     *
     * @param index
     * @return
     */
    public int getNode(int index) {
        if (index > ll_content.getChildCount() || index < 0) {
            throw new RuntimeException("index不能viewGroup的Child数量");
        }
        NodeView currentNode = ((NodeView) ll_content.getChildAt(index));
        currentNode.setBackgroundResource(R.drawable.bg_circle_main_color);
        AnimUtil.getInstance().startScaleAnimator(currentNode, 300, 1f, 1.5f, 1f);
        return Integer.parseInt(currentNode.getText().toString());
    }

    /**
     * 获取链表的长度
     *
     * @return
     */
    public int getNodeCount() {
        return ll_content.getChildCount();
    }

    /**
     * 清空链表
     */
    public void clrar() {
        ll_content.removeAllViews();
    }
}
