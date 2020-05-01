package com.example.developerandroidx.bean;

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
     * @param itemName
     * @param itemIconId
     * @param goTo
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
     * @param itemIconId
     * @param itemName
     * @param goTo
     * @param paramsMap
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
     * @param itemIconId
     * @param itemName
     * @param goTo
     * @param paramStr
     */
    public FunctionItemBean(String itemName, int itemIconId, String goTo, String paramStr) {
        this.itemIconId = itemIconId;
        this.itemName = itemName;
        this.goTo = goTo;
        this.paramStr = paramStr;
        this.paramsMap = null;
    }
}
