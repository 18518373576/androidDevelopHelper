package com.example.developerandroidx.adapter;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseRcvAdapter;
import com.example.developerandroidx.base.BaseRcvHolder;
import com.example.developerandroidx.model.EventBusMessageBean;
import com.example.developerandroidx.model.FunctionItemBean;
import com.example.developerandroidx.ui.android.architecture.ArchitectureIndexDialog;
import com.example.developerandroidx.ui.android.dialog.indexDialog.IndexDialog;
import com.example.developerandroidx.ui.android.httpRequest.dialog.HttpRequestLibraryDialog;
import com.example.developerandroidx.ui.android.notification.NotificationDialog;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.RouteUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class FunctionRcvAdapter extends BaseRcvAdapter<FunctionItemBean> {
    /**
     * 构造方法，初始化数据
     *
     * @param mList
     */
    public FunctionRcvAdapter(List<FunctionItemBean> mList) {
        super(mList);

        //设置点击事件，三个界面比较统一，所以在这里进行了一次性设置
        setOnItemClickListener(new OnRecyclerViewItemClickListner() {
            @Override
            public void onItemClick(@NonNull View v, int viewType, @NonNull Object data, int position) {
                FunctionItemBean itemBean = (FunctionItemBean) data;
                if (!TextUtils.isEmpty(itemBean.goTo)) {
                    RouteUtil.goTo(v.getContext(), itemBean.goTo, itemBean.paramsMap, itemBean.paramStr);//路由到指定界面
                } else {
                    switch (itemBean.itemName) {
                        /**
                         * 不需要路由，展示弹框{@link com.example.developerandroidx.ui.android.AndroidViewModel}
                         */
                        case "Notification":
                            new NotificationDialog().show(v.getContext());
                            break;
                        /**
                         *给{@link com.example.developerandroidx.ui.MainActivity}发消息，启动扫码界面
                         */
                        case "扫码":

                            EventBus.getDefault().post(new EventBusMessageBean(Constant.EventBusMsgId.START_SCAN, this.getClass().getName(), ""));
                            break;
                        /**
                         * 弹框展示数据请求库{@link com.example.developerandroidx.ui.android.AndroidViewModel}
                         */
                        case "HttpRequest":
                            new HttpRequestLibraryDialog().show(v.getContext());
                            break;
                        case "Dialog":
                            new IndexDialog().show(v.getContext());
                            break;
                        case "架构":
                            new ArchitectureIndexDialog().show(v.getContext());
                            break;
                        default:
                            DialogUtils.getInstance().showWarningTip(v.getContext(), "developing");
                            break;
                    }
                }
            }
        });
    }

    /**
     * 绑定item布局
     *
     * @param viewType
     * @return
     */
    @Override
    protected int bindItemLayout(int viewType) {
        return R.layout.item_function;
    }

    /**
     * 绑定ViewHolder
     *
     * @param v        用于展示的 {@link View}
     * @param viewType 布局类型
     * @return
     */
    @NonNull
    @Override
    protected BaseRcvHolder<FunctionItemBean> bindHolder(@NonNull View v, int viewType) {
        return new FunctionRcvHolder(v);
    }
}
