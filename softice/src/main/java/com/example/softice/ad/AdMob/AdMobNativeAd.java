package com.example.softice.ad.AdMob;


import static com.daimajia.androidanimations.library.YoYo.INFINITE;
import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;
import static com.google.android.gms.ads.nativead.NativeAdOptions.ADCHOICES_TOP_RIGHT;
import static com.google.android.gms.ads.nativead.NativeAdOptions.NATIVE_MEDIA_ASPECT_RATIO_LANDSCAPE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.softice.ad.HandleClick.NativeAdListener;
import com.example.softice.databinding.AdmobBigNativeAdLayoutBinding;
import com.example.softice.databinding.AdmobMeduimNativeAdLayoutBinding;
import com.example.softice.databinding.AdmobSmallNativeAdLayoutBinding;
import com.example.softice.model.AdMobNativeAdModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;

import java.util.ArrayList;
import java.util.Objects;

public class AdMobNativeAd {

    private static final String TAG = AdMobNativeAd.class.getSimpleName();
    public static ArrayList<AdMobNativeAdModel> adMobNativeAdModels = new ArrayList<>();
    public static int CurrentNativeAdPosition = 0;



    public static String adType;
    public static Boolean adloaded = false;

    @SuppressLint("StaticFieldLeak")
    private static AdMobNativeAd mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    NativeAdListener nativeAdListener = null;
    NativeAd nativeAd = null;
    NativeAd nativeAd2 = null;
    NativeAd nativeAd3 = null;
    ViewGroup nativeAdViewGroup;
    AdLoader adLoader;

    public AdMobNativeAd(Activity activity) {
        AdMobNativeAd.activity = activity;

    }

    public static AdMobNativeAd getInstance(Activity adShowingActivity) {
        if (mInstance == null) {
            mInstance = new AdMobNativeAd(adShowingActivity);
        }
        return mInstance;
    }

