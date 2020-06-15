package com.example.developerandroidx.ui.java.arithmetic;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.ArithmeticRcvAdapter;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.model.ArithMeticModel;
import com.example.developerandroidx.ui.java.arithmetic.dialog.BubbleSortDialog;
import com.example.developerandroidx.ui.java.arithmetic.dialog.GetNodeDialog;
import com.example.developerandroidx.ui.java.arithmetic.dialog.ReversalLinkedListDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ArithmeticActivity extends BaseActivity implements BaseRcvAdapter.OnRecyclerViewItemClickListner {

    @BindView(R.id.rcv_arithmetic)
    RecyclerView rcv_arithmetic;
    private ArithmeticRcvAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_arithmetic;
    }

    @Override
    protected void initView() {
        setTitle("算法");
        rcv_arithmetic.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new ArithmeticRcvAdapter(new ArrayList<>());
        rcv_arithmetic.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        ArithmeticViewModel viewModel = (ArithmeticViewModel) getViewModel(this, ArithmeticViewModel.class);
        viewModel.getData().observe(this, new Observer<List<ArithMeticModel>>() {
            @Override
            public void onChanged(List<ArithMeticModel> meticModels) {
                adapter.notifyDataChanged(meticModels);
            }
        });
    }

    @Override
    public void onItemClick(@NonNull View v, int viewType, @NonNull Object data, int position) {
        switch (position) {

            case 0:
                new BubbleSortDialog().show(context);
                break;
            case 8:
                new GetNodeDialog().show(context);
                break;
            case 9:
                new ReversalLinkedListDialog().show(context);
                break;
        }

    }
}
