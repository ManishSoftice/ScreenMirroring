package com.jenuvid.scrnmirroring.Adapter;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.R;
import com.jenuvid.scrnmirroring.Utils.Constat;
import com.jenuvid.scrnmirroring.VideoPlayer.ProjectorThemeActivity;
import com.jenuvid.scrnmirroring.model.VideoModel;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class AllVideoAdapter extends RecyclerView.Adapter<AllVideoAdapter.Myclass> {

    Activity activity;
    ArrayList<VideoModel> videoList;

    public AllVideoAdapter(Activity activity, ArrayList<VideoModel> videoList) {
        this.activity = activity;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.video_item, parent, false);
        return new Myclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {

        Log.d("fdd", "THum" + videoList.get(position).getStr_path());
        Glide.with(activity).load(videoList.get(position).getStr_path()).placeholder(R.drawable.ic_logo).into(holder.videothum);
        holder.videotitle.setText(videoList.get(position).getTitle());
        holder.videosize.setText(humanReadableByteCountBin(videoList.get(position).getDuration()));

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {

        ImageView videothum;
        TextView videotitle, videosize;

        public Myclass(@NonNull View itemView) {
            super(itemView);

            videothum = itemView.findViewById(R.id.videothum);
            videotitle = itemView.findViewById(R.id.videotitle);
            videosize = itemView.findViewById(R.id.videosize);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AdShow.getInstance(activity).ShowAd(new HandleClick() {
                        @Override
                        public void Show(boolean adShow) {
                            Constat.videopath = videoList.get(getAdapterPosition()).getStr_path();
                            activity.startActivity(new Intent(activity, ProjectorThemeActivity.class));

                        }
                    }, AdUtils.ClickType.MAIN_CLICK);
                }
            });
        }
    }

    public static String humanReadableByteCountBin(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %cB", value / 1024.0, ci.current());
    }
}
