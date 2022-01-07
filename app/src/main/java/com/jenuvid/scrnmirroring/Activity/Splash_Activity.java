package com.jenuvid.scrnmirroring.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.example.softice.activity.AppSettingActivity;
import com.example.softice.ad.AdMob.AdMobOpenAppAd;
import com.example.softice.ad.HandleClick.HandleOpenAd;
import com.example.softice.databinding.DialogAppUpdateBinding;
import com.example.softice.databinding.DialogInternetBinding;
import com.example.softice.listeners.AppSettingListeners;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.BuildConfig;
import com.jenuvid.scrnmirroring.R;
import com.jenuvid.scrnmirroring.databinding.ActivitySplashBinding;

public class Splash_Activity extends AppSettingActivity {


    private static final String TAG = Splash_Activity.class.getSimpleName();
    Activity activity;
    int LAUNCH_INTERNET_ACTIVITY = 1;
    ActivitySplashBinding x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;

        checkConnection();

    }

    private void PassActivity() {
        startActivity(new Intent(Splash_Activity.this, First_Start_Activity.class));
        finish();
    }

    private void ShowNetworkDialog() {
        Dialog dialog = new Dialog(activity);
        DialogInternetBinding dialogInternetBinding = DialogInternetBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogInternetBinding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialogInternetBinding.buttonRetry.setOnClickListener(v -> {
            dialog.dismiss();
            if (AdUtils.isNetworkAvailable(activity)) {
                loadSetting();
            } else {
                Intent i = new Intent(android.provider.Settings.ACTION_SETTINGS);
                startActivityForResult(i, LAUNCH_INTERNET_ACTIVITY);
            }

        });


        dialog.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_INTERNET_ACTIVITY) {
            checkConnection();
        }
    }

    private void checkConnection() {
        if (AdUtils.isNetworkAvailable(activity)) {
            loadSetting();
        } else {
            ShowNetworkDialog();
        }
    }

    private void loadSetting() {
        AppSettings(activity, BuildConfig.APPLICATION_ID, AdUtils.getKeyHash(activity, BuildConfig.APPLICATION_ID), AdUtils.getCurrentVersionCode(activity), BuildConfig.DEBUG, new AppSettingListeners() {
            @Override
            public void onResponseSuccess() {
                Log.d(TAG, "onResponseSuccess: ");
                AdMobOpenAppAd.getInstance(activity).ShowOpenAd(new HandleOpenAd() {
                    @Override
                    public void Show(boolean adShow) {
                        PassActivity();

                    }
                });

            }

            @Override
            public void onUnderMaintenance() {
                AdMobOpenAppAd.getInstance(activity).ShowOpenAd(new HandleOpenAd() {
                    @Override
                    public void Show(boolean adShow) {
                        startActivity(new Intent(activity, UnderMaintenanceActivity.class));
                        finish();

                    }
                });

            }

            @Override
            public void onResponseFail() {
                Log.d(TAG, "onResponseFail: ");
                recreate();
            }

            @Override
            public void onAppUpdate(String url) {
                Log.d(TAG, "onAppUpdate: ");
                AppDialogShow(url, 0);
            }

            @Override
            public void onAppRedirect(String url) {
                Log.d(TAG, "onAppRedirect: ");
                AppDialogShow(url, 1);
            }

            @Override
            public void onStatusChange() {
                Log.d(TAG, "onStatusChange: ");

            }
        });

    }

    private void AppDialogShow(String url, int i) {
        Dialog dialog = new Dialog(activity);
        DialogAppUpdateBinding dialogAppUpdateBinding = DialogAppUpdateBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogAppUpdateBinding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (i == 0) {
            dialogAppUpdateBinding.buttonRetry.setText(R.string.update_title);
            dialogAppUpdateBinding.txtTitle.setText(R.string.update_sub_title);
            dialogAppUpdateBinding.txtDecription.setText("");
            dialogAppUpdateBinding.txtDecription.setVisibility(View.GONE);
        } else if (i == 1) {
            dialogAppUpdateBinding.buttonRetry.setText(R.string.install_title);
            dialogAppUpdateBinding.txtTitle.setText(R.string.install_sub_title);
            dialogAppUpdateBinding.txtDecription.setVisibility(View.VISIBLE);
            dialogAppUpdateBinding.txtDecription.setText(R.string.install_descrption);
        } else {
            dialog.dismiss();
        }
        dialogAppUpdateBinding.buttonRetry.setOnClickListener(v -> {
            dialog.dismiss();
            try {
                Uri marketUri = Uri.parse(url);
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                startActivity(marketIntent);
            } catch (ActivityNotFoundException ignored) {
            }
        });


        dialog.show();
    }
}