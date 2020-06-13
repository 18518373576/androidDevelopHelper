package com.example.developerandroidx.ui.android.httpRequest.historyBlog;

import androidx.annotation.Nullable;

import com.example.developerandroidx.base.BaseModel;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.HistoryBlogBean;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.api.Api;
import com.example.developerandroidx.utils.httpRequest.HttpRequestUtil;
import com.example.developerandroidx.utils.httpRequest.RequestCallBack;
import com.example.developerandroidx.utils.httpRequest.RequestLibrary;
import com.google.gson.Gson;

/**
 * 作者： zjf 2020/6/6 10:26 AM
 * 参考：
 * 描述：获取公众号文章历史记录
 */
public class HistoryBlogViewModel extends BaseViewModel<BaseModel> {

    private HistoryBlogBean historyBlogBean;


    @Override
    protected void initData(@Nullable String... param) {

        String loadingType = param[1];
        //主要为了防止屏幕方向切换后,数据重复加载
        if (loadingType.equals(Constant.Internet.FIRST_LOAD) && historyBlogBean != null) {
            setData(historyBlogBean);
            return;
        }
        switch (param[0]) {
            case "OkHttp":
                request(RequestLibrary.OK_HTTP, param[2], param[3]);
                break;
            case "Volley":
                request(RequestLibrary.VOLLEY, param[2], param[3]);
                break;
            case "Retrofit":
                request(RequestLibrary.RETROFIT, param[2], param[3]);
                break;
        }
    }

    private void request(RequestLibrary library, String id, String page) {
        HttpRequestUtil.getInstance().get(library, Api.getBlogHistory(id, page),
                new RequestCallBack() {
                    @Override
                    public void onFail(String msg) {
                        setData(null);
                    }

                    @Override
                    public void onSuc(String result) {
                        try {
                            HistoryBlogBean tempBean = new Gson().fromJson(result, HistoryBlogBean.class);
                            if (historyBlogBean == null) {
                                historyBlogBean = tempBean;
                            } else {
                                historyBlogBean.data.curPage = tempBean.data.curPage;
                                historyBlogBean.data.datas.addAll(tempBean.data.datas);
                            }
                            setData(tempBean);
                        } catch (Exception e) {
                            setData(null);
                            e.printStackTrace();
                        }
                    }
                });
    }
}
