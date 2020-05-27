package com.example.developerandroidx.model;

/**
 * Date: 2020/5/16 17:22
 * 参考:
 * 描述: eventbus的消息类
 */
public class EventBusMessageBean {

    public int msgId;
    public String msgFrom;
    public String msg;

    /**
     * 创建消息
     *
     * @param msgId   消息id，区分消息作用
     * @param msgFrom 主要为了定位消息来源
     * @param msg     消息主题
     */
    public EventBusMessageBean(int msgId, String msgFrom, String msg) {
        this.msgId = msgId;
        this.msgFrom = msgFrom;
        this.msg = msg;
    }
}
