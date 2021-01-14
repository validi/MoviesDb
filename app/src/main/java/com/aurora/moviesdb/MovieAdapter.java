package com.aurora.moviesdb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aurora.moviesdb.databinding.ItemMovieBinding;
import com.aurora.moviesdb.model.Movie;
import com.aurora.moviesdb.view.MovieActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdpteHolder> {

    private OnItemClickListener onItemClickListener;
    ArrayList<Movie.Datum> data = new ArrayList<>();

    public MovieAdapter(Context context) {
        this.context = context;
    }

    Context context;

    @NonNull
    @Override
    public MovieAdpteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding itemMovieBinding =DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_movie,parent,false);
        return new MovieAdpteHolder(itemMovieBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdpteHolder holder, int position) {
        Movie.Datum datum=data.get(position);
        holder.itemMovieBinding.mShimmerViewContainer.startShimmerAnimation();
        holder.itemMovieBinding.txtRate.setText(datum.getImdbRating());
        holder.itemMovieBinding.txtTitle.setText(datum.getTitle());
        Picasso.with(context).load(datum.getPoster()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.itemMovieBinding.imageView.setImageBitmap(bitmap);
                holder.itemMovieBinding.mShimmerViewContainer.stopShimmerAnimation();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
               // holder.itemMovieBinding.imageView.setImageResource(R.drawable.loading);
              //  holder.itemMovieBinding.mShimmerViewContainer.stopShimmerAnimation();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
               // holder.itemMovieBinding.imageView.setImageResource(R.drawable.loading);
                holder.itemMovieBinding.mShimmerViewContainer.stopShimmerAnimation();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MovieAdpteHolder extends RecyclerView.ViewHolder {
        ItemMovieBinding itemMovieBinding;

        public MovieAdpteHolder(@NonNull ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding = itemMovieBinding;
            itemMovieBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickPosition = getAdapterPosition();
                    if (onItemClickListener != null && clickPosition != RecyclerView.NO_POSITION)
                    {
                        onItemClickListener.onItemClick(data.get(clickPosition));


                    }

                }
            });
        }
    }


    public void setData(ArrayList<Movie.Datum> data) {
        this.data = data;

    }

    public interface OnItemClickListener {
        void onItemClick(Movie.Datum post);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
