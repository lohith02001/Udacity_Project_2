package com.example.lram.moviesapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.lram.moviesapplication.model.MovieDbReturnObject;
import com.example.lram.moviesapplication.network.MovieAppUtils;
import com.example.lram.moviesapplication.network.MovieDbService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mMovieRecyclerView;
    private MovieDbService retrofitService = MovieAppUtils.createService();
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMovieRecyclerView = findViewById(R.id.movie_list);
        int numberOfColumns = 2;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numberOfColumns);
        mMovieRecyclerView.setHasFixedSize(true);
        mMovieRecyclerView.setLayoutManager(gridLayoutManager);
        mMovieAdapter = new MovieAdapter(null);
        mMovieRecyclerView.setAdapter(mMovieAdapter);

        retrofitService.queryPopular(MovieAppUtils.API_KEY).enqueue(new Callback<MovieDbReturnObject>() {
            @Override
            public void onResponse(Call<MovieDbReturnObject> call, Response<MovieDbReturnObject> response) {
                mMovieAdapter.updateLists(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieDbReturnObject> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error in API call",Toast.LENGTH_LONG).show();
            }
        });
    }
}
