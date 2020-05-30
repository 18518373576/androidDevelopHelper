package com.example.developerandroidx.ui.java.dataStructure.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.ui.java.dataStructure.dataStructureClass.SingleLinkedList;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.linkedList.LinkedListView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/28 14:58
 * 参考:
 * 描述: 单项链表实现
 */
public class SignleLinkedListDialog implements FunctionDialogInterface, View.OnClickListener {
    LinkedListView llv_linked_list;
    private TextView tv_add, tv_remove, tv_edit, tv_search;
    private Context context;

    private SingleLinkedList<Integer> singleLinkedList;

    @Override
    public void show(Context context) {
        this.context = context;
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_single_linked_list, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                llv_linked_list = rootView.findViewById(R.id.llv_linked_list);
                //定义一个单向链表
                singleLinkedList = new SingleLinkedList<>();

                tv_add = rootView.findViewById(R.id.tv_add);
                tv_remove = rootView.findViewById(R.id.tv_remove);
                tv_edit = rootView.findViewById(R.id.tv_edit);
                tv_search = rootView.findViewById(R.id.tv_search);

                tv_add.setOnClickListener(SignleLinkedListDialog.this);
                tv_remove.setOnClickListener(SignleLinkedListDialog.this);
                tv_edit.setOnClickListener(SignleLinkedListDialog.this);
                tv_search.setOnClickListener(SignleLinkedListDialog.this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:
                //在表头添加数据,节点数据设置为链表的节点个数
                singleLinkedList.add(0, 0);
                //添加过节点后再获取节点大小
                singleLinkedList.set(0, singleLinkedList.getSize());
                llv_linked_list.addNode(0, singleLinkedList.getSize());
                break;
            case R.id.tv_remove:
                //删除表头数据
                singleLinkedList.remove(0);
                llv_linked_list.removeNode(0);
                break;
            case R.id.tv_edit:
                //修改表头数据
                singleLinkedList.set(0, 10);
                llv_linked_list.editNode(0, 10);
                break;
            case R.id.tv_search:
                //获取表头数据
                try {
                    llv_linked_list.getNode(0);
                    App.showNotify("获得表头数据：" + singleLinkedList.get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
