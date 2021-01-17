package com.aurora.moviesdb.model;

import android.app.Application;
import android.util.Log;

import androidx.paging.PageKeyedDataSource;

import com.aurora.moviesdb.servies.MoviesDataServise;
import com.aurora.moviesdb.servies.RetrofitInstance;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer,Movie.Datum> {

    MoviesDataServise movieDataSource;
    Application  application;

    public MovieDataSource(MoviesDataServise movieDataSource, Application application) {
        this.movieDataSource = movieDataSource;
        this.application = application;
    }

    @Override
    public void loadAfter(@NotNull LoadParams<Integer> loadParams, @NotNull final LoadCallback<Integer, Movie.Datum> loadCallback) {
        movieDataSource= RetrofitInstance.getServies();
        movieDataSource.getAllMovie(loadParams.key).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movies = response.body();
                ArrayList<Movie.Datum> data = (ArrayList<Movie.Datum>) movies.data;
                // showInRecycler();
                if (response.isSuccessful()&& movies!=null && data!=null) {

                    loadCallback.onResult(data,loadParams.key+1);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NotNull LoadParams<Integer> loadParams, @NotNull LoadCallback<Integer, Movie.Datum> loadCallback) {

    }

    @Override
    public void loadInitial(@NotNull LoadInitialParams<Integer> loadInitialParams, @NotNull LoadInitialCallback<Integer, Movie.Datum> loadInitialCallback) {


        movieDataSource= RetrofitInstance.getServies();
        movieDataSource.getAllMovie(1).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movies = response.body();
                ArrayList<Movie.Datum> data = (ArrayList<Movie.Datum>) movies.data;
                // showInRecycler();
                if (response.isSuccessful()&& movies!=null && data!=null) {

                   loadInitialCallback.onResult(data,null,2);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

    }
}
