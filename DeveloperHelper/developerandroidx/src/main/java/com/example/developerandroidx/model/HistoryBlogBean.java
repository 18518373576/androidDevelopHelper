package com.example.developerandroidx.model;

import com.example.developerandroidx.base.BaseModel;

import java.util.List;

/**
 * 作者： zjf 2020/6/6 10:40 AM
 * 参考：
 * 描述：公众号历史文章
 */
public class HistoryBlogBean implements BaseModel {
    /**
     * {
     * "errorCode" : 0,
     * "data" : {
     * "offset" : 0,
     * "over" : false,
     * "datas" : [
     * {
     * "userId" : -1,
     * "envelopePic" : "",
     * "link" : "https:\/\/mp.weixin.qq.com\/s\/wWB5ENo3eQJH03OXvoup8w",
     * "selfVisible" : 0,
     * "tags" : [
     * {
     * "name" : "公众号",
     * "url" : "\/wxarticle\/list\/408\/1"
     * }
     * ],
     * "title" : "&ldquo;新技术&rdquo; 又又又又来了？",
     * "fresh" : false,
     * "chapterId" : 408,
     * "apkLink" : "",
     * "zan" : 0,
     * "canEdit" : false,
     * "descMd" : "",
     * "origin" : "",
     * "projectLink" : "",
     * "type" : 0,
     * "superChapterName" : "公众号",
     * "id" : 13769,
     * "prefix" : "",
     * "courseId" : 13,
     * "audit" : 1,
     * "chapterName" : "鸿洋",
     * "publishTime" : 1591200000000,
     * "superChapterId" : 408,
     * "niceShareDate" : "1天前",
     * "collect" : false,
     * "shareDate" : 1591282394000,
     * "desc" : "",
     * "niceDate" : "2天前",
     * "visible" : 1,
     * "author" : "鸿洋",
     * "shareUser" : ""
     * }
     * ],
     * "pageCount" : 47,
     * "size" : 20,
     * "total" : 939,
     * "curPage" : 1
     * },
     * "errorMsg" : ""
     * }
     */

    public int errorCode;
    public String errorMsg;
    public Models data;

    public class Models {
        public int pageCount;
        public int size;
        public int total;
        public int curPage;
        public List<Model> datas;
    }

    public class Model {
        public String title;
        public String link;
        public String publishTime;
        public String niceDate;
    }
}
