package com.jenuvid.scrnmirroring.Utils;

import android.view.View;

import com.jenuvid.scrnmirroring.R;
import com.jenuvid.scrnmirroring.VideoPlayer.VideoProjectorActivity;


public class VideoProjector implements Runnable {

    public final VideoProjectorActivity projectorActivity;


    public VideoProjector(VideoProjectorActivity projectorActivity) {
        this.projectorActivity = projectorActivity;
    }

    @Override
    public void run() {
        this.projectorActivity.findViewById(R.id.showProgress).setVisibility(View.GONE);
        this.projectorActivity.E = false;
    }

}
