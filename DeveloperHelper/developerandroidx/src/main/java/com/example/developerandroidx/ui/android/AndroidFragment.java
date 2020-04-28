package com.example.developerandroidx.ui.android;

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

public class AndroidFragment extends Fragment {

    private AndroidViewModel androidViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        androidViewModel =
                ViewModelProviders.of(this).get(AndroidViewModel.class);
        View root = inflater.inflate(R.layout.fragment_android, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        androidViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
