package com.example.volleyexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.volleyexample.adapter.MoviesAdapter;
import com.example.volleyexample.adapter.PopulerAdapter;
import com.example.volleyexample.model.PopulerMovies;
import com.example.volleyexample.model.ResponsePopuler;
import com.example.volleyexample.network.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularActivity extends AppCompatActivity {
    private RecyclerView rcvPopuler;
    private PopulerAdapter populerAdapter;
    private MoviesAdapter moviesAdapter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        rcvPopuler = findViewById(R.id.rv_populer);
        moviesAdapter = new MoviesAdapter(this);
        rcvPopuler.setHasFixedSize(true);
        rcvPopuler.setLayoutManager(new GridLayoutManager(this,2));
        loadData();
    }

    private void loadData() {
       Client.getInstance().getMoviePopuler().enqueue(new Callback<ResponsePopuler>() {
           @Override
           public void onResponse(Call<ResponsePopuler> call, Response<ResponsePopuler> response) {
               if(response.isSuccessful()){
                   moviesAdapter.addAll(response.body().getResults());
//                   List<PopulerMovies> populerMovies = response.body().getResults();
//                   populerAdapter = new PopulerAdapter(PopularActivity.this,populerMovies);
                   rcvPopuler.setAdapter(moviesAdapter);

               }



           }

           @Override
           public void onFailure(Call<ResponsePopuler> call, Throwable t) {
               Toast.makeText(PopularActivity.this, "eror "+t.getMessage(), Toast.LENGTH_SHORT).show();

           }
       });
    }
}
