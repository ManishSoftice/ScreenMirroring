package com.jenuvid.scrnmirroring.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.R;
import com.jenuvid.scrnmirroring.VideoPlayer.VideoProjectorActivity;

import java.util.ArrayList;

public class FolderVideoAdapter extends RecyclerView.Adapter<FolderVideoAdapter.Myclass> {

    Activity activity;
    ArrayList<String> list;

    public FolderVideoAdapter(Activity activity, ArrayList<String> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.all_video_item,parent,false);
        return new Myclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {

        Glide.with(activity).load("file://" + list.get(position))
                .placeholder(R.drawable.ic_logo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {

        ImageView image;

        public Myclass(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AdShow.getInstance(activity).ShowAd(new HandleClick() {
                        @Override
                        public void Show(boolean adShow) {
                            activity.startActivity(new Intent(activity, VideoProjectorActivity.class).putExtra("url", list.get(getAdapterPosition())));

                        }
                    }, AdUtils.ClickType.MAIN_CLICK);

                }
            });
        }
    }
}
