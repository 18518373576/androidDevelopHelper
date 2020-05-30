package com.example.developerandroidx.ui.java.arithmetic.dialog;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.ui.java.dataStructure.dataStructureClass.SingleLinkedList;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.view.linkedList.LinkedListView;
import com.kongzue.dialog.v3.FullScreenDialog;

import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * Date: 2020/5/30 10:53
 * 参考:
 * 描述: 算法找到链表的第n个节点
 */
public class GetNodeDialog implements FunctionDialogInterface, View.OnClickListener {
    private ImageView iv_get;
    private LinkedListView llv_linked_list;
    private SingleLinkedList<Integer> linkedList;

    @Override
    public void show(Context context) {

        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_get_node, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                iv_get = rootView.findViewById(R.id.iv_get);
                iv_get.setOnClickListener(GetNodeDialog.this);
                llv_linked_list = rootView.findViewById(R.id.llv_linked_list);
                initLinkedList();
            }
        });
    }

    private void initLinkedList() {
        linkedList = new SingleLinkedList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //IntStream使用：https://www.jianshu.com/p/461429a5edc9

            IntStream.range(1, 100)
                    .filter(value -> value % 6 == 0)
                    .forEach(value -> {
                        linkedList.add(0, value);
                        llv_linked_list.addNode(0, value);
                    });
        }
    }

    @Override
    public void onClick(View v) {
        //获取倒数第三个节点的数据
        llv_linked_list.getNode(linkedList.getSize() - 3);
        App.showNotify("获取倒数第三个节点数据：" + linkedList.getTheLast_N(3));
    }
}
