package com.example.softice.ad;


import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.softice.ad.AdMob.AdMobBannerAd;
import com.example.softice.ad.AdMob.AdMobInterstitialAd;
import com.example.softice.ad.AdMob.AdMobNativeAd;
import com.example.softice.ad.AdMob.AdMobOpenAppAd;
import com.example.softice.ad.AdMob.AdMobRewardedAd;
import com.example.softice.ad.CustomAd.CustomInterstitialAd;
import com.example.softice.ad.CustomAd.CustomNativeAd;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.ad.HandleClick.HandleRewardedAd;
import com.example.softice.ad.HandleClick.NativeAdListener;
import com.example.softice.databinding.DialogInternetBinding;
import com.example.softice.utils.AdUtils;
import com.example.softice.utils.Constant;

public class AdShow {

    private static final String TAG = AdShow.class.getSimpleName();
    public static int MainClickCount = -1;
    public static int BackClickCount = -1;
    public static Boolean MainAlternatives = false;
    public static Boolean BackAlternatives = false;
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    @SuppressLint("StaticFieldLeak")
    private static AdShow mInstance;
    HandleClick handleClick = null;
    HandleRewardedAd handleRewardedAd = null;

    public AdShow(Activity activity) {
        AdShow.activity = activity;
    }

    public static AdShow getInstance(Activity adShowingActivity) {
        AdShow.activity = adShowingActivity;
        if (mInstance == null) {
            mInstance = new AdShow(adShowingActivity);
        }
        return mInstance;
    }

    public void ShowAd(HandleClick adShowingActivity, AdUtils.ClickType type) {
        if (!AdUtils.isNetworkAvailable(activity)) {
            ShowNetworkDialog();
            return;
        }
        handleClick = adShowingActivity;
        try {
            if (sharedPreferencesHelper.getShowAdinApp()) {
                if (sharedPreferencesHelper.getAdMobAdShow()) {
                    switch (type) {
                        case MAIN_CLICK:
                            MainClickCount++;
                            int mainClickOnline = sharedPreferencesHelper.getMainClick();
                            if (sharedPreferencesHelper.getAMDStatus()) {
                                if (MainClickCount % mainClickOnline == 0) {
                                    HandleClick(false);
                                } else {
                                    ShowAd();
                                }
                            } else {
                                if (mainClickOnline == 0 || MainClickCount % mainClickOnline != 0) {
                                    HandleClick(false);
                                } else {
                                    ShowAd();
                                }
                            }

                            break;
                        case BACK_CLICK:
                            BackClickCount++;
                            int backClickOnline = sharedPreferencesHelper.getBackClick();
                            if (backClickOnline == 0 || BackClickCount % backClickOnline != 0) {
                                HandleClick(false);
                            } else {
                                ShowAd();
                            }
                            break;
                        case EVERY_CLICK:
                            ShowAd();
                            break;

                    }


                } else {
                    HandleClick(false);
                }
            } else {
                HandleClick(false);
            }
        } catch (NullPointerException e) {
            HandleClick(false);
        }


    }

    private void ShowAd() {
        if (sharedPreferencesHelper.getAdShowingPopup()) {
            AdUtils.showAdLoading(activity);
        } else {
            AdUtils.showAdProgress(activity);
        }

        if (sharedPreferencesHelper.getAppOpenaAd()) {
            if (sharedPreferencesHelper.getOpenAdStatus()) {
                if (MainAlternatives) {
                    MainAlternatives = false;
                    ShowOpenAd();
                } else {
                    MainAlternatives = true;
                    ShowInterstitialsAd();
                }

            } else {
                ShowInterstitialsAd();
            }
        } else {
            ShowInterstitialsAd();
        }


    }

