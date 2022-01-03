package com.example.screenmirroring.VideoPlayer;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screenmirroring.Adapter.FolderAdapter;
import com.example.screenmirroring.FolderModel;
import com.example.screenmirroring.databinding.ActivityVideoPlayerBinding;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayerActivity extends AppCompatActivity {

    Activity activity;
    ActivityVideoPlayerBinding binding;
    ArrayList<FolderModel> folderList = new ArrayList();
    List<String> list = new ArrayList<>();
    boolean boolean_folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoPlayerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;
        todo();
    }

    private void todo() {

        getAllFolers();
        FolderAdapter videoAdapter = new FolderAdapter(activity, folderList);
        binding.folderrecycler.setAdapter(videoAdapter);

    }


    private void getAllFolers() {
        folderList.clear();
        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Video.Media.DATE_TAKEN;
        cursor = activity.getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            for (int i = 0; i < folderList.size(); i++) {
                String folderName = folderList.get(i).getStr_folder();
                if (folderName != null) {
                    if (folderList.get(i).getStr_folder().equals(cursor.getString(column_index_folder_name))) {
                        boolean_folder = true;
                        int_position = i;
                        break;
                    } else {
                        boolean_folder = false;
                    }
                }

            }
            if (boolean_folder) {
                ArrayList<String> al_path = new ArrayList<>(folderList.get(int_position).getAl_videoPath());
                al_path.add(absolutePathOfImage);
                folderList.get(int_position).setAl_videoPath(al_path);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                al_path.add(absolutePathOfImage);
                FolderModel obj_model = new FolderModel(cursor.getString(column_index_folder_name), al_path);
                folderList.add(obj_model);

            }
        }

        cursor.close();
    }
}