package com.example.screenmirroring.VideoPlayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screenmirroring.Adapter.FolderVideoAdapter;
import com.example.screenmirroring.databinding.ActivityFolderVideoBinding;

import java.util.ArrayList;

public class FolderVideoActivity extends AppCompatActivity {


    public static ArrayList<String> Video_Folder_Data;
    ArrayList<String> video = new ArrayList<>();
    Boolean isfirsttime = true;
    String name;

    Activity activity;
    ActivityFolderVideoBinding binding;
    String folder_path;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFolderVideoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;
        todo();
    }

    private void todo() {

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        video = Video_Folder_Data;

        FolderVideoAdapter videoAdapter = new FolderVideoAdapter(activity,video);
        binding.foldervideorecycler.setAdapter(videoAdapter);
    }

}