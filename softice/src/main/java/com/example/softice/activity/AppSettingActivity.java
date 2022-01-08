package com.example.softice.activity;


import static com.example.softice.ad.AdMob.AdMobBannerAd.bannerAdIds;
import static com.example.softice.ad.AdMob.AdMobInterstitialAd.adMobInterstitialAds;
import static com.example.softice.ad.AdMob.AdMobNativeAd.adMobNativeAdModels;
import static com.example.softice.ad.AdMob.AdMobOpenAppAd.Splash_Start;
import static com.example.softice.ad.AdMob.AdMobOpenAppAd.adMobOpenAdModels;
import static com.example.softice.ad.AdMob.AdMobRewardedAd.adMobRewardedAds;
import static com.example.softice.utils.Constant.appInhouses;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdMob.AdMobInterstitialAd;
import com.example.softice.ad.AdMob.AdMobNativeAd;
import com.example.softice.listeners.AppSettingListeners;
import com.example.softice.model.AdMobInterstitialAdModel;
import com.example.softice.model.AdMobNativeAdModel;
import com.example.softice.model.AdMobOpenAdModel;
import com.example.softice.model.AdMobRewadedAdModel;
import com.example.softice.model.AppSettings;
import com.example.softice.networking.Api;
import com.example.softice.networking.RetrofitClient;
import com.example.softice.utils.AdUtils;
import com.example.softice.utils.SharedPreferencesHelper;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppSettingActivity extends AppCompatActivity {
    private static final String TAG = AppSettingActivity.class.getSimpleName();
    public static SharedPreferencesHelper sharedPreferencesHelper;
    Api api;
    Activity activity;
    AppSettingListeners appSettingListeners = null;
    int AppVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Splash_Start = true;
    }

    public void AppSettings(Activity activity, String applicationId, String keyHash, int AppVersion, boolean debug, AppSettingListeners settingListeners) {
        sharedPreferencesHelper = SharedPreferencesHelper.init(activity);
        this.activity = activity;
        this.AppVersion = AppVersion;
        this.appSettingListeners = settingListeners;
        api = RetrofitClient.getInstance().getApi();
        Call<AppSettings> appSettingsCall = api.addapikey(applicationId, keyHash, debug, sharedPreferencesHelper.getAppInstallStatus());
        appSettingsCall.enqueue(new Callback<AppSettings>() {
            @Override
            public void onResponse(@NonNull Call<AppSettings> call, @NonNull Response<AppSettings> response) {
                if (response.isSuccessful()) {
                    sharedPreferencesHelper.setAppInstallStatus(false);
                    String data = new Gson().toJson(response.body());
                    AppSettings appSettings = new Gson().fromJson(data, AppSettings.class);
                    if (appSettings.issTATUS()) {
                        sharedPreferencesHelper.setVersionUpdateDialog(appSettings.getaPP_SETTINGS().isVersionupdatedialog());
                        sharedPreferencesHelper.setRedirectOtherApp(appSettings.getaPP_SETTINGS().isRedirectotherapp());
                        sharedPreferencesHelper.setAppAccountLink(appSettings.getaPP_SETTINGS().getRedirectotherapppackage());
                        sharedPreferencesHelper.setVersionCode(appSettings.getaPP_SETTINGS().getVersioncode());
                        sharedPreferencesHelper.setShowAdinApp(appSettings.getaPP_SETTINGS().isShowadinapp());
                        sharedPreferencesHelper.setMainClick(Integer.parseInt(appSettings.getaPP_SETTINGS().getIntCounter()));
                        sharedPreferencesHelper.setBackClick(Integer.parseInt(appSettings.getaPP_SETTINGS().getIntBackCounter()));
                        sharedPreferencesHelper.setAdMobAdShow(appSettings.getpLACEMENT().getAdmob().isAd_showAdStatus());
                        sharedPreferencesHelper.setOpenAdStatus(appSettings.getaPP_SETTINGS().isAltIntAppOpenStuse());
                        sharedPreferencesHelper.setAMDStatus(appSettings.getaPP_SETTINGS().isaMDClickStatus());
                        sharedPreferencesHelper.setAppOpenaAd(appSettings.getaPP_SETTINGS().isAppopenad());
                        sharedPreferencesHelper.setAdShowingPopup(appSettings.getaPP_SETTINGS().isAdshowingpopup());
                        sharedPreferencesHelper.setPrivacyPolicy(appSettings.getaPP_SETTINGS().getPrivacypolicy());
                        sharedPreferencesHelper.setshowappinhouse(appSettings.getaPP_SETTINGS().isShowappinhouse());
                        sharedPreferencesHelper.setButtonAnimation(Boolean.valueOf(appSettings.getaPP_SETTINGS().getAdbuttonanimation()));

                        if (appSettings.getaPP_SETTINGS().isShowadinapp()) {
                            if (appSettings.getpLACEMENT().getAdmob().isAd_showAdStatus()) {
                                for (int i = 0; i < appSettings.getpLACEMENT().getAdmob().getAppOpen().size(); i++) {
                                    AdMobOpenAdModel adMobOpenAdModel = new AdMobOpenAdModel(appSettings.getpLACEMENT().getAdmob().getAppOpen().get(i).getId(), null, 0);
                                    adMobOpenAdModels.add(adMobOpenAdModel);
                                }
                                for (int i = 0; i < appSettings.getpLACEMENT().getAdmob().getNatives().size(); i++) {
                                    AdMobNativeAdModel adMobNativeAdModel = new AdMobNativeAdModel(appSettings.getpLACEMENT().getAdmob().getNatives().get(i).getId(), null);
                                    adMobNativeAdModels.add(adMobNativeAdModel);
                                }
                                for (int i = 0; i < appSettings.getpLACEMENT().getAdmob().getInterstitial().size(); i++) {
                                    AdMobInterstitialAdModel adMobInterstitialAdModel = new AdMobInterstitialAdModel(appSettings.getpLACEMENT().getAdmob().getInterstitial().get(i).getId(), null);
                                    adMobInterstitialAds.add(adMobInterstitialAdModel);
                                }
                                for (int i = 0; i < appSettings.getpLACEMENT().getAdmob().getRewardedVideo().size(); i++) {
                                    AdMobRewadedAdModel adMobRewadedAdModel = new AdMobRewadedAdModel(appSettings.getpLACEMENT().getAdmob().getRewardedVideo().get(i).getId(), null);
                                    adMobRewardedAds.add(adMobRewadedAdModel);
                                }
                                for (int i = 0; i < appSettings.getpLACEMENT().getAdmob().getBanner().size(); i++) {
                                    bannerAdIds.add(appSettings.getpLACEMENT().getAdmob().getBanner().get(i).getId());
                                }
                            }
                            if (appSettings.getaPP_SETTINGS().isShowappinhouse()) {
                                appInhouses = appSettings.getApp_Inhouse();

                            }

                        }

                        HandelData(Boolean.valueOf(appSettings.getaPP_SETTINGS().getUnderMaintenance()));
                    } else {
                        if (appSettingListeners != null) {
                            appSettingListeners.onStatusChange();
                            appSettingListeners = null;
                        }
                    }
                } else {
                    if (appSettingListeners != null) {
                        appSettingListeners.onResponseFail();
                        appSettingListeners = null;
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<AppSettings> call, @NonNull Throwable t) {
                if (appSettingListeners != null) {
                    appSettingListeners.onResponseFail();
                    appSettingListeners = null;
                }


            }
        });

    }


    private void HandelData(Boolean underMaintenance) {
        if (sharedPreferencesHelper.getRedirectOtherApp()) {
            if (appSettingListeners != null) {
                appSettingListeners.onAppRedirect(sharedPreferencesHelper.getAppAccountLink());
                appSettingListeners = null;
            }

        } else if (sharedPreferencesHelper.getVersionUpdateDialog() && AdUtils.checkUpdate(AppVersion)) {
            if (appSettingListeners != null) {
                appSettingListeners.onAppUpdate("https://play.google.com/store/apps/details?id=" + activity.getPackageName());
                appSettingListeners = null;
            }

        } else if (underMaintenance) {
            MobileAds.initialize(activity, initializationStatus -> {
                AdMobNativeAd.getInstance(activity).LoadNativeAds();
            });
            if (appSettingListeners != null) {
                appSettingListeners.onUnderMaintenance();
                appSettingListeners = null;
            }
        } else {
            if (sharedPreferencesHelper.getShowAdinApp()) {
                if (sharedPreferencesHelper.getAdMobAdShow()) {
                    MobileAds.initialize(activity, initializationStatus -> {
                        LoadAdMobAd();
                    });

                }
            }
            if (appSettingListeners != null) {
                appSettingListeners.onResponseSuccess();
                appSettingListeners = null;
            }
        }

    }

    private void LoadAdMobAd() {
        AdMobNativeAd.getInstance(activity).LoadNativeAds();
        AdMobInterstitialAd.getInstance(activity).LoadInterstitialAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Splash_Start = false;
    }
}