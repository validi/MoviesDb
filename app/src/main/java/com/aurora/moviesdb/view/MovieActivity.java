package com.aurora.moviesdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.aurora.moviesdb.R;
import com.aurora.moviesdb.databinding.ActivityMainBinding;
import com.aurora.moviesdb.databinding.ActivityMovieBinding;
import com.aurora.moviesdb.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    Movie.Datum datum=new Movie.Datum();
    ActivityMovieBinding  binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_movie);


        Intent intent=getIntent();
        if(intent.hasExtra("title")){
             datum.setTitle(intent.getStringExtra("title")); ;
             datum.setPoster(intent.getStringExtra("poster"));
             datum.setImdbRating(intent.getStringExtra("rate"));
             datum.setYear(intent.getStringExtra("year"));
             datum.setCountry(intent.getStringExtra("country"));

             binding.setDatum(datum);

            binding.txtGenres.setText(intent.getStringExtra("genres"));

       }
    }
}