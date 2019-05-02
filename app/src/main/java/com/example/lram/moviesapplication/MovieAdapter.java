package com.example.lram.moviesapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.lram.moviesapplication.model.Result;
import com.example.lram.moviesapplication.network.MovieAppUtils;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final MovieAdapterOnClickHandler mMovieAdapterClickHandler;
    private List<Result> mMoviesList;

    public MovieAdapter(MovieAdapterOnClickHandler mMovieAdapterClickHandler) {
        this.mMovieAdapterClickHandler = mMovieAdapterClickHandler;
    }

    public interface MovieAdapterOnClickHandler {
        void onMovieClick(Result result);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MovieViewHolder(inflater.inflate(R.layout.movie_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        Result result = mMoviesList.get(position);
        String posterPath = MovieAppUtils.createImagePath(result.getPosterPath(), MovieAppUtils.POSTER_SIZE);
        Picasso.get().load(posterPath).fit().into(movieViewHolder.moviePoster);
    }

    @Override
    public int getItemCount() {

        if (mMoviesList == null)
            return 0;
        return mMoviesList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView moviePoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Result result = mMoviesList.get(adapterPosition);
            mMovieAdapterClickHandler.onMovieClick(result);
        }
    }

    public void updateLists(List<Result> updatedList) {
        mMoviesList = updatedList;
        notifyDataSetChanged();
    }

}
