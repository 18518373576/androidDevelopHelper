package com.example.developerandroidx.ui.java.arithmetic;

import androidx.annotation.Nullable;

import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.ArithMeticModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2020/5/27 11:48
 * 参考:
 * 描述: 算法列表数据
 */
public class ArithmeticViewModel extends BaseViewModel<List<ArithMeticModel>> {

    @Override
    protected void initData(@Nullable String... param) {
        List<ArithMeticModel> list = new ArrayList<>();
        list.add(new ArithMeticModel("0.排序", "冒泡排序"));
        list.add(new ArithMeticModel("1.排序", "选择排序"));
        list.add(new ArithMeticModel("2.排序", "插入排序"));
        list.add(new ArithMeticModel("3.排序", "希尔排序"));
        list.add(new ArithMeticModel("4.排序", "归并排序"));
        list.add(new ArithMeticModel("5.排序", "堆排序"));
        list.add(new ArithMeticModel("6.排序", "快速排序"));
        list.add(new ArithMeticModel("7.排序", "树排序"));
        list.add(new ArithMeticModel("8.链表", "找到链表的倒数第n个节点"));
        list.add(new ArithMeticModel("9.链表", "逆置单项链表"));
        list.add(new ArithMeticModel("10.两数之和", "给定一个整数数组 nums 和一个目标值 target，请在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。\n" +
                "\n" + "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。\n" + "\n" + "来源：LeetCode"));
        setData(list);
    }
}
