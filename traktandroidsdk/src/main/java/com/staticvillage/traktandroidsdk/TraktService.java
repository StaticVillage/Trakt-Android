package com.staticvillage.traktandroidsdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.staticvillage.traktandroidsdk.model.AccessToken;
import com.staticvillage.traktandroidsdk.model.Alias;
import com.staticvillage.traktandroidsdk.model.AnticipatedMovie;
import com.staticvillage.traktandroidsdk.model.AnticipatedShow;
import com.staticvillage.traktandroidsdk.model.CalendarMovie;
import com.staticvillage.traktandroidsdk.model.CalendarShow;
import com.staticvillage.traktandroidsdk.model.CollectionShow;
import com.staticvillage.traktandroidsdk.model.Episode;
import com.staticvillage.traktandroidsdk.model.EpisodeQueryResult;
import com.staticvillage.traktandroidsdk.model.Genre;
import com.staticvillage.traktandroidsdk.model.LastActivity;
import com.staticvillage.traktandroidsdk.model.Movie;
import com.staticvillage.traktandroidsdk.model.MovieCount;
import com.staticvillage.traktandroidsdk.model.MovieQueryResult;
import com.staticvillage.traktandroidsdk.model.MovieUpdate;
import com.staticvillage.traktandroidsdk.model.Rating;
import com.staticvillage.traktandroidsdk.model.Season;
import com.staticvillage.traktandroidsdk.model.Show;
import com.staticvillage.traktandroidsdk.model.ShowCount;
import com.staticvillage.traktandroidsdk.model.ShowQueryResult;
import com.staticvillage.traktandroidsdk.model.ShowUpdate;
import com.staticvillage.traktandroidsdk.model.StandardSeason;
import com.staticvillage.traktandroidsdk.model.Translation;
import com.staticvillage.traktandroidsdk.model.TrendingMovie;
import com.staticvillage.traktandroidsdk.model.TrendingShow;
import com.staticvillage.traktandroidsdk.util.RetrofitUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

import static com.staticvillage.traktandroidsdk.util.Constants.EXTENDED;
import static com.staticvillage.traktandroidsdk.util.Constants.LIMIT;
import static com.staticvillage.traktandroidsdk.util.Constants.PAGE;
import static com.staticvillage.traktandroidsdk.util.Constants.PERIOD;
import static com.staticvillage.traktandroidsdk.util.Constants.QUERY;

/**
 * Created by joelparrish on 10/30/16.
 */

public class TraktService {
    protected static final String BASE_URL_PROD = "https://api.trakt.tv/";
    protected static final String BASE_URL_STAGING = "https://api-staging.trakt.tv/";

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private TraktApi api;
    private TraktInterceptor interceptor;
    private SimpleDateFormat dateFormat;
    private Gson gson;

    public TraktService(@NonNull String clientId) {
        this(clientId, null, null);
    }

    public TraktService(@NonNull String clientId, String clientSecret, String redirectUri) {
        this(clientId, clientSecret, redirectUri, null);
    }

    public TraktService(@NonNull String clientId, String clientSecret, String redirectUri, String authToken) {
        this(clientId, clientSecret, redirectUri, authToken, false);
    }

    public TraktService(@NonNull String clientId, String clientSecret, String redirectUri,
                        String authToken, boolean staging) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;

        interceptor = new TraktInterceptor();
        interceptor.setApiKey(clientId);
        interceptor.setAuthToken(authToken);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        gson = new Gson();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        builder.addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        String baseUrl = BASE_URL_PROD;
        if (staging) {
            baseUrl = BASE_URL_STAGING;
        }

