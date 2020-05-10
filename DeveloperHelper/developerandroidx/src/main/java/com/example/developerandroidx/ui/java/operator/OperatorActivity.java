package com.example.developerandroidx.ui.java.operator;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.OperatorRcvAdapter;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.bean.OperatorItemBean;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.RouteUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OperatorActivity extends BaseActivity {

    private String TAG = "com.example.developerandroidx.ui.java.operator.OperatorActivity";
    @BindView(R.id.rcv_operator)
    RecyclerView rcv_operator;
    @BindView(R.id.iv_codes)
    ImageView iv_codes;

    @Override
    protected int bindLayout() {
        return R.layout.activity_operator;
    }

    @OnClick({R.id.iv_codes})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.iv_codes:
                RouteUtil.goToCodeViewActivity(context, CodeVariate.getInstance().getCode_4());
                break;
        }
    }

    @Override
    protected void initView() {

        setTitle(getResources().getString(R.string.operator));
        rcv_operator.setLayoutManager(new LinearLayoutManager(context));
        rcv_operator.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.e(TAG + ":", dx + "$" + dy);
                //dy < 0为上滑
                if (dy <= 0) {
                    if (iv_codes.getVisibility() == View.GONE)
                        iv_codes.setVisibility(View.VISIBLE);
                } else {
                    if (iv_codes.getVisibility() == View.VISIBLE)
                        iv_codes.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initData() {

        OperatorViewModel viewModel = ViewModelProviders.of(this).get(OperatorViewModel.class);
//        viewModel.bindLifeCircle(this);
        viewModel.getAdapterList().observe(this, new Observer<List<OperatorItemBean>>() {
            @Override
            public void onChanged(List<OperatorItemBean> operatorItemBeans) {
                rcv_operator.setAdapter(new OperatorRcvAdapter(operatorItemBeans));
            }
        });
    }

    @Override
    public void onDestroy() {
        rcv_operator.clearOnScrollListeners();
        BaseRcvAdapter.releaseAllHolder(rcv_operator);
        super.onDestroy();
    }
}
