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
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.DialogUtils;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： zjf 2020/6/8 2:13 PM
 * 参考：
 * 描述：rxJava使用
 */
public class RxJavaSampleActivity extends BaseActivity implements OnItemClickListener {

    @BindView(R.id.rcv_rxjava_sample)
    RecyclerView rcv_rxjava_sample;
    private RxJavaSampleRcvAdapter adapter;
    private RxJavaSampleViewModel viewModel;
    private String currentList = "操作符";//当前展示的列表

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
        iv_right.setVisibility(View.VISIBLE);

        iv_right.setOnClickListener(v ->
                DialogUtils.getInstance().showBottomMenu(context, new String[]{"操作符", "实际应用"}, (text, index) -> {
                    currentList = text;
                    viewModel.getData(text);
                }));
    }

    @Override
    protected void initData() {
        super.initData();
        viewModel = (RxJavaSampleViewModel) getViewModel(this, RxJavaSampleViewModel.class);
        viewModel.getData("操作符").observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.setList(strings);
            }
        });
    }

    private StringBuffer buffer;

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        switch (currentList) {
            case "操作符":
                showOperator(position);
                break;
            case "实际应用":
                showSample(position);
                break;
        }
    }

    private void showSample(int position) {

    }

    private void showOperator(int position) {
        switch (position) {
            case 0:
                //创建一个事件
                Observable.just("Observable.just(\"一个简单的示例\")").subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        DialogUtils.getInstance().showMessageDialog(context, "提示", s);
                    }
                });
                break;
            case 1:
                buffer = new StringBuffer();
                buffer.append("\n");
                //从集合创建事件
                Observable.fromIterable((List<String>) adapter.getData()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        buffer.append(s + "\n");
                    }
                });
                DialogUtils.getInstance().showMessageDialog(context, buffer.toString());
                break;
            case 2:
                buffer = new StringBuffer();
                buffer.append(" ");
                //在一个整数范围内取值
                Observable.range(1, 10).filter(integer -> integer % 2 == 0)
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                buffer.append(integer + " ");
                            }
                        });
                DialogUtils.getInstance().showMessageDialog(context, buffer.toString());
                break;
            case 3:
                buffer = new StringBuffer();
                buffer.append(" ");
                //map转换事件数据类型
                Observable.range(1, 5).map(integer -> "数据:" + integer)
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                buffer.append(s + " ");
                            }
                        });
                DialogUtils.getInstance().showMessageDialog(context, buffer.toString());
                break;
            case 4:
                buffer = new StringBuffer();
                buffer.append("\n");
                //将事件转换为新的事件进行发送
                Observable.range(1, 3).flatMap(integer -> Observable.just("事件被转换:" + integer))
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                buffer.append(s + "\n");
                            }
                        });
                DialogUtils.getInstance().showMessageDialog(context, buffer.toString());
                break;
            case 5:
                buffer = new StringBuffer();
                buffer.append("\n");
                //设置事件发送缓存
                Observable.just(1, 2, 3, 4, 5).buffer(2, 2)
                        .subscribe(new Consumer<List<Integer>>() {
                            @Override
                            public void accept(List<Integer> integers) throws Exception {
                                buffer.append("缓存事件数量:" + integers.size() + "\n");
                                for (int enent : integers) {
                                    buffer.append("被缓存的事件:" + enent + "\n");
                                }
                            }
                        });
                DialogUtils.getInstance().showMessageDialog(context, buffer.toString());
                break;
            case 6:
                DialogUtils.getInstance().showLoadingDialog(context, "延迟");
                //事件延迟发送
                Observable.timer(2, TimeUnit.SECONDS).map(aLong -> "延迟两秒:" + aLong)
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                DialogUtils.getInstance().dismissLoadingDialog();
                                DialogUtils.getInstance().showMessageDialog(context, s);
                            }
                        });
                break;
            case 7:
                buffer = new StringBuffer();
                buffer.append(" ");
                //组合多个被观察者一起发送,按发送顺序串行执行
                Observable.concat(Observable.just(1, 2), Observable.just(3, 4))
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                buffer.append(integer + " ");
                            }
                        });
                DialogUtils.getInstance().showMessageDialog(context, buffer.toString());
                break;
            case 8:
                //组合多个被观察者，并行发送事件
                Observable.merge(Observable.intervalRange(1, 2, 0, 2, TimeUnit.SECONDS),
                        Observable.intervalRange(3, 2, 1, 2, TimeUnit.SECONDS))
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                DialogUtils.getInstance().showTip(context, aLong + "");
                            }
                        });
                break;
            case 9:
                //组合两个线程的被观察者
                Observable<Long> observable1 = Observable.intervalRange(1, 2, 0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread());
                Observable<Long> observable2 = Observable.intervalRange(3, 2, 0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread());
                Observable.zip(observable1, observable2, (aLong, aLong2) -> aLong + "、" + aLong2).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                DialogUtils.getInstance().showTip(context, s);
                            }
                        });
                break;
            case 10:
                //将先发送数据的Observable的最后一个数据，与后发送数据的Observable中的每一个数据组合，最终基于组合后的数据发送结果
                Observable.combineLatest(Observable.just(1, 2, 3), Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS), (integer, aLong) -> integer + aLong)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                DialogUtils.getInstance().showTip(context, String.valueOf(aLong));
                            }
                        });
                break;
            case 11:
                buffer = new StringBuffer();
                //把被观察者发送的事件合并为一个事件发送
                Observable.just(1, 2, 3, 4).reduce((integer, integer2) -> {
                    buffer.append(integer + "*" + integer2 + "=" + integer * integer2 + "\n");
                    return integer * integer2;
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        buffer.append("最终结果:" + integer);
                        DialogUtils.getInstance().showMessageDialog(context, buffer.toString());
                    }
                });
                break;
            case 12:
                //把Observable发送的数据组合到一个数据结构里面
                Observable.just(1, 2, 3, 4, 5).collect(() -> new ArrayList<>(), (BiConsumer<List<Integer>, Integer>) (integers, integer) -> integers.add(integer))
                        .subscribe(new Consumer<ArrayList<Integer>>() {
                            @Override
                            public void accept(ArrayList<Integer> integers) throws Exception {
                                DialogUtils.getInstance().showTip(context, "集合的size:" + integers.size());
                            }
                        });
                break;
            case 13:
                buffer = new StringBuffer();
                buffer.append(" ");
                //在被观察者发送事件之前追加数据
                Observable.just(4, 5, 6).startWith(Observable.just(1, 2, 3))
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                buffer.append(integer + " ");
                            }
                        });
                DialogUtils.getInstance().showMessageDialog(context, buffer.toString());
                break;
            case 14:
                Observable.just(1, 2, 3).count()
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                DialogUtils.getInstance().showTip(context, "count:" + aLong);
                            }
                        });
                break;
        }
    }
}
