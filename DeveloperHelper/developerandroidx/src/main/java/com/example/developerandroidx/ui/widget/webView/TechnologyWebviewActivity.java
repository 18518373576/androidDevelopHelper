package com.example.developerandroidx.ui.widget.webView;

import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.Constant;

import butterknife.BindView;

public class TechnologyWebviewActivity extends BaseActivity {


    @BindView(R.id.wv_web)
    WebView wv_web;

    @Override
    protected int bindLayout() {
        return R.layout.activity_technology_webview;
    }

    @Override
    protected void initView() {
        setTitle("参考文章");
        String url = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);
        wv_web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                //重定向URL请求，返回true表示拦截此url，返回false表示不拦截此url。
                return true;
            }
        });
        WebSettings webSettings = wv_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv_web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
        wv_web.loadUrl(url);
    }
}
