package com.example.developerandroidx.ui.android.notification;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.RouteUtil;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;

import butterknife.BindView;
import butterknife.OnClick;

public class NotificationActivity extends BaseActivity {

    @BindView(R.id.esv_content)
    ExtensibleScrollView esv_content;
    @BindView(R.id.iv_code)
    ImageView iv_code;

    float Y = 0;

    @Override
    protected int bindLayout() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("通知中心");
        esv_content.setOnScrollChangedListener(new ExtensibleScrollView.OnScrollChangedListener() {
            @Override
            public void toUp(boolean isToTop) {
                if (iv_code.getVisibility() == View.VISIBLE) {
                    iv_code.setVisibility(View.GONE);
                    iv_code.startAnimation(AnimUtil.getInstance().
                            getScaleAnim(1, 0, 1, 0, 300, 0, null));
                }
            }

            @Override
            public void toDown(boolean isToBottom) {
                if (iv_code.getVisibility() == View.GONE) {
                    iv_code.setVisibility(View.VISIBLE);
                    iv_code.startAnimation(AnimUtil.getInstance().
                            getScaleAnim(0, 1, 0, 1, 300, 0, null));
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
//        esv_content.addText("", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("请注意，本页中的代码使用 Android 支持库中的 NotificationCompat API。这些 API 允许您添加仅在较新版本 Android 上可用的功能，同时仍向后兼容 Android 4.0（API 级别 14）。但是，诸如内嵌回复操作等部分新功能在较旧版本上会导致发生空操作。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("请注意，本页中的代码使用 Android 支持库中的 NotificationCompat API。这些 API 允许您添加仅在较新版本 Android 上可用的功能，同时仍向后兼容 Android 4.0（API 级别 14）。但是，诸如内嵌回复操作等部分新功能在较旧版本上会导致发生空操作。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("请注意，本页中的代码使用 Android 支持库中的 NotificationCompat API。这些 API 允许您添加仅在较新版本 Android 上可用的功能，同时仍向后兼容 Android 4.0（API 级别 14）。但是，诸如内嵌回复操作等部分新功能在较旧版本上会导致发生空操作。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("请注意，本页中的代码使用 Android 支持库中的 NotificationCompat API。这些 API 允许您添加仅在较新版本 Android 上可用的功能，同时仍向后兼容 Android 4.0（API 级别 14）。但是，诸如内嵌回复操作等部分新功能在较旧版本上会导致发生空操作。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("请注意，本页中的代码使用 Android 支持库中的 NotificationCompat API。这些 API 允许您添加仅在较新版本 Android 上可用的功能，同时仍向后兼容 Android 4.0（API 级别 14）。但是，诸如内嵌回复操作等部分新功能在较旧版本上会导致发生空操作。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("请注意，本页中的代码使用 Android 支持库中的 NotificationCompat API。这些 API 允许您添加仅在较新版本 Android 上可用的功能，同时仍向后兼容 Android 4.0（API 级别 14）。但是，诸如内嵌回复操作等部分新功能在较旧版本上会导致发生空操作。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("请注意，本页中的代码使用 Android 支持库中的 NotificationCompat API。这些 API 允许您添加仅在较新版本 Android 上可用的功能，同时仍向后兼容 Android 4.0（API 级别 14）。但是，诸如内嵌回复操作等部分新功能在较旧版本上会导致发生空操作。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("请注意，本页中的代码使用 Android 支持库中的 NotificationCompat API。这些 API 允许您添加仅在较新版本 Android 上可用的功能，同时仍向后兼容 Android 4.0（API 级别 14）。但是，诸如内嵌回复操作等部分新功能在较旧版本上会导致发生空操作。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("创建基本通知", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
        esv_content.addText("", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);


    }

    @OnClick({R.id.iv_code})
    public void click(View v) {
        RouteUtil.goToCodeViewActivity(context, CodeVariate.getInstance().getCode_7());
    }
}
