package com.aurora.moviesdb.servies;


import com.aurora.moviesdb.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesDataServise {

    @GET("/api/v1/movies")
    Call<Movie> getAllMovie(@Query("page") Integer Page);
}
