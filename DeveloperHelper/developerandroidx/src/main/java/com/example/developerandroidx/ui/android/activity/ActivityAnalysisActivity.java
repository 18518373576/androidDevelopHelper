package com.example.developerandroidx.ui.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.developerandroidx.R;
import com.example.developerandroidx.ui.android.activity.fragment.ActivityAnalysisFragment;

public class ActivityAnalysisActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ActivityAnalysisFragment.newInstance())
                    .commitNow();
        }
    }
}
