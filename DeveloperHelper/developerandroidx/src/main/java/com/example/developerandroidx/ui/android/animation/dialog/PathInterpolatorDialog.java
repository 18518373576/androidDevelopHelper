package com.example.developerandroidx.ui.android.animation.dialog;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Path;
import android.view.View;
import android.widget.Button;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/24 7:35
 * 参考:
 * 描述: 使用PathInterpolator插值器，是动画路径变得曲线
 */
public class PathInterpolatorDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {

        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_path_interpolator, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                View view = rootView.findViewById(R.id.v_path);
                Button btn_start = rootView.findViewById(R.id.btn_start);

                btn_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Path path = new Path();
                        int width = PixelTransformForAppUtil.getDiaplayWidth();
                        float top = view.getY();
                        float offset = view.getWidth();
                        //各参数含义：https://blog.csdn.net/whyrjj3/article/details/7940385
                        //此处含义为:left:从屏幕左侧偏移为0 top:以view所处的位置的y点 right:以屏幕宽度向左偏移view的宽度，bottom:与宽度相等
                        //为坐标画圆，半径为(right-left)/2，startAngle动画从所画圆的270°处开始，沿着圆向左运行270°
                        path.arcTo(0, top, width - offset, width - offset + top, 270f, -359.99f, true);
                        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.X, View.Y, path);
                        animator.setDuration(5000);
                        animator.start();
                    }
                });
            }
        });
    }
}
