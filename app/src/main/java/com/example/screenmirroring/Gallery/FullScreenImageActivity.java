package com.example.screenmirroring.Gallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.screenmirroring.Adapter.FullScreenImageAdapter;
import com.example.screenmirroring.R;

import java.util.ArrayList;

public class FullScreenImageActivity extends AppCompatActivity {

    public static ArrayList<String> FolderData;
    Activity activity;

    ViewPager pager;
    ArrayList<String> img = new ArrayList<>();
    int pos;

    FullScreenImageAdapter fullScreenImageAdapter;
    float sumPositionAndpostionOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_screen_image);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity = this;

        img = FolderData;
        pos = getIntent().getIntExtra("pos", 1);
        pager = findViewById(R.id.pager);
        fullScreenImageAdapter = new FullScreenImageAdapter(activity, img);
        pager.setAdapter(fullScreenImageAdapter);
        pager.setCurrentItem(pos);
    }
}