package com.example.developerandroidx.ui.android.fragment;

import android.content.Context;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/31 19:17
 * 参考:
 * 描述:
 */
public class FragmentDescDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, new DialogUtils.OnEsvDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content) {
                title.setText("Fragment概述");
                esv_content.addText("生命周期", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                esv_content.addImage(R.mipmap.fragment_lifecycle, 500);

            }
        });
    }
}
