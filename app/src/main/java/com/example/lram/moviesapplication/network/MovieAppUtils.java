package com.example.lram.moviesapplication.network;

public class MovieAppUtils {
    //TODO Please use your API key
    public static final String API_KEY = "";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    static final String POPULAR_PATH = "movie/popular";
    static final String TOP_RATED_PATH = "movie/top_rated";
    static final String API_KEY_PARAM = "api_key";
    static final String LANGUAGE_PARAM = "language";
    static final String PAGE_PARAM = "page";

    public static MovieDbService createService()
    {
        return RetrofitClient.getClient(BASE_URL).create(MovieDbService.class);
    }
}
