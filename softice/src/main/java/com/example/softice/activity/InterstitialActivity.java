package com.example.softice.activity;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.softice.ad.HandleClick.InterstitialAdListener;
import com.example.softice.databinding.ActivityInterstitialBinding;
import com.example.softice.model.AppSettings;
import com.example.softice.utils.AdUtils;

public class InterstitialActivity extends AppCompatActivity {

    public static InterstitialAdListener adListener = null;
    AppSettings.AppInhouse modal;
    private ActivityInterstitialBinding x;
    private int currentApiVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentApiVersion = Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(i -> {
                if ((i & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            });

        }
        x = ActivityInterstitialBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        modal = (AppSettings.AppInhouse) getIntent().getSerializableExtra("modal");
        if (adListener != null) {
            adListener.onAdShown();
        }

        Glide.with(this).asBitmap().load(modal.getApp_icon()).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bitmap, Transition<? super Bitmap> transition) {
                x.adIcon.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadCleared(Drawable placeholder) {


            }
        });
        Glide.with(this).asBitmap().load(modal.getApp_header_image()).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bitmap, Transition<? super Bitmap> transition) {
                x.nativeAdMediaView.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadCleared(Drawable placeholder) {


            }
        });
        x.ratingBarAd.setRating(Float.parseFloat(modal.getApp_rating()));
        x.textAdBody.setText(modal.getApp_desc());
        x.textAdTitle.setText(modal.getApp_title());
        x.textDownload.setText(modal.getApp_cta_text());
        x.textAdDownloads.setText("(" + modal.getApp_download() + "+)");
        x.mainLayout.setOnClickListener(view13 -> {
            if (adListener != null) {
                adListener.onApplicationLeft();
                AdUtils.PlayStore(InterstitialActivity.this, modal.getApp_uri());
                finish();
                adListener = null;
            }
        });

        x.floatingActionButton.setOnClickListener(view12 -> {
            if (adListener != null) {
                adListener.onApplicationLeft();
                AdUtils.PlayStore(InterstitialActivity.this, modal.getApp_uri());
                finish();
                adListener = null;
            }
        });
        x.houseAdsInterstitialButtonClose.setOnClickListener(view1 -> {
            if (adListener != null) {
                adListener.onAdClosed();
                finish();
                adListener = null;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (adListener != null) {
            adListener.onAdClosed();
            finish();
            adListener = null;
        }


    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}