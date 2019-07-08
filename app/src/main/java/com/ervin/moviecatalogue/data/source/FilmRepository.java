package com.ervin.moviecatalogue.data.source;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ervin.moviecatalogue.data.source.local.LocalRepository;
import com.ervin.moviecatalogue.data.source.local.model.CastingModel;
import com.ervin.moviecatalogue.data.source.local.model.FilmModel;
import com.ervin.moviecatalogue.data.source.remote.RemoteRepository;
import com.ervin.moviecatalogue.data.source.remote.response.CastingResponse;
import com.ervin.moviecatalogue.data.source.remote.response.FilmResponse;

import java.util.ArrayList;
import java.util.List;

public class FilmRepository implements FilmDataSource{

    private volatile static FilmRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;

    private FilmRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository){
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public static FilmRepository getInstance(LocalRepository localRepository, RemoteRepository remoteRepository){
        if(INSTANCE == null){
            synchronized (RemoteRepository.class){
                if(INSTANCE == null)
                    INSTANCE = new FilmRepository(localRepository,remoteRepository);
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<FilmModel>> getAllMovies() {
        Log.d("FilmMovie","getAllMovies");
        MutableLiveData<List<FilmModel>> moviesModelResult = new MutableLiveData<>();
        remoteRepository.getListFilmMovies(new RemoteRepository.LoadFilmsCallback() {
            @Override
            public void onAllFilmsReceived(List<FilmResponse> filmResponses) {
                List<FilmModel> moviesModelList= new ArrayList<>();
                for(int i = 0; i< filmResponses.size(); i++){
                    FilmResponse filmResponse = filmResponses.get(i);
                    FilmModel filmModel = new FilmModel(
                            filmResponse.getFilmID(),
                            filmResponse.getFilmName(),
                            filmResponse.getFimGenre(),
                            filmResponse.getFilmImage(),
                            filmResponse.getFilmRating());
                    moviesModelList.add(filmModel);
                }
                moviesModelResult.postValue(moviesModelList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return moviesModelResult;
    }

    @Override
    public LiveData<List<FilmModel>> getAllSeries() {
        MutableLiveData<List<FilmModel>> seriesModelResult = new MutableLiveData<>();
        remoteRepository.getListFilmSeries(new RemoteRepository.LoadFilmsCallback() {
            @Override
            public void onAllFilmsReceived(List<FilmResponse> filmResponses) {
                List<FilmModel> seriesModelList = new ArrayList<>();
                for(int i = 0; i< filmResponses.size(); i++){
                    FilmResponse serieResponse = filmResponses.get(i);
                    FilmModel filmModel = new FilmModel(
                            serieResponse.getFilmID(),
                            serieResponse.getFilmName(),
                            serieResponse.getFimGenre(),
                            serieResponse.getFilmImage(),
                            serieResponse.getFilmRating());
                    seriesModelList.add(filmModel);
                }
                seriesModelResult.postValue(seriesModelList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return seriesModelResult;
    }

    @Override
    public LiveData<FilmModel> getDetailFilm(String filmID, int filmType) {
        MutableLiveData<FilmModel> DetailFilmResult = new MutableLiveData<>();
        remoteRepository.getDetailFilm(new RemoteRepository.LoadDetailFilmCallback() {
            @Override
            public void onDetailReceived(FilmResponse filmResponse) {
                FilmModel filmModel = new FilmModel(
                        filmResponse.getFilmID(),
                        filmResponse.getFimGenre(),
                        filmResponse.getFilmName(),
                        filmResponse.getFilmImage(),
                        filmResponse.getFilmBackdrop(),
                        filmResponse.getFilmRating(),
                        filmResponse.getFilmSinopsis());
                DetailFilmResult.postValue(filmModel);
            }

            @Override
            public void onDataNotAvailable() {

            }
        },filmType, filmID);

        return DetailFilmResult;
    }

    @Override
    public LiveData<List<CastingModel>> getCastersFilm(String filmID, int filmType) {
        MutableLiveData<List<CastingModel>> castingsResult = new MutableLiveData<>();
        remoteRepository.getCasters(new RemoteRepository.LoadCasterFilmCallback() {
            @Override
            public void onCastersResponse(List<CastingResponse> castersResponse) {
                List<CastingModel> castingModelResponse = new ArrayList<>();
                for(int i=0;i<castersResponse.size();i++){
                    CastingResponse casterResponse = castersResponse.get(i);
                    castingModelResponse.add(new CastingModel(
                            casterResponse.getCastingID(),
                            casterResponse.getCastingName(),
                            casterResponse.getCastingAs(),
                            casterResponse.getCastingPhoto()
                            ));
                }
                castingsResult.postValue(castingModelResponse);
            }

            @Override
            public void onDataNotAvailable() {

            }
        }, filmType,filmID);
        return castingsResult;
    }
}
