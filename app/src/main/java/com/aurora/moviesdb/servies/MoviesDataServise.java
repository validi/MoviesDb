package com.aurora.moviesdb.servies;

import android.graphics.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesDataServise {
    @GET("/api/v1/movies?page={page}")
    Call<List<Movie>> getAllMovie(int Page);
}
