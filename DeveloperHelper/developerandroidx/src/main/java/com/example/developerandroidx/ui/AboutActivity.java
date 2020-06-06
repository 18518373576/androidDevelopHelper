package com.example.developerandroidx.ui;

import android.content.Intent;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.widget.webView.TechnologyWebviewActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;

import butterknife.BindView;

public class AboutActivity extends BaseActivity {
    @BindView(R.id.esv_content)
    ExtensibleScrollView esv_content;

    @Override
    protected int bindLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("About");
    }

    @Override
    protected void initData() {
        super.initData();
        esv_content.addBodyWithIntent("DevelopHelper", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/18518373576/androidDevelopHelper"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("图标库：https://www.iconfont.cn", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://www.iconfont.cn"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("butterknife", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/JakeWharton/butterknife"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("CodeView", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/Thereisnospon/CodeView"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("kongzue.dialog", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/kongzue/DialogV3"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("eventbus", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/greenrobot/EventBus"));
        esv_content.addLine(R.color.lightGrayColor);
        esv_content.addBodyWithIntent("BaseRecyclerViewAdapterHelper", R.color.colorMain,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/CymChad/BaseRecyclerViewAdapterHelper"));
    }
}
