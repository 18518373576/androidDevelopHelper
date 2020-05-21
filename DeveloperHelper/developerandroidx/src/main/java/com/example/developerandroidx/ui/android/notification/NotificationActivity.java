package com.example.developerandroidx.ui.android.notification;

import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.widget.webView.TechnologyWebviewActivity;
import com.example.developerandroidx.ui.widget.webView.WebViewActivity;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.utils.CodeVariate;
import com.example.developerandroidx.utils.Constant;
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
            public void onScrolled(int dy) {
                if (dy <= 0) {
                    if (iv_code.getVisibility() == View.GONE) {
                        iv_code.setVisibility(View.VISIBLE);
                        iv_code.startAnimation(AnimUtil.getInstance().
                                getScaleAnim(0, 1, 0, 1, 300, 0, null));
                    }
                } else {
                    if (iv_code.getVisibility() == View.VISIBLE) {
                        iv_code.setVisibility(View.GONE);
                        iv_code.startAnimation(AnimUtil.getInstance().
                                getScaleAnim(1, 0, 1, 0, 300, 0, null));
                    }

                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
//        esv_content.addText("", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("请注意，本页中的代码使用 Android 支持库中的 NotificationCompat API。这些 API 允许您添加仅在较新版本 Android 上可用的功能，同时仍向后兼容 Android 4.0（API 级别 14）。但是，诸如内嵌回复操作等部分新功能在较旧版本上会导致发生空操作。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("创建基本通知", ExtensibleScrollView.InsertTextType.TITLE_2, R.color.textColorBlack);
        esv_content.addText("最基本、精简形式（也称为折叠形式）的通知会显示一个图标、一个标题和少量内容文本。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addImage(R.mipmap.notification_basic, 100);
        esv_content.addText("首先，您需要使用 NotificationCompat.Builder 对象设置通知内容和渠道。以下示例显示了如何创建包含下列内容的通知：", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("1.小图标，通过 setSmallIcon() 设置。这是所必需的唯一一个用户可见内容。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("2.标题，通过 setContentTitle() 设置。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("3.正文文本，通过 setContentText() 设置。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("4.通知优先级，通过 setPriority() 设置。优先级确定通知在 Android 7.1 和更低版本上的干扰程度。（对于 Android 8.0 和更高版本，必须设置渠道重要性。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("请注意，NotificationChannel 构造函数需要一个 importance，它使用 NotificationManager 类中的其中一个常量。该参数确定出现任何属于该渠道的通知时如何打断用户，但您还必须使用 setPriority() 设置优先级以支持 Android 7.1 和更低版本（如上所示）。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        esv_content.addText("虽然必须按本文所示来设置通知重要性/优先级，但系统不能保证您会收到提醒行为。在某些情况下，系统可能会根据其他因素更改重要性级别，并且用户始终可以重新定义给定渠道适用的重要性级别。", ExtensibleScrollView.InsertTextType.BODY, R.color.textColor);
        Intent intent = new Intent(context, TechnologyWebviewActivity.class);
        intent.putExtra(Constant.IntentParams.INTENT_PARAM, "https://developer.android.google.cn/training/notify-user/build-notification");
        esv_content.addBodyWithIntent("了解更多", R.color.colorRed, intent);
    }

    @OnClick({R.id.iv_code})
    public void click(View v) {
        RouteUtil.goToCodeViewActivity(context, CodeVariate.getInstance().getCode_7());
    }
}
