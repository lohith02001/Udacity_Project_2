package com.example.lram.moviesapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lram.moviesapplication.model.Result;
import com.example.lram.moviesapplication.network.MovieAppUtils;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView mBackgroundImage;
    private ImageView mPosterImage;
    private TextView mOriginalTitle;
    private TextView mReleaseDate;
    private TextView mAverageRating;
    private TextView mMovieOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mBackgroundImage = findViewById(R.id.backgroundPoster);
        mPosterImage = findViewById(R.id.posterThumbnail);
        mOriginalTitle = findViewById(R.id.originalTitle);
        mReleaseDate = findViewById(R.id.date);
        mAverageRating = findViewById(R.id.rating);
        mMovieOverview = findViewById(R.id.movieOverview);
        if (getIntent().hasExtra(Result.MOVIE_INTENT_EXTRA)) {
            Result result = (Result) getIntent().getSerializableExtra(Result.MOVIE_INTENT_EXTRA);
            loadMovieData(result);
        }
    }

    public void loadMovieData(Result result) {
        String backgroundImage = MovieAppUtils.createImagePath(result.getBackdropPath(), MovieAppUtils.POSTER_BACKGROUND);
        String portraitImage = MovieAppUtils.createImagePath(result.getPosterPath(), MovieAppUtils.POSTER_SIZE);
        Picasso.get().load(backgroundImage).placeholder(R.drawable.poster_placeholder).fit().into(mBackgroundImage);
        Picasso.get().load(portraitImage).fit().into(mPosterImage);
        mOriginalTitle.setText(result.getOriginalTitle());
        mReleaseDate.setText(result.getReleaseDate());
        mAverageRating.setText(result.getVoteAverage().toString() + "/10");
        mMovieOverview.setText(result.getOverview());
    }
}
