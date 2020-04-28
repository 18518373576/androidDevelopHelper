package com.example.developerandroidx.ui.widget;

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

public class WidgetFragment extends Fragment {

    private WidgetViewModel widgetViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        widgetViewModel =
                ViewModelProviders.of(this).get(WidgetViewModel.class);
        View root = inflater.inflate(R.layout.fragment_widget, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        widgetViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
