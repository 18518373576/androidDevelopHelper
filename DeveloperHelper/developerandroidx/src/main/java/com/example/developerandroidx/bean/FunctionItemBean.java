package com.example.developerandroidx.bean;

public class FunctionItemBean {
    public int itemIconId;//图标id
    public String itemName;//item名称
    public String goTo;//跳转的activity，包名，例：MainActivity.class.getName()

    public FunctionItemBean(String itemName, int itemIconId, String goTo) {
        this.itemName = itemName;
        this.itemIconId = itemIconId;
        this.goTo = goTo;
    }
}
