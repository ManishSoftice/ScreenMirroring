package com.jenuvid.scrnmirroring.Gallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.Adapter.FullScreenImageAdapter;
import com.jenuvid.scrnmirroring.R;

import java.util.ArrayList;

public class FullScreenImageActivity extends AppCompatActivity {

    public static ArrayList<String> FolderData;
    Activity activity;

    ViewPager pager;
    ArrayList<String> img = new ArrayList<>();
    int pos;

    FullScreenImageAdapter fullScreenImageAdapter;
    float sumPositionAndpostionOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_screen_image);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity = this;


        img = FolderData;
        pos = getIntent().getIntExtra("pos", 1);
        pager = findViewById(R.id.pager);
        fullScreenImageAdapter = new FullScreenImageAdapter(activity, img);
        pager.setAdapter(fullScreenImageAdapter);
        pager.setCurrentItem(pos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(findViewById(R.id.native_ad_layout), AdUtils.NativeType.NATIVE_BANNER);

    }

    @Override
    public void onBackPressed() {

        AdShow.getInstance(activity).ShowAd(new HandleClick() {
            @Override
            public void Show(boolean adShow) {
                FullScreenImageActivity.super.onBackPressed();
            }
        }, AdUtils.ClickType.BACK_CLICK);

    }
}