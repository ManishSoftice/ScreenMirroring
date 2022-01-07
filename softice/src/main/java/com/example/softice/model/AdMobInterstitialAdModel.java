package com.example.softice.model;

import com.google.android.gms.ads.interstitial.InterstitialAd;

public class AdMobInterstitialAdModel {
    String adUnit;

    InterstitialAd interstitialAd;

    public AdMobInterstitialAdModel(String adUnit, InterstitialAd interstitialAd) {
        this.adUnit = adUnit;

        this.interstitialAd = interstitialAd;
    }

    public String getAdUnit() {
        return adUnit;
    }

    public InterstitialAd getInterstitialAd() {
        return interstitialAd;
    }

}
