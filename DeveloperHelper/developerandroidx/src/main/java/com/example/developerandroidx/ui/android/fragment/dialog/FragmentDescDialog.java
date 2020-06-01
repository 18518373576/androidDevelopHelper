package com.example.developerandroidx.ui.android.fragment.dialog;

import android.content.Context;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/31 19:17
 * 参考:
 * 描述:
 */
public class FragmentDescDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showEsvDialog(context, new DialogUtils.OnEsvDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, TextView title, ExtensibleScrollView esv_content) {
                title.setText("Fragment概述");
                esv_content.addTitle_2("生命周期");
                esv_content.addBody("片段所在 Activity 的生命周期会直接影响片段的生命周期，其表现为，Activity 的每次生命周期回调都会引发每个片段的类似回调。例如，当 Activity 收到 onPause() 时，Activity 中的每个片段也会收到 onPause()。");
                esv_content.addBody("不过，片段还有几个额外的生命周期回调，用于处理与 Activity 的唯一交互，从而执行构建和销毁片段界面等操作。这些额外的回调方法是：");
                esv_content.addImage(R.mipmap.fragment_lifecycle, 500);
                esv_content.addTitle_2("onAttach()");
                esv_content.addBody("在片段已与 Activity 关联时进行调用（Activity 传递到此方法内）。");
                esv_content.addTitle_2("onCreateView()");
                esv_content.addBody("调用它可创建与片段关联的视图层次结构。");
                esv_content.addTitle_2("onActivityCreated()");
                esv_content.addBody("当 Activity 的 onCreate() 方法已返回时进行调用。");
                esv_content.addTitle_2("onDestroyView()");
                esv_content.addBody("在移除与片段关联的视图层次结构时进行调用。");
                esv_content.addTitle_2("onDetach()");
                esv_content.addBody("在取消片段与 Activity 的关联时进行调用。");

                esv_content.addBody("下图所示为受宿主 Activity 影响的片段生命周期流。在该图中，您可以看到 Activity 的每个连续状态如何确定片段可收到的回调方法。例如，当 Activity 收到其 onCreate() 回调时，Activity 中的片段只会收到 onActivityCreated() 回调。");

                esv_content.addImage(R.mipmap.activity_fragment_lifecycle, 500);
                esv_content.addBody("一旦 Activity 达到已恢复状态，您便可随意向 Activity 添加片段和移除其中的片段。因此，只有当 Activity 处于已恢复状态时，片段的生命周期才能独立变化。");
                esv_content.addBody("不过，当 Activity 离开已恢复状态时，片段会在 Activity 的推动下再次经历其生命周期。");

            }
        });
    }
}
