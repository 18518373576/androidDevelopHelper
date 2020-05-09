package com.example.developerandroidx.ui.android.activity.actionIntent;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.developerandroidx.R;

public class ActionIntentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_intent);
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView tv_message = findViewById(R.id.tv_message);
        Uri data = getIntent().getData();
        if (data != null) {
            tv_message.setText("");
            tv_message.append(data.getScheme() + "\n");
            tv_message.append(data.getHost() + "\n");
            tv_message.append(data.getPort() + "\n");
            tv_message.append(data.getPath() + "\n");
            tv_message.append(data.getQueryParameter("message") + "\n");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
