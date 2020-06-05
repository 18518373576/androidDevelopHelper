package com.example.developerandroidx.ui.android.httpRequest;

import androidx.lifecycle.Observer;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.Constant;

/**
 * 数据请求示例
 */
public class HttpRequestActivity extends BaseActivity {

    private String requestLibrary;
    private HttpRequestViewModel viewModel;

    @Override
    protected int bindLayout() {
        return R.layout.activity_http_request;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("HttpRequest");
    }

    @Override
    protected void initData() {
        super.initData();
        requestLibrary = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);
        //"OkHttp", "Volley", "Retrofit"
        viewModel = (HttpRequestViewModel) getViewModel(this, HttpRequestViewModel.class);
        viewModel.getData(requestLibrary).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                App.showNotify(s);
            }
        });
    }
}