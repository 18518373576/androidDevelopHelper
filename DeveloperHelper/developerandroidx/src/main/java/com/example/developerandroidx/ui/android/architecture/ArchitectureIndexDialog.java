package com.example.developerandroidx.ui.android.architecture;

import android.content.Context;

import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;

/**
 * 作者： zjf 2020/6/11 12:14 PM
 * 参考：
 * 描述：
 */
public class ArchitectureIndexDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showBottomMenu(context, new String[]{"MVC", "MVP", "MVVM"},
                (text, index) -> {
                    DialogUtils.getInstance().showWarningTip(context, "developing");
                    switch (text) {
                        case "MVC":

                            break;
                        case "MVP":

                            break;
                        case "MVVM":

                            break;
                    }
                });
    }
}
