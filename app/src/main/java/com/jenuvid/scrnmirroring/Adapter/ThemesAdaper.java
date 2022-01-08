package com.jenuvid.scrnmirroring.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jenuvid.scrnmirroring.R;
import com.jenuvid.scrnmirroring.Utils.Constat;


public class ThemesAdaper extends RecyclerView.Adapter<ThemesAdaper.MyViewHolder> {
    private final Activity activity;
    ThemesClickListener themesClickListener;

    public ThemesAdaper(Activity activity, ThemesClickListener themesClickListener) {
        this.activity = activity;
        this.themesClickListener = themesClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(
                parent.getContext()
        ).inflate(R.layout.themes_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ivtheme.setImageResource(Constat.GetThemes().get(position).themelist);
        holder.ivtheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themesClickListener.ThemesClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Constat.GetThemes().size();
    }

    public interface ThemesClickListener {
        void ThemesClickListener(int postion);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivtheme;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivtheme = itemView.findViewById(R.id.ivtheme);
        }
    }

}
