package com.ervin.moviecatalogue.ui.detail;

import com.ervin.moviecatalogue.model.FilmModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DetailFilmViewModelTest {
    private DetailFilmViewModel detailFilmViewModel;
    private String expectedFilmName;

    @Before
    public void setUp(){
        detailFilmViewModel = new DetailFilmViewModel();
        detailFilmViewModel.setFilmID("a1");
        expectedFilmName = "Alita: Battle Angel (2019)";
    }

    @Test
    public void getDetailFilm(){
        FilmModel filmModel = detailFilmViewModel.getFilm();
        assertNotNull(filmModel);
        assertEquals(expectedFilmName, filmModel.getFilmName());
    }


}