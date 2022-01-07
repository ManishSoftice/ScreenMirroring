package com.example.softice.ad.CustomAd;


import static com.example.softice.utils.Constant.appInhouses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.softice.databinding.CustomBigNativeAdLayoutBinding;
import com.example.softice.databinding.CustomMeduimNativeAdLayoutBinding;
import com.example.softice.databinding.CustomSmallNativeAdLayoutBinding;
import com.example.softice.model.AppSettings;
import com.example.softice.utils.AdUtils;
import com.squareup.picasso.Picasso;

public class CustomNativeAd {

    @SuppressLint("StaticFieldLeak")
    private static CustomNativeAd mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    private static int lastLoaded1 = 0;
    private static int lastLoaded2 = 0;
    private static int lastLoaded3 = 0;
//    NativeAdListener nativeAdListener = null;

    public CustomNativeAd(Activity activity) {
        CustomNativeAd.activity = activity;

    }

    public static CustomNativeAd getInstance(Activity adShowingActivity) {
        if (mInstance == null) {
            mInstance = new CustomNativeAd(adShowingActivity);
        }
        return mInstance;
    }

    static void customNativeAdCallBack(Boolean aBoolean) {
//        if (nativeAdListener != null) {
//            nativeAdListener.onAdShown(aBoolean);
//            nativeAdListener = null;
//        }
    }

    public void ShowNativeAdSmall(ViewGroup nativeAdContainer) {
//        this.nativeAdListener = adListener;
        if (appInhouses.size() > 0) {
            if (lastLoaded1 == appInhouses.size() - 1) lastLoaded1 = 0;
            else lastLoaded1++;
            AppSettings.AppInhouse modal = appInhouses.get(lastLoaded1);
            nativeAdContainer.setVisibility(View.VISIBLE);
            CustomSmallNativeAdLayoutBinding admobSmallNativeAdLayoutBinding = CustomSmallNativeAdLayoutBinding.inflate(LayoutInflater.from(activity));
            displaySmallNativeAd(modal, admobSmallNativeAdLayoutBinding);
            nativeAdContainer.removeAllViews();
            nativeAdContainer.addView(admobSmallNativeAdLayoutBinding.getRoot());


        } else {
            nativeAdContainer.removeAllViews();
            customNativeAdCallBack(false);
        }


    }

    private void displaySmallNativeAd(AppSettings.AppInhouse modal, CustomSmallNativeAdLayoutBinding admobSmallNativeAdLayoutBinding) {
        admobSmallNativeAdLayoutBinding.buttonCallToAction.setText(modal.getApp_cta_text());
        admobSmallNativeAdLayoutBinding.textAdTitle.setText(modal.getApp_title());
        admobSmallNativeAdLayoutBinding.textAdBody.setText(modal.getApp_desc());
        if (!modal.getApp_icon().isEmpty()) {
            Picasso.get()
                    .load(modal.getApp_icon())
                    .into(admobSmallNativeAdLayoutBinding.imageAdIcon);


        }
        admobSmallNativeAdLayoutBinding.buttonCallToAction.setOnClickListener(v -> AdUtils.PlayStore(activity, modal.getApp_uri()));
        admobSmallNativeAdLayoutBinding.getRoot().setOnClickListener(v -> AdUtils.PlayStore(activity, modal.getApp_uri()));
        customNativeAdCallBack(true);
    }

    public void ShowNativeAdBig(ViewGroup nativeAdContainer) {
//        this.nativeAdListener = adListener;
        if (appInhouses.size() > 0) {
            if (lastLoaded3 == appInhouses.size() - 1) lastLoaded3 = 0;
            else lastLoaded3++;
            AppSettings.AppInhouse modal = appInhouses.get(lastLoaded3);
            nativeAdContainer.setVisibility(View.VISIBLE);
            CustomBigNativeAdLayoutBinding customBigNativeAdLayoutBinding = CustomBigNativeAdLayoutBinding.inflate(LayoutInflater.from(activity));
            displayBigNativeAd(modal, customBigNativeAdLayoutBinding);
            nativeAdContainer.removeAllViews();
            nativeAdContainer.addView(customBigNativeAdLayoutBinding.getRoot());

        } else {
            nativeAdContainer.removeAllViews();
            customNativeAdCallBack(false);
        }
    }

