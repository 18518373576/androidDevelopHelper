package com.example.developerandroidx.ui.android.activity.launchMode;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.R;

public class SingleInstanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_model);
    }
}
