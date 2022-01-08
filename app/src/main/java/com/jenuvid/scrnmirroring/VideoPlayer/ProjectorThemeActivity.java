package com.jenuvid.scrnmirroring.VideoPlayer;

import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.Adapter.ThemesAdaper;
import com.jenuvid.scrnmirroring.databinding.ActivityProjectorThemeBinding;

public class ProjectorThemeActivity extends AppCompatActivity {
    ActivityProjectorThemeBinding x;
    Activity activity;
    Boolean isfirsttime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityProjectorThemeBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;
        init();
    }

    @Override
    public void onBackPressed() {
        AdShow.getInstance(activity).ShowAd(adShow -> ProjectorThemeActivity.super.onBackPressed(), AdUtils.ClickType.BACK_CLICK);

    }

    void init() {
        bottomAd();
        setLayoutManager();
        loadNativeAd();
    }

    void loadNativeAd() {
        AdShow.getInstance(activity).ShowNativeAd(x.nativeLayout.nativeAdLayout, AdUtils.NativeType.NATIVE_BIG);
    }

    void setLayoutManager() {
        x.rlRecyclerView.setAdapter(new ThemesAdaper(activity, position -> AdShow.getInstance(activity).ShowAd(adShow -> startActivity(new Intent(activity, ApplyThemeActivity.class).putExtra("position", position)), AdUtils.ClickType.MAIN_CLICK)));
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