package com.example.developerandroidx.utils.httpRequest;

/**
 * 作者： zjf 2020/6/7 12:50 PM
 * 参考：
 * 描述：请求结果回调
 */
public interface RequestCallBack {
    void onFail(String msg);

    void onSuc(String result);
}
