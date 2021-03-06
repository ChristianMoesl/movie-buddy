package at.mrtramoga.moviebuddy.themoviedb3;

import com.android.volley.Response;

public class MovieReviewsRequest extends MovieDbRequest<MovieReviewsAnswer> {

    private static final String MOVIE_PATH = "/movie";
    private static final String REVIEWS_PATH = "/reviews";

    public MovieReviewsRequest(String apiKey, long movieId, Response.Listener<MovieReviewsAnswer> listener) {
        super(apiKey, MOVIE_PATH + '/' + Long.toString(movieId) + REVIEWS_PATH, MovieReviewsAnswer.class, listener);
    }
}
