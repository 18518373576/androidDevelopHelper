package com.example.developerandroidx.ui.android.dialog;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.interfaces.OnDismissListener;
import com.kongzue.dialog.interfaces.OnInputDialogButtonClickListener;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.v3.BottomMenu;
import com.kongzue.dialog.v3.CustomDialog;
import com.kongzue.dialog.v3.FullScreenDialog;
import com.kongzue.dialog.v3.InputDialog;
import com.kongzue.dialog.v3.MessageDialog;
import com.kongzue.dialog.v3.Notification;
import com.kongzue.dialog.v3.ShareDialog;
import com.kongzue.dialog.v3.TipDialog;
import com.kongzue.dialog.v3.WaitDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * KongZueDialog功能展示
 */
public class KongZueDialogActivity extends BaseActivity {

    private String[] bottomMenus;
    private String[] messageDialogs;

    @Override
    protected int bindLayout() {
        return R.layout.activity_kong_zue_dialog;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Kongzue Dialog");
    }

    @Override
    protected void initData() {
        super.initData();
        bottomMenus = new String[]{"菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6"};
        messageDialogs = new String[]{"dialog defult", "with okButton", "okButton and cancelButton"};
    }

    @OnClick({R.id.btn_message_dialog, R.id.btn_bottom_menu, R.id.btn_custom_dialog
            , R.id.btn_full_screen_dialog, R.id.btn_input_dialog, R.id.btn_notifiction
            , R.id.btn_share_dialog, R.id.btn_tip_dialog, R.id.btn_wait_dialog})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_message_dialog:
                BottomMenu.show((AppCompatActivity) context, messageDialogs, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        switch (text) {
                            case "dialog defult":
                                MessageDialog.show((AppCompatActivity) context, "提示", "明天下雨，记得带伞！");
                                break;
                            case "with okButton":
                                MessageDialog.show((AppCompatActivity) context, "提示", "明天下雨，记得带伞！", "知道了").
                                        setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                                            @Override
                                            public boolean onClick(BaseDialog baseDialog, View v) {
                                                baseDialog.doDismiss();
                                                return false;
                                            }
                                        });
                                break;
                            case "okButton and cancelButton":
                                MessageDialog.show((AppCompatActivity) context, "提示", "明天下雨，记得带伞！", "带伞", "不带伞").
                                        setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                                            @Override
                                            public boolean onClick(BaseDialog baseDialog, View v) {
                                                baseDialog.doDismiss();
                                                return false;
                                            }
                                        }).setOnCancelButtonClickListener(new OnDialogButtonClickListener() {
                                    @Override
                                    public boolean onClick(BaseDialog baseDialog, View v) {
                                        baseDialog.doDismiss();
                                        return false;
                                    }
                                });
                                break;
                        }
                    }
                });
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
                CustomDialog.show((AppCompatActivity) context, R.layout.activity_dialog_testctivity, new CustomDialog.OnBindView() {
                    @Override
                    public void onBind(CustomDialog dialog, View v) {

                    }
                });
                break;
            case R.id.btn_full_screen_dialog:
                FullScreenDialog.show((AppCompatActivity) context, R.layout.activity_kong_zue_dialog, new FullScreenDialog.OnBindView() {
                    @Override
                    public void onBind(FullScreenDialog dialog, View rootView) {

                    }
                });
                break;
            case R.id.btn_input_dialog:
                InputDialog.show((AppCompatActivity) context, "请输入", "输入信息").setOnOkButtonClickListener(new OnInputDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v, String inputStr) {
                        TipDialog.show((AppCompatActivity) context, inputStr, TipDialog.TYPE.SUCCESS);
                        return false;
                    }
                });
                break;
            case R.id.btn_notifiction:
                //api 29不提示弹窗，修改为28即可
                Notification.show(context, "提示", "提示信息", R.mipmap.ic_launcher);
                break;
            case R.id.btn_share_dialog:
                List<ShareDialog.Item> list = new ArrayList<>();
                list.add(new ShareDialog.Item(context, R.mipmap.navigation_android, "Android"));
                list.add(new ShareDialog.Item(context, R.mipmap.navigation_java, "Java"));
                list.add(new ShareDialog.Item(context, R.mipmap.navigation_widget, "Widget"));
                ShareDialog.show((AppCompatActivity) context, list, new ShareDialog.OnItemClickListener() {
                    @Override
                    public boolean onClick(ShareDialog shareDialog, int index, ShareDialog.Item item) {
                        return false;
                    }
                });
                break;
            case R.id.btn_tip_dialog:
                TipDialog.show((AppCompatActivity) context, "Error", TipDialog.TYPE.ERROR);
                break;
            case R.id.btn_wait_dialog:
//                WaitDialog.build((AppCompatActivity) context).show();
                WaitDialog.show((AppCompatActivity) context, "正在加载");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TipDialog.show((AppCompatActivity) context, "成功！", TipDialog.TYPE.SUCCESS).setOnDismissListener(new OnDismissListener() {
                                    @Override
                                    public void onDismiss() {

                                    }
                                });
                            }
                        });
                    }
                }, 2000);
                break;
        }
    }

    /**
     * 添加主题选择
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kongzue_dialog_style_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_style_ios:
                DialogSettings.style = DialogSettings.STYLE.STYLE_IOS;
                break;
            case R.id.meun_style_material:
                DialogSettings.style = DialogSettings.STYLE.STYLE_MATERIAL;
                break;
            case R.id.menu_style_kongzue:
                DialogSettings.style = DialogSettings.STYLE.STYLE_KONGZUE;
                break;
            case R.id.menu_theme_light:
                DialogSettings.theme = DialogSettings.THEME.LIGHT;
                DialogSettings.tipTheme = DialogSettings.THEME.LIGHT;
                break;
            case R.id.menu_theme_dark:
                DialogSettings.theme = DialogSettings.THEME.DARK;
                DialogSettings.tipTheme = DialogSettings.THEME.DARK;
                break;
        }
        return true;
    }

    @Override
    public void onDestroy() {
        DialogSettings.init();
        super.onDestroy();
    }
}
