package com.example.screenmirroring.Gallery;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screenmirroring.Adapter.AllPhotosFolderAdapter;
import com.example.screenmirroring.Model_images;
import com.example.screenmirroring.databinding.ActivityAllPhotoFolderBinding;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AllPhotoFolderActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Executor executor = Executors.newSingleThreadExecutor();
    ArrayList<String> img = new ArrayList<>();
    String name;
    boolean boolean_folder;
    AllPhotosFolderAdapter allPhotosFolderAdapter;
    ArrayList<Model_images> all_images = new ArrayList<>();
    Boolean isfirsttime = true;

    Activity activity;
    ActivityAllPhotoFolderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllPhotoFolderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        activity = this;

        executor.execute(new Runnable() {
            @Override
            public void run() {
                binding.progressBar.setVisibility(View.VISIBLE);
                getAllFolers();
                handler.post(() -> {
                    if (!all_images.isEmpty()) {
                        allPhotosFolderAdapter.setData(all_images);
                        binding.progressBar.setVisibility(View.GONE);
                    }

                });
            }
        });

        todo();
    }

    private void todo() {

        allPhotosFolderAdapter = new AllPhotosFolderAdapter(activity);
        binding.photorecycler.setAdapter(allPhotosFolderAdapter);


    }

    private void getAllFolers() {
        all_images.clear();

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = activity.getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            for (int i = 0; i < all_images.size(); i++) {
                String folderName = all_images.get(i).getStr_folder();
                if (folderName != null) {
                    if (all_images.get(i).getStr_folder().equals(cursor.getString(column_index_folder_name))) {
                        boolean_folder = true;
                        int_position = i;
                        break;
                    } else {
                        boolean_folder = false;
                    }
                }

            }
            if (boolean_folder) {
                ArrayList<String> al_path = new ArrayList<>(all_images.get(int_position).getAl_imagepath());
                al_path.add(absolutePathOfImage);
                all_images.get(int_position).setAl_imagepath(al_path);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                al_path.add(absolutePathOfImage);
                Model_images obj_model = new Model_images(cursor.getString(column_index_folder_name), al_path);
                all_images.add(obj_model);

            }
        }

        cursor.close();
    }
}