package com.jenuvid.scrnmirroring.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.softice.ad.AdShow;
import com.example.softice.ad.HandleClick.HandleClick;
import com.example.softice.utils.AdUtils;
import com.jenuvid.scrnmirroring.Gallery.AlbumPhotosActivity;
import com.jenuvid.scrnmirroring.R;
import com.jenuvid.scrnmirroring.model.Model_images;

import java.util.ArrayList;

public class AllPhotosFolderAdapter extends RecyclerView.Adapter<AllPhotosFolderAdapter.Myclass> {

    Activity activity;
    ArrayList<Model_images> model_images = new ArrayList<>();

    public AllPhotosFolderAdapter(Activity activity) {
        this.activity = activity;

    }


    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.folder_photo_item, parent, false);
        return new Myclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {

        holder.tvFolder.setText(model_images.get(position).getStr_folder());
        holder.tvFolder2.setText(model_images.get(position).getAl_imagepath().size() + " Photos");

        final boolean childCount = holder.ivImage.getBackground() == null;
        if (childCount) {
            Glide.with(activity).load("file://" + model_images.get(position).getAl_imagepath().get(0))
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(holder.ivImage);
        }
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdShow.getInstance(activity).ShowAd(new HandleClick() {
                    @Override
                    public void Show(boolean adShow) {
                        AlbumPhotosActivity.FolderData = new ArrayList<>(model_images.get(position).getAl_imagepath());
                        Intent intent = new Intent(activity, AlbumPhotosActivity.class);
                        intent.putExtra("name", model_images.get(position).getStr_folder());
                        activity.startActivity(intent);
                    }
                }, AdUtils.ClickType.MAIN_CLICK);

            }
        });
    }


    @Override
    public int getItemCount() {
        return model_images.size();
    }

    public void setData(ArrayList<Model_images> all_images) {
        this.model_images=all_images;
        notifyDataSetChanged();
    }

    public class Myclass extends RecyclerView.ViewHolder {

        ImageView ivImage;
        CardView card;
        TextView tvFolder, tvFolder2;

        public Myclass(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvFolder = itemView.findViewById(R.id.tvFolder);
            tvFolder2 = itemView.findViewById(R.id.tvFolder2);
            card = itemView.findViewById(R.id.card);

        }
    }
}
