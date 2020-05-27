package com.example.developerandroidx.ui.java.arithmetic;

import com.example.developerandroidx.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2020/5/27 11:48
 * 参考:
 * 描述:
 */
public class ArithmeticViewModel extends BaseViewModel<List<String>> {

    @Override
    public List<String> initData() {
        List<String> list = new ArrayList<>();
        list.add("冒泡排序");
        list.add("选择排序");
        list.add("插入排序");
        list.add("希尔排序");
        list.add("归并排序");
        list.add("堆排序");
        list.add("快速排序");
        list.add("树排序");
        return list;
    }
}