        api = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(builder.build())
                .build()
                .create(TraktApi.class);
    }

    /**
     * Set authentication token
     *
     * @param authToken authentication token
     */
    public void setAuthToken(String authToken) {
        interceptor.setAuthToken(authToken);
    }

    /**
     * Get authentication token
     *
     * @return authentication token
     */
    public String getAuthToken() {
        return interceptor.getAuthToken();
    }

    //<editor-fold desc="Authorization">

    /**
     * Authorize the user for the app
     *
     * @param context context
     */
    public void authorize(Context context) {
        Uri.Builder builder = new Uri.Builder()
                .scheme("https")
                .authority("trakt.tv").appendPath("oauth").appendPath("authorize")
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("client_id", clientId)
                .appendQueryParameter("redirect_uri", redirectUri);

        Intent intent = new Intent(Intent.ACTION_VIEW, builder.build());
        context.startActivity(intent);
    }

    /**
     * Retrieve Oauth token using code from {@link #authorize(Context)}
     *
     * @param code authorize code
     * @return oauth access token
     */
    public Observable<AccessToken> getToken(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", redirectUri);
        params.put("grant_type", "authorization_code");

        return api.getToken(RetrofitUtil.toRequestBody(params));
    }

    /**
     * Refresh Oauth token using refresh token from {@link #getToken(String)}
     *
     * @param authToken refresh oauth token
     * @return oauth access token
     */
    public Observable<AccessToken> refreshToken(String authToken) {
        Map<String, String> params = new HashMap<>();
        params.put("refresh_token", authToken);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", redirectUri);
        params.put("grant_type", "refresh_token");

        return api.getToken(RetrofitUtil.toRequestBody(params));
    }

    /**
     * Revoke Oauth token
     *
     * @return oauth access token
     */
    public Observable<JsonObject> revokeToken() {
        return api.revokeToken(RetrofitUtil.toRequestBody(
                String.format(Locale.US, "token=%s", interceptor.getAuthToken())));
    }
    //</editor-fold>

    //<editor-fold desc="Calendar">

    /**
     * Get users shows for date range
     *
     * (OAuth Required)
     *
     * @param startDate start date
     * @param days      day range from the start date to retrieve
     * @return Shows in date range
     */
    public Observable<List<CalendarShow>> getMyShows(Date startDate,
                                                     int days,
                                                     boolean extended,
                                                     Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getMyShows(dateFormat.format(startDate), days, filters);
    }

    /**
     * Get users new shows for date range
     *
     * (OAuth Required)
     *
     * @param startDate start date
     * @param days      day range from the start date to retrieve
     * @return New shows in date range
     */
    public Observable<List<CalendarShow>> getMyNewShows(Date startDate,
                                                        int days,
                                                        boolean extended,
                                                        Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getMyNewShows(dateFormat.format(startDate), days, filters);
    }

    /**
     * Get users new shows for date range
     *
     * (OAuth Required)
     *
     * @param startDate start date
     * @param days      day range from the start date to retrieve
     * @return New season premieres in date range
     */
    public Observable<List<CalendarShow>> getMySeasonPremieres(Date startDate,
                                                               int days,
                                                               boolean extended,
                                                               Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getMySeasonPremieres(dateFormat.format(startDate), days, filters);
    }

    /**
     * Get users new movies for date range
     *
     * (OAuth Required)
     *
     * @param startDate start date
     * @param days      day range from the start date to retrieve
     * @return Movies in date range
     */
    public Observable<List<CalendarMovie>> getMyMovies(Date startDate,
                                                       int days,
                                                       boolean extended,
                                                       Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getMyMovies(dateFormat.format(startDate), days, filters);
    }

    /**
     * Get shows for date range
     *
     * @param startDate start date
     * @param days      day range from the start date to retrieve
     * @return Shows in date range
     */
    public Observable<List<CalendarShow>> getShows(Date startDate,
                                                   int days,
                                                   boolean extended,
                                                   Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getShows(dateFormat.format(startDate), days, filters);
    }

    /**
     * Get new shows for date range
     *
     * @param startDate start date
     * @param days      day range from the start date to retrieve
     * @return New shows in date range
     */
    public Observable<List<CalendarShow>> getNewShows(Date startDate,
                                                      int days,
                                                      boolean extended,
                                                      Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getNewShows(dateFormat.format(startDate), days, filters);
    }

    /**
     * Get season premieres for date range
     *
     * @param startDate start date
     * @param days      day range from the start date to retrieve
     * @return New shows in date range
     */
    public Observable<List<CalendarShow>> getSeasonPremieres(Date startDate,
                                                             int days,
                                                             boolean extended,
                                                             Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getSeasonPremieres(dateFormat.format(startDate), days, filters);
    }

    /**
     * Get movies for date range
     *
     * @param startDate start date
     * @param days      day range from the start date to retrieve
     * @return Movies in date range
     */
    public Observable<List<CalendarMovie>> getMovies(Date startDate,
                                                     int days,
                                                     boolean extended,
                                                     Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getMovies(dateFormat.format(startDate), days, filters);
    }
    //</editor-fold>

    //<editor-fold desc="Genres">

    /**
     * Get genre slugs for shows
     *
     * @return genre slugs
     */
    public Observable<List<Genre>> getShowGenres() {
        return api.getGenres("shows");
    }

    /**
     * Get genre slugs for movies
     *
     * @return genre slugs
     */
    public Observable<List<Genre>> getMovieGenres() {
        return api.getGenres("movies");
    }

    //</editor-fold>

    //<editor-fold desc="Movies">

    /**
     * All movies being watched right now.
     * Movies with the most users are returned first.
     *
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.MovieFilterBuilder}
     * @return Movies being watched right now
     */
    public Observable<List<TrendingMovie>> getTrendingMovies(int page,
                                                             int limit,
                                                             boolean extended,
                                                             Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getTrendingMovies(filters);
    }

    /**
     * The most popular movies.
     * Popularity is calculated using the rating percentage and the number of ratings.
     *
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.MovieFilterBuilder}
     * @return Popular movies
     */
    public Observable<List<Movie>> getPopularMovies(int page,
                                                    int limit,
                                                    boolean extended,
                                                    Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getPopularMovies(filters);
    }

    /**
     * The most played (a single user can watch multiple times)
     * movies in the specified time period, defaulting to weekly.
     * All stats are relative to the specific time period.
     *
     * @param period   time period
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.MovieFilterBuilder}
     * @return Most played in time period
     */
    public Observable<List<MovieCount>> getPlayedMovies(String period,
                                                        int page,
                                                        int limit,
                                                        boolean extended,
                                                        Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        filters = addPeriodInfo(period, filters);
        return api.getPlayedMovies(filters);
    }

    /**
     * The most watched (unique users) movies in the specified time period,
     * defaulting to weekly. All stats are relative to the specific time period.
     *
     * @param period   time period
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.MovieFilterBuilder}
     * @return most watched in time period
     */
    public Observable<List<MovieCount>> getWatchedMovies(String period,
                                                         int page,
                                                         int limit,
                                                         boolean extended,
                                                         Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        filters = addPeriodInfo(period, filters);
        return api.getWatchedMovies(filters);
    }

    /**
     * The most collected (unique users) movies in the specified time period,
     * defaulting to weekly. All stats are relative to the specific time period.
     *
     * @param period   time period
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.MovieFilterBuilder}
     * @return Most collected movies in time period
     */
    public Observable<List<MovieCount>> getCollectedMovies(String period,
                                                           int page,
                                                           int limit,
                                                           boolean extended,
                                                           Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        filters = addPeriodInfo(period, filters);
        return api.getCollectedMovies(filters);
    }

    /**
     * The most anticipated movies based on the number of lists a movie appears on.
     *
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.MovieFilterBuilder}
     * @return Most anticipated movies
     */
    public Observable<List<AnticipatedMovie>> getAnticipatedMovies(int page,
                                                                   int limit,
                                                                   boolean extended,
                                                                   Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getAnticipatedMovies(filters);
    }

    /**
     * All movies updated since the specified UTC date.
     * We recommended storing the date you can be efficient using this method moving forward.
     *
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @return
     */
    public Observable<List<MovieUpdate>> getMovieUpdates(int page, int limit, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        params = addPaginationInfo(page, limit, params);
        return api.getMovieUpdates(params);
    }

    /**
     * A single movie's details.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param extended extended info
     * @return movie detail
     */
    public Observable<Movie> getMovieDetails(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getMovieDetails(id, params);
    }

    /**
     * All title aliases for a movie. Includes country where name is different.
     *
     * @param id Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @return movie aliases
     */
    public Observable<List<Alias>> getMovieAliases(String id) {
        return api.getMovieAliases(id);
    }

    /**
     * All releases for a movie including country, certification, release date,
     * release type, and note. The release type can be set to unknown, premiere,
     * limited, theatrical, digital, physical, or tv. The note might have optional
     * info such as the film festival name for a premiere release or Blu-ray specs
     * for a physical release. We pull this info from TMDB.
     *
     * @param id      Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param country 2 character country code
     * @return movie releases
     */
    public Observable<List<Alias>> getMovieReleases(String id, String country) {
        return api.getMovieReleases(id, country);
    }

    /**
     * All translations for a movie, including language and
     * translated values for title, tagline and overview.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param language 2 character language code
     * @return movie translations
     */
    public Observable<List<Translation>> getMovieTranslations(String id, String language) {
        return api.getMovieTranslations(id, language);
    }

    /**
     * Rating (between 0 and 10) and distribution for a movie.
     *
     * @param id Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @return Movie rating
     */
    public Observable<Rating> getMovieRatings(String id) {
        return api.getMovieRatings(id);
    }

    /**
     * Related and similar movies.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.MovieFilterBuilder}
     * @return Related movies
     */
    public Observable<List<Movie>> getRelatedMovies(String id,
                                                    boolean extended,
                                                    Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getRelatedMovies(id, filters);
    }
    //</editor-fold>

    //<editor-fold desc="Recommendations">

    /**
     * Personalized movie recommendations for a user.
     *
     * @param limit    result limit (max 100)
     * @param extended extended info
     * @return movie recommendations
     */
    public Observable<List<Movie>> getMovieRecommendations(int limit, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        params = addLimitInfo(limit, params);
        return api.getMovieRecommendations(params);
    }

    /**
     * Hide a movie from getting recommended anymore.
     *
     * @param id Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @return success
     */
    public Observable<Boolean> hideMovieRecommendation(String id) {
        return api.hideMovieRecommendation(id)
                .map(response -> true)
                .onErrorReturn(response -> false);
    }

    /**
     * Personalized show recommendations for a user.
     *
     * @param limit    result limit
     * @param extended extended info
     * @return show recommendations
     */
    public Observable<List<Show>> getShowRecommendations(int limit, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        params = addLimitInfo(limit, params);
        return api.getShowRecommendations(params);
    }

    /**
     * Hide a show from getting recommended anymore.
     *
     * @param id Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @return success
     */
    public Observable<Boolean> hideShowRecommendation(String id) {
        return api.hideShowRecommendation(id)
                .map(response -> true)
                .onErrorReturn(response -> false);
    }
    //</editor-fold>

    //<editor-fold desc="Search">

    /**
     * Search all text fields that a media object contains (i.e. title, overview, etc).
     * Results are ordered by the most relevant score. Specify the type of results by
     * sending a single value or a comma delimited string for multiple types.By default,
     * all text fields are used to search for the query. You can optionally specify the
     * fields parameter with a single value or comma delimited string for multiple fields.
     * Each type has specific fields that can be specified. This can be useful if you want
     * to support more strict searches (i.e. title only).
     *
     * @param query    query
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.MovieFilterBuilder}
     * @return search results
     */
    public Observable<List<MovieQueryResult>> searchMovies(String query,
                                                           boolean extended,
                                                           Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addQueryInfo(query, filters);
        return api.searchMovies(filters);
    }

    /**
     * Search all text fields that a media object contains (i.e. title, overview, etc).
     * Results are ordered by the most relevant score. Specify the type of results by
     * sending a single value or a comma delimited string for multiple types.By default,
     * all text fields are used to search for the query. You can optionally specify the
     * fields parameter with a single value or comma delimited string for multiple fields.
     * Each type has specific fields that can be specified. This can be useful if you want
     * to support more strict searches (i.e. title only).
     *
     * @param query    query
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.ShowFilterBuilder}
     * @return search results
     */
    public Observable<List<ShowQueryResult>> searchShows(String query,
                                                         boolean extended,
                                                         Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addQueryInfo(query, filters);
        return api.searchShows(filters);
    }

    /**
     * Search all text fields that a media object contains (i.e. title, overview, etc).
     * Results are ordered by the most relevant score. Specify the type of results by
     * sending a single value or a comma delimited string for multiple types.By default,
     * all text fields are used to search for the query. You can optionally specify the
     * fields parameter with a single value or comma delimited string for multiple fields.
     * Each type has specific fields that can be specified. This can be useful if you want
     * to support more strict searches (i.e. title only).
     *
     * @param query    query
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.FilterBuilder}
     * @return search results
     */
    public Observable<List<EpisodeQueryResult>> searchEpisodes(String query,
                                                               boolean extended,
                                                               Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addQueryInfo(query, filters);
        return api.searchEpisodes(filters);
    }
    //</editor-fold>

    //<editor-fold desc="Shows">

    /**
     * All shows being watched right now. Shows with the most users are returned first.
     *
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.ShowFilterBuilder}
     * @return trending shows
     */
    public Observable<List<TrendingShow>> getTrendingShows(int page,
                                                           int limit,
                                                           boolean extended,
                                                           Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getTrendingShows(filters);
    }

    /**
     * The most popular shows. Popularity is calculated using the rating
     * percentage and the number of ratings.
     *
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.ShowFilterBuilder}
     * @return popular shows
     */
    public Observable<List<Show>> getPopularShows(int page,
                                                  int limit,
                                                  boolean extended,
                                                  Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getPopularShows(filters);
    }

    /**
     * The most played (a single user can watch multiple episodes multiple times)
     * shows in the specified time period, defaulting to weekly.
     * All stats are relative to the specific time period.
     *
     * @param period   time period
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.ShowFilterBuilder}
     * @return played shows
     */
    public Observable<List<ShowCount>> getPlayedShows(String period,
                                                      int page,
                                                      int limit,
                                                      boolean extended,
                                                      Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        filters = addPeriodInfo(period, filters);
        return api.getPlayedShows(filters);
    }

    /**
     * The most watched (unique users) shows in the specified time period,
     * defaulting to weekly. All stats are relative to the specific time period.
     *
     * @param period   time period
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.ShowFilterBuilder}
     * @return watched shows
     */
    public Observable<List<ShowCount>> getWatchedShows(String period,
                                                       int page,
                                                       int limit,
                                                       boolean extended,
                                                       Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        filters = addPeriodInfo(period, filters);
        return api.getWatchedShows(filters);
    }

    /**
     * The most collected (unique users) shows in the specified time period,
     * defaulting to weekly. All stats are relative to the specific time period.
     *
     * @param period   time period
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.ShowFilterBuilder}
     * @return most collected shows
     */
    public Observable<List<ShowCount>> getCollectedShows(String period,
                                                         int page,
                                                         int limit,
                                                         boolean extended,
                                                         Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        filters = addPeriodInfo(period, filters);
        return api.getCollectedShows(filters);
    }

    /**
     * The most anticipated shows based on the number of lists a show appears on.
     *
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @param filters  filters {@link com.staticvillage.traktandroidsdk.util.ShowFilterBuilder}
     * @return most anticipated shows
     */
    public Observable<List<AnticipatedShow>> getAnticipatedShows(int page,
                                                                 int limit,
                                                                 boolean extended,
                                                                 Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getAnticipatedShows(filters);
    }

    /**
     * All shows updated since the specified UTC date. We recommended storing
     * the date you can be efficient using this method moving forward.
     *
     * @param page     page
     * @param limit    result limit
     * @param extended extended info
     * @return show updates
     */
    public Observable<List<ShowUpdate>> getShowUpdates(int page,
                                                       int limit,
                                                       boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        params = addPaginationInfo(page, limit, params);
        return api.getShowUpdates(params);
    }

    /**
     * A single shows's details. If you request extended info, the airs object
     * is relative to the show's country. You can use the day, time, and timezone
     * to construct your own date then convert it to whatever timezone your user is in.
     * <p>
     * Note: When getting full extended info, the status field can have a value of
     * returning series (airing right now), in production (airing soon),
     * planned (in development), canceled, or ended.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param extended extended info
     * @return show details
     */
    public Observable<Show> getShowDetails(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getShowDetails(id, params);
    }

    /**
     * All title aliases for a show. Includes country where name is different.
     *
     * @param id Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @return show aliases
     */
    public Observable<List<Alias>> getShowAliases(String id) {
        return api.getShowAliases(id);
    }

    /**
     * All translations for a show, including language and translated
     * values for title and overview.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param language 2 character language code
     * @return show translations
     */
    public Observable<List<Translation>> getShowTranslations(String id, String language) {
        return api.getShowTranslations(id, language);
    }

    /**
     * Rating (between 0 and 10) and distribution for a show.
     *
     * @param id Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @return show rating
     */
    public Observable<Rating> getShowRatings(String id) {
        return api.getShowRatings(id);
    }

    /**
     * Related and similar shows.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param extended extended info
     * @return related shows
     */
    public Observable<List<Show>> getRelatedShows(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getRelatedShows(id, params);
    }

    /**
     * The next scheduled to air episode.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param extended extended info
     * @return next scheduled episode
     */
    public Observable<Episode> getNextEpisode(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getNextEpisode(id, params);
    }

    /**
     * The most recently aired episode.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param extended extended info
     * @return most recently aired episode
     */
    public Observable<Episode> getLastEpisode(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getLastEpisode(id, params);
    }
    //</editor-fold>

    //<editor-fold desc="Season">

    /**
     * All seasons for a show including the number of episodes in each season.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param extended extended info
     * @return all seasons
     */
    public Observable<List<StandardSeason>> getAllSeasons(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getAllSeasons(id, params);
    }

    /**
     * All episodes for a specific season of a show.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param season   season
     * @param extended extended info
     * @return All episodes for a specific season
     */
    public Observable<List<Season>> getSeasonSummary(String id, int season, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getSeasonSummary(id, season, params);
    }

    /**
     * Rating (between 0 and 10) and distribution for a season.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param season   season
     * @param extended extended info
     * @return season rating
     */
    public Observable<Rating> getSeasonRatings(String id, int season, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getSeasonRatings(id, season, params);
    }
    //</editor-fold>

    //<editor-fold desc="Episodes">

    /**
     * Returns a single episode's details. All date and times are in UTC and were
     * calculated using the episode's air_date and show's country and air_time.
     * <p>
     * Note: If the first_aired is unknown, it will be set to null.
     *
     * @param id       Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param season   season number
     * @param episode  episode number
     * @param extended extended info
     * @return episode detail
     */
    public Observable<Episode> getEpisode(String id, int season, int episode, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getEpisode(id, season, episode, params);
    }

    /**
     * Rating (between 0 and 10) and distribution for an episode.
     *
     * @param id      Trakt.tv ID, Trakt.tv slug, or IMDB ID
     * @param season  season number
     * @param episode episode number
     * @return episode rating
     */
    public Observable<Rating> getEpisodeRating(String id, int season, int episode) {
        return api.getEpisodeRating(id, season, episode);
    }
    //</editor-fold>

    //<editor-fold desc="Sync">

    /**
     * This method is a useful first step in the syncing process. We recommended
     * caching these dates locally, then you can compare to know exactly what
     * data has changed recently. This can greatly optimize your syncs so you
     * don't pull down a ton of data only to see nothing has actually changed.
     *
     * @return activity details
     */
    public Observable<LastActivity> getLastActivities() {
        return api.getLastActivities();
    }

    /**
     * Get all collected items in a user's collection. A collected item indicates
     * availability to watch digitally or on physical media.
     *
     * @param extended extended info
     * @return collected shows
     */
    public Observable<List<CollectionShow>> getShowCollection(boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getShowCollection(params);
    }

    /**
     * Get all collected items in a user's collection. A collected item indicates
     * availability to watch digitally or on physical media.
     *
     * @param extended extended info
     * @return collected movies
     */
    public Observable<List<CollectionShow>> getMovieCollection(boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getMovieCollection(params);
    }

    /**
     * Add items to a user's collection. If seasons are specified, all
     * episodes in those seasons will be collected.Send a collected_at UTC
     * datetime to mark items as collected in the past. You can also send
     * additional metadata about the media itself to have a very accurate collection.
     * Showcase what is available to watch from your epic HD DVD collection down
     * to your downloaded iTunes movies.Note: You can resend items already in your
     * collection and they will be updated with any new values. This includes the collected_at
     * and any other metadata.
     *
     * @param movies movies
     * @return status
     */
    public Observable<JsonElement> addMoviesToCollection(List<Movie> movies) {
        return api.addToCollection(RetrofitUtil.toRequestBody(gson.toJson(movies)));
    }

    /**
     * Add items to a user's collection. If seasons are specified, all
     * episodes in those seasons will be collected.Send a collected_at UTC
     * datetime to mark items as collected in the past. You can also send
     * additional metadata about the media itself to have a very accurate collection.
     * Showcase what is available to watch from your epic HD DVD collection down
     * to your downloaded iTunes movies.Note: You can resend items already in your
     * collection and they will be updated with any new values. This includes the collected_at
     * and any other metadata.
     *
     * @param shows shows
     * @return status
     */
    public Observable<JsonElement> addShowsToCollection(List<Show> shows) {
        return api.addToCollection(RetrofitUtil.toRequestBody(gson.toJson(shows)));
    }

    /**
     * Add items to a user's collection. If seasons are specified, all
     * episodes in those seasons will be collected.Send a collected_at UTC
     * datetime to mark items as collected in the past. You can also send
     * additional metadata about the media itself to have a very accurate collection.
     * Showcase what is available to watch from your epic HD DVD collection down
     * to your downloaded iTunes movies.Note: You can resend items already in your
     * collection and they will be updated with any new values. This includes the collected_at
     * and any other metadata.
     *
     * @param episodes episodes
     * @return status
     */
    public Observable<JsonElement> addEpisodesToCollection(List<Episode> episodes) {
        return api.addToCollection(RetrofitUtil.toRequestBody(gson.toJson(episodes)));
    }

    /**
     * Remove one or more items from a user's collection.
     *
     * @param movies movies
     * @return status
     */
    public Observable<JsonElement> removeMoviesToCollection(List<Movie> movies) {
        return api.removeFromCollection(RetrofitUtil.toRequestBody(gson.toJson(movies)));
    }

    /**
     * Remove one or more items from a user's collection.
     *
     * @param shows shows
     * @return status
     */
    public Observable<JsonElement> removeShowsToCollection(List<Show> shows) {
        return api.removeFromCollection(RetrofitUtil.toRequestBody(gson.toJson(shows)));
    }

    /**
     * Remove one or more items from a user's collection.
     *
     * @param episodes episodes
     * @return
     */
    public Observable<JsonElement> removeEpisodesToCollection(List<Episode> episodes) {
        return api.removeFromCollection(RetrofitUtil.toRequestBody(gson.toJson(episodes)));
    }
    //</editor-fold>

    /**
     * Add query to the parameter list
     *
     * @param query query
     * @param map   parameter map
     * @return appended parameter map
     */
    private Map<String, String> addQueryInfo(String query, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(QUERY, query);
        return map;
    }

    /**
     * Add time period to the parameter list
     *
     * @param period time period (weekly, monthly, yearly, all)
     * @param map    parameter map
     * @return appended parameter map
     */
    private Map<String, String> addPeriodInfo(String period, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(PERIOD, period);
        return map;
    }

    /**
     * Add result limit to the parameter list
     *
     * @param limit result limit
     * @param map   parameter map
     * @return appended parameter map
     */
    private Map<String, String> addLimitInfo(int limit, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(LIMIT, String.valueOf(limit));
        return map;
    }

    /**
     * Add pagination info to the parameter list
     *
     * @param page  page
     * @param limit result limit
     * @param map   parameter map
     * @return appended parameter map
     */
    private Map<String, String> addPaginationInfo(int page, int limit, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(PAGE, String.valueOf(page));
        map.put(LIMIT, String.valueOf(limit));
        return map;
    }

    /**
     * Add extended info to the parameter list
     *
     * @param extended extend meta or full data
     * @param map      parameter map
     * @return appended parameter map
     */
    private Map<String, String> addExtendedInfo(boolean extended, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(EXTENDED, extended ? "full" : "metadata");
        return map;
    }
}
