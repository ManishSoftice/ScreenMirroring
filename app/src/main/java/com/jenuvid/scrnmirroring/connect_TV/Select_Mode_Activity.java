package com.jenuvid.scrnmirroring.connect_TV;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.databinding.ActivitySelectModeBinding;

public class Select_Mode_Activity extends AppCompatActivity {

    Activity activity;
    ActivitySelectModeBinding x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivitySelectModeBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;

        todo();

    }

    private void todo() {


        x.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdShow.getInstance(activity).ShowAd(new HandleClick() {
                    @Override
                    public void Show(boolean adShow) {
                        startActivity(new Intent(activity, Proccess_Activity.class));
                    }
                }, AdUtils.ClickType.MAIN_CLICK);

            }
        });
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
                Select_Mode_Activity.super.onBackPressed();
            }
        }, AdUtils.ClickType.MAIN_CLICK);
    }
}