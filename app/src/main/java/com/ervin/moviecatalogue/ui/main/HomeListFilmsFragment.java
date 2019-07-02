package com.ervin.moviecatalogue.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ervin.moviecatalogue.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeListFilmsFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = HomeListFilmsFragment.class.getSimpleName();

    private PageViewModel pageViewModel;

    static HomeListFilmsFragment newInstance(int index) {
        HomeListFilmsFragment fragment = new HomeListFilmsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
//        pageViewModel.setIndex(index);
        pageViewModel.setPageIndex(index);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView rvFilm = root.findViewById(R.id.rvListMovie);
        rvFilm.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        ListFilmAdapter adapter = new ListFilmAdapter(getActivity());
        rvFilm.setAdapter(adapter);
//        pageViewModel.getDataFilms().observe(this,
//                filmModels -> {
//                    Log.d(TAG,filmModels.size()+"");
//                    adapter.setFilms(filmModels);
//                });
        adapter.setFilms(pageViewModel.getFilms());

        return root;
    }
}