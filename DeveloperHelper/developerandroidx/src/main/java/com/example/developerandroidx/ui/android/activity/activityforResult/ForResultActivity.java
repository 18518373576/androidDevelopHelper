package com.example.developerandroidx.ui.android.activity.activityforResult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.R;

public class ForResultActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_for_result);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.btn_finish).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("res", "这是返回的消息");
        switch (v.getId()) {
            case R.id.iv_back:
                setResult(100, resultIntent);
                finish();
                break;
            case R.id.btn_finish:
                setResult(100, resultIntent);
                finish();
                break;
        }
    }
}
