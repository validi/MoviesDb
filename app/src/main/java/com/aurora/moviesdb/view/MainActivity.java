package com.aurora.moviesdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.content.Intent;
import android.os.Bundle;

import com.aurora.moviesdb.adpter.MovieAdapter;
import com.aurora.moviesdb.R;
import com.aurora.moviesdb.databinding.ActivityMainBinding;
import com.aurora.moviesdb.model.Movie;
import com.aurora.moviesdb.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int page = 2;
    Movie movies = new Movie();
    ArrayList<Movie.Datum> data = new ArrayList<>();
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);


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

        mainActivityViewModel.getListMovie(page).observe(this, new Observer<List<Movie.Datum>>() {
            @Override
            public void onChanged(List<Movie.Datum> data1) {
              data = (ArrayList<Movie.Datum>)data1;
                showInRecycler();
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
                intent.putExtra("poster",post.getPoster());
                intent.putExtra("title",post.getTitle());
                intent.putExtra("rate",post.getImdbRating());
                intent.putExtra("year",post.getYear());
                intent.putExtra("country",post.getCountry());

                String genres="";
                for (int i = 0; i <post.getGenres().size() ; i++) {
                    genres+=post.getGenres().get(i)+" - ";
                }

                intent.putExtra("genres", genres.substring(0,genres.length()-3));
                startActivity(intent);

            }
        });


    }
}