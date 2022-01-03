package com.example.screenmirroring.connect_TV;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screenmirroring.Adapter.AllVideoAdapter;
import com.example.screenmirroring.VideoModel;
import com.example.screenmirroring.databinding.ActivityPlayvideonowBinding;

import java.util.ArrayList;

public class PlayvideonowActivity extends AppCompatActivity {

    Activity activity;
    ActivityPlayvideonowBinding binding;
    ArrayList<VideoModel> videoList = new ArrayList();
    ArrayList folderList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayvideonowBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;
        todo();
    }

    private void todo() {

        getAllVideo();
        AllVideoAdapter videoAdapter = new AllVideoAdapter(activity, videoList);
        binding.videorecycler.setAdapter(videoAdapter);

    }

    public void getAllVideo() {

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name,column_id,thum,duration,name;

        String absolutePathOfImage = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,MediaStore.Video.Media._ID,MediaStore.Video.Thumbnails.DATA, MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media.SIZE};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
        column_id = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
        thum = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);
        name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
        duration = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);


        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);


            VideoModel obj_model = new VideoModel(absolutePathOfImage,cursor.getString(thum),false,cursor.getLong(duration),cursor.getString(name));

            videoList.add(obj_model);

        }

    }

}