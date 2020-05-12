package com.example.developerandroidx.ui.widget.webView;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;
import com.example.developerandroidx.utils.RouteUtil;

import butterknife.OnClick;
import thereisnospon.codeview.CodeView;

/**
 * 参考：https://www.jianshu.com/p/3e0136c9e748 https://www.jianshu.com/p/5cc2eae14e07
 */
public class WebViewActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.iv_code})
    public void click(View v) {
        RouteUtil.goTo(context, RouteUtil.getDestination(CodeViewActivity.class), "");
    }
}
