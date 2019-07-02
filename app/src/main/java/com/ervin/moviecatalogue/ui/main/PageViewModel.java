package com.ervin.moviecatalogue.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.ervin.moviecatalogue.model.FilmModel;
import com.ervin.moviecatalogue.utils.DataDummy;

import java.util.List;


public class PageViewModel extends ViewModel {

//    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
//
//    private LiveData<List<FilmModel>> listLiveDataFilm = Transformations.map(mIndex, input -> {
//        if(input==0) return DataDummy.generateDummyFilmMovieList();
//        else return DataDummy.generateDummyFilmSeriesList();
//    });
//
//    void setIndex(int index) {
//        mIndex.postValue(index);
//    }
//
//    LiveData<List<FilmModel>> getDataFilms(){
//        return  listLiveDataFilm;
//    }

    private int pageIndex;

    void setPageIndex(int index){
        pageIndex = index;
    }
    List<FilmModel> getFilms(){
        if(pageIndex==0) return DataDummy.generateDummyFilmMovieList();
        else return DataDummy.generateDummyFilmSeriesList();
    }

}