package com.aurora.moviesdb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.aurora.moviesdb.R;
import com.aurora.moviesdb.model.Movie;

public class MovieActivity extends AppCompatActivity {

    Movie.Datum datum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

      //  Intent intent=getIntent();
       // if(getIntent().hasExtra("movie")){
             datum= (Movie.Datum) getIntent().getSerializableExtra("movie");
            Toast.makeText(this, datum.getTitle()+"", Toast.LENGTH_SHORT).show();
       // }
    }
}