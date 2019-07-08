package com.ervin.moviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.ervin.moviecatalogue.data.source.local.LocalRepository;
import com.ervin.moviecatalogue.data.source.local.model.CastingModel;
import com.ervin.moviecatalogue.data.source.local.model.FilmModel;
import com.ervin.moviecatalogue.data.source.remote.RemoteRepository;
import com.ervin.moviecatalogue.data.source.remote.response.CastingResponse;
import com.ervin.moviecatalogue.data.source.remote.response.FilmResponse;
import com.ervin.moviecatalogue.utils.FakeDataDummy;
import com.ervin.moviecatalogue.utils.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FilmRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LocalRepository local = Mockito.mock(LocalRepository.class);
    private RemoteRepository remote = Mockito.mock(RemoteRepository.class);

    private FakeFilmRepository mockedFilmRepository = new FakeFilmRepository(local, remote);

    private ArrayList<FilmResponse> filmResponses = FakeDataDummy.generateDummyFilmsResponse();
    private String filmID = filmResponses.get(0).getFilmID();
    private FilmResponse detailFilm = FakeDataDummy.getFilmDetailFilmResponse(filmID);
    private ArrayList<CastingResponse> casters = FakeDataDummy.getCasterResponse(filmID);
    private int filmType = 0;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void getAllMovies(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadFilmsCallback) invocation.getArguments()[0]).onAllFilmsReceived(filmResponses);
            return null;
        }).when(remote).getListFilmMovies(any(RemoteRepository.LoadFilmsCallback.class));

        List<FilmModel> resultMovies = LiveDataTestUtil.getValue(mockedFilmRepository.getAllMovies());

        verify(remote, times(1))
                .getListFilmMovies(any(RemoteRepository.LoadFilmsCallback.class));

        assertEquals(filmResponses.size(), resultMovies.size());
    }

    @Test
    public void getAllSeries(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadFilmsCallback) invocation.getArguments()[0]).onAllFilmsReceived(filmResponses);
            return null;
        }).when(remote).getListFilmSeries(any(RemoteRepository.LoadFilmsCallback.class));

        List<FilmModel> resultSeries = LiveDataTestUtil.getValue(mockedFilmRepository.getAllSeries());

        verify(remote, times(1))
                .getListFilmSeries(any(RemoteRepository.LoadFilmsCallback.class));
        assertEquals(filmResponses.size(), resultSeries.size());
    }

    @Test
    public void getDetailFilm(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadDetailFilmCallback)invocation.getArguments()[0]).onDetailReceived(detailFilm);
            return null;
        }).when(remote).getDetailFilm(any(RemoteRepository.LoadDetailFilmCallback.class),eq(filmType), eq(filmID));

        FilmModel resultDetailFilm = LiveDataTestUtil.getValue(mockedFilmRepository.getDetailFilm(filmID,filmType));

        verify(remote, times(1)).getDetailFilm(any(RemoteRepository.LoadDetailFilmCallback.class),eq(filmType),eq(filmID));

        assertEquals(detailFilm.getFilmName(), resultDetailFilm.getFilmName());
    }

    @Test
    public void getCastersFilm(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadCasterFilmCallback) invocation.getArguments()[0]).onCastersResponse(casters);
            return null;
        }).when(remote).getCasters(any(RemoteRepository.LoadCasterFilmCallback.class),eq(filmType),eq(filmID));

        List<CastingModel> resultCasters = LiveDataTestUtil.getValue(mockedFilmRepository.getCastersFilm(filmID,filmType));

        verify(remote, times(1)).getCasters(any(RemoteRepository.LoadCasterFilmCallback.class),eq(filmType),eq(filmID));

        assertEquals(casters.size(), resultCasters.size());
    }
}