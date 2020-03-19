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

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<PopulerMovies> movieData;
    private Context context;

    private OnMovieItemSelectedListener onMovieItemSelectedListener;

    public MoviesAdapter(Context context) {
        this.context = context;
        movieData = new ArrayList<>();
    }

    private void add(PopulerMovies item){
        movieData.add(item);
        notifyItemInserted(movieData.size()-1);
    }

    public void addAll(List<PopulerMovies> movieData){
        for(PopulerMovies populerMovies : movieData){
            add(populerMovies);
        }
    }

    public void remove(PopulerMovies item){
        int position = movieData.indexOf(item);
        if(position > -1){
            movieData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear(){
        while (getItemCount() > 0){
            remove(getItem(0));
        }
    }

    public PopulerMovies getItem(int position){
        return  movieData.get(position);
    }

    public List<PopulerMovies> getMovieData() {
        return movieData;
    }

    public void setMovieData(List<PopulerMovies> movieData) {
        this.movieData = movieData;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        final MoviesViewHolder moviesViewHolder = new MoviesViewHolder(view);
        moviesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterpos = moviesViewHolder.getAdapterPosition();
                if(adapterpos != RecyclerView.NO_POSITION){
                    if(onMovieItemSelectedListener != null){
                        onMovieItemSelectedListener.onItemClick(moviesViewHolder.itemView,adapterpos);
                    }
                }
            }
        });
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        final PopulerMovies populer = movieData.get(position);
        Picasso.get()
                .load(Constant.IMG_URL+populer.getPosterPath())
                .into(holder.img);
    }

    public void setOnMovieItemSelectedListener(OnMovieItemSelectedListener onMovieItemSelectedListener) {
        this.onMovieItemSelectedListener = onMovieItemSelectedListener;
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_poster);
        }


    }

    public interface OnMovieItemSelectedListener{
        void onItemClick(View view, int adapterpos);
    }
}
