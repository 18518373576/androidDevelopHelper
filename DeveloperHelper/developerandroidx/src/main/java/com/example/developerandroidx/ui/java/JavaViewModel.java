package com.example.developerandroidx.ui.java;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.FunctionItemBean;
import com.example.developerandroidx.ui.java.arithmetic.ArithmeticActivity;
import com.example.developerandroidx.ui.java.dataStructure.DataStructureActivity;
import com.example.developerandroidx.ui.java.operator.OperatorActivity;
import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.RouteUtil;

import java.util.ArrayList;
import java.util.List;

public class JavaViewModel extends BaseViewModel<List<FunctionItemBean>> {

    @Override
    protected void initData(@Nullable String... param) {
        setData(initData());
    }

    private List<FunctionItemBean> initData() {
        List<FunctionItemBean> functionList = new ArrayList<>();
        functionList.add(new FunctionItemBean("数据类型和变量", R.mipmap.icon_data_type,
                RouteUtil.getDestination(CodeViewActivity.class), CodeVariate.getInstance().getCode_2()));
        functionList.add(new FunctionItemBean("操作符", R.mipmap.icon_operator, RouteUtil.getDestination(OperatorActivity.class)));
        functionList.add(new FunctionItemBean("流程控制", R.mipmap.icon_process_control, ""));
        functionList.add(new FunctionItemBean("继承", R.mipmap.icon_extend, ""));
        functionList.add(new FunctionItemBean("修饰符", R.mipmap.icon_modifier, ""));
        functionList.add(new FunctionItemBean("接口", R.mipmap.icon_interface, ""));
        functionList.add(new FunctionItemBean("异常处理", R.mipmap.icon_exception, ""));
        functionList.add(new FunctionItemBean("类的生命周期", R.mipmap.icon_class, ""));
        functionList.add(new FunctionItemBean("对象的生命周期", R.mipmap.icon_object, ""));
        functionList.add(new FunctionItemBean("内部类", R.mipmap.icon_inner_class, ""));
        functionList.add(new FunctionItemBean("多线程", R.mipmap.icon_thread, ""));
        functionList.add(new FunctionItemBean("数组", R.mipmap.icon_array, ""));
        functionList.add(new FunctionItemBean("集合", R.mipmap.icon_collection, ""));
        functionList.add(new FunctionItemBean("泛型", R.mipmap.icon_genericity, ""));
        functionList.add(new FunctionItemBean("I/O", R.mipmap.icon_io, ""));
        functionList.add(new FunctionItemBean("java常用类", R.mipmap.icon_common_class, ""));
        functionList.add(new FunctionItemBean("Annotatio注解", R.mipmap.icon_annotation, ""));
        functionList.add(new FunctionItemBean("反射", R.mipmap.icon_reflect, ""));
        functionList.add(new FunctionItemBean("设计模式", R.mipmap.icon_develop_model, ""));
        functionList.add(new FunctionItemBean("数据结构", R.mipmap.icon_data_structure, RouteUtil.getDestination(DataStructureActivity.class)));
        functionList.add(new FunctionItemBean("算法", R.mipmap.icon_algorithm, RouteUtil.getDestination(ArithmeticActivity.class)));

        return functionList;
    }

    public LiveData<List<FunctionItemBean>> getAdapterList() {
        return getData();
    }
}