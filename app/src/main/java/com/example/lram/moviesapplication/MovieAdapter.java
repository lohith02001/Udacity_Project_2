package com.example.lram.moviesapplication;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lram.moviesapplication.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
    static final String POSTER_SIZE = "w185";

    private List<Result> mMoviesList;

    public MovieAdapter(List<Result> movieList)
    {
        this.mMoviesList = movieList;
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
        String posterPath = createPosterPath(result.getPosterPath());
        Picasso.get().load(posterPath).into(movieViewHolder.moviePoster);
        //Picasso.get().load(posterPath).resize(100,100).into(movieViewHolder.moviePoster);
        //movieViewHolder.moviePoster.setImageURI(Uri.parse(posterPath));
        //movieViewHolder.moviePoster.setText(posterPath);
    }

    @Override
    public int getItemCount() {

        if(mMoviesList == null)
            return 0;
        return mMoviesList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView moviePoster;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.movie_poster);
        }
    }

    public void updateLists(List<Result> updatedList)
    {
        mMoviesList = updatedList;
        notifyDataSetChanged();
    }

    public String createPosterPath(String posterPath)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(POSTER_BASE_URL);
        stringBuffer.append(POSTER_SIZE);
        stringBuffer.append(posterPath);
        return stringBuffer.toString();
    }

}
