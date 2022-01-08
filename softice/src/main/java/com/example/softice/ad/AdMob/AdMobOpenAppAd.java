package com.example.softice.ad.AdMob;

import static androidx.lifecycle.Lifecycle.Event.ON_START;
import static androidx.lifecycle.Lifecycle.Event.ON_STOP;
import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.example.softice.ad.HandleClick.HandleOpenAd;
import com.example.softice.model.AdMobOpenAdModel;
import com.example.softice.utils.AdUtils;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.ArrayList;
import java.util.Date;

public class AdMobOpenAppAd implements LifecycleObserver, Application.ActivityLifecycleCallbacks {

    private static final String TAG = AdMobOpenAppAd.class.getSimpleName();
    public static ArrayList<AdMobOpenAdModel> adMobOpenAdModels = new ArrayList<>();
    public static boolean Splash_Start = false;
    private static boolean isShowingAd = false;
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    @SuppressLint("StaticFieldLeak")
    private static AdMobOpenAppAd mInstance;
    HandleOpenAd handleOpenAd = null;
    int CurrentOpenAdPosition = 0;
    private int activityStarted = 0;

    public AdMobOpenAppAd(Activity activity) {
        AdMobOpenAppAd.activity = activity;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            AdMobOpenAppAd.activity.registerActivityLifecycleCallbacks(this);
        }
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    public static AdMobOpenAppAd getInstance(Activity adShowingActivity) {
        if (mInstance == null) {
            mInstance = new AdMobOpenAppAd(adShowingActivity);
        }
        return mInstance;
    }

