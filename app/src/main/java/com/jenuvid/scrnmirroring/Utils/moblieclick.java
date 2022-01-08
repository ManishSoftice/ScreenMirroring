package com.jenuvid.scrnmirroring.Utils;

import android.view.View;
import android.widget.LinearLayout;

import com.jenuvid.scrnmirroring.R;
import com.jenuvid.scrnmirroring.VideoPlayer.VideoProjectorActivity;


public class moblieclick implements View.OnClickListener {

    public final /* synthetic */ Runnable runnable;

    public final /* synthetic */ VideoProjectorActivity videoProjectorActivity;

    public moblieclick(VideoProjectorActivity videoProjectorActivity, Runnable runnable) {
        this.videoProjectorActivity = videoProjectorActivity;
        this.runnable = runnable;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.videoProjectorActivity.D.removeCallbacks(this.runnable);

        boolean z7 = videoProjectorActivity.E;
        LinearLayout linearLayout = videoProjectorActivity.findViewById(R.id.showProgress);
        if (z7) {
            linearLayout.setVisibility(View.GONE);
            this.videoProjectorActivity.E = false;
            return;
        }
        linearLayout.setVisibility(View.VISIBLE);
        this.videoProjectorActivity.D.postDelayed(this.runnable, 5000);
        this.videoProjectorActivity.E = true;
    }
}