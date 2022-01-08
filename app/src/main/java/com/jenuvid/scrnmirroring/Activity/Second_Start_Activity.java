package com.jenuvid.scrnmirroring.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.connect_TV.HowToUseActivity;
import com.jenuvid.scrnmirroring.databinding.ActivitySecondStartBinding;

public class Second_Start_Activity extends AppCompatActivity {

    Activity activity;
    private ActivitySecondStartBinding x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivitySecondStartBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;
        todo();

    }

    private void todo() {

        x.secondStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdShow.getInstance(activity).ShowAd(new HandleClick() {
                    @Override
                    public void Show(boolean adShow) {
                        startActivity(new Intent(activity, Main_Screen_Activity.class));
                    }
                }, AdUtils.ClickType.MAIN_CLICK);

            }
        });

        x.howToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdShow.getInstance(activity).ShowAd(new HandleClick() {
                    @Override
                    public void Show(boolean adShow) {
                        startActivity(new Intent(activity, HowToUseActivity.class));
                    }
                }, AdUtils.ClickType.MAIN_CLICK);

            }
        });

    }

    @Override
    public void onBackPressed() {

        AdShow.getInstance(activity).ShowAd(new HandleClick() {
            @Override
            public void Show(boolean adShow) {
                Second_Start_Activity.super.onBackPressed();
            }
        }, AdUtils.ClickType.BACK_CLICK);

    }

    @Override
    protected void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout.nativeAdLayout, AdUtils.NativeType.NATIVE_BIG);
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout2, AdUtils.NativeType.NATIVE_BANNER);
    }
}