package com.example.screenmirroring.Gallery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screenmirroring.Adapter.GridViewAdapter;
import com.example.screenmirroring.databinding.ActivityAlbumPhotosBinding;

import java.util.ArrayList;

public class AlbumPhotosActivity extends AppCompatActivity {

    public static ArrayList<String> FolderData;
    Activity activity;

    private RecyclerView gridView;
    GridViewAdapter adapter;
    ImageView backImg;
    TextView lblTitle;
    String name;
    Boolean isfirsttime = true;
    ArrayList<String> image = new ArrayList<>();
    ActivityAlbumPhotosBinding x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityAlbumPhotosBinding.inflate(getLayoutInflater());
        setContentView(x.getRoot());
        activity = this;

        name = getIntent().getStringExtra("name");
        image = FolderData;

        image.add(0, null);
        adapter = new GridViewAdapter(this, image);
        x.gridView.setAdapter(adapter);
    }
}