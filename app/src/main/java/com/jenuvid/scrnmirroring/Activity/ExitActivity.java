package com.jenuvid.scrnmirroring.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.databinding.ActivityExitBinding;

public class ExitActivity extends AppCompatActivity {

    Activity activity;
    ActivityExitBinding x;
    Boolean aBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityExitBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;
        todo();
    }

    private void todo() {




        aBoolean = getIntent().getBooleanExtra("Under", false);
        x.btnYes.setOnClickListener(v -> {
            finishAffinity();
            System.exit(0);
        });
        x.btnNo.setOnClickListener(v -> {
            if (aBoolean) {
                startActivity(new Intent(activity, UnderMaintenanceActivity.class));
            } else {
                startActivity(new Intent(activity, First_Start_Activity.class));
            }
            finish();

        });
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout.nativeAdLayout, AdUtils.NativeType.NATIVE_BIG);

    }
}