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

            Picasso.with(getApplicationContext()).load(datum.getPoster()).into(binding.imgPoster);
            binding.toolbar.setTitle(datum.getTitle());
            binding.txtName.setText(datum.getTitle());
            binding.txtRate.setText(datum.getImdbRating());
            binding.txtDate.setText(datum.getYear());
            binding.txtCountry.setText(datum.getCountry());
            binding.txtGenres.setText(intent.getStringExtra("genres"));
            binding.toolbar.setTitleTextColor(Color.WHITE);
       }
    }
}