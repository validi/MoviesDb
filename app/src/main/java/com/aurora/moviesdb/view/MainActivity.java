package com.aurora.moviesdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aurora.moviesdb.MovieAdapter;
import com.aurora.moviesdb.R;
import com.aurora.moviesdb.databinding.ActivityMainBinding;
import com.aurora.moviesdb.model.Movie;
import com.aurora.moviesdb.servies.MoviesDataServise;
import com.aurora.moviesdb.servies.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int page = 2;
    Movie movies = new Movie();
    ArrayList<Movie.Datum> data = new ArrayList<>();
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getListMovies();
        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                getListMovies();

            }
        });

    }

    private void getListMovies() {
        MoviesDataServise moviesDataServise = RetrofitInstance.getServies();

        moviesDataServise.getAllMovie(page).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movies = response.body();
                data = (ArrayList<Movie.Datum>) movies.data;
                showInRecycler();
                if (response.isSuccessful()) {

                    for (Movie.Datum movie : data) {
                        Log.i("TAG", movie.getTitle() + "");
                    }
                }
                binding.swiperefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                binding.swiperefresh.setRefreshing(false);
            }
        });
    }

    private void showInRecycler() {
recyclerView=binding.recycler;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        movieAdapter=new MovieAdapter(getApplicationContext());

        recyclerView.setAdapter(movieAdapter);
        movieAdapter.setData(data);
        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie.Datum post) {
                Intent intent = new Intent(getApplicationContext(), MovieActivity.class);
                intent.putExtra("movie",post);
                startActivity(intent);
               // Toast.makeText(getApplicationContext(), post.getTitle()+"", Toast.LENGTH_SHORT).show();

            }
        });
    }
}