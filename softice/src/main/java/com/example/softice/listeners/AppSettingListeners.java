package com.example.softice.listeners;

public interface AppSettingListeners {

    void onResponseSuccess();
    void onUnderMaintenance();
    void onResponseFail();

    void onAppUpdate(String url);

    void onAppRedirect(String url);

    void onStatusChange();


}
