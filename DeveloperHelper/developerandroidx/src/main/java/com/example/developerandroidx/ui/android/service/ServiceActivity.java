package com.example.developerandroidx.ui.android.service;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

import butterknife.OnClick;

/**
 * service(服务)知识点整理
 */
public class ServiceActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_service;
    }

    @Override
    protected void initView() {
        setTitle("Service");
        iv_right.setVisibility(View.VISIBLE);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @OnClick({R.id.iv_right})
    public void click(View v) {

        switch (v.getId()) {
            case R.id.iv_right:
                DialogUtils.getInstance().shouFullScreenDialog(context, R.layout.dialog_service_desc, new DialogUtils.OnFullScreenDialogBindView() {
                    @Override
                    public void onBind(FullScreenDialog dialog, View rootView) {
                        ExtensibleScrollView esv_content = rootView.findViewById(R.id.esv_content);
                        esv_content.addText("Service 是一种可在后台执行长时间运行操作而不提供界面的应用组件。服务可由其他应用组件启动，" +
                                "而且即使用户切换到其他应用，服务仍将在后台继续运行。此外，组件可通过绑定到服务与之进行交互，甚至是执行进程间通信 (IPC)。" +
                                "例如，服务可在后台处理网络事务、播放音乐，执行文件 I/O 或与内容提供程序进行交互。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
//                esv_content.addText("标题1", ExtensibleScrollView.InsertTextType.TITLE_1, R.color.textColorBlack);
//                esv_content.addText("标题2", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
                        esv_content.addImage(R.mipmap.service_lifecycle, 350);
                    }
                });
                break;
        }

    }
}
