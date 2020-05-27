package com.example.developerandroidx.ui.android.animation.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

import butterknife.BindView;

/**
 * Date: 2020/5/27 15:36
 * 参考:
 * 描述: 为布局更改添加动画
 */
public class LayoutTransitionDialog implements FunctionDialogInterface {

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_layout_transition, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                LinearLayout ll_content = rootView.findViewById(R.id.ll_content);
                ImageView iv_add = rootView.findViewById(R.id.iv_add);
                iv_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ll_content.getChildCount() > 4) {
                            iv_add.startAnimation(AnimUtil.getInstance().getShakeAnim());
                            return;
                        }
                        View view = View.inflate(context, R.layout.item_layout_transition, null);
                        view.findViewById(R.id.iv_remove).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ll_content.removeView(view);
                            }
                        });
                        ll_content.addView(view, 0);
                    }
                });
            }
        });
    }
}
