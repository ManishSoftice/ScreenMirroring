package com.jenuvid.scrnmirroring.connect_TV;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.databinding.ActivityHowToUseBinding;

public class HowToUseActivity extends AppCompatActivity {

    Activity activity;
    ActivityHowToUseBinding x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityHowToUseBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        activity = this;
        setContentView(view);
        todo();
    }

    private void todo() {

        x.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {

        AdShow.getInstance(activity).ShowAd(new HandleClick() {
            @Override
            public void Show(boolean adShow) {
                HowToUseActivity.super.onBackPressed();
            }
        }, AdUtils.ClickType.BACK_CLICK);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout.nativeAdLayout, AdUtils.NativeType.NATIVE_BIG);

    }
}