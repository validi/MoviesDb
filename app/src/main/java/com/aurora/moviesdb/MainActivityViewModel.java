package com.aurora.moviesdb;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedList;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.aurora.moviesdb.model.Movie;
import com.aurora.moviesdb.model.MovieDataSource;
import com.aurora.moviesdb.model.MovieDataSourceFactory;
import com.aurora.moviesdb.model.MovieRepository;
import com.aurora.moviesdb.servies.MoviesDataServise;
import com.aurora.moviesdb.servies.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    LiveData<MovieDataSource> movieDataSourceLiveData;
    Executor executor;
    LiveData<PagedList<Movie.Datum>>pagedListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);

        MoviesDataServise moviesDataServise = RetrofitInstance.getServies();
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(application, moviesDataServise);
        movieDataSourceLiveData = movieDataSourceFactory.getSourceMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor= Executors.newFixedThreadPool(5);

        pagedListLiveData=(new LivePagedListBuilder<Integer,Movie.Datum>(movieDataSourceFactory,config))
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<PagedList<Movie.Datum>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public LiveData<List<Movie.Datum>> getListMovie(int page) {
        return movieRepository.getListMutableLiveData(page);
    }
}
