package com.ervin.moviecatalogue.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ervin.moviecatalogue.data.source.FilmRepository;
import com.ervin.moviecatalogue.data.source.local.model.FilmModel;

import java.util.List;


public class PageViewModel extends ViewModel {
    private FilmRepository filmRepository;

    public PageViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    LiveData<List<FilmModel>> getFilmsMovie(){
            return filmRepository.getAllMovies();
    }

    LiveData<List<FilmModel>> getFilmsSeries(){
        return filmRepository.getAllSeries();
    }

}