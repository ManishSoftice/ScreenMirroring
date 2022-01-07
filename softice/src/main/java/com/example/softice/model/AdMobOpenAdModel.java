package com.example.softice.model;

import com.google.android.gms.ads.appopen.AppOpenAd;

public class AdMobOpenAdModel {
    String adUnit;
    AppOpenAd appOpenAd;
    long time;

    public AdMobOpenAdModel(String adUnit, AppOpenAd appOpenAd, long time) {
        this.adUnit = adUnit;
        this.time = time;
        this.appOpenAd = appOpenAd;

    }

    public String getAdUnit() {
        return adUnit;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public AppOpenAd getAppOpenAd() {
        return appOpenAd;
    }


}
