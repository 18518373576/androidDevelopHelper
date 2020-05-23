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
        esv_content.addBodyWithIntent("项目GitHub", R.color.textColor,
                new Intent(context, TechnologyWebviewActivity.class).putExtra(Constant.IntentParams.INTENT_PARAM, "https://github.com/18518373576/androidDevelopHelper"));
    }
}