    public void LoadNativeAds() {

        if (!adMobNativeAdModels.isEmpty()) {
            if (CurrentNativeAdPosition == adMobNativeAdModels.size() - 1)
                CurrentNativeAdPosition = 0;
            else CurrentNativeAdPosition++;

            if (nativeAd == null) {
                adLoader = new AdLoader.Builder(activity, adMobNativeAdModels.get(CurrentNativeAdPosition).getAdUnit())
                        .forNativeAd(nativeAd -> {
                            this.nativeAd = nativeAd;
                            CheckNativeAd();
                        })
                        .withAdListener(new AdListener() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                                CheckNativeFailAd();
                            }
                        })
                        .withNativeAdOptions(new NativeAdOptions.Builder().setRequestMultipleImages(true).setAdChoicesPlacement(ADCHOICES_TOP_RIGHT).setMediaAspectRatio(NATIVE_MEDIA_ASPECT_RATIO_LANDSCAPE).build()).build();
                adLoader.loadAd(new AdRequest.Builder().build());

            } else if (nativeAd2 == null) {
                adLoader = new AdLoader.Builder(activity, adMobNativeAdModels.get(CurrentNativeAdPosition).getAdUnit())
                        .forNativeAd(nativeAd -> {
                            this.nativeAd2 = nativeAd;
                            CheckNativeAd();

                        })
                        .withAdListener(new AdListener() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                                CheckNativeFailAd();
                            }
                        })
                        .withNativeAdOptions(new NativeAdOptions.Builder().setRequestMultipleImages(true).setAdChoicesPlacement(ADCHOICES_TOP_RIGHT).setMediaAspectRatio(NATIVE_MEDIA_ASPECT_RATIO_LANDSCAPE).build()).build();
                adLoader.loadAd(new AdRequest.Builder().build());

            } else if (nativeAd3 == null) {
                adLoader = new AdLoader.Builder(activity, adMobNativeAdModels.get(CurrentNativeAdPosition).getAdUnit())
                        .forNativeAd(nativeAd2 -> {
                            this.nativeAd3 = nativeAd2;
                            CheckNativeAd();

                        })
                        .withAdListener(new AdListener() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                                CheckNativeFailAd();
                            }
                        })
                        .withNativeAdOptions(new NativeAdOptions.Builder().setRequestMultipleImages(true).setAdChoicesPlacement(ADCHOICES_TOP_RIGHT).setMediaAspectRatio(NATIVE_MEDIA_ASPECT_RATIO_LANDSCAPE).build()).build();
                adLoader.loadAd(new AdRequest.Builder().build());

            }


        }
    }

    private void CheckNativeFailAd() {
        if (adloaded) {
            if (adType.equals("ShowNativeAdSmall")) {
                if (nativeAdViewGroup != null && nativeAdListener != null) {
                    nativeAdViewGroup.setVisibility(View.GONE);
                    NativeAdCallBack(false);

                }

            }
            if (adType.equals("ShowNativeAdBig")) {
                if (nativeAdViewGroup != null && nativeAdListener != null) {
                    nativeAdViewGroup.setVisibility(View.GONE);
                    NativeAdCallBack(false);
                }

            }
            if (adType.equals("ShowNativeAdMedium")) {
                if (nativeAdViewGroup != null && nativeAdListener != null) {
                    nativeAdViewGroup.setVisibility(View.GONE);
                    NativeAdCallBack(false);
                }

            }
        }
    }


    private void CheckNativeAd() {
        if (adloaded) {
            if (adType.equals("ShowNativeAdSmall")) {
                if (nativeAdViewGroup != null && nativeAdListener != null) {
                    ShowNativeAdSmall(nativeAdViewGroup, nativeAdListener);
                }

            }
            if (adType.equals("ShowNativeAdBig")) {
                if (nativeAdViewGroup != null && nativeAdListener != null) {
                    ShowNativeAdBig(nativeAdViewGroup, nativeAdListener);
                }

            }
            if (adType.equals("ShowNativeAdMedium")) {
                if (nativeAdViewGroup != null && nativeAdListener != null) {
                    ShowNativeAdMedium(nativeAdViewGroup, nativeAdListener);
                }

            }
        }
    }

    public void ShowNativeAdSmall(ViewGroup nativeAdContainer, NativeAdListener adListener) {
        this.nativeAdListener = adListener;
        if (!adMobNativeAdModels.isEmpty()) {
            nativeAdViewGroup = nativeAdContainer;
            if (nativeAd != null) {
                SmallNativeAd(nativeAd);
                nativeAd = null;

            } else if (nativeAd2 != null) {
                SmallNativeAd(nativeAd2);
                nativeAd2 = null;

            } else if (nativeAd3 != null) {
                SmallNativeAd(nativeAd3);
                nativeAd3 = null;

            } else {
                adloaded = true;
                adType = "ShowNativeAdSmall";

            }


        } else {
            nativeAdContainer.setVisibility(View.GONE);
            NativeAdCallBack(false);
        }

    }

    public void ShowNativeAdMedium(ViewGroup nativeAdContainer, NativeAdListener adListener) {
        this.nativeAdListener = adListener;
        if (!adMobNativeAdModels.isEmpty()) {
            nativeAdViewGroup = nativeAdContainer;
            if (nativeAd != null) {
                MeduimNativeAd(nativeAd);
                nativeAd = null;

            } else if (nativeAd2 != null) {
                MeduimNativeAd(nativeAd2);
                nativeAd2 = null;

            } else if (nativeAd3 != null) {
                MeduimNativeAd(nativeAd3);
                nativeAd3 = null;

            } else {
                adloaded = true;
                adType = "ShowNativeAdMedium";
            }

        } else {
            nativeAdContainer.setVisibility(View.GONE);
            NativeAdCallBack(false);
        }

    }

    public void ShowNativeAdBig(ViewGroup nativeAdContainer, NativeAdListener adListener) {
        this.nativeAdListener = adListener;
        if (!adMobNativeAdModels.isEmpty()) {
            nativeAdViewGroup = nativeAdContainer;
            if (nativeAd != null) {
                BigNativeAd(nativeAd);
                nativeAd = null;
            } else if (nativeAd2 != null) {
                BigNativeAd(nativeAd2);
                nativeAd2 = null;

            } else if (nativeAd3 != null) {
                BigNativeAd(nativeAd3);
                nativeAd3 = null;

            } else {
                adloaded = true;
                adType = "ShowNativeAdBig";

            }


        } else {
            nativeAdContainer.setVisibility(View.GONE);
            NativeAdCallBack(false);
        }

    }


    private void SmallNativeAd(NativeAd nativeAd) {
        adloaded = false;
        adType = "";
        nativeAdViewGroup.setVisibility(View.VISIBLE);
        AdmobSmallNativeAdLayoutBinding admobSmallNativeAdLayoutBinding = AdmobSmallNativeAdLayoutBinding.inflate(LayoutInflater.from(activity));
        displaySmallNativeAd(nativeAd, admobSmallNativeAdLayoutBinding);
        nativeAdViewGroup.removeAllViews();
        nativeAdViewGroup.addView(admobSmallNativeAdLayoutBinding.getRoot());
    }

    private void MeduimNativeAd(NativeAd nativeAd) {
        adloaded = false;
        adType = "";
        nativeAdViewGroup.setVisibility(View.VISIBLE);
        AdmobMeduimNativeAdLayoutBinding admobMeduimNativeAdLayoutBinding = AdmobMeduimNativeAdLayoutBinding.inflate(LayoutInflater.from(activity));
        displayMediumNativeAd(nativeAd, admobMeduimNativeAdLayoutBinding);
        nativeAdViewGroup.removeAllViews();
        nativeAdViewGroup.addView(admobMeduimNativeAdLayoutBinding.getRoot());
    }

    private void BigNativeAd(NativeAd nativeAd) {
        adloaded = false;
        adType = "";
        nativeAdViewGroup.setVisibility(View.VISIBLE);
        AdmobBigNativeAdLayoutBinding admobBigNativeAdLayoutBinding = AdmobBigNativeAdLayoutBinding.inflate(LayoutInflater.from(activity));
        displayBigNativeAd(nativeAd, admobBigNativeAdLayoutBinding);
        nativeAdViewGroup.removeAllViews();
        nativeAdViewGroup.addView(admobBigNativeAdLayoutBinding.getRoot());
    }


    private void displayBigNativeAd(NativeAd loadedNativeAd, AdmobBigNativeAdLayoutBinding adView) {
        LoadNativeAds();
        adView.getRoot().setIconView(adView.imageAdIcon);
        adView.getRoot().setHeadlineView(adView.textAdTitle);
        adView.getRoot().setStarRatingView(adView.ratingBarAd);
        adView.getRoot().setBodyView(adView.textAdBody);
        adView.getRoot().setMediaView(adView.nativeAdMediaView);
        adView.getRoot().setPriceView(adView.textAdvertiser);
        adView.getRoot().setCallToActionView(adView.buttonCallToAction);
        if (loadedNativeAd.getIcon() == null) {
            adView.imageAdIcon.setVisibility(View.GONE);
        } else {
            adView.imageAdIcon.setVisibility(View.VISIBLE);
            adView.imageAdIcon.setImageDrawable(loadedNativeAd.getIcon().getDrawable());
        }
        if (loadedNativeAd.getHeadline() == null) {
            adView.textAdTitle.setVisibility(View.GONE);
        } else {
            adView.textAdTitle.setVisibility(View.VISIBLE);
            adView.textAdTitle.setText(loadedNativeAd.getHeadline());
        }
        if (loadedNativeAd.getStarRating() == null) {
            adView.ratingBarAd.setVisibility(View.GONE);
        } else {
            adView.ratingBarAd.setVisibility(View.VISIBLE);
            adView.ratingBarAd.setRating(loadedNativeAd.getStarRating().floatValue());
        }
        if (loadedNativeAd.getBody() == null) {
            adView.textAdBody.setVisibility(View.GONE);
        } else {
            adView.textAdBody.setVisibility(View.VISIBLE);
            adView.textAdBody.setText(loadedNativeAd.getBody());
        }
        if (loadedNativeAd.getMediaContent() == null) {
            adView.nativeAdMediaView.setVisibility(View.GONE);
        } else {
            adView.nativeAdMediaView.setVisibility(View.VISIBLE);
            adView.nativeAdMediaView.setMediaContent(loadedNativeAd.getMediaContent());
//            adView.nativeAdMediaView.setImageScaleType(ImageView.ScaleType.FIT_CENTER);

        }
        if (loadedNativeAd.getPrice() == null) {
            adView.textAdvertiser.setVisibility(View.GONE);
        } else {
            adView.textAdvertiser.setVisibility(View.VISIBLE);
            adView.textAdvertiser.setText(loadedNativeAd.getPrice());
        }
        if (loadedNativeAd.getCallToAction() == null) {
            adView.buttonCallToAction.setVisibility(View.GONE);
        } else {
            adView.buttonCallToAction.setVisibility(View.VISIBLE);
            adView.buttonCallToAction.setText(loadedNativeAd.getCallToAction());
        }

        adView.getRoot().setNativeAd(loadedNativeAd);
        VideoController videoController = loadedNativeAd.getMediaContent().getVideoController();
        if (videoController.hasVideoContent()) {
            videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        }
        if (sharedPreferencesHelper.getButtonAnimation()) {
            YoYo.with(Techniques.Flash)
                    .duration(1500)
                    .repeat(INFINITE)
                    .playOn(adView.buttonCallToAction);

        }
        NativeAdCallBack(true);
    }

    private void displayMediumNativeAd(NativeAd loadedNativeAd, AdmobMeduimNativeAdLayoutBinding adView) {
        LoadNativeAds();
        adView.getRoot().setIconView(adView.imageAdIcon);
        adView.getRoot().setHeadlineView(adView.textAdTitle);
        adView.getRoot().setBodyView(adView.textAdBody);
        adView.getRoot().setStarRatingView(adView.ratingBarAd);
        adView.getRoot().setMediaView(adView.nativeAdMediaView);
        adView.getRoot().setCallToActionView(adView.buttonCallToAction);
        if (loadedNativeAd.getIcon() == null) {
            adView.imageAdIcon.setVisibility(View.GONE);
        } else {
            adView.imageAdIcon.setVisibility(View.VISIBLE);
            adView.imageAdIcon.setImageDrawable(loadedNativeAd.getIcon().getDrawable());
        }
        if (loadedNativeAd.getHeadline() == null) {
            adView.textAdTitle.setVisibility(View.GONE);
        } else {
            adView.textAdTitle.setVisibility(View.VISIBLE);
            adView.textAdTitle.setText(loadedNativeAd.getHeadline());
        }
        if (loadedNativeAd.getStarRating() == null) {
            adView.ratingBarAd.setVisibility(View.GONE);
        } else {
            adView.ratingBarAd.setVisibility(View.VISIBLE);
            adView.ratingBarAd.setRating(loadedNativeAd.getStarRating().floatValue());
        }
        if (loadedNativeAd.getBody() == null) {
            adView.textAdBody.setVisibility(View.GONE);
        } else {
            adView.textAdBody.setVisibility(View.VISIBLE);
            adView.textAdBody.setText(loadedNativeAd.getBody());
        }
        if (loadedNativeAd.getMediaContent() == null) {
            adView.nativeAdMediaView.setVisibility(View.GONE);
        } else {
            adView.nativeAdMediaView.setVisibility(View.VISIBLE);
            adView.nativeAdMediaView.setMediaContent(loadedNativeAd.getMediaContent());
//            adView.nativeAdMediaView.setImageScaleType(ImageView.ScaleType.FIT_CENTER);

        }

        if (loadedNativeAd.getCallToAction() == null) {
            adView.buttonCallToAction.setVisibility(View.GONE);
        } else {
            adView.buttonCallToAction.setVisibility(View.VISIBLE);
            adView.buttonCallToAction.setText(loadedNativeAd.getCallToAction());
        }

        adView.getRoot().setNativeAd(loadedNativeAd);
        VideoController videoController = loadedNativeAd.getMediaContent().getVideoController();
        if (videoController.hasVideoContent()) {
            videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        }
        if (sharedPreferencesHelper.getButtonAnimation()) {
            YoYo.with(Techniques.Flash)
                    .duration(1500)
                    .repeat(INFINITE)
                    .playOn(adView.buttonCallToAction);

        }
        NativeAdCallBack(true);
    }

    private void displaySmallNativeAd(NativeAd loadedNativeAd, AdmobSmallNativeAdLayoutBinding adView) {
        LoadNativeAds();
        adView.getRoot().setIconView(adView.imageAdIcon);
        adView.getRoot().setHeadlineView(adView.textAdTitle);
        adView.getRoot().setBodyView(adView.textAdBody);
        adView.getRoot().setCallToActionView(adView.buttonCallToAction);
        if (loadedNativeAd.getIcon() == null) {
            adView.imageAdIcon.setVisibility(View.GONE);
        } else {
            adView.imageAdIcon.setVisibility(View.VISIBLE);
            adView.imageAdIcon.setImageDrawable(loadedNativeAd.getIcon().getDrawable());
        }
        if (loadedNativeAd.getHeadline() == null) {
            adView.textAdTitle.setVisibility(View.GONE);
        } else {
            adView.textAdTitle.setVisibility(View.VISIBLE);
            adView.textAdTitle.setText(loadedNativeAd.getHeadline());
        }

        if (loadedNativeAd.getBody() == null) {
            adView.textAdBody.setVisibility(View.GONE);
        } else {
            adView.textAdBody.setVisibility(View.VISIBLE);
            adView.textAdBody.setText(loadedNativeAd.getBody());
        }


        if (loadedNativeAd.getCallToAction() == null) {
            adView.buttonCallToAction.setVisibility(View.GONE);
        } else {
            adView.buttonCallToAction.setVisibility(View.VISIBLE);
            adView.buttonCallToAction.setText(loadedNativeAd.getCallToAction());
        }

        adView.getRoot().setNativeAd(loadedNativeAd);
        VideoController videoController = Objects.requireNonNull(loadedNativeAd.getMediaContent()).getVideoController();
        if (videoController.hasVideoContent()) {
            videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        }
        if (sharedPreferencesHelper.getButtonAnimation()) {
            YoYo.with(Techniques.Flash)
                    .duration(1500)
                    .repeat(INFINITE)
                    .playOn(adView.buttonCallToAction);

        }
        NativeAdCallBack(true);
    }

    public void NativeAdCallBack(Boolean aBoolean) {
        if (nativeAdListener != null) {
            nativeAdListener.onAdShown(aBoolean);
            nativeAdListener = null;
        }
    }


}
