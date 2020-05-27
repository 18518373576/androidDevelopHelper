package com.example.developerandroidx.model;

import java.util.Map;

public class FunctionItemBean {
    public int itemIconId;//图标id
    public String itemName;//item名称
    public String goTo;//跳转的activity，包名，例：MainActivity.class.getName()
    public Map<String, String> paramsMap;//跳转传递的参数
    public String paramStr;//字符串参数

    /**
     * 不传递参数
     *
     * @param itemName   item名称
     * @param itemIconId 展示的图标id
     * @param goTo       要跳转的activity,例：MainActivity.class.getName()
     */
    public FunctionItemBean(String itemName, int itemIconId, String goTo) {
        this.itemName = itemName;
        this.itemIconId = itemIconId;
        this.goTo = goTo;
        this.paramsMap = null;
        this.paramStr = null;
    }

    /**
     * 传递参数
     *
     * @param itemName   item名称
     * @param itemIconId 展示的图标id
     * @param goTo       要跳转的activity,例：MainActivity.class.getName()
     * @param paramsMap  携带的参数，多参数定义为map
     */
    public FunctionItemBean(String itemName, int itemIconId, String goTo, Map<String, String> paramsMap) {
        this.itemIconId = itemIconId;
        this.itemName = itemName;
        this.goTo = goTo;
        this.paramsMap = paramsMap;
        this.paramStr = null;
    }

    /**
     * 传递一个字符串参数
     *
     * @param itemName   item名称
     * @param itemIconId 展示的图标id
     * @param goTo       要跳转的activity,例：MainActivity.class.getName()
     * @param paramStr   携带的参数，单个参数，定义为String
     */
    public FunctionItemBean(String itemName, int itemIconId, String goTo, String paramStr) {
        this.itemIconId = itemIconId;
        this.itemName = itemName;
        this.goTo = goTo;
        this.paramStr = paramStr;
        this.paramsMap = null;
    }
}
