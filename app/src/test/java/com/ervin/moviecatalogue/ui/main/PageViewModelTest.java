package com.ervin.moviecatalogue.ui.main;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ervin.moviecatalogue.data.source.FilmRepository;
import com.ervin.moviecatalogue.data.source.local.model.FilmModel;
import com.ervin.moviecatalogue.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PageViewModelTest{
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private PageViewModel pageViewModel;
    private FilmRepository mockedFilmRepository = mock(FilmRepository.class);


    @Before
    public void setUp(){
        pageViewModel = new PageViewModel(mockedFilmRepository);
        //setindex 0 untuk film movie, 1 untuk film series
    }

    @Test
    public void getFilmMovies(){
        MutableLiveData<List<FilmModel>> dummyMovieList = new MutableLiveData<>();
        dummyMovieList.setValue(FakeDataDummy.generateDummyFilmMovieList());
        when(mockedFilmRepository.getAllMovies()).thenReturn(dummyMovieList);

        Observer<List<FilmModel>> observer = Mockito.mock(Observer.class);

        pageViewModel.getFilmsMovie().observeForever(observer);

        verify(mockedFilmRepository).getAllMovies();
    }

    @Test
    public void getFilmSeries(){
        MutableLiveData<List<FilmModel>> dummyMovieList = new MutableLiveData<>();
        dummyMovieList.setValue(FakeDataDummy.generateDummyFilmSeriesList());
        when(mockedFilmRepository.getAllSeries()).thenReturn(dummyMovieList);

        Observer<List<FilmModel>> observer = Mockito.mock(Observer.class);

        pageViewModel.getFilmsSeries().observeForever(observer);

        verify(mockedFilmRepository).getAllSeries();
    }


}