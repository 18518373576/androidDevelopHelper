package com.example.developerandroidx.ui.widget.webView;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.view.loadingView.LoadingView;

import butterknife.BindView;

public class TechnologyWebviewActivity extends BaseActivity {


    @BindView(R.id.wv_web)
    WebView wv_web;
    @BindView(R.id.lv_loading)
    LoadingView lv_loading;
    @BindView(R.id.pb_progress)
    ProgressBar pb_progress;

    @Override
    protected int bindLayout() {
        return R.layout.activity_technology_webview;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        setTitle("链接");
        String url = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);
        wv_web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                //重定向URL请求，返回true表示拦截此url，返回false表示不拦截此url。
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (!request.getUrl().toString().contains("github")) {
                    lv_loading.loadingFail(wv_web, R.mipmap.icon_404);
                }
            }
        });
        WebSettings webSettings = wv_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv_web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (pb_progress != null) {
                    pb_progress.setProgress(newProgress);
                    if (newProgress == 100) {
                        pb_progress.setVisibility(View.GONE);
                    }
                }
            }
        });
        wv_web.loadUrl(url);
    }
}
