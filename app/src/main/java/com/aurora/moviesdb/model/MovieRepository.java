package com.aurora.moviesdb.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aurora.moviesdb.servies.MoviesDataServise;
import com.aurora.moviesdb.servies.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Movie.Datum> data = new ArrayList<>();
    private MutableLiveData<List<Movie.Datum>> listMutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie.Datum>> getListMutableLiveData(int page) {
        MoviesDataServise moviesDataServise = RetrofitInstance.getServies();

        moviesDataServise.getAllMovie(page).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movies = response.body();
                data = (ArrayList<Movie.Datum>) movies.data;
                // showInRecycler();
                if (response.isSuccessful()) {

                    for (Movie.Datum movie : data) {
                        Log.i("TAG", movie.getTitle() + "");
                    }
                    listMutableLiveData.setValue(data);
                }

                // binding.swiperefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

        return listMutableLiveData;
    }


}
