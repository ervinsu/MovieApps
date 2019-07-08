package com.ervin.moviecatalogue.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ervin.moviecatalogue.data.source.FilmRepository;
import com.ervin.moviecatalogue.data.source.local.model.FilmModel;
import com.ervin.moviecatalogue.utils.DataDummy;
import com.ervin.moviecatalogue.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailFilmViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailFilmViewModel detailFilmViewModel;
    private FilmRepository mockedFilmRepository = mock(FilmRepository.class);

    @Before
    public void setUp(){
        detailFilmViewModel = new DetailFilmViewModel(mockedFilmRepository);
        detailFilmViewModel.setFilmID("28794");
        detailFilmViewModel.setFilmType(0);
    }

    @Test
    public void getDetailFilm(){
//        when(detailFilmViewModel.getFilm()).thenReturn(mockedFilmRepository.getDetailFilm("287947",0));
        MutableLiveData<FilmModel> dummyDetailFilm = new MutableLiveData<>();
        dummyDetailFilm.setValue(FakeDataDummy.generateDummyDetailFilm());
        when(mockedFilmRepository.getDetailFilm("28794",0)).thenReturn(dummyDetailFilm);
        Observer<FilmModel> observer = Mockito.mock(Observer.class);
        detailFilmViewModel.getFilm().observeForever(observer);
        verify(mockedFilmRepository).getDetailFilm("28794",0);
    }


}