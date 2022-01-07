package com.example.softice.model;

import com.google.android.gms.ads.nativead.NativeAd;

public class AdMobNativeAdModel {
    String adUnit;
    NativeAd nativeAd;

    public AdMobNativeAdModel(String adUnit, NativeAd nativeAd) {
        this.adUnit = adUnit;

        this.nativeAd = nativeAd;
    }

    public String getAdUnit() {
        return adUnit;
    }

    public NativeAd getNativeAd() {
        return nativeAd;
    }

}
