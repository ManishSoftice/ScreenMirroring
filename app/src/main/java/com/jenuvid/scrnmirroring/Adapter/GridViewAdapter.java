package com.jenuvid.scrnmirroring.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.Gallery.FullScreenImageActivity;
import com.jenuvid.scrnmirroring.R;

import java.util.ArrayList;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.Myclass> {

    Activity activity;
    ArrayList<String> al_menu;

    public GridViewAdapter(Activity activity, ArrayList<String> al_menu) {
        this.al_menu = al_menu;
        this.activity = activity;

    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.photo_item, parent, false);
        return new Myclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        Glide.with(activity).load("file://" + al_menu.get(position))
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.iv_image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AdShow.getInstance(activity).ShowAd(new HandleClick() {
                    @Override
                    public void Show(boolean adShow) {
                        FullScreenImageActivity.FolderData = al_menu;
                        activity.startActivity(new Intent(activity, FullScreenImageActivity.class)
                                .putExtra("pos", position));
                    }
                }, AdUtils.ClickType.MAIN_CLICK);
            }
        });
    }

    @Override
    public int getItemCount() {
        return al_menu.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {

        ImageView iv_image;
        CardView cardView;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            cardView = itemView.findViewById(R.id.card);


        }
    }
}
