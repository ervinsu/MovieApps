package com.ervin.moviecatalogue.ui.main;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.ervin.moviecatalogue.model.FilmModel;
import com.ervin.moviecatalogue.utils.DataDummy;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class PageViewModelTest{
    private PageViewModel pageViewModel;
    private int sizeMovie,sizeSeries;
    @Before
    public void setUp(){
        pageViewModel = new PageViewModel();
        //setindex 0 untuk film movie, 1 untuk film series
        sizeMovie = 7;
        sizeSeries = 3;
    }

    @Test
    public void getSizeMovies(){
        //todo kenapa kalo begini dia hasilnya null ya? mungkin salah ya implementasi live data di modelviewnya?
//        pageViewModel.setIndex(0);
//        assertEquals(sizeMovie, pageViewModel.getDataFilms().getValue());
        pageViewModel.setPageIndex(0);
        assertEquals(sizeMovie, pageViewModel.getFilms().size());
    }

    @Test
    public void getSizeSeries(){
        pageViewModel.setPageIndex(1);
        assertEquals(sizeSeries, pageViewModel.getFilms().size());
    }


}