package com.jenuvid.scrnmirroring.connect_TV;

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
import com.jenuvid.scrnmirroring.Adapter.AllVideoAdapter;
import com.jenuvid.scrnmirroring.databinding.ActivityPlayvideonowBinding;
import com.jenuvid.scrnmirroring.model.VideoModel;

import java.util.ArrayList;

public class PlayvideonowActivity extends AppCompatActivity {

    Activity activity;
    ActivityPlayvideonowBinding x;
    ArrayList<VideoModel> videoList = new ArrayList();
    Boolean isfirsttime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityPlayvideonowBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;
        todo();
    }

    private void todo() {

        getAllVideo();

        if(videoList.size() <= 0){
            x.notAvailable.setVisibility(View.VISIBLE);
        }else {
            x.notAvailable.setVisibility(View.GONE);
        }
        AllVideoAdapter videoAdapter = new AllVideoAdapter(activity, videoList);
        x.videorecycler.setAdapter(videoAdapter);

    }

    public void getAllVideo() {

        bottomAd();
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
                PlayvideonowActivity.super.onBackPressed();
            }
        }, AdUtils.ClickType.BACK_CLICK);
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

}