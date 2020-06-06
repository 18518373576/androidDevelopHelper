package com.example.developerandroidx.ui.android.httpRequest;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.BlogListRcvAdapter;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.base.BaseModel;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.model.BlogListBean;
import com.example.developerandroidx.ui.android.httpRequest.historyBlog.HistoryBlogActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.PreferenceUtils;
import com.example.developerandroidx.utils.RouteUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 数据请求示例
 */
public class HttpRequestActivity extends BaseActivity implements BaseRcvAdapter.OnRecyclerViewItemClickListner {

    @BindView(R.id.rcv_blog)
    RecyclerView rcv_blog;

    private String requestLibrary;
    private HttpRequestViewModel viewModel;
    private BlogListRcvAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_http_request;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("公众号");
        rcv_blog.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BlogListRcvAdapter(new ArrayList<>());
        rcv_blog.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        /**
         * {@link com.example.developerandroidx.ui.android.httpRequest.dialog.HttpRequestLibraryDialog}
         *  请求使用的库 "OkHttp", "Volley", "Retrofit"
         */
        requestLibrary = PreferenceUtils.getInstance().getStringalue(Constant.PreferenceKeys.HTTP_REQUEST_LIBRARY);
        viewModel = (HttpRequestViewModel) getViewModel(this, HttpRequestViewModel.class);
        viewModel.getData(requestLibrary).observe(this, new Observer<BaseModel>() {
            @Override
            public void onChanged(BaseModel baseModel) {
                BlogListBean blogListBean = (BlogListBean) baseModel;
                adapter.notifyDataChanged(blogListBean.data);
                if (rcv_blog.getVisibility() == View.INVISIBLE) {
                    showView();
                }
            }
        });
    }

    /**
     * 加载数据过后展示列表
     * 目前网络比较快，加载少量数据，为了更好的体验使用动画过渡，不使用loading框
     */
    private void showView() {
        rcv_blog.setAlpha(0);
        rcv_blog.setVisibility(View.VISIBLE);
        rcv_blog.animate().alpha(1f).setDuration(500);
    }

    /**
     * RecyclerView条目点击事件
     *
     * @param v        被点击的view
     * @param viewType 布局类型
     * @param data     数据
     * @param position 在RecyclerView中的位置
     */
    @Override
    public void onItemClick(@NonNull View v, int viewType, @NonNull Object data, int position) {
        BlogListBean.Model model = (BlogListBean.Model) data;
        RouteUtil.goTo(context, RouteUtil.getDestination(HistoryBlogActivity.class), String.valueOf(model.id));
    }
}