package com.example.developerandroidx.view.navigationView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
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
 * @描述： 导航栏的底部itemView，使用RelativeLayout
 */
public class NavigationItem extends RelativeLayout {

    private NavigationBean navigationBean;
    private Context context;
    private ImageView imageView;
    private TextView textView;
    private TextView notify;
    private ImageView imageViewForeground;


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
        //背景view
        imageView = new ImageView(context);
        imageView.setId(1001);//设置id，为了添加通知的时候paramsNotofy.addRule(RIGHT_OF, imageView.getId());
        imageView.setLayoutParams(paramsIV);
        imageView.setImageResource(navigationBean.navigationMipmapId);

        //前景view，用于显示动画
        imageViewForeground = new ImageView(context);
        imageViewForeground.setId(1002);
        imageViewForeground.setLayoutParams(paramsIV);
        imageViewForeground.setImageResource(navigationBean.navigationMipmapId);
        imageViewForeground.setVisibility(INVISIBLE);


        LayoutParams paramsTV = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTV.addRule(CENTER_HORIZONTAL);
        paramsTV.addRule(ALIGN_PARENT_BOTTOM);
        textView = new TextView(context);
        textView.setLayoutParams(paramsTV);
        textView.setTextSize(12);
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        textView.setText(navigationBean.navigationName);


        this.addView(imageView);
        this.addView(imageViewForeground);

        this.addView(textView);
    }

    /**
     * 加载的时候设置默认的时候调的方法，不加动画
     *
     * @param isChecked
     * @param checkedColorId
     * @param unCheckedColorId
     */
    public void setCheckedDefult(boolean isChecked, int checkedColorId, int unCheckedColorId) {
        setClickable(!isChecked);

        imageView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, unCheckedColorId)));
        imageViewForeground.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, checkedColorId)));

        if (isChecked) {
            textView.setTextColor(context.getResources().getColor(checkedColorId));
            imageViewForeground.setVisibility(View.VISIBLE);
        } else {
            textView.setTextColor(context.getResources().getColor(unCheckedColorId));
            imageViewForeground.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 点击的时候，设置选中效果，单独设置是为了避免动画在view还没渲染的时候执行抛出异常
     * java.lang.IllegalStateException: Cannot start this animator on a detached view!
     *
     * @param isChecked
     * @param checkedColorId
     * @param unCheckedColorId
     */
    public void setChecked(boolean isChecked, int checkedColorId, int unCheckedColorId) {
        setClickable(!isChecked);

        imageView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, unCheckedColorId)));
        imageViewForeground.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, checkedColorId)));
        //获取视图的中心
        int cx = imageViewForeground.getWidth();
        int cy = imageViewForeground.getHeight();
        //计算最终的圆的半径
        float radius = (float) Math.hypot(cx, cy);

        if (isChecked) {
            textView.setTextColor(context.getResources().getColor(checkedColorId));
            Animator animator = ViewAnimationUtils.createCircularReveal(imageViewForeground, 0, cy, 0f, radius);
            animator.setDuration(150);
            imageViewForeground.setVisibility(View.VISIBLE);
            animator.start();

        } else {
            textView.setTextColor(context.getResources().getColor(unCheckedColorId));
            Animator animator = ViewAnimationUtils.createCircularReveal(imageViewForeground, 0, cy, radius, 0f);
            animator.setDuration(150);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    imageViewForeground.setVisibility(INVISIBLE);
                }
            });
            animator.start();
        }
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
