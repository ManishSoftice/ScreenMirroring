package com.example.screenmirroring.connect_TV;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screenmirroring.databinding.ActivityConnectTvBinding;

public class Connect_tv_Activity extends AppCompatActivity {

    Activity activity;
    private ActivityConnectTvBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnectTvBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;
        todo();
    }

    private void todo() {

        binding.connectNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, All_Compny_Name_Activity.class));
            }
        });

        binding.playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, PlayvideonowActivity.class));
            }
        });
    }
}