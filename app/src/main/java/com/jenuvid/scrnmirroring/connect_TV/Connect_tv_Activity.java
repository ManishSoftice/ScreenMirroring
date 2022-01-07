package com.jenuvid.scrnmirroring.connect_TV;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.databinding.ActivityConnectTvBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class Connect_tv_Activity extends AppCompatActivity {

    Activity activity;
    private ActivityConnectTvBinding x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityConnectTvBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;
        todo();
    }

    private void todo() {

        x.connectNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdShow.getInstance(activity).ShowAd(new HandleClick() {
                    @Override
                    public void Show(boolean adShow) {
                        startActivity(new Intent(activity, All_Compny_Name_Activity.class));
                    }
                }, AdUtils.ClickType.MAIN_CLICK);

            }
        });

        x.playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               checkPermision();
            }
        });
    }

    private void checkPermision() {
        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

                if (report.areAllPermissionsGranted()) {
                    AdShow.getInstance(activity).ShowAd(new HandleClick() {
                        @Override
                        public void Show(boolean adShow) {
                            startActivity(new Intent(activity, PlayvideonowActivity.class));
                        }
                    }, AdUtils.ClickType.MAIN_CLICK);

                } else {
                    checkPermision();
                }

                if (report.isAnyPermissionPermanentlyDenied()) {
                    checkPermision();
                }
            }


            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();

    }

    @Override
    protected void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout.nativeAdLayout, AdUtils.NativeType.NATIVE_BIG);
    }

    @Override
    public void onBackPressed() {

        AdShow.getInstance(activity).ShowAd(new HandleClick() {
            @Override
            public void Show(boolean adShow) {
                Connect_tv_Activity.super.onBackPressed();
            }
        }, AdUtils.ClickType.BACK_CLICK);
    }
}