package com.jenuvid.scrnmirroring.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.Utils.Utils;
import com.jenuvid.scrnmirroring.databinding.ActivityFirstStartBinding;

public class First_Start_Activity extends AppCompatActivity {

    Activity activity;
    ActivityFirstStartBinding x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityFirstStartBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;
        todo();

    }

    private void todo() {

        x.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdShow.getInstance(activity).ShowAd(new HandleClick() {
                    @Override
                    public void Show(boolean adShow) {
                        startActivity(new Intent(First_Start_Activity.this, Second_Start_Activity.class));
                    }
                }, AdUtils.ClickType.MAIN_CLICK);

            }
        });

        x.cvPolicy.setOnClickListener(view -> {
            startActivity(new Intent(activity, PrivacyPolicyActivity.class));
        });
        x.cvShare.setOnClickListener(view -> {
            Utils.shareApp(activity);
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(First_Start_Activity.this, ExitActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout.nativeAdLayout, AdUtils.NativeType.NATIVE_BIG);
    }
}