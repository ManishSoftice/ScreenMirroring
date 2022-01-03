package com.example.screenmirroring.connect_TV;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screenmirroring.databinding.ActivityStepsBinding;

public class Steps_Activity extends AppCompatActivity {


    Activity activity;
    ActivityStepsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStepsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;
        todo();
    }

    private void todo() {
        binding.screenMirroring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent("android.settings.CAST_SETTINGS"));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(activity, "Device not supported", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.howToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, HowToUseActivity.class));
            }
        });

        binding.playVideoNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, PlayvideonowActivity.class));
            }
        });
    }
}