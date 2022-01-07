package com.example.softice.ad.AdMob;


import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

import java.util.ArrayList;

public class AdMobBannerAd {

    private static final String TAG = AdMobBannerAd.class.getSimpleName();
    public static ArrayList<String> bannerAdIds = new ArrayList<>();
    public static int CurrentBannerAdId = 0;

    public static void ShowAd(Activity activity, ViewGroup bannerAdContainer) {
        if (!bannerAdIds.isEmpty()) {
            if (CurrentBannerAdId == bannerAdIds.size() - 1) CurrentBannerAdId = 0;
            else CurrentBannerAdId++;
            bannerAdContainer.setVisibility(View.GONE);
            bannerAdContainer.removeAllViews();
            AdView bannerAdView = new AdView(activity);
            bannerAdView.setAdUnitId(bannerAdIds.get(CurrentBannerAdId));
            bannerAdContainer.addView(bannerAdView);
            AdRequest build = new AdRequest.Builder().build();
            bannerAdView.setAdSize(AdSize.BANNER);
            bannerAdView.loadAd(build);
            bannerAdView.setAdListener(new AdListener() {
                public void onAdClicked() {

                }

                public void onAdClosed() {

                }

                public void onAdOpened() {


                }

                public void onAdLoaded() {

                    bannerAdContainer.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    bannerAdContainer.setVisibility(View.GONE);


                }
            });
        }


    }

    public static AdSize getAdSize(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, (int) (((float) displayMetrics.widthPixels) / displayMetrics.density));
    }

}
