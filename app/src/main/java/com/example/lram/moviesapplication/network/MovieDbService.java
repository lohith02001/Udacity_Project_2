package com.example.lram.moviesapplication.network;

import com.example.lram.moviesapplication.model.MovieDbReturnObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbService {
    @GET(MovieAppUtils.POPULAR_PATH)
    Call<MovieDbReturnObject> queryPopular(@Query(MovieAppUtils.API_KEY_PARAM) String apiKey);

    @GET(MovieAppUtils.TOP_RATED_PATH)
    Call<MovieDbReturnObject> queryTopRated(@Query(MovieAppUtils.API_KEY_PARAM) String apiKey);
}
