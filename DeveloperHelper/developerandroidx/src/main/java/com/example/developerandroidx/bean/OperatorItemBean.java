package com.example.developerandroidx.bean;

/**
 * Date: 2020/5/4 21:10
 * 参考:
 * 描述:
 */
public class OperatorItemBean {
    public String operator;//操作符
    public String description;//操作符描述
    public String operatorType;//操作符类型

    public OperatorItemBean(String operator, String description, String operatorType) {
        this.operator = operator;
        this.description = description;
        this.operatorType = operatorType;
    }
}
