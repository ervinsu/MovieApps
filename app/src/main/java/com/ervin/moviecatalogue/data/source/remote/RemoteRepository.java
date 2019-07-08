package com.ervin.moviecatalogue.data.source.remote;

import com.ervin.moviecatalogue.data.source.remote.response.CastingResponse;
import com.ervin.moviecatalogue.data.source.remote.response.FilmResponse;
import com.ervin.moviecatalogue.utils.EspressoIdlingResource;

import java.util.List;

public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private RepositoryHelper repositoryHelper;

    private RemoteRepository(RepositoryHelper repositoryHelper) {
        this.repositoryHelper = repositoryHelper;
    }

    public static RemoteRepository getInstance(RepositoryHelper repositoryHelper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(repositoryHelper);
        }
        return INSTANCE;
    }

    public void getCasters(LoadCasterFilmCallback callback, int filmType, String filmID) {
        EspressoIdlingResource.increment();
        repositoryHelper.loadCasters(callback,filmID,filmType);
    }

    public void getListFilmMovies(LoadFilmsCallback callback) {
        EspressoIdlingResource.increment();
//        Handler handler = new Handler();//repositoryHelper.loadFilmMovies()
//        handler.postDelayed(()-> callback.onAllFilmsReceived(DataDummy.generateDummy()),SERVICE_LATENCY_IN_MILLIS);
        repositoryHelper.loadFilmMovies(callback);
    }

    public void getListFilmSeries(LoadFilmsCallback callback) {
        //Handler handler = new Handler();
        //handler.postDelayed(()-> callback.onAllFilmsReceived(repositoryHelper.loadFilmSeries()),SERVICE_LATENCY_IN_MILLIS);
        EspressoIdlingResource.increment();
        repositoryHelper.loadFilmSeries(callback);
    }

    public void getDetailFilm(LoadDetailFilmCallback callback, int filmType, String filmID){
        EspressoIdlingResource.increment();
        repositoryHelper.loadDetailFilm(callback, filmType, filmID);
    }

    public interface LoadFilmsCallback {
        void onAllFilmsReceived(List<FilmResponse> filmResponses);
        void onDataNotAvailable();
    }


    public interface LoadDetailFilmCallback {
        void onDetailReceived(FilmResponse filmResponse);
        void onDataNotAvailable();
    }

    public interface LoadCasterFilmCallback {
        void onCastersResponse(List<CastingResponse> castersResponse);
        void onDataNotAvailable();
    }
}
