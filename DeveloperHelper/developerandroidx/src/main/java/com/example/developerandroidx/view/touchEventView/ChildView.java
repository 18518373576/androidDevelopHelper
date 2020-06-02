package com.example.developerandroidx.view.touchEventView;

import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;

import com.example.developerandroidx.R;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * @作者： zjf 2020/6/2 5:14 PM
 * @参考：
 * @描述：
 */
public class ChildView extends androidx.appcompat.widget.AppCompatTextView {

    public boolean getCvTouchReturn() {
        return cvTouchReturn;
    }

    public void setCvTouchReturn(boolean cvTouchReturn) {
        this.cvTouchReturn = cvTouchReturn;
    }

    //onTouchEvent返回值
    private boolean cvTouchReturn = true;

    public ChildView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        setBackgroundResource(R.drawable.bg_round_corner_gray_unpressed);
        setTextColor(context.getResources().getColor(R.color.textColor));
        int padding = PixelTransformForAppUtil.dip2px(10);
        setPadding(padding, padding, padding, padding);
        setGravity(Gravity.CENTER_VERTICAL);
        setTextSize(14);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setText("ChildView onTouchEvent：ACTION_DOWN");
                break;
//            case MotionEvent.ACTION_MOVE:
//                setText("ChildView：ACTION_MOVE");
//                break;
            case MotionEvent.ACTION_UP:
                setText("ChildView onTouchEvent：ACTION_UP");
                break;
        }
        return cvTouchReturn;
    }
}