    private void displayBigNativeAd(AppSettings.AppInhouse modal, CustomBigNativeAdLayoutBinding customBigNativeAdLayoutBinding) {
        customBigNativeAdLayoutBinding.buttonCallToAction.setText(modal.getApp_cta_text());
        customBigNativeAdLayoutBinding.textAdTitle.setText(modal.getApp_title());
        customBigNativeAdLayoutBinding.textAdBody.setText(modal.getApp_desc());
        customBigNativeAdLayoutBinding.ratingBarAd.setRating(Float.parseFloat(modal.getApp_rating()));
        customBigNativeAdLayoutBinding.textAdvertiser.setText(modal.getApp_price());
        if (!modal.getApp_icon().isEmpty()) {
            Picasso.get()
                    .load(modal.getApp_icon())
                    .into(customBigNativeAdLayoutBinding.imageAdIcon);


        }
        if (!modal.getApp_header_image().isEmpty()) {
            Picasso.get()
                    .load(modal.getApp_header_image())
                    .into(customBigNativeAdLayoutBinding.nativeAdMediaView);

        }
        customBigNativeAdLayoutBinding.buttonCallToAction.setOnClickListener(v -> AdUtils.PlayStore(activity, modal.getApp_uri()));
        customBigNativeAdLayoutBinding.getRoot().setOnClickListener(v -> AdUtils.PlayStore(activity, modal.getApp_uri()));
        customNativeAdCallBack(true);

    }

    public void ShowNativeAdMedium(ViewGroup nativeAdContainer) {
//        this.nativeAdListener = adListener;
        if (appInhouses.size() > 0) {
            if (lastLoaded2 == appInhouses.size() - 1) lastLoaded2 = 0;
            else lastLoaded2++;
            AppSettings.AppInhouse modal = appInhouses.get(lastLoaded2);
            nativeAdContainer.setVisibility(View.VISIBLE);
            CustomMeduimNativeAdLayoutBinding admobMeduimNativeAdLayoutBinding = CustomMeduimNativeAdLayoutBinding.inflate(LayoutInflater.from(activity));
            displayMediumNativeAd(modal, admobMeduimNativeAdLayoutBinding);
            nativeAdContainer.removeAllViews();
            nativeAdContainer.addView(admobMeduimNativeAdLayoutBinding.getRoot());

        } else {
            nativeAdContainer.removeAllViews();
            customNativeAdCallBack(false);
        }

    }

    private void displayMediumNativeAd(AppSettings.AppInhouse modal, CustomMeduimNativeAdLayoutBinding admobMeduimNativeAdLayoutBinding) {
        admobMeduimNativeAdLayoutBinding.buttonCallToAction.setText(modal.getApp_cta_text());
        admobMeduimNativeAdLayoutBinding.textAdTitle.setText(modal.getApp_title());
        admobMeduimNativeAdLayoutBinding.textAdBody.setText(modal.getApp_desc());
        admobMeduimNativeAdLayoutBinding.ratingBarAd.setRating(Float.parseFloat(modal.getApp_rating()));
        if (!modal.getApp_icon().isEmpty()) {
            Picasso.get()
                    .load(modal.getApp_icon())
                    .into(admobMeduimNativeAdLayoutBinding.imageAdIcon);


        }
        if (!modal.getApp_header_image().isEmpty()) {
            Picasso.get()
                    .load(modal.getApp_header_image())
                    .into(admobMeduimNativeAdLayoutBinding.nativeAdMediaView);

        }
        admobMeduimNativeAdLayoutBinding.buttonCallToAction.setOnClickListener(v -> AdUtils.PlayStore(activity, modal.getApp_uri()));
        admobMeduimNativeAdLayoutBinding.getRoot().setOnClickListener(v -> AdUtils.PlayStore(activity, modal.getApp_uri()));
        customNativeAdCallBack(true);
    }

}
