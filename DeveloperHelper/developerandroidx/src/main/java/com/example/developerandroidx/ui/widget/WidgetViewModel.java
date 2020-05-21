package com.example.developerandroidx.ui.widget;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.developerandroidx.R;
import com.example.developerandroidx.bean.FunctionItemBean;
import com.example.developerandroidx.ui.widget.actionBar.ActionBarActivity;
import com.example.developerandroidx.ui.widget.navigationView.NavigationViewActivity;
import com.example.developerandroidx.ui.widget.webView.WebViewActivity;
import com.example.developerandroidx.utils.RouteUtil;

import java.util.ArrayList;
import java.util.List;

public class WidgetViewModel extends ViewModel {

    private MutableLiveData<List<FunctionItemBean>> mList;

    public WidgetViewModel() {
        mList = new MutableLiveData<>();
        mList.setValue(initData());
    }

    private List<FunctionItemBean> initData() {
        List<FunctionItemBean> functionList = new ArrayList<>();
        functionList.add(new FunctionItemBean("ActionBar", R.mipmap.icon_action, RouteUtil.getDestination(ActionBarActivity.class)));
        functionList.add(new FunctionItemBean("ToolBar", R.mipmap.icon_tool_bar, ""));
        functionList.add(new FunctionItemBean("RecyclerView", R.mipmap.icon_recycler, ""));
        functionList.add(new FunctionItemBean("CardView", R.mipmap.icon_card_view, ""));
        functionList.add(new FunctionItemBean("WebView", R.mipmap.icon_web_view, RouteUtil.getDestination(WebViewActivity.class)));
        functionList.add(new FunctionItemBean("CalendarView", R.mipmap.icon_web_view, ""));
        functionList.add(new FunctionItemBean("Custom Toast", R.mipmap.icon_web_view, ""));
        functionList.add(new FunctionItemBean("Snackbar", R.mipmap.icon_web_view, ""));
        functionList.add(new FunctionItemBean("Navigation", R.mipmap.icon_web_view, RouteUtil.getDestination(NavigationViewActivity.class)));

        return functionList;
    }

    public LiveData<List<FunctionItemBean>> getAdapterList() {
        return mList;
    }
}