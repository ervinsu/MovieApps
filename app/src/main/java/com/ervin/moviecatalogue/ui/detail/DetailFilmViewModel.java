package com.ervin.moviecatalogue.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ervin.moviecatalogue.data.source.FilmRepository;
import com.ervin.moviecatalogue.data.source.local.model.CastingModel;
import com.ervin.moviecatalogue.data.source.local.model.FilmModel;

import java.util.List;

public class DetailFilmViewModel extends ViewModel {
    private FilmRepository filmRepository;
    private int filmType;
    private String filmID;

    public DetailFilmViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }

    LiveData<FilmModel> getFilm(){
        return filmRepository.getDetailFilm(this.filmID, this.filmType);
    }

    LiveData<List<CastingModel>> getCasters(){
        return filmRepository.getCastersFilm(this.filmID, this.filmType);
    }

    public void setFilmType(int filmType) {
        this.filmType = filmType;
    }
}
