package com.example.softice.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.softice.BuildConfig;


public class SharedPreferencesHelper {
    public SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static SharedPreferencesHelper init(Context context) {
        SharedPreferences sharedPreferences =context.getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, Context.MODE_PRIVATE);
        return new SharedPreferencesHelper(sharedPreferences);
    }

    public Boolean getAppInstallStatus() {
        return sharedPreferences.getBoolean(Constant.first_time_app_install, true);
    }

    public void setAppInstallStatus(Boolean appInstallStatus) {
        sharedPreferences.edit().putBoolean(Constant.first_time_app_install, appInstallStatus).apply();
    }

    public Boolean getVersionUpdateDialog() {
        return sharedPreferences.getBoolean(Constant.versionupdatedialog, false);
    }

    public void setVersionUpdateDialog(Boolean versionupdatedialog) {
        sharedPreferences.edit().putBoolean(Constant.versionupdatedialog, versionupdatedialog).apply();
    }

    public Boolean getRedirectOtherApp() {
        return sharedPreferences.getBoolean(Constant.redirectotherapp, false);
    }

    public void setRedirectOtherApp(Boolean redirectotherapp) {
        sharedPreferences.edit().putBoolean(Constant.redirectotherapp, redirectotherapp).apply();
    }

    public String getAppAccountLink() {
        return sharedPreferences.getString(Constant.appaccountlink, "1");
    }

    public void setAppAccountLink(String appaccountlink) {
        sharedPreferences.edit().putString(Constant.appaccountlink, appaccountlink).apply();
    }

    public String getVersionCode() {
        return sharedPreferences.getString(Constant.versioncode, "1");
    }

    public void setVersionCode(String versioncode) {
        sharedPreferences.edit().putString(Constant.versioncode, versioncode).apply();

    }

    public Boolean getShowAdinApp() {
        return sharedPreferences.getBoolean(Constant.showadinapp, false);
    }

    public void setShowAdinApp(Boolean showadinapp) {
        sharedPreferences.edit().putBoolean(Constant.showadinapp, showadinapp).apply();
    }

    public Boolean getAdMobAdShow() {
        return sharedPreferences.getBoolean(Constant.AdMobAdShow, false);
    }

    public void setAdMobAdShow(Boolean adMobAdShow) {
        sharedPreferences.edit().putBoolean(Constant.AdMobAdShow, adMobAdShow).apply();
    }

    public int getMainClick() {
        return sharedPreferences.getInt(Constant.IntCounter, 0);
    }

    public void setMainClick(int mainClick) {
        sharedPreferences.edit().putInt(Constant.IntCounter, mainClick).apply();
    }

    public int getBackClick() {
        return sharedPreferences.getInt(Constant.IntBackCounter, 0);
    }

    public void setBackClick(int backClick) {
        sharedPreferences.edit().putInt(Constant.IntBackCounter, backClick).apply();
    }

    public Boolean getOpenAdStatus() {
        return sharedPreferences.getBoolean(Constant.AltIntAppOpenStuse, false);
    }

    public void setOpenAdStatus(boolean altIntAppOpenStuse) {
        sharedPreferences.edit().putBoolean(Constant.AltIntAppOpenStuse, altIntAppOpenStuse).apply();
    }

    public Boolean getAMDStatus() {
        return sharedPreferences.getBoolean(Constant.AMDClickStatus, false);
    }

    public void setAMDStatus(boolean isaMDClickStatus) {
        sharedPreferences.edit().putBoolean(Constant.AMDClickStatus, isaMDClickStatus).apply();
    }

    public Boolean getAppOpenaAd() {
        return sharedPreferences.getBoolean(Constant.appopenad, false);
    }

    public void setAppOpenaAd(boolean appOpenaAd) {
        sharedPreferences.edit().putBoolean(Constant.appopenad, appOpenaAd).apply();
    }

    public Boolean getAdShowingPopup() {
        return sharedPreferences.getBoolean(Constant.adshowingpopup, true);
    }

    public void setAdShowingPopup(boolean adShowingPopup) {
        sharedPreferences.edit().putBoolean(Constant.adshowingpopup, adShowingPopup).apply();
    }

    public String getPrivacyPolicy() {
        return sharedPreferences.getString(Constant.privacypolicy, "");
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        sharedPreferences.edit().putString(Constant.privacypolicy, privacyPolicy).apply();
    }

    public Boolean getshowappinhouse() {
        return sharedPreferences.getBoolean(Constant.showappinhouse, false);
    }

    public void setshowappinhouse(boolean appOpenaAd) {
        sharedPreferences.edit().putBoolean(Constant.showappinhouse, appOpenaAd).apply();
    }
    public Boolean getButtonAnimation() {
        return sharedPreferences.getBoolean(Constant.buttonAnimation, false);
    }

    public void setButtonAnimation(Boolean buttonAnimation) {
        sharedPreferences.edit().putBoolean(Constant.buttonAnimation, buttonAnimation).apply();
    }
    public void setVal(String key, boolean appOpenaAd) {
        sharedPreferences.edit().putBoolean(key, appOpenaAd).apply();
    }

    public Boolean getBooleanVal(String key) {
        return sharedPreferences.getBoolean(key, false);
    }
}
