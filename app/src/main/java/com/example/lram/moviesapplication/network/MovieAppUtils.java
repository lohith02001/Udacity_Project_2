package com.example.lram.moviesapplication.network;

import android.content.Context;
import android.util.DisplayMetrics;

public class MovieAppUtils {
    //TODO: Please add your API key here
    public static final String API_KEY = "YOUR API KEY GOES HERE";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    static final String POPULAR_PATH = "movie/popular";
    static final String TOP_RATED_PATH = "movie/top_rated";
    static final String API_KEY_PARAM = "api_key";
    static final String LANGUAGE_PARAM = "language";
    static final String PAGE_PARAM = "page";
    public static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String POSTER_SIZE = "w185";
    public static final String POSTER_BACKGROUND = "w500";

    public static MovieDbService createService() {
        return RetrofitClient.getClient(BASE_URL).create(MovieDbService.class);
    }

    public static String createImagePath(String posterPath, String imageType) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(MovieAppUtils.POSTER_BASE_URL);
        if(imageType.equals(MovieAppUtils.POSTER_SIZE))
        {
            stringBuffer.append(MovieAppUtils.POSTER_SIZE);
        }
        else if(imageType.equals(MovieAppUtils.POSTER_BACKGROUND))
        {
            stringBuffer.append(MovieAppUtils.POSTER_BACKGROUND);
        }
        stringBuffer.append(posterPath);
        return stringBuffer.toString();
    }

    //Used for getting number of coulums for the grid view
    //https://stackoverflow.com/questions/33575731/gridlayoutmanager-how-to-auto-fit-columns
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / 200);
        return noOfColumns;
    }
}
