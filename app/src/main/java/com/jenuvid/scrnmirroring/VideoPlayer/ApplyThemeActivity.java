package com.jenuvid.scrnmirroring.VideoPlayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.Utils.Constat;
import com.jenuvid.scrnmirroring.databinding.ActivityApplyThemeBinding;

public class ApplyThemeActivity extends AppCompatActivity {
    ActivityApplyThemeBinding x;
    Activity activity;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityApplyThemeBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;
        init();
    }

    @Override
    public void onBackPressed() {
        AdShow.getInstance(activity).ShowAd(adShow -> ApplyThemeActivity.super.onBackPressed(), AdUtils.ClickType.BACK_CLICK);

    }

    void init() {
        getAndSetData();
        setClickEvent();
        loadNativeAd();
    }

    void loadNativeAd() {
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout, AdUtils.NativeType.NATIVE_BANNER);
    }

    void getAndSetData() {
        position = getIntent().getIntExtra("position", 0);
        x.ivThemeImage.setImageResource(Constat.GetThemes().get(position).themelist);
    }

    void setClickEvent() {
        x.cvApplyTheme.setOnClickListener(view -> AdShow.getInstance(activity).ShowAd(adShow -> startActivity(new Intent(activity, VideoProjectorActivity.class).putExtra("background", position)), AdUtils.ClickType.MAIN_CLICK));
    }
}