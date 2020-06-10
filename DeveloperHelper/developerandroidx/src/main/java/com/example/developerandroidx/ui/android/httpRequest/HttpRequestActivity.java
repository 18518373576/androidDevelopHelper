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
import com.example.developerandroidx.view.loadingView.LoadingPage;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 数据请求示例
 */
public class HttpRequestActivity extends BaseActivity implements BaseRcvAdapter.OnRecyclerViewItemClickListner {

    @BindView(R.id.rcv_blog)
    RecyclerView rcv_blog;
    @BindView(R.id.lv_loading)
    LoadingPage lv_loading;

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
        lv_loading.onLoading(rcv_blog);
        /**
         * {@link com.example.developerandroidx.ui.android.httpRequest.dialog.HttpRequestLibraryDialog}
         *  请求使用的库 "OkHttp", "Volley", "Retrofit"
         */
        requestLibrary = PreferenceUtils.getInstance().getStringalue(Constant.PreferenceKeys.HTTP_REQUEST_LIBRARY);
        viewModel = (HttpRequestViewModel) getViewModel(this, HttpRequestViewModel.class);
        viewModel.getData(requestLibrary).observe(this, new Observer<BaseModel>() {
            @Override
            public void onChanged(BaseModel baseModel) {
                //数据错误
                if (baseModel == null) {
                    lv_loading.loadingFail(rcv_blog, R.mipmap.icon_404);
                } else {

                    BlogListBean blogListBean = (BlogListBean) baseModel;
                    adapter.notifyDataChanged(blogListBean.data);

                    lv_loading.loadingSuc(rcv_blog);
                }
            }
        });
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