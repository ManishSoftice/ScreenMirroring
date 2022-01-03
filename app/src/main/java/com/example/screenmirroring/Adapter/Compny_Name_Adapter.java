package com.example.screenmirroring.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screenmirroring.R;
import com.example.screenmirroring.connect_TV.Select_Mode_Activity;

import java.util.List;

public class Compny_Name_Adapter extends RecyclerView.Adapter<Compny_Name_Adapter.Myclass> {

    Activity activity;
    List<String> list;

    public Compny_Name_Adapter(Activity activity, List<String> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.name_item,parent,false);
        return new Myclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {
        holder.name.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myclass extends RecyclerView.ViewHolder {

        TextView name;

        public Myclass(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.startActivity(new Intent(activity, Select_Mode_Activity.class));
                }
            });
        }
    }
}
