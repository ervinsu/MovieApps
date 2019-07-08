package com.ervin.moviecatalogue.di;

import android.app.Application;

import com.ervin.moviecatalogue.data.source.FilmRepository;
import com.ervin.moviecatalogue.data.source.local.LocalRepository;
import com.ervin.moviecatalogue.data.source.remote.RemoteRepository;
import com.ervin.moviecatalogue.data.source.remote.RepositoryHelper;

public class Injection {
    public static FilmRepository provideRepository(){
        LocalRepository localRepository = new LocalRepository();
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new RepositoryHelper());
        return FilmRepository.getInstance(localRepository,remoteRepository);
    }
}
