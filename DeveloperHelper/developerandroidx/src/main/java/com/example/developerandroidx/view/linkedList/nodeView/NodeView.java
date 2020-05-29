package com.example.developerandroidx.view.linkedList.nodeView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.example.developerandroidx.R;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * Date: 2020/5/28 15:28
 * 参考:
 * 描述: 数据结构中的节点
 */
public class NodeView extends androidx.appcompat.widget.AppCompatTextView {
    Context context;

    public NodeView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public NodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public NodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        setBackgroundResource(R.drawable.bg_circle_red_color);
        setGravity(Gravity.CENTER);
        setTextColor(context.getResources().getColor(R.color.white));
        setTextSize(14);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PixelTransformForAppUtil.dip2px(45), PixelTransformForAppUtil.dip2px(45));
        int margin = PixelTransformForAppUtil.dip2px(10);
        params.setMargins(margin, margin, margin, margin);
        setLayoutParams(params);

    }
}
