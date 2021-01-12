package com.aurora.moviesdb.servies;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
   public static Retrofit retrofit;
    public static String BaseURL="https://moviesapi.ir";

    public static MoviesDataServise getServies(){
        if(retrofit==null){
            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MoviesDataServise.class);
    }
}
