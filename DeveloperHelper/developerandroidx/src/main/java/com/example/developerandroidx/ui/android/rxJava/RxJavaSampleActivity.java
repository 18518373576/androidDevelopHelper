package com.example.developerandroidx.ui.android.rxJava;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.quickAdapter.RxJavaSampleRcvAdapter;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.DialogUtils;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * 作者： zjf 2020/6/8 2:13 PM
 * 参考：
 * 描述：rxJava使用
 */
public class RxJavaSampleActivity extends BaseActivity implements OnItemClickListener {

    @BindView(R.id.rcv_rxjava_sample)
    RecyclerView rcv_rxjava_sample;
    private RxJavaSampleRcvAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_rx_java_sample;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("RxJava");
        rcv_rxjava_sample.setLayoutManager(new LinearLayoutManager(context));
        adapter = new RxJavaSampleRcvAdapter();
        adapter.setOnItemClickListener(this);
        rcv_rxjava_sample.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        RxJavaSampleViewModel viewModel = (RxJavaSampleViewModel) getViewModel(this, RxJavaSampleViewModel.class);
        viewModel.getData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setList(strings);
            }
        });
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        switch ((String) adapter.getData().get(position)) {
            case "一个简单的示例":
                Observable.just("Observable.just(\"一个简单的示例\")").
                        subscribe(s -> DialogUtils.getInstance().showMessageDialog(context, "提示", s));
                break;
            case "遍历集合数据":
                StringBuffer buffer = new StringBuffer();
                Observable.fromIterable((List<String>) adapter.getData()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        buffer.append(s + "\n");
                    }
                });
                DialogUtils.getInstance().showMessageDialog(context, "提示", buffer.toString());
                break;
        }
    }
}
