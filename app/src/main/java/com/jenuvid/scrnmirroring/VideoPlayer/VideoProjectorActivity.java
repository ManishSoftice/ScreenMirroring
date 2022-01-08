package com.jenuvid.scrnmirroring.VideoPlayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.R;
import com.jenuvid.scrnmirroring.Utils.Constat;
import com.jenuvid.scrnmirroring.Utils.VideoProjector;
import com.jenuvid.scrnmirroring.Utils.moblieclick;

public class VideoProjectorActivity extends AppCompatActivity {

    public ImageView ivLightB;

    public ImageView ivScreen;

    public LinearLayout showProgress;

    public ImageView pause;


    public TextView current;

    public TextView total;

    public SeekBar seekbar;

    public VideoView vv_1;

    public VideoView vv_2;
    public boolean A = true;
    public int B = 0;
    public Handler C = new Handler();
    public Handler D = new Handler();
    public boolean E = true;
    Activity activity;
    ImageView ivPrevious, ivNext;
    ImageView iv_device;
    ImageView iv_back;
    LinearLayout smallLinearLayout;
    private int position;
    private int currentApiVersion;

    public String E(long j7) {
        int i7 = (int) j7;
        int i8 = i7 / 3600000;
        int i9 = (i7 / 60000) % 60000;
        int i10 = (i7 % 60000) / 1000;
        return i8 > 0 ? String.format("%02d:%02d:%02d", Integer.valueOf(i8), Integer.valueOf(i9), Integer.valueOf(i10)) : String.format("%02d:%02d", Integer.valueOf(i9), Integer.valueOf(i10));
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        currentApiVersion = Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            });
        }
        setContentView(R.layout.activity_video_projector);
        activity = this;
        position = getIntent().getIntExtra("background", 0);

        vv_1 = findViewById(R.id.vv_1);
        vv_2 = findViewById(R.id.vv_2);
        showProgress = findViewById(R.id.showProgress);
        pause = findViewById(R.id.pause);
        current = findViewById(R.id.current);
        total = findViewById(R.id.total);
        seekbar = findViewById(R.id.seekbar);
        ivScreen = findViewById(R.id.ivScreen);
        ivLightB = findViewById(R.id.ivLightB);
        iv_device = findViewById(R.id.iv_device);
        iv_back = findViewById(R.id.backImg);
        ivPrevious = findViewById(R.id.ivPrevious);
        ivNext = findViewById(R.id.ivNext);
        ivLightB.setBackgroundResource(R.drawable.movie_light);
        ivLightB.setVisibility(View.VISIBLE);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("type", 0);
        bundle2.putString("path", Constat.videopath);
        Bundle bundle3 = new Bundle();
        bundle3.putInt("type", 1);
        bundle3.putString("path", Constat.videopath);
        vv_2.setVideoPath(Constat.videopath);
        vv_2.setOnPreparedListener(new mediaplayer2());
        Handler handler = new Handler();
        handler.postDelayed(new seekbar(handler), 1000);
        pause.setOnClickListener(new click());
        seekbar.setOnSeekBarChangeListener(new seekbarchange());
        vv_1.setVideoPath(Constat.videopath);
        vv_1.setOnPreparedListener(new mediaplayer());
        VideoProjector videoProjector = new VideoProjector(this);
        C.postDelayed(videoProjector, 5000);
        findViewById(R.id.rlMobile).setOnClickListener(new moblieclick(this, videoProjector));
        ivScreen.setImageResource(Constat.GetThemes().get(position).getThemelistapply());
        iv_device.setImageResource(Constat.GetThemes().get(position).getDevice());

        ivLightB.setBackgroundResource(R.drawable.movie_light);
