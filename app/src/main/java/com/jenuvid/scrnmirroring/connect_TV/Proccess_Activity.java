package com.jenuvid.scrnmirroring.connect_TV;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.R;
import com.jenuvid.scrnmirroring.databinding.ActivityProccessBinding;

public class Proccess_Activity extends AppCompatActivity {

    Activity activity;
    ActivityProccessBinding x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x = ActivityProccessBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;
        todo();

    }

    private void todo() {

        setprocess(1);
        x.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdShow.getInstance(activity).ShowAd(new HandleClick() {
                    @Override
                    public void Show(boolean adShow) {
                        startActivity(new Intent(activity,Steps_Activity.class));
                        finish();
                    }
                }, AdUtils.ClickType.MAIN_CLICK);

            }
        });
    }

    private void setprocess(int i) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                switch (i) {
                    case 1:
                        x.image1.setImageResource(R.drawable.ic_baseline_offline_pin_24);
                        setprocess(2);
                        break;
                    case 2:
                        x.image2.setImageResource(R.drawable.ic_baseline_offline_pin_24);
                        setprocess(3);
                        break;
                    case 3:
                        x.image3.setImageResource(R.drawable.ic_baseline_offline_pin_24);
                        setprocess(4);
                        break;
                    case 4:
                        x.image4.setImageResource(R.drawable.ic_baseline_offline_pin_24);
                        x.ok.setVisibility(View.VISIBLE);
                        x.animationView.pauseAnimation();
                        break;
                }
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout.nativeAdLayout, AdUtils.NativeType.NATIVE_BIG);

    }

    @Override
    public void onBackPressed() {

    }
}