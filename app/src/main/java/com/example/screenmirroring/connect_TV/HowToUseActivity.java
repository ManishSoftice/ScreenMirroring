package com.example.screenmirroring.connect_TV;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screenmirroring.databinding.ActivityHowToUseBinding;

public class HowToUseActivity extends AppCompatActivity {

    Activity activity;
    ActivityHowToUseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHowToUseBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        todo();
    }

    private void todo() {

        binding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}