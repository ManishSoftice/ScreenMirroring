package com.example.screenmirroring.connect_TV;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screenmirroring.databinding.ActivitySelectModeBinding;

public class Select_Mode_Activity extends AppCompatActivity {

    Activity activity;
    ActivitySelectModeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectModeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;

        todo();

    }

    private void todo() {


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, Proccess_Activity.class));
            }
        });
    }
}