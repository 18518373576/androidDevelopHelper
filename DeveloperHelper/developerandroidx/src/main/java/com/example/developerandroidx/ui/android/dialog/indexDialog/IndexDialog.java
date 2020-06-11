package com.example.developerandroidx.ui.android.dialog.indexDialog;

import android.content.Context;

import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.ui.android.dialog.KongZueDialogActivity;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.RouteUtil;

/**
 * 作者： zjf 2020/6/11 12:05 PM
 * 参考：
 * 描述：
 */
public class IndexDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showBottomMenu(context, new String[]{"Kongzue Dialog"}, new DialogUtils.OnItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                switch (text) {
                    case "Kongzue Dialog":
                        RouteUtil.goTo(context, RouteUtil.getDestination(KongZueDialogActivity.class));
                        break;
                }
            }
        });
    }
}
