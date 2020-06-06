package com.example.developerandroidx.ui.android.httpRequest;

import androidx.annotation.Nullable;

import com.example.developerandroidx.base.BaseModel;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.BlogListBean;
import com.example.developerandroidx.utils.Api;
import com.example.developerandroidx.utils.HttpRequestUtil;
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
                requestByOkHttp();
                break;
            case "Volley":

                break;
            case "Retrofit":

                break;
        }
    }

    /**
     * 使用OkHttp请求数据
     */
    private void requestByOkHttp() {
        HttpRequestUtil.getInstance().requestByOkHttp(HttpRequestUtil.RequestType.GET,
                Api.BLOG_LIST,
                new HttpRequestUtil.OkHttpCallBack() {
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
