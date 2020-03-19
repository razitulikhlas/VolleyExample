package com.example.volleyexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volleyexample.R;
import com.example.volleyexample.model.PopulerMovies;
import com.example.volleyexample.util.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopulerAdapter extends RecyclerView.Adapter<PopulerAdapter.PopulerHolder> {
    private Context context;
    private List<PopulerMovies> populerMovies;

    public PopulerAdapter(Context context, List<PopulerMovies> populerMovies) {
        this.context = context;
        this.populerMovies = populerMovies;
    }

    @NonNull
    @Override
    public PopulerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new PopulerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopulerHolder holder, int position) {
        final PopulerMovies populer = populerMovies.get(position);

        Picasso.get()
                .load(Constant.IMG_URL+populer.getPosterPath())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return populerMovies.size();
    }

    public class PopulerHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public PopulerHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_poster);
        }
    }
}
