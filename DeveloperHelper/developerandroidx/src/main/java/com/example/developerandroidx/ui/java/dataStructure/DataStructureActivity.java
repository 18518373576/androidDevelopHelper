package com.example.developerandroidx.ui.java.dataStructure;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.DataStructureRcvAdapter;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.ui.java.dataStructure.dialog.SignleLinkedListDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DataStructureActivity extends BaseActivity implements BaseRcvAdapter.OnRecyclerViewItemClickListner {

    @BindView(R.id.rcv_data_structure)
    RecyclerView rcv_data_structure;
    private DataStructureRcvAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_data_structure;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("数据结构");
        rcv_data_structure.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new DataStructureRcvAdapter(new ArrayList<String>());
        rcv_data_structure.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        DataStructureViewModel viewModel = (DataStructureViewModel) getViewModel(this, DataStructureViewModel.class);
        viewModel.getData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.notifyDataChanged(strings);
            }
        });
        super.initData();
    }

    @Override
    public void onItemClick(@NonNull View v, int viewType, @NonNull Object data, int position) {
        switch ((String) data) {
            case "单向链表":
                new SignleLinkedListDialog().show(context);
                break;
        }
    }
}
