package com.example.lram.moviesapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.lram.moviesapplication.model.MovieDbReturnObject;
import com.example.lram.moviesapplication.model.Result;
import com.example.lram.moviesapplication.network.MovieAppUtils;
import com.example.lram.moviesapplication.network.MovieDbService;
import java.io.Serializable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mMovieRecyclerView;
    private MovieDbService retrofitService = MovieAppUtils.createService();
    private MovieAdapter mMovieAdapter;
    private TextView mErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMovieRecyclerView = findViewById(R.id.movie_list);
        mErrorMessage = findViewById(R.id.error_message);
        int numberOfColumns = MovieAppUtils.calculateNoOfColumns(getApplicationContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numberOfColumns);
        mMovieRecyclerView.setLayoutManager(gridLayoutManager);
        mMovieRecyclerView.setHasFixedSize(true);
        mMovieAdapter = new MovieAdapter(this);
        mMovieRecyclerView.setAdapter(mMovieAdapter);
        queryForPopular();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sortPopular) {
            if (!item.isChecked()) {
                item.setChecked(true);
            }
            queryForPopular();
            return true;
        } else if (id == R.id.sortTopRated) {
            if (!item.isChecked()) {
                item.setChecked(true);
            }
            queryForTopRated();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void queryForPopular() {

        retrofitService.queryPopular(MovieAppUtils.API_KEY).enqueue(new Callback<MovieDbReturnObject>() {
            @Override
            public void onResponse(Call<MovieDbReturnObject> call, Response<MovieDbReturnObject> response) {
                mErrorMessage.setVisibility(View.INVISIBLE);
                mMovieAdapter.updateLists(response.body().getResults());
                mMovieRecyclerView.smoothScrollToPosition(0);
            }

            @Override
            public void onFailure(Call<MovieDbReturnObject> call, Throwable t) {
                mErrorMessage.setVisibility(View.VISIBLE);
                mMovieAdapter.updateLists(null);
            }
        });
    }

    public void queryForTopRated() {
        retrofitService.queryTopRated(MovieAppUtils.API_KEY).enqueue(new Callback<MovieDbReturnObject>() {
            @Override
            public void onResponse(Call<MovieDbReturnObject> call, Response<MovieDbReturnObject> response) {
                mErrorMessage.setVisibility(View.INVISIBLE);
                mMovieAdapter.updateLists(response.body().getResults());
                mMovieRecyclerView.smoothScrollToPosition(0);
            }

            @Override
            public void onFailure(Call<MovieDbReturnObject> call, Throwable t) {
                mErrorMessage.setVisibility(View.VISIBLE);
                mMovieAdapter.updateLists(null);
            }
        });
    }

    @Override
    public void onMovieClick(Result result) {
        Intent movieDetailsIntent = new Intent(this, MovieDetailsActivity.class);
        movieDetailsIntent.putExtra(Result.MOVIE_INTENT_EXTRA, (Serializable) result);
        startActivity(movieDetailsIntent);
    }
}

