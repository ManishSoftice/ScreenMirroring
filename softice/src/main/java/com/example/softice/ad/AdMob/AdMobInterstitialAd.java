package com.example.softice.ad.AdMob;


import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;

import android.annotation.SuppressLint;
import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.softice.ad.HandleClick.HandleInterstitialAd;
import com.example.softice.model.AdMobInterstitialAdModel;
import com.example.softice.utils.AdUtils;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

public class AdMobInterstitialAd {

    private static final String TAG = AdMobInterstitialAd.class.getSimpleName();
    public static ArrayList<AdMobInterstitialAdModel> adMobInterstitialAds = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    @SuppressLint("StaticFieldLeak")
    private static AdMobInterstitialAd mInstance;
    int CurrentInterstitialAdPosition = 0;
    HandleInterstitialAd handleInterstitialAd = null;



    public AdMobInterstitialAd(Activity activity) {
        AdMobInterstitialAd.activity = activity;

    }

    public static AdMobInterstitialAd getInstance(Activity adShowingActivity) {
        if (mInstance == null) {
            mInstance = new AdMobInterstitialAd(adShowingActivity);
        }
        return mInstance;
    }

    public void ShowInterstitialAd(HandleInterstitialAd handleInterstitialAd) {
        this.handleInterstitialAd = handleInterstitialAd;
        if (!adMobInterstitialAds.isEmpty()) {
            for (int i = 0; i < adMobInterstitialAds.size(); i++) {
                if (adMobInterstitialAds.get(i).getInterstitialAd() != null) {
                    ShowAd(adMobInterstitialAds.get(i).getInterstitialAd(), i);
                    return;
                }
            }
            ShowRetryInterstitialAd();
        } else {
            HandleInterstitialAd(false);
        }

    }

    private void ShowRetryInterstitialAd() {
        if (!adMobInterstitialAds.isEmpty()) {
            if (CurrentInterstitialAdPosition == adMobInterstitialAds.size() - 1)
                CurrentInterstitialAdPosition = 0;
            else CurrentInterstitialAdPosition++;

            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(activity, adMobInterstitialAds.get(CurrentInterstitialAdPosition).getAdUnit(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    interstitialAd.show(activity);
                    interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            HandleInterstitialAd(true);
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            LoadInterstitialAd();
                            HandleInterstitialAd(false);
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            LoadInterstitialAd();
                        }
                    });

                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    LoadInterstitialAd();
                    HandleInterstitialAd(false);

                }
            });

        } else {
            HandleInterstitialAd(false);
        }

    }

    private void ShowAd(InterstitialAd interstitialAd, int i) {
        if (interstitialAd != null) {
            interstitialAd.show(activity);
            interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {

                    AdMobInterstitialAdModel adMobInterstitialAdModel = new AdMobInterstitialAdModel(
                            adMobInterstitialAds.get(i).getAdUnit(),
                            null
                    );
                    adMobInterstitialAds.set(i, adMobInterstitialAdModel);
                    ShowRetryInterstitialAd();

                }

                @Override
                public void onAdShowedFullScreenContent() {
                    AdMobInterstitialAdModel adMobInterstitialAdModel = new AdMobInterstitialAdModel(
                            adMobInterstitialAds.get(i).getAdUnit(),
                            null
                    );
                    adMobInterstitialAds.set(i, adMobInterstitialAdModel);
                    LoadInterstitialAd();

                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    HandleInterstitialAd(true);
                }
            });
        } else {
            ShowRetryInterstitialAd();
        }
    }

    void HandleInterstitialAd(Boolean AdShowed) {
        try {
            if (sharedPreferencesHelper.getAdShowingPopup()) {
                AdUtils.dismissAdLoading();
            } else {
                AdUtils.dismissAdProgress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (handleInterstitialAd != null) {
            handleInterstitialAd.Show(AdShowed);
            handleInterstitialAd = null;
        }

    }

    public void LoadInterstitialAd() {
        if (!adMobInterstitialAds.isEmpty()) {
            if (CurrentInterstitialAdPosition == adMobInterstitialAds.size() - 1)
                CurrentInterstitialAdPosition = 0;
            else CurrentInterstitialAdPosition++;
            if (adMobInterstitialAds.get(CurrentInterstitialAdPosition).getInterstitialAd() == null) {
                AdRequest adRequest = new AdRequest.Builder().build();
                InterstitialAd.load(activity, adMobInterstitialAds.get(CurrentInterstitialAdPosition).getAdUnit(), adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        AdMobInterstitialAdModel adMobInterstitialAdModel = new AdMobInterstitialAdModel(
                                adMobInterstitialAds.get(CurrentInterstitialAdPosition).getAdUnit(),
                                interstitialAd
                        );
                        adMobInterstitialAds.set(CurrentInterstitialAdPosition, adMobInterstitialAdModel);

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                    }
                });
            }

        }
    }


}
