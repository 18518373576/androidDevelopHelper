package com.example.developerandroidx.ui.java.arithmetic.dialog;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.ui.java.dataStructure.dataStructureClass.SingleLinkedList;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.linkedList.LinkedListView;
import com.kongzue.dialog.v3.FullScreenDialog;

import java.util.stream.IntStream;

/**
 * Date: 2020/5/30 10:53
 * 参考:
 * 描述: 算法找到链表的第n个节点
 */
public class ReversalLinkedListDialog implements FunctionDialogInterface, View.OnClickListener {
    private ImageView iv_reveral;
    private LinkedListView llv_linked_list;
    private SingleLinkedList<Integer> linkedList;

    @Override
    public void show(Context context) {

        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_reversal_linked_list, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                iv_reveral = rootView.findViewById(R.id.iv_reveral);
                iv_reveral.setOnClickListener(ReversalLinkedListDialog.this);
                llv_linked_list = rootView.findViewById(R.id.llv_linked_list);
                initLinkedList();
            }
        });
    }

    /**
     * 添加链表数据
     */
    private void initLinkedList() {
        linkedList = new SingleLinkedList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //IntStream使用：https://www.jianshu.com/p/461429a5edc9

            IntStream.range(1, 60)
                    .filter(value -> value % 6 == 0)
                    .forEach(value -> {
                        linkedList.add(0, value);
                        llv_linked_list.addNode(0, value);
                    });
        }
    }

    @Override
    public void onClick(View v) {
        //逆置链表
        linkedList.reversalLinkedList();

        new Task().execute();

    }

    private class Task extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {

            for (int i = 0; i < linkedList.getSize(); i++) {
                try {
                    publishProgress(i);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            llv_linked_list.editNode(values[0], linkedList.get(values[0]));
        }
    }
}
