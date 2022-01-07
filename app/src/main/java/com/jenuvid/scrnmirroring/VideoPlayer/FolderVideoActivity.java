package com.jenuvid.scrnmirroring.VideoPlayer;

import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.Adapter.FolderVideoAdapter;
import com.jenuvid.scrnmirroring.databinding.ActivityFolderVideoBinding;

import java.util.ArrayList;

public class FolderVideoActivity extends AppCompatActivity {


    public static ArrayList<String> Video_Folder_Data;
    ArrayList<String> video = new ArrayList<>();
    Boolean isfirsttime = true;
    String name;

    Activity activity;
    ActivityFolderVideoBinding x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityFolderVideoBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;
        todo();
    }

    private void todo() {

        bottomAd();

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        video = Video_Folder_Data;

        if (video == null) {
            x.notAvailable.setVisibility(View.VISIBLE);
        } else {
            x.notAvailable.setVisibility(View.GONE);
        }

        FolderVideoAdapter videoAdapter = new FolderVideoAdapter(activity, video);
        x.foldervideorecycler.setAdapter(videoAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout.nativeAdLayout, AdUtils.NativeType.NATIVE_BIG);

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
    public void onBackPressed() {

        AdShow.getInstance(activity).ShowAd(new HandleClick() {
            @Override
            public void Show(boolean adShow) {
                FolderVideoActivity.super.onBackPressed();
            }
        }, AdUtils.ClickType.BACK_CLICK);

    }
}