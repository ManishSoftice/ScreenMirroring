package com.jenuvid.scrnmirroring.connect_TV;

import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.Adapter.Compny_Name_Adapter;
import com.jenuvid.scrnmirroring.databinding.ActivityAllCompnyNameBinding;

import java.util.ArrayList;
import java.util.List;

public class All_Compny_Name_Activity extends AppCompatActivity {


    List<String> list = new ArrayList<>();
    Activity activity;
    ActivityAllCompnyNameBinding x;
    Boolean isfirsttime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityAllCompnyNameBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;

        getAllName();
        todo();


    }

    private void todo() {

        bottomAd();

        Compny_Name_Adapter name_adapter = new Compny_Name_Adapter(activity, list);
        x.recycler.setAdapter(name_adapter);

        x.other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdShow.getInstance(activity).ShowAd(new HandleClick() {
                    @Override
                    public void Show(boolean adShow) {
                        startActivity(new Intent(activity, Select_Mode_Activity.class));
                    }
                }, AdUtils.ClickType.MAIN_CLICK);

            }
        });
    }

    private void getAllName() {
        list.add("Acer");
        list.add("Admiral");
        list.add("Aiwa");
        list.add("Akai");
        list.add("Apex");
        list.add("Audiovox");
        list.add("Bose");
        list.add("Bush");
        list.add("Changhong");
        list.add("Coby");
        list.add("Colby");
        list.add("Condor");
        list.add("Zenith");
        list.add("Dura Brand");
        list.add("Dynex");
        list.add("Element");
        list.add("Emerson");
        list.add("Funai");
        list.add("Haier");
        list.add("Hisense");
        list.add("Hitachi");
        list.add("Hyundai");
        list.add("Insignia");
        list.add("Jensen");
        list.add("Kenwood");
        list.add("LG");
        list.add("Logik");
        list.add("magnavox");
        list.add("mascom");
        list.add("Medion");
        list.add("Micromax");
        list.add("Mitsubishi");
        list.add("Mystery");
        list.add("NEC");
        list.add("Nexus");
        list.add("Nikai");
        list.add("Noblex");
        list.add("Olevia");
        list.add("Onida");
        list.add("Orion");
        list.add("Palsonic");
        list.add("Panasonic");
        list.add("Philco");
        list.add("Philips");
        list.add("Pioneer");
        list.add("Polaroid");
        list.add("Polytron");
        list.add("Prima");
        list.add("Promac");
        list.add("Proscan");
        list.add("Proton");
        list.add("Rubin");
        list.add("Samsung");
        list.add("Samsung Smart");
        list.add("Sansui");
        list.add("Sanyo");
        list.add("Scott");
        list.add("Seiki");
        list.add("Sharp");
        list.add("Singer");
        list.add("Sinotec");
        list.add("Skyworth");
        list.add("Sony");
        list.add("Supra");
        list.add("Swisstec");
        list.add("Sylvania");
        list.add("Symphonic");
        list.add("TCL");
        list.add("Technical");
        list.add("Thomson");
        list.add("Tokai");
        list.add("Toshiba");
        list.add("TurboX");
        list.add("Upstar");
        list.add("Venturer");
        list.add("Veon");
        list.add("Videocon");
        list.add("Viore");
        list.add("Vizio");
        list.add("Voxson");
        list.add("Westinghouse");
        list.add("Multi TV");
        list.add("SFR");
        list.add("Start Times");
        list.add("Total Play");
        list.add("Trend");
    }

    private void bottomAd() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            x.nestedScrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (scrollY > 700) {
                    if (sharedPreferencesHelper.getShowAdinApp()) {
                        if (sharedPreferencesHelper.getAdMobAdShow()) {
                            x.nativeAdLayout2.setVisibility(View.VISIBLE);
                            if (isfirsttime) {
                                AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout2, AdUtils.NativeType.NATIVE_BANNER);
                                isfirsttime = false;
                            }
                        } else {
                            x.nativeAdLayout2.setVisibility(View.GONE);
                        }

                    } else {
                        x.nativeAdLayout2.setVisibility(View.GONE);
                    }

                } else {
                    x.nativeAdLayout2.setVisibility(View.GONE);
                }

            });
        }

    }

    @Override
    public void onBackPressed() {

        AdShow.getInstance(activity).ShowAd(new HandleClick() {
            @Override
            public void Show(boolean adShow) {
                All_Compny_Name_Activity.super.onBackPressed();
            }
        }, AdUtils.ClickType.BACK_CLICK);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(x.nativeAdLayout.nativeAdLayout, AdUtils.NativeType.NATIVE_BIG);
    }
}