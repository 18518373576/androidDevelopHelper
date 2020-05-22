package com.example.developerandroidx.ui.widget.codeView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;
import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

/**
 * 描述：代码展示界面
 * 引用：https://github.com/Thereisnospon/CodeView
 */
public class CodeViewActivity extends BaseActivity {

    private String code;

    @BindView(R.id.cv_code_view)
    CodeView cv_code_view;
    @BindView(R.id.tltle)
    View tltle;
    @BindView(R.id.iv_shrink)
    ImageView iv_shrink;

    private ChangeOrientationHandler handler;
    private SensorManager sm;
    private Sensor sensor;
    private OrientationSensorListener listener;

    private class ChangeOrientationHandler extends Handler {
        @SuppressLint("SourceLockedOrientationActivity")
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 888) {
                int orientation = msg.arg1;
                if (orientation > 45 && orientation < 135) {
                    if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
//                Log.d(MainActivity.TAG, "横屏翻转: ");
                } else if (orientation > 135 && orientation < 225) {
                    //倒转竖屏，效果还是竖屏，所以不做处理
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
//                Log.d(MainActivity.TAG, "竖屏翻转: ");
                } else if (orientation > 225 && orientation < 315) {
                    if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE)
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                Log.d(MainActivity.TAG, "横屏: ");
                } else if ((orientation > 315 && orientation < 360) || (orientation > 0 && orientation < 45)) {
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                Log.d(MainActivity.TAG, "竖屏: ");
                }
            }
            super.handleMessage(msg);
        }
    }

    private class OrientationSensorListener implements SensorEventListener {
        private static final int _DATA_X = 0;
        private static final int _DATA_Y = 1;
        private static final int _DATA_Z = 2;

        public static final int ORIENTATION_UNKNOWN = -1;

        private Handler rotateHandler;


        public OrientationSensorListener(Handler handler) {
            rotateHandler = handler;
        }

        public void onAccuracyChanged(Sensor arg0, int arg1) {
            // TODO Auto-generated method stub

        }

        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            int orientation = ORIENTATION_UNKNOWN;
            float X = -values[_DATA_X];
            float Y = -values[_DATA_Y];
            float Z = -values[_DATA_Z];
            float magnitude = X * X + Y * Y;
            // Don't trust the angle if the magnitude is small compared to the y value
            if (magnitude * 4 >= Z * Z) {
                float OneEightyOverPi = 57.29577957855f;
                float angle = (float) Math.atan2(-Y, X) * OneEightyOverPi;
                orientation = 90 - (int) Math.round(angle);
                // normalize to 0 - 359 range
                while (orientation >= 360) {
                    orientation -= 360;
                }
                while (orientation < 0) {
                    orientation += 360;
                }
            }

            if (rotateHandler != null) {
                rotateHandler.obtainMessage(888, orientation, 0).sendToTarget();
            }
        }
    }


    @Override
    protected int bindLayout() {
        return R.layout.activity_code_view;
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void initView() {
        cv_code_view.setTheme(CodeViewTheme.ANDROIDSTUDIO);
        cv_code_view.fillColor();
        tltle.setBackgroundResource(R.color.codeViewBackground);
        setTopBarTextLight();
        setTitle("Code");
        iv_right.setImageResource(R.mipmap.icon_blow_up);
        iv_right.setVisibility(View.VISIBLE);

        //取消使用重力感应切换，使用体验比较差，改用放大缩小按钮
        //根据手重力感应切换屏幕方向
        handler = new ChangeOrientationHandler();

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new OrientationSensorListener(handler);
        sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);

        //横屏的时候不显示title栏，可视面积最大化
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ||
                getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
            tltle.setVisibility(View.GONE);
            iv_shrink.setVisibility(View.VISIBLE);
        } else {
            tltle.setVisibility(View.VISIBLE);
            iv_shrink.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        DialogUtils.getInstance().showLoadingDialog(context, "正在加载...");
        code = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);
        cv_code_view.showCode(code);

        cv_code_view.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    DialogUtils.getInstance().dismissLoadingDialog();
                }
            }
        });
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @OnClick({R.id.iv_right, R.id.iv_shrink})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.iv_right:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                break;
            case R.id.iv_shrink:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
        }
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(listener);
        super.onPause();
    }

    @Override
    protected void onResume() {
        sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

}
