package com.example.developerandroidx.ui.java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.developerandroidx.R;

public class JavaFragment extends Fragment {

    private JavaViewModel javaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        javaViewModel =
                ViewModelProviders.of(this).get(JavaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_java, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        javaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
