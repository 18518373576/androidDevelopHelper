package com.example.developerandroidx.view.navigationView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.developerandroidx.R;
import com.example.developerandroidx.view.navigationView.bean.NavigationBean;
import com.example.developerandroidx.view.navigationView.utils.PixelTransformUtil;

/**
 * @作者： zjf 2020/5/11 14:25
 * @参考：
 * @描述：
 */
public class NavigationItem extends RelativeLayout {

    private NavigationBean navigationBean;
    private Context context;
    private ImageView imageView;
    private TextView textView;
    private TextView notify;


    public NavigationItem(Context context, NavigationBean navigationBean) {
        super(context);
        this.navigationBean = navigationBean;
        this.context = context;

        initView();
    }

    @SuppressLint("ResourceType")
    private void initView() {
        //获取系统的点击水波纹效果
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackgroundBorderless, typedValue, true);
        this.setBackgroundResource(typedValue.resourceId);
        //此控件默认NavigationView高度为50dp，根据高度分配item展示
        this.setPadding(0, PixelTransformUtil.dip2px(context, 5), 0, PixelTransformUtil.dip2px(context, 5));
        LayoutParams paramsIV = new LayoutParams(PixelTransformUtil.dip2px(context, 23), PixelTransformUtil.dip2px(context, 23));
        paramsIV.addRule(CENTER_HORIZONTAL);
        imageView = new ImageView(context);
        imageView.setId(1001);
        imageView.setLayoutParams(paramsIV);
        imageView.setImageResource(navigationBean.navigationMipmapId);

        LayoutParams paramsTV = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTV.addRule(CENTER_HORIZONTAL);
        paramsTV.addRule(ALIGN_PARENT_BOTTOM);
        textView = new TextView(context);
        textView.setLayoutParams(paramsTV);
        textView.setTextSize(12);
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        textView.setText(navigationBean.navigationName);


        this.addView(imageView);
        this.addView(textView);
    }

    public void setCheckColor(int colorID) {

        textView.setTextColor(context.getResources().getColor(colorID));
        imageView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, colorID)));
    }

    /**
     * 显示通知
     */
    public void showNotify() {

        showNotify(-1);
    }

    /**
     * 显示通知，和通知条数
     *
     * @param notifyNum
     */

    public void showNotify(int notifyNum) {
        if (notify != null) {
            this.removeView(notify);
        }
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(90);//设置4个角的弧度
        drawable.setColor(Color.rgb(255, 69, 0));// 设置颜色

        if (notifyNum < 0) {
            LayoutParams paramsNotofy = new LayoutParams(PixelTransformUtil.dip2px(context, 5), PixelTransformUtil.dip2px(context, 5));
            paramsNotofy.addRule(RIGHT_OF, imageView.getId());
            paramsNotofy.addRule(ALIGN_PARENT_TOP);
            notify = new TextView(context);
            notify.setLayoutParams(paramsNotofy);
            notify.setBackground(drawable);
            this.addView(notify);
        } else {
            LayoutParams paramsNotofy = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsNotofy.addRule(RIGHT_OF, imageView.getId());
            paramsNotofy.addRule(ALIGN_PARENT_TOP);
            notify = new TextView(context);
            int padding = PixelTransformUtil.dip2px(context, 3);
            notify.setPadding(padding, 0, padding, 0);
            notify.setLayoutParams(paramsNotofy);
            notify.setBackground(drawable);
            notify.setTextSize(8);
            notify.setTextColor(Color.rgb(255, 255, 255));
            notify.setText(String.valueOf(notifyNum));
            this.addView(notify);
        }
    }
}
