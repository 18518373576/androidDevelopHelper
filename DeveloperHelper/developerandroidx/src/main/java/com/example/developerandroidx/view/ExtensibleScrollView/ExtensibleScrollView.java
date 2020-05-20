package com.example.developerandroidx.view.ExtensibleScrollView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.developerandroidx.utils.LogUtils;

/**
 * @作者： zjf 2020/5/15 11:16
 * @需求： 参考网页的文章展示，想要构建一个可扩展的scrollView可添加图片和文字，按照添加的顺序排列
 * @描述： 提供方法：addText(String text, InsertTextType textType, int colorId) addImage(int imageResourceId, int height_dp)
 */
public class ExtensibleScrollView extends ScrollView {

    private Context context;
    private LinearLayout contentLayout;
    private float Y = 0;

    /**
     * 可添加的文类型
     * TITLE_1一级标题
     * TITLE_2二级标题
     * BODY正文
     */
    public enum InsertTextType {
        TITLE_1, TITLE_2, BODY
    }

    public ExtensibleScrollView(Context context) {
        super(context);
        initView(context);
    }

    public ExtensibleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ExtensibleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        setOverScrollMode(OVER_SCROLL_NEVER);
        setVerticalScrollBarEnabled(false);
        ViewGroup.LayoutParams contentParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentLayout = new LinearLayout(context);
        int padding = PixelTransformUtil.dip2px(context, 10);
        contentLayout.setPadding(padding, padding, padding, padding);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        this.addView(contentLayout, contentParam);
    }

    /**
     * 添加内容，带着意图，如点击可以跳转
     *
     * @param intent
     */
    public void addBodyWithIntent(String text, int colorId, Intent intent) {
        TextView body = new TextView(context);
        body.setText("        " + text);
        body.setTextSize(14);
        int padding = PixelTransformUtil.dip2px(context, 5);
        body.setPadding(0, padding, 0, padding);
        //add增加的间距，mult增加的间距倍数
        body.setLineSpacing(0, 1.5f);
        body.setTextColor(context.getResources().getColor(colorId));
        body.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(intent);
            }
        });
        contentLayout.addView(body);
    }

    /**
     * 添加文本
     *
     * @param text     文本内容
     * @param textType 文本类型
     * @param colorId  字体颜色
     */
    public void addText(String text, InsertTextType textType, int colorId) {
        switch (textType) {
            case BODY: {
                TextView body = new TextView(context);
                body.setTextIsSelectable(true);
                body.setText("        " + text);
                body.setTextSize(14);
                int padding = PixelTransformUtil.dip2px(context, 5);
                body.setPadding(0, padding, 0, padding);
                //add增加的间距，mult增加的间距倍数
                body.setLineSpacing(0, 1.5f);
                body.setTextColor(context.getResources().getColor(colorId));

                contentLayout.addView(body);
            }
            break;
            case TITLE_1: {
                TextView title_1 = new TextView(context);
                title_1.setText("    " + text);
                title_1.setTextSize(18);
                //设置字体加粗
                title_1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                //add增加的间距，mult增加的间距倍数
                title_1.setLineSpacing(0, 1.5f);
                int padding = PixelTransformUtil.dip2px(context, 5);
                title_1.setPadding(0, padding, 0, padding);
                title_1.setTextColor(context.getResources().getColor(colorId));

                contentLayout.addView(title_1);
            }
            break;
            case TITLE_2: {
                TextView title_2 = new TextView(context);
                title_2.setText("    " + text);
                title_2.setTextSize(16);
                //设置字体加粗
                title_2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                //add增加的间距，mult增加的间距倍数
                title_2.setLineSpacing(0, 1.5f);
                int padding = PixelTransformUtil.dip2px(context, 5);
                title_2.setPadding(0, padding, 0, padding);
                title_2.setTextColor(context.getResources().getColor(colorId));

                contentLayout.addView(title_2);
            }
            break;
        }
    }

    /**
     * 添加图片
     *
     * @param imageResourceId 图片的资源id
     * @param height_dp       图片的高度，宽度默认MATCH_PARENT
     */
    public void addImage(int imageResourceId, int height_dp) {

        ImageView image = new ImageView(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, PixelTransformUtil.dip2px(context, height_dp));
        params.gravity = Gravity.CENTER_HORIZONTAL;
        int margin = PixelTransformUtil.dip2px(context, 10);
        params.setMargins(0, margin, 0, margin);
        image.setImageResource(imageResourceId);

        contentLayout.addView(image, params);
    }

    public interface OnScrollChangedListener {
        /**
         * 上滑
         *
         * @param isToTop 是否到顶,未实现，需要的时候再实现
         */
        void toUp(boolean isToTop);

        /**
         * 下滑
         *
         * @param isToBottom 是否到底
         */
        void toDown(boolean isToBottom);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        LogUtils.e("滑动监听", motionEvent.getY() + "");
                        Y = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        Y = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtils.e("滑动监听", motionEvent.getY() + "");
                        if ((Y - motionEvent.getY()) < -100) {
                            onScrollChangedListener.toDown(false);
                        } else if ((Y - motionEvent.getY()) > 100) {
                            onScrollChangedListener.toUp(false);
                        }
                        break;
                }
                return false;
            }
        });
    }
}
