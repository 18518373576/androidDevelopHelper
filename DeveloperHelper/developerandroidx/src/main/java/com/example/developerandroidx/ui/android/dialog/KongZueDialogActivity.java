package com.example.developerandroidx.ui.android.dialog;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.v3.BottomMenu;
import com.kongzue.dialog.v3.MessageDialog;
import com.kongzue.dialog.v3.TipDialog;

import butterknife.OnClick;

public class KongZueDialogActivity extends BaseActivity {

    private String[] bottomMenus;

    @Override
    protected int bindLayout() {
        return R.layout.activity_kong_zue_dialog;
    }

    @Override
    protected void initView() {
        super.initView();
        actionBar.setTitle("Kongzue Dialog");
    }

    @Override
    protected void initData() {
        super.initData();
        bottomMenus = new String[]{"菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6"};
    }

    @OnClick({R.id.btn_message_dialog, R.id.btn_bottom_menu, R.id.btn_custom_dialog
            , R.id.btn_full_screen_dialog, R.id.btn_input_dialog, R.id.btn_Notifiction
            , R.id.btn_share_dialog, R.id.btn_tip_dialog, R.id.btn_wait_dialog})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_message_dialog:
                MessageDialog.show((AppCompatActivity) context, "提示", "明天下雨，记得带伞！");
                break;
            case R.id.btn_bottom_menu:
                BottomMenu.show((AppCompatActivity) context, bottomMenus, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        TipDialog.show((AppCompatActivity) context, text, TipDialog.TYPE.SUCCESS);
                    }
                });
                break;
            case R.id.btn_custom_dialog:

                break;
            case R.id.btn_full_screen_dialog:

                break;
            case R.id.btn_input_dialog:

                break;
            case R.id.btn_Notifiction:

                break;
            case R.id.btn_share_dialog:

                break;
            case R.id.btn_tip_dialog:

                break;
            case R.id.btn_wait_dialog:

                break;
        }
    }
}