    public void ShowOpenAd() {
        if (sharedPreferencesHelper.getAdShowingPopup()) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> AdMobOpenAppAd.getInstance(activity).ShowOpenAd(AdShow.this::HandleClick), Constant.adProgressTime);

        } else {
            AdMobOpenAppAd.getInstance(activity).ShowOpenAd(this::HandleClick);
        }

    }

    public void ShowInterstitialsAd() {
        if (sharedPreferencesHelper.getAdShowingPopup()) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                AdMobInterstitialAd.getInstance(activity).ShowInterstitialAd(adShow -> {
                    if (adShow) {
                        HandleClick(true);
                    } else {
                        if (sharedPreferencesHelper.getshowappinhouse()) {

                            CustomInterstitialAd.getInstance(activity).ShowInterstitialAd(this::HandleClick);

                        } else {
                            HandleClick(false);
                        }
                    }
                });
            }, Constant.adProgressTime);
        } else {
            AdMobInterstitialAd.getInstance(activity).ShowInterstitialAd(adShow -> {
                if (adShow) {
                    HandleClick(true);
                } else {
                    if (sharedPreferencesHelper.getshowappinhouse()) {
                        CustomInterstitialAd.getInstance(activity).ShowInterstitialAd(this::HandleClick);

                    } else {
                        HandleClick(false);
                    }
                }
            });
        }

    }

    public void ShowRewardAd(HandleRewardedAd adShowingActivity) {
        if (!AdUtils.isNetworkAvailable(activity)) {
            ShowNetworkDialog();
            return;
        }
        handleRewardedAd = adShowingActivity;
        try {
            if (sharedPreferencesHelper.getShowAdinApp()) {
                if (sharedPreferencesHelper.getAdMobAdShow()) {
                    AdUtils.showAdLoading(activity);
                    if (sharedPreferencesHelper.getAdShowingPopup()) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(() -> {
                            AdMobRewardedAd.getInstance(activity).ShowRewardedAd(this::HandleRewardedClick);
                        }, Constant.adProgressTime);
                    } else {
                        AdMobRewardedAd.getInstance(activity).ShowRewardedAd(this::HandleRewardedClick);
                    }

                } else {
                    HandleRewardedClick(true);
                }
            } else {
                HandleRewardedClick(true);
            }
        } catch (NullPointerException e) {
            HandleRewardedClick(false);
        }


    }

    public void ShowNativeAd(ViewGroup nativeAdContainer, AdUtils.NativeType nativeType) {
        if (!AdUtils.isNetworkAvailable(activity)) {
            nativeAdContainer.setVisibility(View.GONE);
        }else {
            try {
                if (sharedPreferencesHelper.getShowAdinApp()) {
                    if (sharedPreferencesHelper.getAdMobAdShow()) {
                        switch (nativeType) {
                            case NATIVE_BANNER:
                                AdMobNativeAd.getInstance(activity).ShowNativeAdSmall(nativeAdContainer, new NativeAdListener() {
                                    @Override
                                    public void onAdShown(Boolean shown) {
                                        if (shown) {
                                            nativeAdContainer.setVisibility(View.VISIBLE);
                                        } else {
                                            CustomNativeAd.getInstance(activity).ShowNativeAdSmall(nativeAdContainer);
                                        }
                                    }
                                });


                                break;
                            case NATIVE_MEDIUM:
                                AdMobNativeAd.getInstance(activity).ShowNativeAdMedium(nativeAdContainer, new NativeAdListener() {
                                    @Override
                                    public void onAdShown(Boolean shown) {
                                        if (shown) {
                                            nativeAdContainer.setVisibility(View.VISIBLE);
                                        } else {
                                            CustomNativeAd.getInstance(activity).ShowNativeAdMedium(nativeAdContainer);
                                        }
                                    }
                                });


                                break;
                            case NATIVE_BIG:
                                AdMobNativeAd.getInstance(activity).ShowNativeAdBig(nativeAdContainer, new NativeAdListener() {
                                    @Override
                                    public void onAdShown(Boolean shown) {
                                        if (shown) {

                                            nativeAdContainer.setVisibility(View.VISIBLE);
                                        } else {
                                            CustomNativeAd.getInstance(activity).ShowNativeAdBig(nativeAdContainer);
                                        }
                                    }
                                });


                                break;
                            default:
                                nativeAdContainer.setVisibility(View.GONE);
                                break;
                        }

                    } else {
                        nativeAdContainer.setVisibility(View.GONE);
                    }

                } else {
                    nativeAdContainer.setVisibility(View.GONE);
                }
            } catch (NullPointerException e ) {
                nativeAdContainer.setVisibility(View.GONE);
            }catch (IllegalStateException e){
                e.printStackTrace();
            }
        }



    }

    public void ShowBannerAd(ViewGroup bannerAdContainer) {
        AdMobBannerAd.ShowAd(activity, bannerAdContainer);

    }

    void HandleClick(Boolean AdShow) {
        try {
            if (sharedPreferencesHelper.getAdShowingPopup()) {
                AdUtils.dismissAdLoading();
            } else {
                AdUtils.dismissAdProgress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (handleClick != null) {
            handleClick.Show(AdShow);
            handleClick = null;
        }
    }

    void HandleRewardedClick(Boolean AdShow) {
        try {
            AdUtils.dismissAdLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (handleRewardedAd != null) {
            handleRewardedAd.Show(AdShow);
            handleRewardedAd = null;
        }
    }


    private void ShowNetworkDialog() {
        Dialog dialog = new Dialog(activity);
        DialogInternetBinding dialogInternetBinding = DialogInternetBinding.inflate(activity.getLayoutInflater());
        dialog.setContentView(dialogInternetBinding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialogInternetBinding.buttonRetry.setOnClickListener(v -> {
            dialog.dismiss();
            Intent i = new Intent(android.provider.Settings.ACTION_SETTINGS);
            activity.startActivityForResult(i, 15);
        });


        dialog.show();

    }


}
