package com.staticvillage.traktandroidsdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
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

import org.json.JSONObject;

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
        //if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        //}
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

    public void setAuthToken(String authToken) {
        interceptor.setAuthToken(authToken);
    }

    public String getAuthToken() {
        return interceptor.getAuthToken();
    }

    //<editor-fold desc="Authorization">

    /**
     * Authorize app
     *
     * @param context
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
     * @param startDate
     * @param days
     * @return
     */
    public Observable<List<CalendarShow>> getMyShows(Date startDate,
                                                     int days,
                                                     boolean extended,
                                                     Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getMyShows(dateFormat.format(startDate), days, filters);
    }

    /**
     * @param startDate
     * @param days
     * @return
     */
    public Observable<List<CalendarShow>> getMyNewShows(Date startDate,
                                                        int days,
                                                        boolean extended,
                                                        Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getMyNewShows(dateFormat.format(startDate), days, filters);
    }

    /**
     * @param startDate
     * @param days
     * @return
     */
    public Observable<List<CalendarShow>> getMySeasonPremieres(Date startDate,
                                                               int days,
                                                               boolean extended,
                                                               Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getMySeasonPremieres(dateFormat.format(startDate), days, filters);
    }

    /**
     * @param startDate
     * @param days
     * @return
     */
    public Observable<List<CalendarMovie>> getMyMovies(Date startDate,
                                                       int days,
                                                       boolean extended,
                                                       Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getMyMovies(dateFormat.format(startDate), days, filters);
    }

    /**
     * @param startDate
     * @param days
     * @return
     */
    public Observable<List<CalendarShow>> getShows(Date startDate,
                                                   int days,
                                                   boolean extended,
                                                   Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getShows(dateFormat.format(startDate), days, filters);
    }

    /**
     * @param startDate
     * @param days
     * @return
     */
    public Observable<List<CalendarShow>> getNewShows(Date startDate,
                                                      int days,
                                                      boolean extended,
                                                      Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getNewShows(dateFormat.format(startDate), days, filters);
    }

    /**
     * @param startDate
     * @param days
     * @return
     */
    public Observable<List<CalendarShow>> getSeasonPremieres(Date startDate,
                                                             int days,
                                                             boolean extended,
                                                             Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getSeasonPremieres(dateFormat.format(startDate), days, filters);
    }

    /**
     * @param startDate
     * @param days
     * @return
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
     * @return
     */
    public Observable<List<Genre>> getShowGenres() {
        return api.getGenres("shows");
    }

    /**
     * @return
     */
    public Observable<List<Genre>> getMovieGenres() {
        return api.getGenres("movies");
    }
    //</editor-fold>

    //<editor-fold desc="Movies">
    public Observable<List<TrendingMovie>> getTrendingMovies(int page,
                                                             int limit,
                                                             boolean extended,
                                                             Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getTrendingMovies(filters);
    }

    public Observable<List<Movie>> getPopularMovies(int page,
                                                    int limit,
                                                    boolean extended,
                                                    Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getPopularMovies(filters);
    }

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

    public Observable<List<AnticipatedMovie>> getAnticipatedMovies(int page,
                                                                   int limit,
                                                                   boolean extended,
                                                                   Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getAnticipatedMovies(filters);
    }

    public Observable<List<MovieUpdate>> getMovieUpdates(int page, int limit, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        params = addPaginationInfo(page, limit, params);
        return api.getMovieUpdates(params);
    }

    public Observable<Movie> getMovieDetails(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getMovieDetails(id, params);
    }

    public Observable<List<Alias>> getMovieAliases(String id) {
        return api.getMovieAliases(id);
    }

    public Observable<List<Alias>> getMovieReleases(String id, String country) {
        return api.getMovieReleases(id, country);
    }


    public Observable<List<Translation>> getMovieTranslations(String id, String language) {
        return api.getMovieTranslations(id, language);
    }


    public Observable<Rating> getMovieRatings(String id) {
        return api.getMovieRatings(id);
    }


    public Observable<List<Movie>> getRelatedMovies(String id,
                                                    boolean extended,
                                                    Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        return api.getRelatedMovies(id, filters);
    }
    //</editor-fold>

    //<editor-fold desc="Recommendations">
    public Observable<List<Movie>> getMovieRecommendations(int limit, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        params = addLimitInfo(limit, params);
        return api.getMovieRecommendations(params);
    }


    public Observable<List<Movie>> hideMovieRecommendation(String id) {
        return api.hideMovieRecommendation(id);
    }


    public Observable<List<Show>> getShowRecommendations(int limit, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        params = addLimitInfo(limit, params);
        return api.getShowRecommendations(params);
    }


    public Observable<List<Show>> hideShowRecommendation(String id) {
        return api.hideShowRecommendation(id);
    }
    //</editor-fold>

    //<editor-fold desc="Search">

    public Observable<List<MovieQueryResult>> searchMovies(String query,
                                                           boolean extended,
                                                           Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addQueryInfo(query, filters);
        return api.searchMovies(filters);
    }


    public Observable<List<ShowQueryResult>> searchShows(String query,
                                                         boolean extended,
                                                         Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addQueryInfo(query, filters);
        return api.searchShows(filters);
    }


    public Observable<List<EpisodeQueryResult>> searchEpisodes(String query,
                                                               boolean extended,
                                                               Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addQueryInfo(query, filters);
        return api.searchEpisodes(filters);
    }
    //</editor-fold>

    //<editor-fold desc="Shows">

    public Observable<List<TrendingShow>> getTrendingShows(int page,
                                                           int limit,
                                                           boolean extended,
                                                           Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getTrendingShows(filters);
    }


    public Observable<List<Show>> getPopularShows(int page,
                                                  int limit,
                                                  boolean extended,
                                                  Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getPopularShows(filters);
    }


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


    public Observable<List<AnticipatedShow>> getAnticipatedShows(int page,
                                                                 int limit,
                                                                 boolean extended,
                                                                 Map<String, String> filters) {
        filters = addExtendedInfo(extended, filters);
        filters = addPaginationInfo(page, limit, filters);
        return api.getAnticipatedShows(filters);
    }


    public Observable<List<ShowUpdate>> getShowUpdates(int page,
                                                       int limit,
                                                       boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        params = addPaginationInfo(page, limit, params);
        return api.getShowUpdates(params);
    }


    public Observable<Show> getShowDetails(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getShowDetails(id, params);
    }


    public Observable<List<Alias>> getShowAliases(String id) {
        return api.getShowAliases(id);
    }


    public Observable<List<Translation>> getShowTranslations(String id, String language) {
        return api.getShowTranslations(id, language);
    }


    public Observable<Rating> getShowRatings(String id) {
        return api.getShowRatings(id);
    }


    public Observable<List<Show>> getRelatedShows(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getRelatedShows(id, params);
    }


    public Observable<Episode> getNextEpisode(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getNextEpisode(id, params);
    }


    public Observable<Episode> getLastEpisode(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getLastEpisode(id, params);
    }
    //</editor-fold>

    //<editor-fold desc="Season">

    public Observable<List<StandardSeason>> getAllSeasons(String id, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getAllSeasons(id, params);
    }


    public Observable<List<Season>> getSeasonSummary(String id, int season, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getSeasonSummary(id, season, params);
    }


    public Observable<Rating> getSeasonRatings(String id, int season, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getSeasonRatings(id, season, params);
    }
    //</editor-fold>

    //<editor-fold desc="Episodes">

    public Observable<Episode> getEpisode(String id, int season, int episode, boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getEpisode(id, season, episode, params);
    }


    public Observable<Rating> getEpisodeRating(String id, int season, int episode) {
        return api.getEpisodeRating(id, season, episode);
    }
    //</editor-fold>

    //<editor-fold desc="Sync">
    public Observable<LastActivity> getLastActivities() {
        return api.getLastActivities();
    }


    public Observable<List<CollectionShow>> getShowCollection(boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getShowCollection(params);
    }


    public Observable<List<CollectionShow>> getMovieCollection(boolean extended) {
        Map<String, String> params = addExtendedInfo(extended, null);
        return api.getMovieCollection(params);
    }


    public Observable<String> addMoviesToCollection(List<Movie> movies) {
        return api.addToCollection(RetrofitUtil.toRequestBody(gson.toJson(movies)));
    }

    public Observable<String> addShowsToCollection(List<Show> shows) {
        return api.addToCollection(RetrofitUtil.toRequestBody(gson.toJson(shows)));
    }

    public Observable<String> addEpisodesToCollection(List<Episode> episodes) {
        return api.addToCollection(RetrofitUtil.toRequestBody(gson.toJson(episodes)));
    }

    public Observable<String> removeMoviesToCollection(List<Movie> movies) {
        return api.removeFromCollection(RetrofitUtil.toRequestBody(gson.toJson(movies)));
    }

    public Observable<String> removeShowsToCollection(List<Show> shows) {
        return api.removeFromCollection(RetrofitUtil.toRequestBody(gson.toJson(shows)));
    }

    public Observable<String> removeEpisodesToCollection(List<Episode> episodes) {
        return api.removeFromCollection(RetrofitUtil.toRequestBody(gson.toJson(episodes)));
    }
    //</editor-fold>

    private Map<String, String> addQueryInfo(String query, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(QUERY, query);
        return map;
    }

    private Map<String, String> addPeriodInfo(String period, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(PERIOD, period);
        return map;
    }

    private Map<String, String> addLimitInfo(int limit, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(LIMIT, String.valueOf(limit));
        return map;
    }

    private Map<String, String> addPaginationInfo(int page, int limit, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(PAGE, String.valueOf(page));
        map.put(LIMIT, String.valueOf(limit));
        return map;
    }

    private Map<String, String> addExtendedInfo(boolean extended, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(EXTENDED, extended ? "full" : "metadata");
        return map;
    }
}
