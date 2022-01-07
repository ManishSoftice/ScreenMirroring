package com.example.softice.ad.CustomAd;



import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;
import static com.example.softice.utils.Constant.appInhouses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.softice.activity.InterstitialActivity;
import com.example.softice.ad.HandleClick.HandleInterstitialAd;
import com.example.softice.ad.HandleClick.InterstitialAdListener;
import com.example.softice.model.AppSettings;
import com.example.softice.utils.AdUtils;

public class CustomInterstitialAd {

    private static final String TAG = CustomInterstitialAd.class.getSimpleName();

    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    @SuppressLint("StaticFieldLeak")
    private static CustomInterstitialAd mInstance;
    int lastLoaded = 0;
    HandleInterstitialAd handleInterstitialAd = null;


    public CustomInterstitialAd(Activity activity) {
        CustomInterstitialAd.activity = activity;

    }

    public static CustomInterstitialAd getInstance(Activity adShowingActivity) {
        if (mInstance == null) {
            mInstance = new CustomInterstitialAd(adShowingActivity);
        }
        return mInstance;
    }

    public void ShowInterstitialAd(HandleInterstitialAd handleInterstitialAd) {
        this.handleInterstitialAd = handleInterstitialAd;
        if (appInhouses.size() > 0) {
            if (lastLoaded == appInhouses.size() - 1) lastLoaded = 0;
            else lastLoaded++;
            AppSettings.AppInhouse modal = appInhouses.get(lastLoaded);

            InterstitialActivity.adListener = new InterstitialAdListener() {
                @Override
                public void onAdLoaded() {
                    Log.d(TAG, "onAdLoaded: ");
                }

                @Override
                public void onAdClosed() {
                    Log.d(TAG, "onAdClosed: ");
                    HandleInterstitialAd(true);

                }

                @Override
                public void onAdShown() {
                    try {
                        if (sharedPreferencesHelper.getAdShowingPopup()) {
                            AdUtils.dismissAdLoading();
                        } else {
                            AdUtils.dismissAdProgress();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "onAdShown: ");
                }

                @Override
                public void onApplicationLeft() {
                    Log.d(TAG, "onApplicationLeft: ");
                    HandleInterstitialAd(true);
                }

                @Override
                public void onAdFailedToLoad(Exception exception) {
                    Log.d(TAG, "onAdFailedToLoad: ");
                    HandleInterstitialAd(false);
                }
            };
            Intent intent = new Intent(activity, InterstitialActivity.class);
            intent.putExtra("modal", modal);
            activity.startActivity(intent);
            activity.overridePendingTransition(0, 0);

        } else {
            HandleInterstitialAd(false);
        }

    }


    void HandleInterstitialAd(Boolean AdShowed) {

        if (handleInterstitialAd != null) {
            handleInterstitialAd.Show(AdShowed);
            handleInterstitialAd = null;
        }

    }


}
