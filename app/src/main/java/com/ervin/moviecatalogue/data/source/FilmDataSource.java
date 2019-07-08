package com.ervin.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;

import com.ervin.moviecatalogue.data.source.local.model.CastingModel;
import com.ervin.moviecatalogue.data.source.local.model.FilmModel;

import java.util.List;

public interface FilmDataSource {
    LiveData<List<FilmModel>> getAllMovies();
    LiveData<List<FilmModel>> getAllSeries();
    LiveData<FilmModel> getDetailFilm(String filmID,int filmType);
    LiveData<List<CastingModel>> getCastersFilm(String filmID, int filmType);
}
