package com.example.developerandroidx.ui.android.httpRequest.historyBlog;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.BlogHistoryRcvAdapter;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.base.BaseModel;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.model.HistoryBlogBean;
import com.example.developerandroidx.ui.widget.webView.TechnologyWebviewActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.PreferenceUtils;
import com.example.developerandroidx.utils.RouteUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class HistoryBlogActivity extends BaseActivity implements BaseRcvAdapter.OnRecyclerViewItemClickListner {

    @BindView(R.id.rcv_blog_history)
    RecyclerView rcv_blog_history;
    private BlogHistoryRcvAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_history_blog;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("历史文章");
        rcv_blog_history.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BlogHistoryRcvAdapter(new ArrayList<>());
        rcv_blog_history.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        String id = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);
        String requestLibrary = PreferenceUtils.getInstance().getStringalue(Constant.PreferenceKeys.HTTP_REQUEST_LIBRARY);
        HistoryBlogViewModel viewModel = (HistoryBlogViewModel) getViewModel(this, HistoryBlogViewModel.class);
        viewModel.getData(requestLibrary, id).observe(this, new Observer<BaseModel>() {
            @Override
            public void onChanged(BaseModel baseModel) {
                HistoryBlogBean historyBlogBean = (HistoryBlogBean) baseModel;
                adapter.notifyDataChanged(historyBlogBean.data.datas);
                if (rcv_blog_history.getVisibility() == View.INVISIBLE) {
                    showView();
                }
            }
        });

    }

    private void showView() {
        rcv_blog_history.setAlpha(0);
        rcv_blog_history.setVisibility(View.VISIBLE);
        rcv_blog_history.animate().alpha(1f).setDuration(500);
    }

    @Override
    public void onItemClick(@NonNull View v, int viewType, @NonNull Object data, int position) {
        HistoryBlogBean.Model model = (HistoryBlogBean.Model) data;
        RouteUtil.goTo(context, RouteUtil.getDestination(TechnologyWebviewActivity.class), ((HistoryBlogBean.Model) data).link);
    }
}