package com.example.developerandroidx.ui.java.dataStructure.dialog;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.example.developerandroidx.view.node.NodeView;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * Date: 2020/5/28 14:58
 * 参考:
 * 描述: 单项链表实现
 */
public class SignleLinkedListDialog implements FunctionDialogInterface, View.OnClickListener {
    private LinearLayout ll_content;
    private TextView tv_add, tv_remove, tv_edit, tv_search;
    private Context context;

    @Override
    public void show(Context context) {
        this.context = context;
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_single_linked_list, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                ll_content = rootView.findViewById(R.id.ll_content);
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
                NodeView nodeView = new NodeView(context);
                ll_content.addView(nodeView, 0);
                break;
            case R.id.tv_remove:

                break;
            case R.id.tv_edit:

                break;
            case R.id.tv_search:

                break;
        }
    }


    /**
     * 定义链表
     */
    private class SignleLinkedList {

    }
}
