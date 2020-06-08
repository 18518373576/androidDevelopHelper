package com.example.developerandroidx.ui.android.httpRequest;

import androidx.annotation.Nullable;

import com.example.developerandroidx.base.BaseModel;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.BlogListBean;
import com.example.developerandroidx.utils.api.Api;
import com.example.developerandroidx.utils.httpRequest.HttpRequestUtil;
import com.example.developerandroidx.utils.httpRequest.RequestCallBack;
import com.example.developerandroidx.utils.httpRequest.RequestLibrary;
import com.google.gson.Gson;

/**
 * @作者： zjf 2020/6/5 10:23 AM
 * @参考：
 * @描述： 网络请求
 */
public class HttpRequestViewModel extends BaseViewModel<BaseModel> {

    @Override
    protected void initData(@Nullable String... param) {
        switch (param[0]) {
            case "OkHttp":
                request(RequestLibrary.OK_HTTP);
                break;
            case "Volley":
                request(RequestLibrary.VOLLEY);
                break;
            case "Retrofit":
                request(RequestLibrary.RETROFIT);
                break;
        }
    }

    /**
     * 使用OkHttp请求数据
     */
    private void request(RequestLibrary library) {
        HttpRequestUtil.getInstance().get(library, Api.BLOG_LIST,
                new RequestCallBack() {
                    @Override
                    public void onFail(String msg) {
                        setData(null);
                    }

                    @Override
                    public void onSuc(String result) {
                        try {
                            BlogListBean listBean = new Gson().fromJson(result, BlogListBean.class);
                            setData(listBean);
                        } catch (Exception e) {
                            setData(null);
                        }
                    }
                });
    }
}
