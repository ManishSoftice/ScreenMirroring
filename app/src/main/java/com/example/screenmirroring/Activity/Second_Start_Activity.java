package com.example.screenmirroring.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screenmirroring.databinding.ActivitySecondStartBinding;

public class Second_Start_Activity extends AppCompatActivity {

    Activity activity;
    private ActivitySecondStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondStartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;
        todo();

    }

    private void todo() {

        binding.secondStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, Main_Screen_Activity.class));
            }
        });

    }
}