package com.jenuvid.scrnmirroring.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jenuvid.scrnmirroring.R;


public class UnderMaintenanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_maintenance);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UnderMaintenanceActivity.this, ExitActivity.class).putExtra("Under", true));
        finish();
    }
}