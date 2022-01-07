package com.jenuvid.scrnmirroring.connect_TV;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

import com.jenuvid.scrnmirroring.databinding.ActivityPlayVideoBinding;

public class PlayVideoActivity extends AppCompatActivity {

    Activity activity;
    ActivityPlayVideoBinding binding;

    String videopath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlayVideoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;
        todo();
    }

    private void todo() {
        Intent intent = getIntent();
        videopath = intent.getStringExtra("url");

        binding.videoView.setVideoPath(videopath);
        binding.videoView.start();
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(binding.videoView);
        binding.videoView.setMediaController(mediaController);

    }

}