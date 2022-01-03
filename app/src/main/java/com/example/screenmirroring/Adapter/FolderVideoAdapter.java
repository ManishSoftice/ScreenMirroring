package com.example.screenmirroring.Adapter;

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
import com.example.screenmirroring.R;
import com.example.screenmirroring.connect_TV.PlayVideoActivity;

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
                .placeholder(R.mipmap.ic_launcher)
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
                    activity.startActivity(new Intent(activity, PlayVideoActivity.class).putExtra("url", list.get(getAdapterPosition())));
                }
            });
        }
    }
}
