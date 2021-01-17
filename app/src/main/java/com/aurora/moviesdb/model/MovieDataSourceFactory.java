package com.aurora.moviesdb.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.aurora.moviesdb.servies.MoviesDataServise;

import org.jetbrains.annotations.NotNull;

public class MovieDataSourceFactory extends DataSource.Factory {

    MovieDataSource movieDataSource;
    Application application;
    MoviesDataServise moviesDataServise;
    MutableLiveData<MovieDataSource> sourceMutableLiveData;

    public MovieDataSourceFactory(Application application, MoviesDataServise moviesDataServise) {
        this.application = application;
        this.moviesDataServise = moviesDataServise;
        sourceMutableLiveData=new MutableLiveData<>();
    }

    @NotNull
    @Override
    public DataSource create() {
        movieDataSource=new MovieDataSource(moviesDataServise,application);
        sourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getSourceMutableLiveData() {
        return sourceMutableLiveData;
    }

    public void setSourceMutableLiveData(MutableLiveData<MovieDataSource> sourceMutableLiveData) {
        this.sourceMutableLiveData = sourceMutableLiveData;
    }
}
