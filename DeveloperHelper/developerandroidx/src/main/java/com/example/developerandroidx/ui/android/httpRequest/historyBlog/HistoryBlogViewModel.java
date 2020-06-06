package com.example.developerandroidx.ui.android.httpRequest.historyBlog;

import androidx.annotation.Nullable;

import com.example.developerandroidx.base.BaseModel;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.HistoryBlogBean;
import com.example.developerandroidx.utils.Api;
import com.example.developerandroidx.utils.httpRequest.HttpRequestUtil;
import com.google.gson.Gson;

/**
 * 作者： zjf 2020/6/6 10:26 AM
 * 参考：
 * 描述：获取公众号文章历史记录
 */
public class HistoryBlogViewModel extends BaseViewModel<BaseModel> {
    @Override
    protected void initData(@Nullable String... param) {

        switch (param[0]) {
            case "OkHttp":
                requestByOkHttp(param[1], param[2]);
                break;
            case "Volley":

                break;
            case "Retrofit":

                break;
        }
    }

    private void requestByOkHttp(String id, String page) {
        HttpRequestUtil.getInstance().requestByOkHttpGet(Api.getBlogHistory(id, page), new HttpRequestUtil.OkHttpCallBack() {
            @Override
            public void onFail(String msg) {
                setData(null);
            }

            @Override
            public void onSuc(String result) {
                try {
                    HistoryBlogBean historyBlogBean = new Gson().fromJson(result, HistoryBlogBean.class);
                    setData(historyBlogBean);
                } catch (Exception e) {
                    setData(null);
                    e.printStackTrace();
                }
            }
        });
    }
}
