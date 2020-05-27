package com.example.developerandroidx.model;

/**
 * Date: 2020/5/4 21:10
 * 参考:
 * 描述:
 */
public class OperatorItemBean {
    public String operator;//操作符
    public String description;//操作符描述
    public String operatorType;//操作符类型
    public boolean isShowOperatorType;//是否展示操作符类型
    public boolean isEnd;//这个是为了展示底部圆角边框，做一个判断

    public OperatorItemBean(String operator, String description, String operatorType, boolean isShowOperatorType, boolean isEnd) {
        this.operator = operator;
        this.description = description;
        this.operatorType = operatorType;
        this.isShowOperatorType = isShowOperatorType;
        this.isEnd = isEnd;
    }
}