    public void LoadAd() {
        if (!adMobOpenAdModels.isEmpty()) {
            if (CurrentOpenAdPosition == adMobOpenAdModels.size() - 1) CurrentOpenAdPosition = 0;
            else CurrentOpenAdPosition++;

            if (isAdAvailable()) {
                return;
            }
            AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull AppOpenAd ad) {
                    AdMobOpenAdModel adMobOpenAdModel = new AdMobOpenAdModel(adMobOpenAdModels.get(CurrentOpenAdPosition).getAdUnit(), ad, (new Date()).getTime());
                    adMobOpenAdModels.set(CurrentOpenAdPosition, adMobOpenAdModel);
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                }

            };
            AdRequest request = getAdRequest();
            AppOpenAd.load(activity, adMobOpenAdModels.get(CurrentOpenAdPosition).getAdUnit(), request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
        }

    }



    public void ShowOpenAd(HandleOpenAd handleOpenAdActivity) {
        this.handleOpenAd = handleOpenAdActivity;
        if (sharedPreferencesHelper.getAppOpenaAd()) {
            if (!adMobOpenAdModels.isEmpty()) {

                if (!isShowingAd && isAdAvailable()) {
                    try {
                        if (sharedPreferencesHelper.getAdShowingPopup()) {
                            AdUtils.dismissAdLoading();
                        } else {
                            AdUtils.dismissAdProgress();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FullScreenContentCallback fullScreenContentCallback =
                            new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    isShowingAd = false;
                                    AdMobOpenAdModel adMobOpenAdModel = new AdMobOpenAdModel(adMobOpenAdModels.get(CurrentOpenAdPosition).getAdUnit(), null, adMobOpenAdModels.get(CurrentOpenAdPosition).getTime());
                                    adMobOpenAdModels.set(CurrentOpenAdPosition, adMobOpenAdModel);
                                    LoadAd();
                                    HandleOpenAd(true);


                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                    AdMobOpenAdModel adMobOpenAdModel = new AdMobOpenAdModel(adMobOpenAdModels.get(CurrentOpenAdPosition).getAdUnit(), null, adMobOpenAdModels.get(CurrentOpenAdPosition).getTime());
                                    adMobOpenAdModels.set(CurrentOpenAdPosition, adMobOpenAdModel);
                                    LoadAd();
                                    HandleOpenAd(false);

                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    isShowingAd = true;


                                }
                            };

                    adMobOpenAdModels.get(CurrentOpenAdPosition).getAppOpenAd().setFullScreenContentCallback(fullScreenContentCallback);
                    adMobOpenAdModels.get(CurrentOpenAdPosition).getAppOpenAd().show(activity);

                } else {
                    if (!adMobOpenAdModels.isEmpty()) {
                        if (CurrentOpenAdPosition == adMobOpenAdModels.size() - 1)
                            CurrentOpenAdPosition = 0;
                        else CurrentOpenAdPosition++;

                        if (isAdAvailable()) {
                            HandleOpenAd(false);
                            return;
                        }
                        AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull AppOpenAd ad) {
                                AdMobOpenAdModel adMobOpenAdModel = new AdMobOpenAdModel(adMobOpenAdModels.get(CurrentOpenAdPosition).getAdUnit(), ad, (new Date()).getTime());
                                adMobOpenAdModels.set(CurrentOpenAdPosition, adMobOpenAdModel);
                                if (!isShowingAd && isAdAvailable()) {
                                    try {
                                        if (sharedPreferencesHelper.getAdShowingPopup()) {
                                            AdUtils.dismissAdLoading();
                                        } else {
                                            AdUtils.dismissAdProgress();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    FullScreenContentCallback fullScreenContentCallback =
                                            new FullScreenContentCallback() {
                                                @Override
                                                public void onAdDismissedFullScreenContent() {
                                                    isShowingAd = false;
                                                    AdMobOpenAdModel adMobOpenAdModel = new AdMobOpenAdModel(adMobOpenAdModels.get(CurrentOpenAdPosition).getAdUnit(), null, adMobOpenAdModels.get(CurrentOpenAdPosition).getTime());
                                                    adMobOpenAdModels.set(CurrentOpenAdPosition, adMobOpenAdModel);
                                                    LoadAd();
                                                    HandleOpenAd(true);
                                                }

                                                @Override
                                                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                                    AdMobOpenAdModel adMobOpenAdModel = new AdMobOpenAdModel(adMobOpenAdModels.get(CurrentOpenAdPosition).getAdUnit(), null, adMobOpenAdModels.get(CurrentOpenAdPosition).getTime());
                                                    adMobOpenAdModels.set(CurrentOpenAdPosition, adMobOpenAdModel);
                                                    LoadAd();
                                                    HandleOpenAd(false);
                                                }

                                                @Override
                                                public void onAdShowedFullScreenContent() {
                                                    isShowingAd = true;

                                                }
                                            };

                                    adMobOpenAdModels.get(CurrentOpenAdPosition).getAppOpenAd().setFullScreenContentCallback(fullScreenContentCallback);
                                    adMobOpenAdModels.get(CurrentOpenAdPosition).getAppOpenAd().show(activity);

                                } else {
                                    LoadAd();
                                    HandleOpenAd(false);

                                }
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                LoadAd();

                                HandleOpenAd(false);

                            }

                        };
                        AdRequest request = getAdRequest();
                        AppOpenAd.load(activity, adMobOpenAdModels.get(CurrentOpenAdPosition).getAdUnit(), request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
                    } else {
                        HandleOpenAd(false);
                    }


                }
            } else {
                HandleOpenAd(false);
            }
        } else {
            HandleOpenAd(false);
        }


    }


    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    private boolean wasLoadTimeLessThanNHoursAgo() {
        long dateDifference = (new Date()).getTime() - adMobOpenAdModels.get(CurrentOpenAdPosition).getTime();
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour));
    }

    public boolean isAdAvailable() {
        return adMobOpenAdModels.get(CurrentOpenAdPosition).getAppOpenAd() != null && wasLoadTimeLessThanNHoursAgo();
    }


    void HandleOpenAd(Boolean AdShowed) {
        if (handleOpenAd != null) {
            handleOpenAd.Show(AdShowed);
            handleOpenAd = null;
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }


    @OnLifecycleEvent(ON_START)
    public void onStart() {

        if (activityStarted == 0) {
            if (!Splash_Start) {
                ShowOpenAd(null);
            }
        }
        activityStarted = activityStarted + 1;
    }

    @OnLifecycleEvent(ON_STOP)
    public void onStopped() {
        activityStarted = activityStarted - 1;
    }
}
