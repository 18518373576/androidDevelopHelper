package com.example.developerandroidx.utils.httpRequest;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.LogUtils;

import java.util.Map;

/**
 * 作者： zjf 2020/6/7 4:49 PM
 * 参考：
 * 描述：使用volley请求网络
 */
public class RequestByVolley {
    private RequestQueue queue;

    private RequestByVolley() {
        queue = Volley.newRequestQueue(App.context);
    }

    private static class VolleyInstance {
        public static final RequestByVolley INTERFACE = new RequestByVolley();
    }

    public static RequestByVolley getInstance() {
        return VolleyInstance.INTERFACE;
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void post(String url, Map<String, String> params, RequestCallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    LogUtils.e("数据访问成功", url + ":" + response);
                    callBack.onSuc(response);
                },
                error -> {
                    LogUtils.e("数据访问失败", url + ":" + error.getMessage());
                    callBack.onFail(error.getMessage());
                }) {
            @Override
            protected Map<String, String> getParams() {
                //访问参数
                return params;
            }
        };

        queue.add(stringRequest);
    }

    /**
     * get请求
     *
     * @param url
     * @param callBack
     */
    public void get(String url, RequestCallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    LogUtils.e("数据访问成功", url + ":" + response);
                    callBack.onSuc(response);
                },
                error -> {
                    LogUtils.e("数据访问失败", url + ":" + error.getMessage());
                    callBack.onFail(error.getMessage());
                });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constant.Internet.CONNECT_TIME_OUT_MILLISECOND,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
}
