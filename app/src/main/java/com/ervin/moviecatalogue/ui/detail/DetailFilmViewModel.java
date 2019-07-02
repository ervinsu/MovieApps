package com.ervin.moviecatalogue.ui.detail;

import androidx.lifecycle.ViewModel;

import com.ervin.moviecatalogue.model.FilmModel;
import com.ervin.moviecatalogue.utils.DataDummy;

public class DetailFilmViewModel extends ViewModel {

    private String filmID;

    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }

    FilmModel getFilm(){
        return DataDummy.getFilm(filmID);
    }
}
