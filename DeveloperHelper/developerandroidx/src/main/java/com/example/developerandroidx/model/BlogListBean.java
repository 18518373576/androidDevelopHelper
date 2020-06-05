package com.example.developerandroidx.model;

import com.example.developerandroidx.base.BaseModel;

import java.util.List;

/**
 * @作者： zjf 2020/6/5 3:44 PM
 * @参考：
 * @描述：公众号列表数据
 */
public class BlogListBean implements BaseModel {
    /**
     * {
     * "errorCode" : 0,
     * "data" : [
     * {
     * "children" : [],
     * "courseId" : 13,
     * "id" : 408,
     * "order" : 190000,
     * "parentChapterId" : 407,
     * "userControlSetTop" : false,
     * "visible" : 1,
     * "name" : "鸿洋"
     * }
     * ],
     * "errorMsg" : ""
     * }
     */

    public int errorCode;
    public String errorMsg;
    public List<Model> data;

    public class Model {
        public int courseId;
        public int id;
        public int order;
        public int parentChapterId;
        public boolean userControlSetTop;
        public int visible;
        public String name;
    }
}
