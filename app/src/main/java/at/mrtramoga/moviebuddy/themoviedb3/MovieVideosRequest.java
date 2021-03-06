package at.mrtramoga.moviebuddy.themoviedb3;

import com.android.volley.Response;

public class MovieVideosRequest extends MovieDbRequest<MovieVideosAnswer> {

    private static final String MOVIE_PATH = "/movie";
    private static final String VIDEOS_PATH = "/videos";

    public MovieVideosRequest(String apiKey, long movieId, Response.Listener<MovieVideosAnswer> listener) {
        super(apiKey, MOVIE_PATH + '/' + Long.toString(movieId) + VIDEOS_PATH, MovieVideosAnswer.class, listener);
    }
}
