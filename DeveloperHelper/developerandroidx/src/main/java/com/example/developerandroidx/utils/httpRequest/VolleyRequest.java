package com.example.developerandroidx.utils.httpRequest;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
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
public class VolleyRequest {
    private RequestQueue queue;

    private VolleyRequest() {
        queue = Volley.newRequestQueue(App.context);
    }

    private static class VolleyInterface {
        public static final VolleyRequest INTERFACE = new VolleyRequest();
    }

    public static VolleyRequest getInstance() {
        return VolleyInterface.INTERFACE;
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
                    LogUtils.e("数据访问成功", response);
                    callBack.onSuc(response);
                },
                error -> {
                    LogUtils.e("数据访问失败", error.getMessage());
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
                    LogUtils.e("数据访问成功", response);
                    callBack.onSuc(response);
                },
                error -> {
                    LogUtils.e("数据访问失败", error.getMessage());
                    callBack.onFail(error.getMessage());
                });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constant.Internet.CONNECT_TIME_OUT_MILLISECOND,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
}
