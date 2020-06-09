package com.example.developerandroidx.view.loadingView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 2020/6/6 3:14 PM
 * 参考：
 * 描述：页面加载页面，目标提供功能
 * onLoading()正在加载，显示加载动画
 * loadingSuc()加载成功，隐藏LoadingView展示内容View
 * loadingFail()加载失败，展示加载失败提醒
 * noData()无数据
 */
public class LoadingPage extends RelativeLayout {

    private Context context;

    public LoadingPage(Context context) {
        this(context, null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        this.setVisibility(GONE);
        this.setBackgroundColor(Color.rgb(255, 255, 255));
    }

    /**
     * 加载失败
     *
     * @param contentView 内容展示的view，失败后隐藏
     * @imageId 加载失败展示的图片ID
     */
    public void loadingFail(View contentView, int imageId) {
        contentView.setVisibility(GONE);
        this.setVisibility(VISIBLE);
        ImageView loadingFailImage = new ImageView(context);
        loadingFailImage.setImageResource(imageId);
        LayoutParams params = new LayoutParams(PixelTransformForAppUtil.dip2px(100), PixelTransformForAppUtil.dip2px(100));
        params.addRule(CENTER_IN_PARENT);
        this.addView(new LoadingView(context), params);
    }

    public void onLoading() {

    }

    public void loadingSuc() {
    }

    public void noData() {
    }
}
