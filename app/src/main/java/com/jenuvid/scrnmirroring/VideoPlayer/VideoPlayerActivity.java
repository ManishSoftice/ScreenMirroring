package com.jenuvid.scrnmirroring.VideoPlayer;

import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.Adapter.FolderAdapter;
import com.jenuvid.scrnmirroring.databinding.ActivityVideoPlayerBinding;
import com.jenuvid.scrnmirroring.model.FolderModel;

import java.util.ArrayList;

public class VideoPlayerActivity extends AppCompatActivity {

    Activity activity;
    ActivityVideoPlayerBinding x;
    ArrayList<FolderModel> folderList = new ArrayList();
    boolean boolean_folder;
    Boolean isfirsttime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityVideoPlayerBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;
        todo();
    }

    private void todo() {

        getAllFolers();
        bottomAd();

        if (folderList.size() <= 0) {
            x.notAvailable.setVisibility(View.VISIBLE);
        } else {
            x.notAvailable.setVisibility(View.GONE);
        }

        FolderAdapter videoAdapter = new FolderAdapter(activity, folderList);
        x.folderrecycler.setAdapter(videoAdapter);

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

    private void bottomAd() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            x.nestedScrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (scrollY > 700) {
                    if (sharedPreferencesHelper.getShowAdinApp()) {
                        if (sharedPreferencesHelper.getAdMobAdShow()) {
                            x.nativeAdLayout2.setVisibility(View.VISIBLE);
                            if (isfirsttime) {
                                AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout2, AdUtils.NativeType.NATIVE_BANNER);
                                isfirsttime = false;
                            }
                        } else {
                            x.nativeAdLayout2.setVisibility(View.GONE);
                        }

                    } else {
                        x.nativeAdLayout2.setVisibility(View.GONE);
                    }

                } else {
                    x.nativeAdLayout2.setVisibility(View.GONE);
                }

            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout.nativeAdLayout, AdUtils.NativeType.NATIVE_BIG);
    }

    @Override
    public void onBackPressed() {

        AdShow.getInstance(activity).ShowAd(new HandleClick() {
            @Override
            public void Show(boolean adShow) {
                VideoPlayerActivity.super.onBackPressed();
            }
        }, AdUtils.ClickType.BACK_CLICK);

    }
}