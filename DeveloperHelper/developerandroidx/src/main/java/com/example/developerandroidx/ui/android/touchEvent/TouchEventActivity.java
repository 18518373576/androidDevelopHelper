package com.example.developerandroidx.ui.android.touchEvent;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.touchEventView.TouchEventView;
import com.kongzue.dialog.v3.FullScreenDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 触摸事件的处理与拦截
 */
public class TouchEventActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.tev_content)
    TouchEventView tev_content;

    @Override
    protected int bindLayout() {
        return R.layout.activity_touch_event;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("触摸事件");
        iv_right.setVisibility(View.VISIBLE);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @OnClick({R.id.iv_right})
    public void click(View v) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_set_touch_event, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {

                RadioButton rb_intercept_return_true = rootView.findViewById(R.id.rb_intercept_return_true);
                rb_intercept_return_true.setOnCheckedChangeListener(TouchEventActivity.this);
                RadioGroup rg_intercept_setting = rootView.findViewById(R.id.rg_intercept_setting);
                rg_intercept_setting.check(tev_content.getInterceptReturn() ? R.id.rb_intercept_return_true : R.id.rb_intercept_return_false);

                RadioButton rb_vg_touch_return_true = rootView.findViewById(R.id.rb_vg_touch_return_true);
                rb_vg_touch_return_true.setOnCheckedChangeListener(TouchEventActivity.this);
                RadioGroup rg_vg_touch_event_setting = rootView.findViewById(R.id.rg_vg_touch_event_setting);
                rg_vg_touch_event_setting.check(tev_content.getVgTouchReturn() ? R.id.rb_vg_touch_return_true : R.id.rb_vg_touch_return_false);


                RadioButton rb_cv_touch_return_true = rootView.findViewById(R.id.rb_cv_touch_return_true);
                rb_cv_touch_return_true.setOnCheckedChangeListener(TouchEventActivity.this);
                RadioGroup rg_cv_touch_event_setting = rootView.findViewById(R.id.rg_cv_touch_event_setting);
                rg_cv_touch_event_setting.check(tev_content.getCvTouchReturn() ? R.id.rb_cv_touch_return_true : R.id.rb_cv_touch_return_false);

            }
        });
    }

    /**
     * RadioButton监听回调
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.rb_intercept_return_true:
                tev_content.setInterceptReturn(isChecked);
                break;
            case R.id.rb_vg_touch_return_true:
                tev_content.setVgTouchReturn(isChecked);
                break;
            case R.id.rb_cv_touch_return_true:
                tev_content.setCvTouchReturn(isChecked);
                break;
        }
    }
}