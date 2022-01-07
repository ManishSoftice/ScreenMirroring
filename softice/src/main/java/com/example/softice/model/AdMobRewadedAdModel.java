package com.example.softice.model;

import com.google.android.gms.ads.rewarded.RewardedAd;

public class AdMobRewadedAdModel {
    String adUnit;

    RewardedAd rewardedAd;

    public AdMobRewadedAdModel(String adUnit, RewardedAd rewardedAd) {
        this.adUnit = adUnit;
        this.rewardedAd = rewardedAd;
    }

    public String getAdUnit() {
        return adUnit;
    }

    public RewardedAd getRewardedAd() {
        return rewardedAd;
    }

}
