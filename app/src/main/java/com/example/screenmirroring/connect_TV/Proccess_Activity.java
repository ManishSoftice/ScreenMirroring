package com.example.screenmirroring.connect_TV;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screenmirroring.R;
import com.example.screenmirroring.databinding.ActivityProccessBinding;

public class Proccess_Activity extends AppCompatActivity {

    Activity activity;
    ActivityProccessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProccessBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;
        todo();

    }

    private void todo() {

        setprocess(1);
        binding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity,Steps_Activity.class));
            }
        });
    }

    private void setprocess(int i) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                switch (i) {
                    case 1:
                        binding.image1.setImageResource(R.drawable.ic_baseline_offline_pin_24);
                        setprocess(2);
                        break;
                    case 2:
                        binding.image2.setImageResource(R.drawable.ic_baseline_offline_pin_24);
                        setprocess(3);
                        break;
                    case 3:
                        binding.image3.setImageResource(R.drawable.ic_baseline_offline_pin_24);
                        setprocess(4);
                        break;
                    case 4:
                        binding.image4.setImageResource(R.drawable.ic_baseline_offline_pin_24);
                        binding.ok.setVisibility(View.VISIBLE);
                        binding.animationView.pauseAnimation();
                        break;
                }
            }
        }, 2000);
    }
}