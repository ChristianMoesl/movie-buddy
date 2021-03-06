package at.mrtramoga.moviebuddy.themoviedb3;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;

/**
 * A Request to fetch a json object from the movie db API.
 */
public class JsonRequest<T> extends com.android.volley.toolbox.JsonRequest {

    private static final String LOG_TAG = JsonRequest.class.getSimpleName();

    private final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    private final Class<T> clazz;

    public static JsonRequestBuilder create() {
        return new JsonRequestBuilder();
    }

    protected JsonRequest(int method, String url, String requestBody, Response.Listener listener, Response.ErrorListener errorListener, Class<T> clazz) {
        super(method, url, requestBody, listener, errorListener);
        Log.v(LOG_TAG, "Request to: " + url);
        this.clazz = clazz;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        Response<T> result;

        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            //Allow null
            if ((jsonString == null || jsonString.length() == 0) && response.statusCode >= 200 && response.statusCode <= 299) {
                return Response.success(null, HttpHeaderParser.parseCacheHeaders(response));
            }

            T parsedObject = gson.fromJson(jsonString, clazz);

            result = Response.success(parsedObject, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            result = Response.error(new ParseError(e));
        }

        return result;
    }

    /**
     * Dummy implementation for the Comparable interface, because it isn't needed-
     */
    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
