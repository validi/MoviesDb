package com.aurora.moviesdb;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aurora.moviesdb.model.Movie;
import com.aurora.moviesdb.model.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository=new MovieRepository(application);
    }

    public LiveData<List<Movie.Datum>> getListMovie(int page){
        return movieRepository.getListMutableLiveData(page);
    }
}
