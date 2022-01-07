package com.jenuvid.scrnmirroring.Gallery;

import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.Adapter.GridViewAdapter;
import com.jenuvid.scrnmirroring.databinding.ActivityAlbumPhotosBinding;

import java.util.ArrayList;

public class AlbumPhotosActivity extends AppCompatActivity {

    public static ArrayList<String> FolderData;
    Activity activity;
    GridViewAdapter adapter;
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
        bottomAd();

        image.add(0, null);
        adapter = new GridViewAdapter(this, image);
        x.gridView.setAdapter(adapter);
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
                AlbumPhotosActivity.super.onBackPressed();
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