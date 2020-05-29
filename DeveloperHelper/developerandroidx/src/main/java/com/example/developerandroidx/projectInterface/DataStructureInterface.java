package com.example.developerandroidx.projectInterface;

/**
 * Date: 2020/5/29 11:51
 * 参考:
 * 描述: 数据结构接口，定义数据结构需实现增，删，改，查,获取节点数量方法
 */
public interface DataStructureInterface<T> {
    void add(int index, T value);

    void remove(int index);

    void set(int index, T value);

    T get(int index);

    int getSize();
}
