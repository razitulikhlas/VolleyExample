package com.example.volleyexample.network;

import com.example.volleyexample.model.ResponsePopuler;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("movie/popular?api_key=15bd1c26c3ee8ac797dc6f96348db0a6&language=en-US&page=1")
    Call<ResponsePopuler> getMoviePopuler();
}
