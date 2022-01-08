package com.jenuvid.scrnmirroring.Activity;

import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jenuvid.scrnmirroring.databinding.ActivityPrivacyPolicyBinding;


public class PrivacyPolicyActivity extends AppCompatActivity {
    Activity activity;
    private ActivityPrivacyPolicyBinding x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = ActivityPrivacyPolicyBinding.inflate(getLayoutInflater());
        View view = x.getRoot();
        setContentView(view);
        activity = this;
        todo();
        setData();
    }

    private void todo() {
        x.layoutToolbar.imageViewBack.setOnClickListener(view -> onBackPressed());
        x.layoutToolbar.textViewTitle.setText("Privacy Policy");
    }

    private void setData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            x.textPrivacyPolicy.setText(Html.fromHtml(sharedPreferencesHelper.getPrivacyPolicy(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            x.textPrivacyPolicy.setText(Html.fromHtml(sharedPreferencesHelper.getPrivacyPolicy()));
        }


    }
}