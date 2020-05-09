package com.example.developerandroidx.ui.android.activity.launchMode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.RouteUtil;

public class StandardActivity extends AppCompatActivity implements View.OnClickListener {

    private String param;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_model);
        findViewById(R.id.iv_back).setOnClickListener(this);
        param = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(param);
        findViewById(R.id.btn_jump).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_jump:
                switch (param) {
                    case "standard_activity_1":
                        RouteUtil.goTo(this, RouteUtil.getDestination(StandardActivity.class), "standard_activity_2");
                        break;
                    case "form_singleTask":
                        RouteUtil.goTo(this, RouteUtil.getDestination(SingleTaskActivity.class), "from_standard");
                        break;
                    case "form_singleInstance":
                        RouteUtil.goTo(this, RouteUtil.getDestination(SingleInstanceActivity.class), "from_standard");
                        break;
                }
                break;
        }
    }
}
