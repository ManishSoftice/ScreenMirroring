package com.example.screenmirroring.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.screenmirroring.FolderModel;
import com.example.screenmirroring.R;
import com.example.screenmirroring.VideoPlayer.FolderVideoActivity;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.Myclass> {

    Activity activity;
    ArrayList<FolderModel> folderList;

    public FolderAdapter(Activity activity, ArrayList<FolderModel> folderList) {
        this.activity = activity;
        this.folderList = folderList;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.folder_item, parent, false);
        return new Myclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {


        holder.foldervideo.setText(folderList.get(position).getStr_folder());
        holder.foldername.setText(folderList.get(position).getAl_videoPath().size() + "");
        Glide.with(activity).load("file://" + folderList.get(position).getAl_videoPath().get(0))
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.folder_image);



    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {

        TextView foldername, foldervideo;
        ImageView folder_image;

        public Myclass(@NonNull View itemView) {
            super(itemView);

            foldername = itemView.findViewById(R.id.foldername);
            foldervideo = itemView.findViewById(R.id.foldervideo);
            folder_image = itemView.findViewById(R.id.folder_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FolderVideoActivity.Video_Folder_Data = new ArrayList<>(folderList.get(getAdapterPosition()).getAl_videoPath());
                    Intent intent = new Intent(activity, FolderVideoActivity.class);
                    intent.putExtra("name", folderList.get(getAdapterPosition()).getStr_folder());
                    activity.startActivity(intent);

                }
            });
        }
    }
}
