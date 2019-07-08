package com.ervin.moviecatalogue.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ervin.moviecatalogue.data.source.FilmRepository;
import com.ervin.moviecatalogue.di.Injection;
import com.ervin.moviecatalogue.ui.detail.DetailFilmViewModel;
import com.ervin.moviecatalogue.ui.main.PageViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final FilmRepository mFilmRepository;

    private ViewModelFactory(FilmRepository FilmRepository) {
        mFilmRepository = FilmRepository;
    }

    public static ViewModelFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository());
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PageViewModel.class)) {
            //noinspection unchecked
            return (T) new PageViewModel(mFilmRepository);
        } else if (modelClass.isAssignableFrom(DetailFilmViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailFilmViewModel(mFilmRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
