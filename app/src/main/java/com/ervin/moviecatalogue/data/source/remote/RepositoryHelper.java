package com.ervin.moviecatalogue.data.source.remote;

import android.util.Log;

import com.ervin.moviecatalogue.BuildConfig;
import com.ervin.moviecatalogue.data.source.remote.response.CastingResponse;
import com.ervin.moviecatalogue.data.source.remote.response.FilmResponse;
import com.ervin.moviecatalogue.data.source.remote.response.GenreResponse;
import com.ervin.moviecatalogue.utils.EspressoIdlingResource;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class RepositoryHelper {
    private List<GenreResponse> listGenreMovies;
    private List<GenreResponse> listGenreSeries;

    public RepositoryHelper() {
        loadGenreMovies();
        loadGenreSeries();
    }

    public void loadCasters(RemoteRepository.LoadCasterFilmCallback callback, String filmID, int filmType) {
        List<CastingResponse> castersResult = new ArrayList<>();
        String url;
        if (filmType == 0) url = BuildConfig.BaseUrl + "movie/" + filmID + "/credits?api_key=" + BuildConfig.ApiKey;
        else url = BuildConfig.BaseUrl + "tv/" + filmID + "/credits?api_key=" + BuildConfig.ApiKey;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray casters = responseObject.getJSONArray("cast");
                    for(int i=0;i<casters.length();i++){
                        JSONObject caster = casters.getJSONObject(i);
                        String casterID="";
                        if(caster.has("cast_id")) casterID = caster.getString("cast_id");
                        else if(caster.has("id")) casterID = caster.getString("id");
                        CastingResponse casterResponse = new CastingResponse(
                                casterID,
                                caster.getString("name"),
                                BuildConfig.BaseUrlImage + caster.getString("profile_path"),
                                caster.getString("character")
                        );
                        castersResult.add(casterResponse);
                    }
                    callback.onCastersResponse(castersResult);
                    EspressoIdlingResource.decrement();
                }catch (Exception e){
                    Log.d("Exception", e.toString());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.toString());
            }
        });
    }

    public void loadGenreMovies() {
        listGenreMovies = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = BuildConfig.BaseUrl + "genre/movie/list?api_key=" + BuildConfig.ApiKey + "&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("genres");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject obj = list.getJSONObject(i);
                        listGenreMovies.add(new GenreResponse(obj.getString("id"), obj.getString("name")));
                    }
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public void loadGenreSeries() {
        listGenreSeries = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = BuildConfig.BaseUrl + "genre/tv/list?api_key=" + BuildConfig.ApiKey + "&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("genres");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject obj = list.getJSONObject(i);
                        listGenreSeries.add(new GenreResponse(obj.getString("id"), obj.getString("name")));
                    }
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public void loadFilmMovies(RemoteRepository.LoadFilmsCallback callback) {
        List<FilmResponse> movies = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = BuildConfig.BaseUrl + "movie/popular?api_key=" + BuildConfig.ApiKey + "&language=en-US&page=1";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject obj = list.getJSONObject(i);
                        JSONArray arrayGenres = obj.getJSONArray("genre_ids");
                        List<String> genres = new ArrayList<>();
                        if (listGenreMovies != null) {
                            for (int j = 0; j < arrayGenres.length(); j++) {
                                String genreIDResponse = arrayGenres.getString(j);
                                for (int k = 0; k < listGenreMovies.size(); k++) {
                                    if (listGenreMovies.get(k).getGenreID().equals(genreIDResponse)) {
                                        genres.add(listGenreMovies.get(k).getGenreName());
                                    }
                                }
                            }
                        }
                        FilmResponse movie = new FilmResponse(
                                obj.getString("id"),
                                obj.getString("title"),
                                genres,
                                BuildConfig.BaseUrlImage + obj.getString("poster_path"),
                                obj.getString("vote_average")
                        );
                        movies.add(movie);
                        callback.onAllFilmsReceived(movies);
                    }
                    EspressoIdlingResource.decrement();
                    Log.d("FilmMoviesize", movies.size() + "");
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public void loadFilmSeries(RemoteRepository.LoadFilmsCallback callback) {
        List<FilmResponse> series = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = BuildConfig.BaseUrl + "tv/popular?api_key=" + BuildConfig.ApiKey + "&language=en-US&page=1";
        Log.d("FilmMovieSeries", url);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject obj = list.getJSONObject(i);
                        JSONArray arrayGenres = obj.getJSONArray("genre_ids");
                        List<String> genres = new ArrayList<>();
                        if (listGenreSeries != null) {
                            for (int j = 0; j < arrayGenres.length(); j++) {
                                String genreIDResponse = arrayGenres.getString(j);
                                for (int k = 0; k < listGenreSeries.size(); k++) {
                                    if (listGenreSeries.get(k).getGenreID().equals(genreIDResponse)) {
                                        genres.add(listGenreSeries.get(k).getGenreName());
                                    }
                                }
                            }
                        }
                        FilmResponse serie = new FilmResponse(
                                obj.getString("id"),
                                obj.getString("name"),
                                genres,
                                BuildConfig.BaseUrlImage + obj.getString("poster_path"),
                                obj.getString("vote_average")
                        );
                        series.add(serie);
                        callback.onAllFilmsReceived(series);
                    }
                    EspressoIdlingResource.decrement();
                    Log.d("FilmMovieSeriesList", series.size() + "");

                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public void loadDetailFilm(RemoteRepository.LoadDetailFilmCallback callback, int filmType, String filmID) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url;
        if (filmType == 0)
            url = BuildConfig.BaseUrl + "movie/" + filmID + "?api_key=" + BuildConfig.ApiKey + "&language=en-US&page=1";
        else
            url = BuildConfig.BaseUrl + "tv/" + filmID + "?api_key=" + BuildConfig.ApiKey + "&language=en-US&page=1";
        Log.d("filmDetailFilm", url);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray genresResponse = object.getJSONArray("genres");
                    List<String> genres = new ArrayList<>();
                    for (int i = 0; i < genresResponse.length(); i++) {
                        JSONObject obj = genresResponse.getJSONObject(i);
                        genres.add(obj.getString("name"));
                    }
                    String title = "";
                    if (object.has("name")) title = object.getString("name");
                    else if (object.has("title")) title = object.getString("title");
                    FilmResponse filmResponse = new FilmResponse(
                            object.getString("id"),
                            title,
                            genres,
                            BuildConfig.BaseUrlImage + object.getString("poster_path"),
                            BuildConfig.BaseUrlImage + object.getString("backdrop_path"),
                            object.getString("vote_average"),
                            object.getString("overview")
                    );
                    callback.onDetailReceived(filmResponse);
                    EspressoIdlingResource.decrement();
                } catch (Exception e) {
                    Log.d("Exception", e.toString());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.toString());
            }
        });
    }
}
