package com.example.developerandroidx.utils.httpRequest;

import android.os.Handler;
import android.os.Message;

import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.LogUtils;

/**
 * 作者： zjf 2020/6/7 12:48 PM
 * 参考：
 * 描述：网络请求的handler
 */
public class RequestHandler extends Handler {
    private RequestCallBack callBack;

    public void setCallBack(RequestCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case Constant.Internet.SUC:
                LogUtils.e("数据访问成功：", msg.obj.toString());
                if (callBack != null)
                    callBack.onSuc(msg.obj.toString());
                break;
            case Constant.Internet.FAIL:
                LogUtils.e("数据访问失败：", msg.obj.toString());
                if (callBack != null)
                    callBack.onFail(msg.obj.toString());
                break;
        }
    }
}
