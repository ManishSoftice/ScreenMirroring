package com.example.screenmirroring.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screenmirroring.Gallery.AllPhotoFolderActivity;
import com.example.screenmirroring.VideoPlayer.VideoPlayerActivity;
import com.example.screenmirroring.connect_TV.Connect_tv_Activity;
import com.example.screenmirroring.databinding.ActivityManiScreenBinding;

public class Main_Screen_Activity extends AppCompatActivity {

    Activity activity;
    private ActivityManiScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManiScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;
        todo();

    }

    private void todo() {

        binding.connectToTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(activity, Connect_tv_Activity.class));
            }
        });

        binding.videoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, VideoPlayerActivity.class));
            }
        });

        binding.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, AllPhotoFolderActivity.class));
            }
        });
    }

}