//        animationDrawable = new AnimationDrawable();
        iv_back.setOnClickListener(view -> onBackPressed());

        ivPrevious.setOnClickListener(view -> {

            if (position > 0) {
                position--;
                ivScreen.setImageResource(Constat.GetThemes().get(position).getThemelistapply());
                iv_device.setImageResource(Constat.GetThemes().get(position).getDevice());

                SetPreNextVisibility();

            }
        });
        ivNext.setOnClickListener(view -> {

            if (position < Constat.GetThemes().size() - 1) {
                position++;
                ivScreen.setImageResource(Constat.GetThemes().get(position).getThemelistapply());
                iv_device.setImageResource(Constat.GetThemes().get(position).getDevice());
                SetPreNextVisibility();


            }
        });
    }

    private void SetPreNextVisibility() {
        if (position <= 0) {
            ivPrevious.setVisibility(View.GONE);
            ivNext.setVisibility(View.VISIBLE);
        } else if (position >= Constat.GetThemes().size() - 1) {
            ivPrevious.setVisibility(View.VISIBLE);
            ivNext.setVisibility(View.GONE);
        } else {
            ivNext.setVisibility(View.VISIBLE);
            ivPrevious.setVisibility(View.VISIBLE);
        }
    }

    @Override // v0.p, android.app.Activity
    public void onPause() {
        super.onPause();
        B = vv_2.getCurrentPosition();
        A = vv_2.isPlaying();
    }

    @Override // v0.p, android.app.Activity
    public void onResume() {
        super.onResume();
        AdShow.getInstance(activity).ShowNativeAd(findViewById(R.id.native_ad_layout), AdUtils.NativeType.NATIVE_BANNER);
        new Handler().postDelayed(new mediaplayercontroller(), 100);
    }

    @Override // h.j, v0.p, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public class mediaplayer2 implements MediaPlayer.OnPreparedListener {
        public mediaplayer2() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            seekbar.setMax(mediaPlayer.getDuration());
            total.setText(E(mediaPlayer.getDuration()));
            vv_2.start();
            mediaPlayer.setLooping(true);
            pause.setImageResource(R.drawable.hvp3_pause);
        }
    }

    /* loaded from: classes.dex */
    public class seekbar implements Runnable {

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ Handler f18883c;

        public seekbar(Handler handler) {
            f18883c = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                current.setText(E(vv_2.getCurrentPosition()));
                seekbar.setProgress(vv_2.getCurrentPosition());
                f18883c.postDelayed(this, 1000);
            } catch (IllegalStateException e8) {
                e8.printStackTrace();
            }
        }
    }

    /* loaded from: classes.dex */
    public class click implements View.OnClickListener {
        public click() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageView imageView;
            int i7;
            if (vv_2.isPlaying()) {
                vv_2.pause();
                vv_1.pause();
                imageView = pause;
                i7 = R.drawable.hvp3_play;
            } else {
                vv_2.start();
                vv_1.start();
                imageView = pause;
                i7 = R.drawable.hvp3_pause;
            }
            imageView.setImageResource(i7);
        }
    }

    /* loaded from: classes.dex */
    public class seekbarchange implements SeekBar.OnSeekBarChangeListener {
        public seekbarchange() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i7, boolean z7) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            vv_2.seekTo(seekBar.getProgress());
            vv_1.seekTo(seekBar.getProgress());
        }
    }

    /* loaded from: classes.dex */
    public class mediaplayer implements MediaPlayer.OnPreparedListener {
        public mediaplayer() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.setVolume(0.0f, 0.0f);
            vv_1.start();
            mediaPlayer.setLooping(true);
        }
    }

    @Override
    public void onBackPressed() {
        AdShow.getInstance(activity).ShowAd(new HandleClick() {
            @Override
            public void Show(boolean adShow) {
                VideoProjectorActivity.super.onBackPressed();
            }
        }, AdUtils.ClickType.BACK_CLICK);


    }

    /* loaded from: classes.dex */
    public class mediaplayercontroller implements Runnable {

        public mediaplayercontroller() {
        }

        @Override // java.lang.Runnable
        public void run() {

            if (A) {
                vv_2.seekTo(B);
                vv_1.seekTo(B);
                vv_2.start();
                vv_1.start();
                return;
            }
            vv_2.stopPlayback();
            vv_1.stopPlayback();
            vv_2.setVideoPath(Constat.videopath);
            vv_1.setVideoPath(Constat.videopath);
            vv_2.setOnPreparedListener(new a());
            vv_1.setOnPreparedListener(new b());
        }

        /* loaded from: classes.dex */
        public class a implements MediaPlayer.OnPreparedListener {
            public a() {
            }

            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                vv_2.seekTo(B);
                vv_2.pause();
                seekbar.setMax(mediaPlayer.getDuration());
                total.setText(E(mediaPlayer.getDuration()));
            }
        }

        /* loaded from: classes.dex */
        public class b implements MediaPlayer.OnPreparedListener {
            public b() {
            }

            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {

                vv_1.seekTo(B);
                vv_1.pause();
            }
        }
    }
}