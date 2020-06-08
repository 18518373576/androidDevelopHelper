package com.example.developerandroidx.utils.api;

/**
 * 作者： zjf 2020/6/5 2:57 PM
 * 参考：测试使用接口来自 https://www.wanandroid.com/blog/show/2
 * 描述：接口
 */
public class Api {
    public static final String BASE_URL = "https://www.wanandroid.com/";
    /**
     * 获取公众号列表
     */
    public static String BLOG_LIST = "wxarticle/chapters/json";

    /**
     * 获取公众号历史文章
     *
     * @param blogId 公众号ID
     * @return url
     */
    public static String getBlogHistory(String blogId, String page) {
        //https://wanandroid.com/wxarticle/list/408/1/json
        return "wxarticle/list/" + blogId + "/" + page + "/json";
    }
}
