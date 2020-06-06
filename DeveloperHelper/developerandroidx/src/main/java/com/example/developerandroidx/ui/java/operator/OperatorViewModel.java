package com.example.developerandroidx.ui.java.operator;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.OperatorItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2020/5/4 21:08
 * 参考:
 * 描述: 操作符数据
 */
public class OperatorViewModel extends BaseViewModel<List<OperatorItemBean>> {

    @Override
    protected void initData(@Nullable String... param) {
        setData(initData());
    }

    private List<OperatorItemBean> initData() {
        List<OperatorItemBean> operatorItemBeans = new ArrayList<>();
        operatorItemBeans.add(new OperatorItemBean("+", "(加法)将两个数相加", "算数操作符", true, false));
        operatorItemBeans.add(new OperatorItemBean("++", "(自增)将表示数值的变量加1", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("-", "(求相反数或者减法)作为一元操作符时,返回操作元的相反数" +
                "作为两元操作符时，将两个数相减", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("--", "(自减)将表示数值的变量减1", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("*", "(乘法)将两个数相乘", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("/", "(除法)将两个数相除", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("%", "(求余)获得两个数相除的余数", null, false, true));

        operatorItemBeans.add(new OperatorItemBean("+", "(字符串加法)连接两个字符串", "字符串操作符", true, false));
        operatorItemBeans.add(new OperatorItemBean("+=", "连接两个字符串,并将结果赋给第一个字符串变量", null, false, true));

        operatorItemBeans.add(new OperatorItemBean("&", "(按位与)如果两个操作元对应位都是1,则在该位返回1", "位操作符", true, false));
        operatorItemBeans.add(new OperatorItemBean("^", "(按位异或)如果两个操作元对应位只有一个1,则在该位返回1", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("|", "(按位或)如果两个操作元对应位都是0,则在该位返回0", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("~", "(求反)反转操作元的每一位", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("<<", "(左移)将第一个操作元的二进制形式的每一位向左移位,所移位的数目由第二个操作元指定,右面的空位补0", null, false, false));
        operatorItemBeans.add(new OperatorItemBean(">>", "(算数右移)将第一个操作元的二进制形式的每一位向右移位,所移位的数目由第二个操作元指定,忽略被移出的位，左面的空位补符号位", null, false, false));
        operatorItemBeans.add(new OperatorItemBean(">>>", "(逻辑右移)将第一个操作元的二进制形式的每一位向右移位,所移位的数目由第二个操作元指定,忽略被移出的位,左面的空位补0", null, false, true));

        operatorItemBeans.add(new OperatorItemBean("&&", "(短路逻辑与)如果两个操作元都是true,则返回true,否则返回false", "逻辑操作符", true, false));
        operatorItemBeans.add(new OperatorItemBean("||", "(短路逻辑或)如果两个操作元都是false,则返回false,否则返回true", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("&", "(非短路逻辑与)如果两个操作元都是true,则返回true,否则返回false", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("|", "(非短路逻辑或)如果两个操作元都是false,则返回false,否则返回true", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("!", "(逻辑非)如果操作元为true,则返回false,否则返回true", null, false, true));

        operatorItemBeans.add(new OperatorItemBean("==", "如果操作元相等,则返回true", "比较操作符", true, false));
        operatorItemBeans.add(new OperatorItemBean("!=", "如果操作元不相等,则返回true", null, false, false));
        operatorItemBeans.add(new OperatorItemBean(">", "如果左操作元大于右操作元,则返回true", null, false, false));
        operatorItemBeans.add(new OperatorItemBean(">=", "如果左操作元大于等于右操作元,则返回true", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("<", "如果左操作元小于右操作元,则返回true", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("<=", "如果左操作元小于等于右操作元,则返回true", null, false, true));

        operatorItemBeans.add(new OperatorItemBean("=", "将第二个操作元的值赋给第一个操作元", "赋值操作符", true, false));
        operatorItemBeans.add(new OperatorItemBean("+=", "将两个操作元相加，并将和赋给第一个操作元", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("-=", "将两个操作元相减，并将差赋给第一个操作元", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("*=", "将两个操作元相乘，并将积赋给第一个操作元", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("/=", "将两个操作元相除，并将商赋给第一个操作元", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("%=", "计算两个数相除的余数，并将余数赋给第一个操作元", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("&=", "执行按位与，并将结果赋给第一个操作元", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("^=", "执行按位异或，并将结果赋给第一个操作元", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("|=", "执行按位或，并将结果赋给第一个操作元", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("<<=", "执行左移，并将结果赋给第一个操作元", null, false, false));
        operatorItemBeans.add(new OperatorItemBean(">>=", "执行算数右移，并将结果赋给第一个操作元", null, false, false));
        operatorItemBeans.add(new OperatorItemBean(">>>=", "执行逻辑右移，并将结果赋给第一个操作元", null, false, true));

        operatorItemBeans.add(new OperatorItemBean("?:", "等价于一个简单的if…else语句", "特殊操作符", true, false));
        operatorItemBeans.add(new OperatorItemBean(".", "访问类的属性或方法的操作符", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("new", "创建一个对象,并返回这个对象的引用", null, false, false));
        operatorItemBeans.add(new OperatorItemBean("instanceof", "如果一个对象是一个类或接口的实例,则返回true", null, false, true));
        return operatorItemBeans;
    }

    public LiveData<List<OperatorItemBean>> getAdapterList() {
        return getData();
    }
}
